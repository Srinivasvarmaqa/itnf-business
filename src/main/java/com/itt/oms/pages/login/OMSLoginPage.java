package com.itt.oms.pages.login;

import static com.itt.browser.common.BrowserLocator.byCssSelector;
import static com.itt.browser.common.BrowserLocator.byId;
import static com.itt.browser.common.BrowserLocator.byXpath;
import static com.itt.browser.common.BrowserLocator.withClearOption;
import static com.itt.browser.common.BrowserLocator.withText;
import static com.itt.factoryhelper.BrowserHelperFactory.getBrowserDriver;

import org.openqa.selenium.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itt.oms.datamodelhelper.OMSDataModelHelperFactory;

public class OMSLoginPage {

	private static final Logger LOG = LoggerFactory.getLogger(OMSLoginPage.class);
	private static String iDUserName = "UserName";
	private static String iDPassword = "Password";
	private static String xLogonButton = "//button[contains(.,'Logon')]";
	private static String cssPasswordFocus = "input.x-form-field.x-form-focus[type='password']";
	OMSDataModelHelperFactory omsDataModelHelperFactory;

	public enum USER {
		BUYER, VENDOR, CARRIER, XDOCK, OPERATOR;
	}

	public void enterUserName(final String userName) throws Exception {
		LOG.info("ENTER USERNAME:" + userName);
		if (getBrowserDriver().waitForElement(byId(iDUserName)))  {
			getBrowserDriver().sendValue(withText(withClearOption(byId(iDUserName), true), userName));
			getBrowserDriver().waitForPageLoad();
			Thread.sleep(1000);
		} else {
			throw new RuntimeException("Unable to find Username field");
		}
	}

	public void enterPassword(final String password) throws Exception {
		LOG.info("ENTER USER PASSWORD");
		if (getBrowserDriver().waitForElement(byId(iDPassword)))  {
			getBrowserDriver().click(byId(iDPassword));
			Thread.sleep(1000);
			if (!getBrowserDriver().isElementPresent(byCssSelector(cssPasswordFocus))) {
				getBrowserDriver().click(byId(iDPassword));
			}
			getBrowserDriver().sendValue(withText(withClearOption(byId(iDPassword), true), password));
		} else {
			throw new RuntimeException("Unable to find Password field");
		}
	}

	public void clickLogonButton() throws Exception {
		LOG.info("Click on Logon Button");
		getBrowserDriver().click(byXpath(xLogonButton));
	}

	public void goToLoginPage(String url) throws Exception {
		getBrowserDriver().openUrl(url);
		getBrowserDriver().waitForPageLoad();
	}

	public void loginToOMS(USER user, OMSDataModelHelperFactory omsDataModelHelperFactory) throws Exception {
		String username;
		String password;

		switch (user) {
			case BUYER :
				username = omsDataModelHelperFactory.getOmsDataModelLoginUsers().getBuyerusername();
				password = omsDataModelHelperFactory.getOmsDataModelLoginUsers().getPassword();
				break;
			case VENDOR :
				username = omsDataModelHelperFactory.getOmsDataModelLoginUsers().getVendorusername();
				password = omsDataModelHelperFactory.getOmsDataModelLoginUsers().getPassword();
				break;
			case CARRIER :
				username = omsDataModelHelperFactory.getOmsDataModelLoginUsers().getCarrierusername();
				password = omsDataModelHelperFactory.getOmsDataModelLoginUsers().getPassword();
				break;
			case XDOCK :
				username = omsDataModelHelperFactory.getOmsDataModelLoginUsers().getXdockusername();
				password = omsDataModelHelperFactory.getOmsDataModelLoginUsers().getPassword();
				break;
			case OPERATOR :
				username = omsDataModelHelperFactory.getOmsDataModelLoginUsers().getOperatorusername();
				password = omsDataModelHelperFactory.getOmsDataModelLoginUsers().getPassword();
				break;
			default :
				LOG.error("Error! unknown user " + user);
				throw new Exception("Incorrect UserAccount");
		}
		LOG.info("LOGIN TO OMS AS: " + username);
		enterUserName(username);
		
		enterPassword(password);
		clickLogonButton();
	}
}
