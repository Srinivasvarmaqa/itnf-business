package com.itt.mobile.common.android;

import java.util.HashMap;

import org.openqa.selenium.Point;

public class AndroidLocator {
	
	

	/**
	 * Constant class that holds the keys to be used as keys in the params for ToolHelperFactory Driver APIs
	 * 
	 *
	 */
	public static class KEYS {
		public static final String SEARCH_BY = "searchBy";
		public static final String SEARCH_VALUE = "searchValue";
		public static final String TIMEOUT = "timeout";
		public static final String ENTER_VALUE = "enterValue";
		public static final String PROPERTY_NAME = "propertyName";
		public static final String VISIBILITY = "visibility";
		public static final String PROPERTY_VALUE = "propertyValue";
		public static final String SCROLL_TO_TEXT = "scrollToText";
		public static final String APP_PATH = "AppPath";
		public static final String BUNDLE_ID = "BundleId";
		public static final String APP_PACKAGE = "AppPackage";
		public static final String WITH_ADB = "withAdb";
		public static final String NO_CLEAR = "noClear";

		public static final String SCREEN_ORIENTATION = "screenOrientation";

		//click method keys
		public static final String BY_POSITION = "byPosition";
		public static final String X_POSITION = "xPosition";
		public static final String Y_POSITION = "yPosition";

		//scroll method keys
		public static final String START_POSITION_X = "startPositionX";
		public static final String START_POSITION_Y = "startPositionY";
		public static final String DIFF_X = "diffX";
		public static final String DIFF_Y = "diffY";
	}
	
	
	/**
	 * Constant class that holds the constant literals to be used as values in the params for ToolHelperFactory Driver APIs
	 * 
	 *
	 */
	public static class VALUES {
		public static final String ID = "id";
		public static final String XPATH = "xpath";
		public static final String CONTENT_DESC = "content-desc";
		public static final String TEXT = "text";
		public static final String PARTIALTEXT = "partialtext";
		public static final String CLASS = "class";
		public static final String ENABLED = "enabled";
        public static final String CHECKED = "checked";
        public static final String LANDSCAPE = "landscape";
        public static final String PORTRAIT = "portrait";
        public static final String PROPERTY_NAME = "name";
	}
	
	
	/**
	 * Prepares the params to use the id as identifier
	 * @param id The value of id to be used as identifier
	 * @return HashMap<String, String> to be used as params for Driver APIs
	 */
	public static HashMap<String,String> byId(String id) {
		HashMap<String,String> params = new HashMap<String,String>();
		params.put(KEYS.SEARCH_BY, VALUES.ID);
		params.put(KEYS.SEARCH_VALUE, id);
		params.put(KEYS.TIMEOUT, AndroidResources.TIMEOUTS.TIMEOUT_30_SECS);
		return params;
	}

	/**
	 * Prepares the params to use the xpath as identifier
	 * @param xpath The value of xpath to be used as identifier
	 * @return HashMap<String, String> to be used as params for Driver APIs
	 */
	public static HashMap<String,String> byXpath(String xpath) {
		HashMap<String,String> params = new HashMap<String,String>();
		params.put(KEYS.SEARCH_BY, VALUES.XPATH);
		params.put(KEYS.SEARCH_VALUE, xpath);
		params.put(KEYS.TIMEOUT, AndroidResources.TIMEOUTS.TIMEOUT_30_SECS);
		return params;
	}

	/**
	 * Prepares the params to use the content-desc as identifier
	 * @param contentDescription The value of content-desc to be used as identifier
	 * @return HashMap<String, String> to be used as params for Driver APIs
	 */
	public static HashMap<String, String> byContentDescription(String contentDescription) {
		HashMap<String,String> params = new HashMap<String,String>();
		params.put(KEYS.SEARCH_BY, VALUES.CONTENT_DESC);
		params.put(KEYS.SEARCH_VALUE, contentDescription);
		params.put(KEYS.TIMEOUT, AndroidResources.TIMEOUTS.TIMEOUT_30_SECS);
		return params;
	}

	/**
	 * Prepares the params to use the text as identifier
	 * @param text The value of text to be used as identifier
	 * @return HashMap<String, String> to be used as params for Driver APIs
	 */
	public static HashMap<String, String> byText(String text) {
		HashMap<String,String> params = new HashMap<String,String>();
		params.put(KEYS.SEARCH_BY, VALUES.TEXT);
		params.put(KEYS.SEARCH_VALUE, text);
		params.put(KEYS.TIMEOUT, AndroidResources.TIMEOUTS.TIMEOUT_30_SECS);
		return params;
	}
	
	/**
	 * Prepares the params to use the Partial text as identifier
	 * @param text The value of text to be used as identifier
	 * @return HashMap<String, String> to be used as params for Driver APIs
	 */
	public static HashMap<String, String> byPartialText(String text) {
		HashMap<String,String> params = new HashMap<String,String>();
		params.put(KEYS.SEARCH_BY, VALUES.PARTIALTEXT);
		params.put(KEYS.SEARCH_VALUE, text);
		params.put(KEYS.TIMEOUT, AndroidResources.TIMEOUTS.TIMEOUT_30_SECS);
		return params;
	}

	/**
	 * Prepares the params to use the class as identifier
	 * @param className The value of class to be used as identifier
	 * @return HashMap<String, String> to be used as params for Driver APIs
	 */
	public static HashMap<String, String> byClass(String className) {
		HashMap<String,String> params = new HashMap<String,String>();
		params.put(KEYS.SEARCH_BY, VALUES.CLASS);
		params.put(KEYS.SEARCH_VALUE, className);
		params.put(KEYS.TIMEOUT, AndroidResources.TIMEOUTS.TIMEOUT_30_SECS);
		return params;
	}

	/**
	 * Prepares the params to use the class and text as identifier
	 * @param widgetClass The value of class to be used as identifier
	 * @param text The value of text to be used as identifier
	 * @return HashMap<String, String> to be used as params for Driver APIs
	 */
	public static HashMap<String,String> byClassAndText(String widgetClass, String text) {
		HashMap<String,String> params = new HashMap<String,String>();
		params.put(KEYS.SEARCH_BY, VALUES.XPATH);
		params.put(KEYS.SEARCH_VALUE, "//" + widgetClass + "[@text=\"" + text + "\"]");
		params.put(KEYS.TIMEOUT, AndroidResources.TIMEOUTS.TIMEOUT_30_SECS);
		return params;
	}

	/**
	 * Adds the key-values for the sendValue API of the Driver
	 * @param byProperty HashMap<String, String> params that has already been prepared by any of the "by" methods
	 * @param enterValue Value to be entered in the text
	 * @return HashMap<String, String> to be used as params for Driver APIs
	 */
	public static HashMap<String, String> withSendValue(HashMap<String, String> byProperty, String enterValue) {
		byProperty.put(KEYS.ENTER_VALUE, enterValue);
		return byProperty;
	}

	/**
	 * Adds the key-values for the waitForVisibility API of the Driver
	 * @param byProperty HashMap<String, String> params that has already been prepared by any of the "by" methods
	 * @param visibility true or false based on whether the wait is for element to appear or disappear respectively
	 * @return HashMap<String, String> to be used as params for Driver APIs
	 */
	public static HashMap<String, String> withWaitForVisibility(HashMap<String, String> byProperty, String visibility) {
		byProperty.put(KEYS.VISIBILITY, visibility);
		return byProperty;
	}

	/**
	 * Adds the key-values for the verifyEnabledProperty API of the Driver
	 * @param byProperty HashMap<String, String> params that has already been prepared by any of the "by" methods
	 * @param enabled true or false based on whether the UI element to be verified as enabled or disabled respectively
	 * @return HashMap<String, String> to be used as params for Driver APIs
	 */
	public static HashMap<String, String> withVerifyEnabledProperty(HashMap<String, String> byProperty, String enabled) {
		byProperty.put(KEYS.PROPERTY_NAME, VALUES.ENABLED);
		byProperty.put(KEYS.PROPERTY_VALUE, enabled);
		return byProperty;
	}

	/**
	 * Adds the custom timeout in property map
	 * @param byProperty HashMap<String, String> params that has already been prepared by any of the "by" methods
	 * @param customTimeout string custom timeout value
	 * @return HashMap<String, String> to be used as params for Driver APIs
	 */
	public static HashMap<String, String> withTimeout(HashMap<String, String> byProperty, String customTimeout) {
		byProperty.put(KEYS.TIMEOUT, customTimeout);
		return byProperty;
	}

	/**
     * Method for work with sendValue.
	 * Specifies that text should be typed via adb directly
     * @param byProperty HashMap<String, String> params that has already been prepared by any of the "by" methods
     * @param clear weather clear text or append value to it
	 * @return HashMap<String, String> to be used as params for sendValue Driver API
     */
	public static HashMap<String, String> withAdbText(HashMap<String, String> byProperty, boolean clear) {
		byProperty.put(KEYS.WITH_ADB, "true");
		if (!clear) {
			byProperty.put(KEYS.NO_CLEAR, "true");
		}
	    return byProperty;
	}

	/**
	 * Method for work with setDeviceOrientation.
	 * Specifies new orientation to be used by API
	 * @param orientation use {@link VALUES#PORTRAIT} or {@link VALUES#LANDSCAPE}
	 * @return HashMap<String, String> to be used as params for setDeviceOrientation Driver API
	 */
	public static HashMap<String, String> byDeviceOrientation(String orientation) {
		HashMap<String,String> params = new HashMap<String,String>();
		params.clear();
		params.put(KEYS.SCREEN_ORIENTATION, orientation);
		return params;
	}

    /**
     * General method for work with getElementProperty
     * @param byProperty element locator in HashMap<String, String> format
     * @param propertyName name of property to get
     * @return modified HashMap<String, String> which includes property request
     */
    public static HashMap<String, String> withPropertyName(HashMap<String, String> byProperty, String propertyName) {
		byProperty.put(KEYS.PROPERTY_NAME, propertyName);
        return byProperty;
    }

	/**
	 * General method for work with different scroll methods
	 * @param startX x coordinate of start position
	 * @param startY y coordinate of start position
	 * @param diffX	x part of whole "path" that will be scrolled
	 * @param diffY y part of whole "path" that will be scrolled
	 * @return modified HashMap<String, String> which includes scrolling details
	 */
	public static HashMap<String, String> byScrollCoordinates(String startX, String startY, String diffX, String diffY) {
		HashMap<String,String> params = new HashMap<String,String>();
		if (startX != null) params.put(KEYS.START_POSITION_X, startX);
		if (startY != null) params.put(KEYS.START_POSITION_Y, startY);
		if (diffX != null) params.put(KEYS.DIFF_X, diffX);
		if (diffY != null) params.put(KEYS.DIFF_Y, diffY);
		return params;
	}

	/**
	 * Prepares the params to be used with scrollTo API of the Driver
	 * @param scrollToText The text to which the scroll to be done
	 * @return HashMap<String, String> to be used as params for Driver APIs
	 */
	public static HashMap<String, String> byScrollTo(String scrollToText) {
		HashMap<String,String> params = new HashMap<String,String>();
		params.put(KEYS.SCROLL_TO_TEXT, scrollToText);
		return params;
	}

	/**
	 * Prepares the params to use the AppPath for the Driver APIs
	 * @param appPath Path of the app's APK
	 * @return HashMap<String, String> to be used as params for Driver APIs
	 */
	public static HashMap<String, String> byAppPath(String appPath) {
		HashMap<String,String> params = new HashMap<String,String>();
		params.put(KEYS.APP_PATH, appPath);
		return params;
	}

	/**
	 * Prepares the params to use the BundleId for the Driver APIs
	 * @param bundleId BundleId of the app
	 * @return HashMap<String, String> to be used as params for Driver APIs
	 */
	public static HashMap<String, String> byBundleId(String bundleId) {
		HashMap<String,String> params = new HashMap<String,String>();
		params.put(KEYS.BUNDLE_ID, bundleId);
		return params;
	}

	/**
	 * Prepares the params to use the AppPackage for the Driver APIs
	 * @param appPackage Package of the app to be used
	 * @return HashMap<String, String> to be used as params for Driver APIs
	 */
	public static HashMap<String, String> byAppPackage(String appPackage) {
		HashMap<String,String> params = new HashMap<String,String>();
		params.put(KEYS.APP_PACKAGE, appPackage);
		return params;
	}

    /**
     * @return returns empty params
     */
    public static HashMap<String, String> byEmptyParams() {
        return new HashMap<String,String>();
    }

	/**
	 * Method for work with click API method
	 * @param position Point which contains x and y of position to click on
	 * @return HashMap<String, String> to be used as params for click method
	 */
	public static HashMap<String, String> byPosition(Point position) {
		HashMap<String,String> params = new HashMap<String,String>();
		params.put(KEYS.BY_POSITION, "true");
		params.put(KEYS.X_POSITION, String.valueOf(position.getX()));
		params.put(KEYS.Y_POSITION, String.valueOf(position.getY()));
		return params;
	}

}
