package com.itt.itradeorder.helper;

import static com.itt.browser.common.BrowserLocator.byCssSelector;
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
import com.itt.itradeorder.pages.ItradeOrderLoginPage;
import com.itt.itradeorder.pages.ItradeOrderOrderDetailsPage;
import com.itt.itradeorder.pages.ItradeOrderOrdersPage;
import com.itt.itradeorder.pages.ItradeOrderNewOrderPage;

import lombok.Getter;
import lombok.Setter;

public class ItradeOrderHelperFactory {

	private static final Logger LOG = LoggerFactory.getLogger(ItradeOrderHelperFactory.class);

	private static ItradeOrderHelperFactory itradeOrderHelperFactory = new ItradeOrderHelperFactory();
	private ItradeOrderLoginPage itradeOrderLoginPage = new ItradeOrderLoginPage();
	private ItradeOrderNewOrderPage itradeOrderNewOrderPage = new ItradeOrderNewOrderPage();
	private ItradeOrderOrderDetailsPage itradeOrderOrderDetailsPage = new ItradeOrderOrderDetailsPage();
	private ItradeOrderOrdersPage itradeOrderOrdersPage = new ItradeOrderOrdersPage();
	private static final String cssUserMenuArrowButton = "button.btn-user-menu.mat-icon-button";
	private static final String xLogoutButton = "//span[contains(@class, 'itn-icon-logout')]/following-sibling::span[contains(text(), 'Logout')]";
	private static String xCloseFeedbackMessage = "//div[@class='cdk-overlay-container']//span[contains(text(), 'CLOSE')]";
	private static String cssFeedbackMessage = "div.cdk-overlay-container div.sb-text";
	private static String cssOKConfirmationDialogButton = ".mat-dialog-container > confirm-dialog .action-primary";
	
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

	public void logout() throws Exception {
		LOG.info("LOGOUT FROM ItradeOrder");
		try {
			getBrowserDriver().click(byCssSelector(cssUserMenuArrowButton));
			getBrowserDriver().click(byXpath(xLogoutButton));
		} catch (Exception e) {
			LOG.debug("Logout element is not found, might have already logged out");
		}
	}

	public static String getDate() {
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy ");
		Date date = new Date();
		return dateFormat.format(date);
	}

	public static void waitForloaderToDisapper() throws Exception {
		getBrowserDriver().waitForElement(withWaitForVisibility(byCssSelector("div.loader-overlay"), "false"));
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
		return getBrowserDriver().getText(byCssSelector(cssFeedbackMessage));
	}

	public static void clickOnOkConfirmationDialogButton() throws Exception {
		LOG.debug("Click on OK confirmation dialog");
		getBrowserDriver().click(byCssSelector(cssOKConfirmationDialogButton));
	}
}
