package com.itt.oms.pages.shipmentorder;

import org.slf4j.LoggerFactory;

import com.itt.common.Timeout;
import com.itt.factoryhelper.BrowserHelperFactory.ALERT;
import com.itt.factoryhelper.BrowserHelperFactory.DROPDOWN;
import com.itt.oms.datamodelhelper.OMSDataModelHelperFactory;
import com.itt.oms.helper.OMSHelperFactory;

import org.slf4j.Logger;
import static com.itt.factoryhelper.BrowserHelperFactory.getBrowserDriver;
import static com.itt.browser.common.BrowserLocator.*;

public class OMSShipmentOrderPage {

	private static final Logger LOG = LoggerFactory.getLogger(OMSShipmentOrderPage.class);
	private static String cssShippingMenu = "a[title='Shipping']";
	private static String cssShippingStatusMenu = "a[title='Shipping W/H']";
	private static String xShippingStatusMenu = "//a[@title='Shipping W/H' or @title='Shipping Status']";
	private static String cssPONumberlistedLink = "a[href*='shipping_details.cfm']";
	private static String cssSaveButton = "input[name='Save']";
	private static String cssSubmitButton = "input[name^='Submit']";
	private static String cssCoolTextBox = "input[name^='COOL']";
	private static String idBioPopupDialogBox = "dialogbox";
	private static String cssBioEngineeredDropDown = ".bio_eng";
	private static String cssOkButton ="body > div.ui-dialog.ui-widget.ui-widget-content.ui-corner-all.ui-draggable > div.ui-dialog-buttonpane.ui-widget-content.ui-helper-clearfix > div > button";
	
	
	public void selectShippingFromMenu() throws Exception {
		LOG.debug("Switch to Left Frame");
		OMSHelperFactory.switchToLeftFrame();

		LOG.info("Click on Shipping Menu from the left menu");
		getBrowserDriver().click(byCssSelector(cssShippingMenu));

		LOG.info("Click on Vendor menu");
		getBrowserDriver().click(byXpath(xShippingStatusMenu));
	}

	public void shipOrder(String poNumber, OMSDataModelHelperFactory omsDataModelHelperFactory) throws Exception {
		String cool = omsDataModelHelperFactory.getOmsDataModelShipmentOrder().getCool();
		this.selectShippingFromMenu();

		LOG.debug("Switch to Main Frame");
		OMSHelperFactory.switchToMainFrame();

		OMSHelperFactory.lookUpPO(poNumber);

		LOG.info("Find the PO number from the list");
		getBrowserDriver().click(byCssSelector(cssPONumberlistedLink));

		if (cool != null) {
			LOG.info("Enter COOL value");
			getBrowserDriver().sendValue(withText(withClearOption(byCssSelector(cssCoolTextBox), true), cool));
		}

		LOG.info("Click on Submit Button");
		try {
			this.clickSubmitButton();
		} catch (Exception e) {
			LOG.info("Alert Pop-up dispalyed");
		}
		
		try {
			getBrowserDriver().handleAlerts(withAlertAction(ALERT.ACCEPT.toString()));
		} catch (Exception e) {
			LOG.info("Alert Pop-up is NOT displayed");
		}

		// If the bio-engineered option isn't available in the UI, click submit again
		if (omsDataModelHelperFactory.getOmsDataModelShipmentOrder().getBioengineer() != null)
		{
			try {
				this.selectBioEngineered();
			} catch (Exception e) {
				LOG.debug("Bio-Engineered option isn't avilable in the UI");
			}
			
			LOG.info("Click on Submit Button");
			try {
				this.clickSubmitButton();
			} catch (Exception e) {
				LOG.info("Alert Pop-up dispalyed");
			}
	
			try {
				getBrowserDriver().handleAlerts(withAlertAction(ALERT.ACCEPT.toString()));
			} catch (Exception e) {
				LOG.info("Alert Pop-up is NOT displayed");
			}
		}

	}
	
	public void clickSaveButton() throws Exception {
		LOG.info("Click Save Button");
		getBrowserDriver().click(byCssSelector(cssSaveButton));
	}

	public void clickSubmitButton() throws Exception {
		LOG.debug("Click on Submit Button");
		getBrowserDriver().click(byCssSelector(cssSubmitButton));
	}

	public String getBioAlert() throws Exception {
		return getBrowserDriver().getText(byId(idBioPopupDialogBox));
	}

	public void clickOKButton() throws Exception {
		getBrowserDriver().click(byCssSelector(cssOkButton));
	}
	
	public void shipmentAlert() throws Exception {
		clickOKButton();
		LOG.debug("Switch to Main frame");
		OMSHelperFactory.switchToMainFrame();
		getBrowserDriver().waitForPageLoad();

		selectBioEngineered();
		LOG.debug("Switch to Main frame");
		OMSHelperFactory.switchToMainFrame();
		getBrowserDriver().waitForPageLoad();

		clickSubmitButton();
	}

	public void selectBioEngineered() throws Exception {
		if (getBrowserDriver().isElementPresent(withCustomTimeout(byCssSelector(cssBioEngineeredDropDown), Timeout.FIVE_SECONDS_TIMEOUT)))
			getBrowserDriver().selectDropDown(selectDropDownValue(byCssSelector(cssBioEngineeredDropDown), DROPDOWN.VISIBLETEXT.toString(), "bioengineer"));
	}

}
