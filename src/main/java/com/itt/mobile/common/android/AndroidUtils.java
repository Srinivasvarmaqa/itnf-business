package com.itt.mobile.common.android;

import java.util.concurrent.TimeUnit;

import org.slf4j.LoggerFactory;

import com.itt.android.adb.AdbFactory;
import com.itt.factoryhelper.MobileHelperFactory;
import com.itt.mobile.common.android.AndroidResources.TIMEOUTS;
import com.itt.utils.Utils;

import static com.itt.mobile.common.android.AndroidLocator.*;
import org.slf4j.Logger;

public class AndroidUtils {

	private static final Logger LOG = LoggerFactory.getLogger(AndroidUtils.class);

	// delay values
	public static final int DELAY_1_SEC = 1;
	public static final int DELAY_2_SEC = 2;
	public static final int DELAY_3_SEC = 3;
	public static final int DELAY_5_SEC = 5;
	public static final int DELAY_7_SEC = 7;
	public static final int DELAY_10_SEC = 10;
	public static final int DELAY_15_SEC = 15;
	public static final int DELAY_25_SEC = 25;
	public static final int DELAY_60_SEC = 60;

	public enum Operator {
		LESSTHAN, GREATERTHAN, LESSTHAN_OR_EQUALTO, GREATERTHAN_OR_EQUALTO, EQUALTO, NOT_EQUALTO
	};

	/**
	 * Sleeps current executions thread for specified amount of seconds
	 * 
	 * @param seconds to sleep
	 * @param silent  if true - no log message about this delay will be printed
	 */
	public static void delay(int seconds, boolean silent) {
		if (!silent) {
			LOG.info("Delay for " + seconds + " seconds");
		}
		try {
			Thread.sleep(seconds * TimeUnit.SECONDS.toMillis(1));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Sleeps current executions thread for specified amount of seconds
	 * 
	 * @param seconds to sleep
	 */
	public static void delay(int seconds) {
		delay(seconds, false);
	}

	/**
	 * Simply scrolls up for 30% of screen height from center point
	 */
	public static void simpleScrollUp() throws Exception {
		LOG.info("Scroll up");
		MobileHelperFactory.getMobileDriver().scrollUp(byEmptyParams());
	}

	/**
	 * Simply scrolls down for 30% of screen height from center point
	 */
	public static void simpleScrollDown() throws Exception {
		LOG.info("Scroll down");
		MobileHelperFactory.getMobileDriver().scrollDown(byEmptyParams());
	}

	/**
	 * Simply scrolls left for 30% of screen width from center point
	 */
	public static void simpleScrollLeft() throws Exception {
		LOG.info("Scroll left");
		MobileHelperFactory.getMobileDriver().swipeLeft(byEmptyParams());
	}

	/**
	 * Simply scrolls left for 30% of screen width from center point
	 */
	public static void simpleScrollRight() throws Exception {
		LOG.info("Scroll right");
		MobileHelperFactory.getMobileDriver().swipeRight(byEmptyParams());
	}

	/**
	 * Matches the manufacturer name of the device with the user provided
	 * manufacturer name
	 * 
	 * @throws Exception
	 */
	public static boolean isManufacturer(final String manufacturerName) throws Exception {
		LOG.info("Get Manufacturer-Name");
		return AdbFactory.getAdbController().isManufacturer(manufacturerName);
	}

	/**
	 * Returns device width
	 */
	public static int getDeviceScreenWidth() throws Exception {
		String width = AdbFactory.getAdbController().getClickableScreenSize().split("x")[0];
		return Integer.parseInt(width);
	}

	/**
	 * Compare OS version of device with required value
	 * 
	 * @throws Exception
	 * @throws NumberFormatException
	 */
	public static boolean comapreOsVersion(int n, Operator op) throws NumberFormatException, Exception {

		boolean validOsVersion = false;
		LOG.info("Checking OS version");

		switch (op) {
		case LESSTHAN:
			if (Integer.parseInt((AdbFactory.getAdbController().getOSVersion().split("\\.")[0])) < n)
				validOsVersion = true;
			break;
		case GREATERTHAN:
			if (Integer.parseInt((AdbFactory.getAdbController().getOSVersion().split("\\.")[0])) > n)
				validOsVersion = true;
			break;
		case LESSTHAN_OR_EQUALTO:
			if (Integer.parseInt((AdbFactory.getAdbController().getOSVersion().split("\\.")[0])) <= n)
				validOsVersion = true;
			break;
		case GREATERTHAN_OR_EQUALTO:
			if (Integer.parseInt((AdbFactory.getAdbController().getOSVersion().split("\\.")[0])) >= n)
				validOsVersion = true;
			break;
		case EQUALTO:
			if (Integer.parseInt((AdbFactory.getAdbController().getOSVersion().split("\\.")[0])) == n)
				validOsVersion = true;
			break;
		case NOT_EQUALTO:
			if (Integer.parseInt((AdbFactory.getAdbController().getOSVersion().split("\\.")[0])) != n)
				validOsVersion = true;
			break;
		default:
			LOG.error("Please enter valid operator");
			break;
		}
		return validOsVersion;
	}

	/**
	 * Lock device using ADB command
	 */
	public static void lockDevice() throws Exception {
		LOG.info("locking device through adb command");
		MobileHelperFactory.getMobileDriver().sendSpecialKeys(AndroidResources.KEYCODES.ANDROID_KEYCODE_LOCK_DEVICE);
	}

	/**
	 * LongPress Home Button on device
	 * 
	 * @throws Exception
	 */
	public static void longPressDeviceHomeButton() {
		AdbFactory.getAdbController()
				.executeAdbCommand(" shell input keyevent --longpress KEYCODE_HOME --long_press_timeout 10");
		AndroidUtils.delay(AndroidUtils.DELAY_5_SEC);
	}

	/**
	 * Returns the Model number of the device
	 * 
	 * @throws Exception
	 */
	public static String getModelNumber() throws Exception {
		return AdbFactory.getAdbController().getDeviceModelNumber();
	}

	/**
	 * Go To HomeScreen of the Device
	 * 
	 * @throws Exception
	 */
	public static void navigateToDeviceHomeScreen() {
		// Click on Home button twice other wise it is not navigating to HOME Screen
		AdbFactory.getAdbController().sendKeyEvent(AndroidResources.KEYCODES.ANDROID_KEYCODE_HOME);
		AdbFactory.getAdbController().sendKeyEvent(AndroidResources.KEYCODES.ANDROID_KEYCODE_HOME);
	}

	/**
	 * Close All Recent apps from Task Manager
	 * 
	 * @throws Exception
	 */
	public static void closeAllRecentApps() throws Exception {
		navigateToDeviceHomeScreen();
		String fetchStackIdListCommand = " shell am stack list | egrep -io 'Stack id=.*bounds'";
		String stackIDs = AdbFactory.getAdbController().executeAdbCommand(fetchStackIdListCommand);
		stackIDs = stackIDs.replace("Stack id=", "");
		stackIDs = stackIDs.replace(" bounds", "");
		String[] stackIDs2 = stackIDs.trim().split("\\s+");
		for (int id = 0; id < stackIDs2.length; id++) {
			String stackCommandToKillRecentOpenedApps = " shell am stack remove " + stackIDs2[id];
			AdbFactory.getAdbController().executeAdbCommand(stackCommandToKillRecentOpenedApps);
		}
		navigateToDeviceHomeScreen();
	}
	
	
	/**
     * Opens the Notifications Tray on device.
     * @throws Exception
     */
	public static void openDeviceNotificationsTray() throws Exception {
		MobileHelperFactory.getMobileDriver().openNotificationTray();
	}
	
	/**
	 * This method clicks on Clear ALL button in Device Notification Tray to clear the notificaitons
	 * @throws Exception
	 */
	public static void clearALLDeviceNotifications() throws Exception{
		openDeviceNotificationsTray();
		if(MobileHelperFactory.getMobileDriver().waitForElement(withTimeout(byText("CLEAR ALL"), TIMEOUTS.TIMEOUT_5_SECS)))
		{
			MobileHelperFactory.getMobileDriver().click(byText("CLEAR ALL"));
		}
		if(MobileHelperFactory.getMobileDriver().waitForElement(withTimeout(byText("CLEAR"), TIMEOUTS.TIMEOUT_5_SECS)))
		{
			MobileHelperFactory.getMobileDriver().click(byText("CLEAR"));
		}
	}
	
	/**
	 * This method waits for the Notification with specified message and opens the notification tray if found
	 * @return true if the notification message is found in the notification tray
	 * @throws Exception
	 */
	public static boolean waitForNotificationAndOpenTray(String message) throws Exception {
		boolean isNotificationPrsent = false;
		for (int attempt = 1; attempt < 30; attempt++) {
			MobileHelperFactory.getMobileDriver().openNotificationTray();
			if (MobileHelperFactory.getMobileDriver().isElementPresent(AndroidLocator.withTimeout(AndroidLocator.byText(message), TIMEOUTS.TIMEOUT_60_SECS))) {
				isNotificationPrsent = true;
				break;
			}
			MobileHelperFactory.getMobileDriver().pressHome();
			AndroidUtils.delay(AndroidUtils.DELAY_10_SEC);
		}
		return isNotificationPrsent;
	}
	
	/**
	 * Kill the existing Opened adb logcat process.
	 */
	public static void killExistingLogcatProcessOfAndroidDevice() {
		String SHELL = "/bin/sh";
		String SHELL_OPTS = "-c";
		String device_id = AdbFactory.getAdbController().getDeviceID();
		String processName = device_id + " logcat -v threadtime";
		String grepProcessPid = "ps aux | grep " +device_id +" | grep 'logcat -v threadtime' | awk '{print $2}' | xargs kill -9";
		LOG.info("Killing the preocess {}", processName);
		String[] killProcessCommand = { SHELL, SHELL_OPTS, String.format(grepProcessPid, processName) };
		new Utils().executeCommand(killProcessCommand);
	}

}
