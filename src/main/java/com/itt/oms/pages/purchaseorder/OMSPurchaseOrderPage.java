package com.itt.oms.pages.purchaseorder;

import static com.itt.browser.common.BrowserLocator.byCssSelector;
import static com.itt.browser.common.BrowserLocator.byFrame;
import static com.itt.browser.common.BrowserLocator.byId;
import static com.itt.browser.common.BrowserLocator.byName;
import static com.itt.browser.common.BrowserLocator.byXpath;
import static com.itt.browser.common.BrowserLocator.selectDropDownValue;
import static com.itt.browser.common.BrowserLocator.withSwitchToMainWindow;
import static com.itt.factoryhelper.BrowserHelperFactory.getBrowserDriver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itt.factoryhelper.BrowserHelperFactory.DROPDOWN;
import com.itt.oms.datamodelhelper.OMSDataModelHelperFactory;
import com.itt.oms.helper.OMSHelperFactory;

public class OMSPurchaseOrderPage {

	private static final Logger LOG = LoggerFactory.getLogger(OMSPurchaseOrderPage.class);
	private static String cssBuyerMenu = "a[title='Buyer']";
	private static String cssOMS = "a[title='OMS']";
	private static String cssCreatePO = "a[title='Create PO']";
	private static String nSubmit = "Next";
	private static String idVendorDropDown = "Vendor";
	private static String xModeDropDown = "//select[@name = 'mode']";
	private static String xRoutingDropDown = "//select[@name='RoutingType']";
	private static String nTemplateDropDown = "Template";
	private static String xShipToDropDown = "//select[@name='DeliveryPoint']";
	private static String cssCustomerOrder = "a[title='Customer Order']";
	private static final String OMS = "oms";
	private static final String CUSTOMER_ORDER = "Customer Order";

	public void openBuyerMenu() throws Exception {

		LOG.debug("Switch to Left Frame");
		OMSHelperFactory.switchToLeftFrame();

		LOG.info("Click on Buyer Menu from the left menu");
		getBrowserDriver().click(byCssSelector(cssBuyerMenu));
	}

	public void openOMSMenu() throws Exception {
		LOG.debug("Switch to Left Frame");
		OMSHelperFactory.switchToLeftFrame();
		LOG.info("Click on OMS menu");
		getBrowserDriver().click(byCssSelector(cssOMS));
		LOG.debug("Switch to Main frame");
		OMSHelperFactory.switchToMainFrame();
		int i=0;
		while(i < 60) {
			try {
				getBrowserDriver().switchToFrame(withSwitchToMainWindow(byFrame("frameSP"), false));
				break;
			} catch (Exception e) {
				LOG.debug("Retring until Frame found");
				Thread.sleep(1000);
			}
		}
	}

	public void createDirectPurchaseOrder(OMSDataModelHelperFactory omsDataModelHelperFactory) throws Exception {

		String vendor = omsDataModelHelperFactory.getOmsDataModelPurchaseOrder().getVendor();
		String shipTo = omsDataModelHelperFactory.getOmsDataModelPurchaseOrder().getShipToWarehouse();
		String routing = omsDataModelHelperFactory.getOmsDataModelPurchaseOrder().getRouting();
		String mode = omsDataModelHelperFactory.getOmsDataModelPurchaseOrder().getMode();
		String templateName = omsDataModelHelperFactory.getOmsDataModelPurchaseOrder().getTemplatename();

		LOG.info("Open Buyer Menu");
		this.openBuyerMenu();

		LOG.info("Click on OMS menu");
		getBrowserDriver().click(byCssSelector(cssOMS));

		LOG.debug("Switch to Header frame");
		OMSHelperFactory.switchToHeaderFrame();

		LOG.info("Click PO direct");
		getBrowserDriver().click(byCssSelector(cssCreatePO));

		LOG.debug("Switch to Main frame");
		OMSHelperFactory.switchToMainFrame();

		LOG.info("Enter the PO input values");
		getBrowserDriver().selectDropDown(selectDropDownValue(byXpath(xShipToDropDown),DROPDOWN.VISIBLETEXT.toString(), shipTo));
		getBrowserDriver().selectDropDown(selectDropDownValue(byXpath(xRoutingDropDown),DROPDOWN.VALUE.toString(), routing));
		getBrowserDriver().selectDropDown(selectDropDownValue(byXpath(xModeDropDown),DROPDOWN.VALUE.toString(), mode));
		getBrowserDriver().selectDropDown(selectDropDownValue(byName(nTemplateDropDown),DROPDOWN.VISIBLETEXT.toString(), templateName));
		getBrowserDriver().selectDropDown(selectDropDownValue(byId(idVendorDropDown),DROPDOWN.VISIBLETEXT.toString(), vendor));

		LOG.info("Click Create Order Button");
		getBrowserDriver().click(byName(nSubmit));
	}

}
