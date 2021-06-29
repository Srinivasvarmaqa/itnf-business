package com.itt.oms.pages.vendor;

import org.slf4j.LoggerFactory;

import com.itt.factoryhelper.BrowserHelperFactory.DROPDOWN;
import com.itt.oms.datamodelhelper.OMSDataModelHelperFactory;
import com.itt.oms.helper.OMSHelperFactory;

import org.slf4j.Logger;
import static com.itt.factoryhelper.BrowserHelperFactory.getBrowserDriver;
import static com.itt.browser.common.BrowserLocator.*;

public class OMSVendorOrderManagementPage {

	private static final Logger LOG = LoggerFactory.getLogger(OMSVendorOrderManagementPage.class);
	private static String cssVendorMenu = "a[title='Vendor']";
	private static String cssVendorOrderStatusMenu = "a[title='Order Status']";
	private static String cssPONumberlistedLink = "a[href*='details']";
	private static String cssSaveButton = "input[name='Save']";
	private static String cssShippingWareHouseDropDown = "#lineitemsdiv select[name^='pickuplocation']";
	private static String cssSubmitButton = "input[name='MySubmit']";
	
	
	public void selectVendorFromMenu() throws Exception {
		LOG.debug("Switch to Left Frame");
		OMSHelperFactory.switchToLeftFrame();

		LOG.info("Click on Vendor Menu from the left menu");
		getBrowserDriver().click(byCssSelector(cssVendorMenu));

		LOG.info("Click on Vendor menu");
		getBrowserDriver().click(byCssSelector(cssVendorOrderStatusMenu));
	}
	
	public void confirmPurchaseOrder(String poNumber, OMSDataModelHelperFactory omsDataModelHelperFactory) throws Exception {

		String shippingWareHouse = omsDataModelHelperFactory.getOmsDataModelVendorOrderManagement().getShippingwarehouse();
		this.selectVendorFromMenu();
		
		LOG.debug("Switch to Main Frame");
		OMSHelperFactory.switchToMainFrame();

		OMSHelperFactory.lookUpPO(poNumber);
		
		LOG.info("Find the PO number from the list");
		getBrowserDriver().click(byCssSelector(cssPONumberlistedLink));
		this.clickSaveButton();

		if (shippingWareHouse != null) {
			LOG.info("Enter Shippingwarehouse input");
			getBrowserDriver().selectDropDown(selectDropDownValue(byCssSelector(cssShippingWareHouseDropDown), DROPDOWN.VISIBLETEXT.toString(), shippingWareHouse));
		}

		LOG.debug("Switch to Main frame");
		OMSHelperFactory.switchToMainFrame();
		
		LOG.info("Click on Submit button twice");
		this.clickSubmitButton();
		this.clickSubmitButton();

	}
	
	public void clickSaveButton() throws Exception {
		LOG.info("Click Save Button");
		getBrowserDriver().click(byCssSelector(cssSaveButton));
	}

	public void clickSubmitButton() throws Exception {
		LOG.info("Click on Submit Button");
		getBrowserDriver().click(byCssSelector(cssSubmitButton));
	}

}
