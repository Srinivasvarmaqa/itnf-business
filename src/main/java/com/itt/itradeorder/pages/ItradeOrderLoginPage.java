package com.itt.itradeorder.pages;

import static com.itt.browser.common.BrowserLocator.byName;
import static com.itt.browser.common.BrowserLocator.byXpath;
import static com.itt.browser.common.BrowserLocator.withClearOption;
import static com.itt.browser.common.BrowserLocator.withText;
import static com.itt.browser.common.BrowserLocator.withWaitForVisibility;
import static com.itt.factoryhelper.BrowserHelperFactory.getBrowserDriver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itt.itradeorder.datamodelhelper.ItradeOrderDataModelHelperFactory;
import com.itt.itradeorder.helper.ItradeOrderHelperFactory;
import com.itt.oms.datamodelhelper.OMSDataModelHelperFactory;

public class ItradeOrderLoginPage {

	private static final Logger LOG = LoggerFactory.getLogger(ItradeOrderLoginPage.class);
	private static String iDUserName = "username";
	private static String iDPassword = "password";
	private static String xLoginButton = "//button[contains(.,'LOGIN')]";
	OMSDataModelHelperFactory omsDataModelHelperFactory;

	public enum USER {
		BUYER, VENDOR, CARRIER, XDOCK, OPERATOR;
	}

	public void enterUserName(final String userName) throws Exception {
		LOG.info("ENTER USERNAME:" + userName);
		Thread.sleep(1000);
		if (getBrowserDriver().waitForElement(withWaitForVisibility(byName(iDUserName), "true")))  {
			getBrowserDriver().sendValue(withText(withClearOption(byName(iDUserName), true), userName));
		} else {
			throw new RuntimeException("Unable to find Username field");
		}
	}

	public void enterPassword(final String password) throws Exception {
		LOG.info("ENTER USER PASSWORD" + password);
		Thread.sleep(1000);
		if (getBrowserDriver().waitForElement(withWaitForVisibility(byName(iDPassword), "true")))  {
			getBrowserDriver().sendValue(withText(withClearOption(byName( iDPassword), true), password));
		} else {
			throw new RuntimeException("Unable to find Password field");
		}
	}

	public void clickLoginButton() throws Exception {
		if (getBrowserDriver().waitForElement(withWaitForVisibility(byXpath(xLoginButton), "true")))  {
			getBrowserDriver().click(byXpath(xLoginButton));
		} else {
			throw new RuntimeException("Unable to find Login button");
		}
		ItradeOrderHelperFactory.waitForloaderToDisapper();
	}

	public void goToLoginPage(String url) throws Exception {
		getBrowserDriver().openUrl(url);
		getBrowserDriver().waitForPageLoad();
	}

	public void loginToItradeOrder(USER user, ItradeOrderDataModelHelperFactory itradeOrderDataModelHelperFactory) throws Exception {
		String username;
		String password;

		switch (user) {
			case BUYER :
				username = itradeOrderDataModelHelperFactory.getItradeOrderDataModelLoginUsers().getBuyerusername();
				password = itradeOrderDataModelHelperFactory.getItradeOrderDataModelLoginUsers().getPassword();
				break;
			case VENDOR :
				username = itradeOrderDataModelHelperFactory.getItradeOrderDataModelLoginUsers().getVendorusername();
				password = itradeOrderDataModelHelperFactory.getItradeOrderDataModelLoginUsers().getPassword();
				break;
			case CARRIER :
				username = itradeOrderDataModelHelperFactory.getItradeOrderDataModelLoginUsers().getCarrierusername();
				password = itradeOrderDataModelHelperFactory.getItradeOrderDataModelLoginUsers().getPassword();
				break;
			case XDOCK :
				username = itradeOrderDataModelHelperFactory.getItradeOrderDataModelLoginUsers().getXdockusername();
				password = itradeOrderDataModelHelperFactory.getItradeOrderDataModelLoginUsers().getPassword();
				break;
			case OPERATOR :
				username = itradeOrderDataModelHelperFactory.getItradeOrderDataModelLoginUsers().getOperatorusername();
				password = itradeOrderDataModelHelperFactory.getItradeOrderDataModelLoginUsers().getPassword();
				break;
			default :
				LOG.error("Error! unknown user " + user);
				throw new Exception("Incorrect UserAccount");
		}
		LOG.info("LOGIN TO OMS AS: " + username);
		enterUserName(username);
		enterPassword(password);
		clickLoginButton();
	}
}
