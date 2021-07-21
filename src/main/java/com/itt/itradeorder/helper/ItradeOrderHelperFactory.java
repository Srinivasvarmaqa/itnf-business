package com.itt.itradeorder.helper;

import static com.itt.browser.common.BrowserLocator.byCssSelector;
import static com.itt.browser.common.BrowserLocator.byName;
import static com.itt.browser.common.BrowserLocator.byXpath;
import static com.itt.browser.common.BrowserLocator.withCustomTimeout;
import static com.itt.browser.common.BrowserLocator.withWaitForVisibility;
import static com.itt.factoryhelper.BrowserHelperFactory.getBrowserDriver;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itt.common.Timeout;
import com.itt.itradeorder.pages.ItradeOrderDashboardPage;
import com.itt.itradeorder.pages.ItradeOrderLoginPage;
import com.itt.itradeorder.pages.ItradeOrderNewOrderPage;
import com.itt.itradeorder.pages.ItradeOrderOrderDetailsPage;
import com.itt.itradeorder.pages.ItradeOrderOrdersPage;
import com.itt.itradeorder.pages.ItradeOrderShippingPage;

import lombok.Getter;
import lombok.Setter;

public class ItradeOrderHelperFactory {

	private static final Logger LOG = LoggerFactory.getLogger(ItradeOrderHelperFactory.class);

	private static ItradeOrderHelperFactory itradeOrderHelperFactory = new ItradeOrderHelperFactory();
	private ItradeOrderLoginPage itradeOrderLoginPage = new ItradeOrderLoginPage();
	private ItradeOrderNewOrderPage itradeOrderNewOrderPage = new ItradeOrderNewOrderPage();
	private ItradeOrderOrderDetailsPage itradeOrderOrderDetailsPage = new ItradeOrderOrderDetailsPage();
	private ItradeOrderOrdersPage itradeOrderOrdersPage = new ItradeOrderOrdersPage();
	private ItradeOrderShippingPage itradeOrderShippingPage = new ItradeOrderShippingPage();
	private ItradeOrderDashboardPage itradeOrderDashboardPage = new ItradeOrderDashboardPage();
	private static final String cssUserMenuArrowButton = "button.btn-user-menu.mat-icon-button";
	private static final String xLogoutButton = "//span[contains(@class, 'itn-icon-logout')]/following-sibling::span[contains(text(), 'Logout')]";
	private static String xCloseFeedbackMessage = "//div[@class='cdk-overlay-container']//span[contains(text(), 'CLOSE')]";
	private static String cssFeedbackMessage = "div.cdk-overlay-container div.sb-text";
	private static String cssOKConfirmationDialogButton = ".mat-dialog-container > confirm-dialog .action-primary";
	private static String iDUserName = "username";
	private static String xClickBlank = "//body";
	private static String cssLoader = "div.loader-overlay";
	
	@Getter
	@Setter
	private static String project;

	public ItradeOrderLoginPage getItradeOrderLoginPage() {
		return this.itradeOrderLoginPage;
	}

	public ItradeOrderNewOrderPage getItradeOrderNewOrderPage() {
		return this.itradeOrderNewOrderPage;
	}
	
	public ItradeOrderOrderDetailsPage getItradeOrderOrderDetailsPage() {
		return this.itradeOrderOrderDetailsPage;
	}

	public ItradeOrderOrdersPage getItradeOrderOrdersPage() {
		return this.itradeOrderOrdersPage;
	}
	
	public ItradeOrderShippingPage getItradeOrderShippingPage() {
		return this.itradeOrderShippingPage;
	}
	
	public ItradeOrderDashboardPage getItradeOrderDashboardPage() {
		return this.itradeOrderDashboardPage;
	}

	public void logout() throws Exception {
		LOG.info("LOGOUT FROM ItradeOrder");
		ItradeOrderHelperFactory.waitForloaderToDisapper();
		try {
			ItradeOrderHelperFactory.waitForloaderToDisapper();
			getBrowserDriver().click(byCssSelector(cssUserMenuArrowButton));
			getBrowserDriver().click(byXpath(xLogoutButton));
			getBrowserDriver().waitForElement(byName(iDUserName));
		} catch (Exception e) {
			LOG.debug("Logout element is not found, might have already logged out, retrying ..");
		}
	}

	public static String getDate() {
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy ");
		Date date = new Date();
		return dateFormat.format(date);
	}

	public static void waitForloaderToDisapper() throws Exception {
		int count = 0;
		getBrowserDriver().waitForElement(withCustomTimeout(byCssSelector(cssLoader), Timeout.TWO_SECONDS_TIMEOUT));
		getBrowserDriver().waitForElement(withWaitForVisibility(byCssSelector(cssLoader), "false"));
		while(getBrowserDriver().findElements(byCssSelector(cssLoader)).size()!=0 && count<60) {
			LOG.info("WAIT FOR LOADER TO DISAPPER - " + count);
			Thread.sleep(1000);
			count++;
		}
	}

	public void closeFeedbackMessage() throws Exception {
		try {
			getBrowserDriver().waitForElement(withCustomTimeout(byXpath(xCloseFeedbackMessage), Timeout.FIVE_SECONDS_TIMEOUT));
			getBrowserDriver().click(byXpath(xCloseFeedbackMessage));
			ItradeOrderHelperFactory.waitForloaderToDisapper();
		} catch (Exception e) {
			LOG.debug("Couldn't close the feedback message");
		}
	}

	public String getFeedbackMessage() throws Exception {
		LOG.debug("Get feedback Message");
		getBrowserDriver().waitForElement(withCustomTimeout(byCssSelector(cssFeedbackMessage), Timeout.FIVE_SECONDS_TIMEOUT));
		String feedBackMessage = getBrowserDriver().getText(byCssSelector(cssFeedbackMessage));
		closeFeedbackMessage();
		return feedBackMessage;
	}

	public static void clickOnOkConfirmationDialogButton() throws Exception {
		LOG.debug("Click on OK confirmation dialog");
		getBrowserDriver().click(byCssSelector(cssOKConfirmationDialogButton));
	}
	
	public static void clickOnBlankArea() throws Exception {
		ItradeOrderHelperFactory.waitForloaderToDisapper();
		getBrowserDriver().click(byXpath(xClickBlank));
	}

	public static void mouseOverToLogoutArrow() throws Exception {
		ItradeOrderHelperFactory.waitForloaderToDisapper();
		getBrowserDriver().moveToElement(byCssSelector(cssUserMenuArrowButton));
	}
}
