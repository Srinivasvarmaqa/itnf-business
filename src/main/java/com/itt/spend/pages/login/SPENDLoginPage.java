package com.itt.spend.pages.login;

import static com.itt.browser.common.BrowserLocator.byCssSelector;
import static com.itt.browser.common.BrowserLocator.byId;
import static com.itt.browser.common.BrowserLocator.byXpath;
import static com.itt.browser.common.BrowserLocator.withClearOption;
import static com.itt.browser.common.BrowserLocator.withText;
import static com.itt.factoryhelper.BrowserHelperFactory.getBrowserDriver;

import org.openqa.selenium.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itt.spend.datamodelhelper.SPENDDataModelHelperFactory;
import com.itt.spend.helper.SPENDHelperFactory;

public class SPENDLoginPage {

	private static final Logger LOG = LoggerFactory.getLogger(SPENDLoginPage.class);
	private static String iDUserName = "username";
	private static String iDPassword = "password";
	private static String xProxyButton = "//img[@name='SD']";
	private static String iDProxyId = "proxyname";
	private static String xSigninButton = "//a[@class='btn-login']";
	
	SPENDDataModelHelperFactory spendDataModelHelperFactory;

	public enum USER {
		OPERATOR;
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
			
			getBrowserDriver().sendValue(withText(withClearOption(byId(iDPassword), true), password));
		} else {
			throw new RuntimeException("Unable to find Password field");
		}
	}
	public void clickProxyButton() throws Exception {
		LOG.info("Click on Proxy Button");
		getBrowserDriver().click(byXpath(xProxyButton));
	}
	public void enterProxyId(final String proxyid) throws Exception {
		LOG.info("ENTER USER ProxyId");
		if (getBrowserDriver().waitForElement(byId(iDProxyId)))  {
			getBrowserDriver().click(byId(iDProxyId));
			
			getBrowserDriver().sendValue(withText(withClearOption(byId(iDProxyId), true), proxyid));
		} else {
			throw new RuntimeException("Unable to find ProxyId field");
		}
	}


	public void clickSigninButton() throws Exception {
		LOG.info("Click on Signin Button");
		getBrowserDriver().click(byXpath(xSigninButton));
	}

	public void goToLoginPage(String url) throws Exception {
		getBrowserDriver().openUrl(url);
		getBrowserDriver().waitForPageLoad();
	}

	public void loginToSPEND(USER user, SPENDDataModelHelperFactory spendDataModelHelperFactory) throws Exception {
		String username;
		String password;
		String proxyid;

		switch (user) {
						case OPERATOR :
				username = spendDataModelHelperFactory.getSpendDataModelLoginUsers().getOperatorusername();
				password = spendDataModelHelperFactory.getSpendDataModelLoginUsers().getPassword();
				proxyid = spendDataModelHelperFactory.getSpendDataModelLoginUsers().getProxyid();

				break;
			default :
				LOG.error("Error! unknown user " + user);
				throw new Exception("Incorrect UserAccount");
		}
		LOG.info("LOGIN TO NDM AS: " + username);
		enterUserName(username);
		
		enterPassword(password);
		clickProxyButton();
		enterProxyId(proxyid);

		clickSigninButton();
		SPENDHelperFactory.waitForloaderToDisapper();
	}
}
