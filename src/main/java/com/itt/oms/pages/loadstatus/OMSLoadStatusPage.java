package com.itt.oms.pages.loadstatus;

import static com.itt.browser.common.BrowserLocator.byCssSelector;
import static com.itt.browser.common.BrowserLocator.byName;
import static com.itt.browser.common.BrowserLocator.byXpath;
import static com.itt.browser.common.BrowserLocator.selectDropDownValue;
import static com.itt.browser.common.BrowserLocator.withCustomTimeout;
import static com.itt.browser.common.BrowserLocator.withText;
import static com.itt.factoryhelper.BrowserHelperFactory.getBrowserDriver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itt.common.Timeout;
import com.itt.factoryhelper.BrowserHelperFactory.DROPDOWN;
import com.itt.oms.datamodelhelper.OMSDataModelHelperFactory;
import com.itt.oms.helper.OMSHelperFactory;

public class OMSLoadStatusPage {

	private static final Logger LOG = LoggerFactory.getLogger(OMSLoadStatusPage.class);
	private static final String cssLogisticsCmenu = "a[title='Logistics C']";
	private static final String cssOrderStatusmenu = "a[title='Order Status']";
	private static final String nPONumberSearchTextBox = "txtsearch";
	private static final String nButtonSearch = "search";
	private static final String cssPONumberlistedLink = "a[href*='_details']";
	private static final String cssSearchDropDown = "select[name='SearchPOSO']";
	private static final String xDriverDropDown = "//select[@name='Driver']";
	private static final String xVehicleDropDown = "//select[@name='Vehicle']";
	private static final String nSubmitButton = "MySubmit";
	private static final String xSubmitButton = "//div[@id='lineitemsdiv']/table[2]//input[@name='MySubmit']";
	OMSDataModelHelperFactory omsDataModelHelperFactory;

	public void openOrderStatusMenu() throws Exception {

		LOG.debug("Switch to Left Frame");
		OMSHelperFactory.switchToLeftFrame();

		LOG.info("Click on Logistics C from the left menu");
		getBrowserDriver().click(byCssSelector(cssLogisticsCmenu));

		LOG.info("Click on Order Status from the left menu");
		getBrowserDriver().click(byCssSelector(cssOrderStatusmenu));
	}
	
	public void confirmLoadStatus(String poNumber, String loadNumber, OMSDataModelHelperFactory omsDataModelHelperFactory) throws Exception {
		String driver = omsDataModelHelperFactory.getOmsDataModelLoadStatus().getDriver();
		String vehicle = omsDataModelHelperFactory.getOmsDataModelLoadStatus().getVehicle();

		this.openOrderStatusMenu();

		LOG.debug("Switch to Main Frame");
		OMSHelperFactory.switchToMainFrame();

		if (loadNumber != null) {
			lookUpLoad(loadNumber);
		} else {
			OMSHelperFactory.lookUpPO(poNumber);
		}

		LOG.info("Find the PO number from the list");
		getBrowserDriver().click(byCssSelector(cssPONumberlistedLink));
		
		LOG.info("Enter the input values");
		getBrowserDriver().selectDropDown(selectDropDownValue(byXpath(xDriverDropDown), DROPDOWN.VISIBLETEXT.toString(), driver));
		getBrowserDriver().selectDropDown(selectDropDownValue(byXpath(xVehicleDropDown), DROPDOWN.VISIBLETEXT.toString(), vehicle));
		
		LOG.info("Click on Submit Button");
		this.clickSubmitButton();
	}

	public void confirmLoadAfterShipping(String poNumber) throws Exception {
		this.openOrderStatusMenu();
		
		LOG.debug("Switch to Main Frame");
		OMSHelperFactory.switchToMainFrame();
		
		OMSHelperFactory.lookUpPO(poNumber);
		
		LOG.info("Find the PO number from the list");
		getBrowserDriver().click(byCssSelector(cssPONumberlistedLink));
		
		LOG.info("Click on Submit Button");
		getBrowserDriver().click(byXpath(xSubmitButton));
	}

	public static void lookUpLoad(String loadNumber) throws Exception {
		LOG.info("Look for loadNumber - " + loadNumber);
		if (getBrowserDriver().waitForElement(withCustomTimeout(byCssSelector(cssSearchDropDown), Timeout.FIVE_SECONDS_TIMEOUT))) {
			getBrowserDriver().selectDropDown(selectDropDownValue(byCssSelector(cssSearchDropDown), DROPDOWN.VALUE.toString(), "Load"));
		}
		getBrowserDriver().sendValue(withText(byName(nPONumberSearchTextBox), loadNumber));
		getBrowserDriver().click(byName(nButtonSearch));
	}

	public void clickSubmitButton() throws Exception {
		LOG.info("Click on Submit Button");
		getBrowserDriver().click(byName(nSubmitButton));
	}
}
