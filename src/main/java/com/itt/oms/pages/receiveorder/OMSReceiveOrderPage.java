package com.itt.oms.pages.receiveorder;

import org.slf4j.LoggerFactory;

import com.itt.common.Timeout;
import com.itt.factoryhelper.BrowserHelperFactory.ALERT;
import com.itt.oms.helper.OMSHelperFactory;

import org.slf4j.Logger;
import static com.itt.factoryhelper.BrowserHelperFactory.getBrowserDriver;
import static com.itt.browser.common.BrowserLocator.*;

public class OMSReceiveOrderPage {

	private static final Logger LOG = LoggerFactory.getLogger(OMSReceiveOrderPage.class);
	private static String cssReceivingMenu = "a[title='Receiving']";
	private static String cssReceivingStatusMenu = "a[title='Receiving W/H']";
	private static String xReceivingStatusMenu = "//a[@title='Receiving W/H' or (@title='Receiving' and contains(@href, 'submenu'))]";
	private static String cssPONumberlistedLink = "a[href*='receive_details']";
	private static String cssSaveButton = "input[name='Save']";
	private static String cssSubmitButton = "input[name^='MySubmit']";
	private static String cssDuplicateButton = "input[name^='Duplicate']";
	
	
	public void openReceiveOrderFromMenu() throws Exception {

		LOG.debug("Switch to Left Frame");
		OMSHelperFactory.switchToLeftFrame();

		LOG.info("Click on Shipping Menu from the left menu");
		getBrowserDriver().click(byCssSelector(cssReceivingMenu));

		LOG.info("Click on Receving menu");
		getBrowserDriver().click(byXpath(xReceivingStatusMenu));
	}

	public void receiveOrder(String poNumber) throws Exception {
		LOG.info("Open Receviced Order Menu");
		this.openReceiveOrderFromMenu();
		
		LOG.debug("Switch to Main Frame");
		OMSHelperFactory.switchToMainFrame();

		OMSHelperFactory.lookUpPO(poNumber);

		LOG.info("Click on the PO from Search results");
		getBrowserDriver().click(byCssSelector(cssPONumberlistedLink));

		this.clickSaveButton();
		
		LOG.debug("Switch to Main frame");
		OMSHelperFactory.switchToMainFrame();
		getBrowserDriver().waitForPageLoad();

		LOG.info("Click Save Button Twice");
		this.clickSaveButton();
		this.clickSaveButton();
		
		this.clickDuplicateButton();

		try {
			this.clickSubmitButton();
		} catch(Exception e) {
			LOG.info("Alert Pop-up is Displayed");
		}
		
		try {
			getBrowserDriver().handleAlerts(withAlertAction(ALERT.ACCEPT.toString()));
		} catch (Exception e) {
			LOG.info("Failed to Accept alert or no pop up");
		}
	}
	
	public void clickSaveButton() throws Exception {
		LOG.info("Click Save Button");
		getBrowserDriver().click(byCssSelector(cssSaveButton));
	}

	public void clickSubmitButton() throws Exception {
		LOG.info("Click on Submit Button");
		getBrowserDriver().click(byCssSelector(cssSubmitButton));
	}

	public void clickDuplicateButton() throws Exception {
		if (getBrowserDriver().isElementPresent(withCustomTimeout(byCssSelector(cssDuplicateButton), Timeout.FIVE_SECONDS_TIMEOUT))) {
			LOG.info("Click on Duplicate Button");
			getBrowserDriver().click(byCssSelector(cssDuplicateButton));
		}
	}
}
