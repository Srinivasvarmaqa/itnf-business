package com.itt.oms.pages.masterorder;

import static com.itt.browser.common.BrowserLocator.*;
import static com.itt.factoryhelper.BrowserHelperFactory.getBrowserDriver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itt.common.Timeout;
import com.itt.factoryhelper.BrowserHelperFactory.DROPDOWN;
import com.itt.oms.datamodelhelper.OMSDataModelHelperFactory;
import com.itt.oms.helper.OMSHelperFactory;

public class OMSMasterPurchaseOrderPage {

	private static final Logger LOG = LoggerFactory.getLogger(OMSMasterPurchaseOrderPage.class);
	private static String cssBuyerMenu = "a[title='Buyer']";
	private static String cssOMS = "a[title='OMS']";
	private static String cssCustomerOrder = "a[title='Customer Order']";
	private static String cssCreateMO = "a[title='Create MO']";
	private static String xModeDropDown = "//select[@name = 'mode']";
	private static String xRoutingDropDown = "//select[@name='RoutingType']";
	private static String xGroupDropDown = "//select[@name='group']";
	private static String xInvoiceDropDown = "//select[@name='InvoiceType']";
	private static String nTemplateDropDown = "Template";
	private static String nCustomerDropDown = "Customers";
	private static String xShipToDropDown = "//select[@name='DeliveryPoint']";
	private static String nSubmit = "Next";
	private static String nCustomerRefNumTextBox = "conum";
	private static final String OMS = "oms";
	private static final String CUSTOMER_ORDER = "Customer Order";

	public void openBuyerMenu() throws Exception {

		LOG.debug("Switch to Left Frame");
		OMSHelperFactory.switchToLeftFrame();

		LOG.info("Click on Buyer Menu from the left menu");
		getBrowserDriver().click(byCssSelector(cssBuyerMenu));
	}

	public void clickOnMenu(String menu) throws Exception {
		switch (menu) {
			case OMS :
				getBrowserDriver().click(byCssSelector(cssOMS));
				break;
			case CUSTOMER_ORDER:
				getBrowserDriver().click(byCssSelector(cssCustomerOrder));
				break;
			default :
				LOG.error("Error! unknown option " + menu);
				throw new Exception("Incorrect menu name");
		}
	}
	
	public boolean isCreateMoTabPresent() throws Exception{
		return getBrowserDriver().waitForElement(withCustomTimeout(byCssSelector(cssCreateMO), Timeout.THIRTY_SECONDS_TIMEOUT));
	}

	public void createMasterPurchaseOrder(OMSDataModelHelperFactory omsDataModelHelperFactory) throws Exception {
		String group = omsDataModelHelperFactory.getOmsDataModelMasterPurchaseOrder().getGroup();
		String shipTo = omsDataModelHelperFactory.getOmsDataModelMasterPurchaseOrder().getShipToWarehouse();
		String routing = omsDataModelHelperFactory.getOmsDataModelMasterPurchaseOrder().getRouting();
		String mode = omsDataModelHelperFactory.getOmsDataModelMasterPurchaseOrder().getMode();
		String templateName = omsDataModelHelperFactory.getOmsDataModelMasterPurchaseOrder().getTemplatename();
		String invoiceFrom = omsDataModelHelperFactory.getOmsDataModelMasterPurchaseOrder().getInvoiceFrom();
		String customer = omsDataModelHelperFactory.getOmsDataModelMasterPurchaseOrder().getCustomer();

		LOG.info("Open Buyer Menu");
		this.openBuyerMenu();
		
		LOG.info("Click on Customer Order menu");
		getBrowserDriver().click(byCssSelector(cssCustomerOrder));

		LOG.debug("Switch to Header frame");
		OMSHelperFactory.switchToHeaderFrame();

		if(this.isCreateMoTabPresent()) {
			try {
				getBrowserDriver().click(byCssSelector(cssCreateMO));
			} catch (Exception e) {
				LOG.info("TEST");
			}
		} else {
			LOG.debug("Cannot find Create MO menu. programatically setting the content of mainFrame");
			OMSHelperFactory.switchToMainFrame();
			getBrowserDriver().executeJavaScript("document.getElementsByName ('mainFrame')[0].contentWindow.location = 'buy/mo_indirect_create.cfm?';");
		}

		LOG.debug("Switch to Main Frame");
		OMSHelperFactory.switchToMainFrame();

		LOG.info("Enter the PO input values");
	    getBrowserDriver().selectDropDown(selectDropDownValue(byXpath(xModeDropDown), DROPDOWN.VALUE.toString(), mode));
	    getBrowserDriver().selectDropDown(selectDropDownValue(byXpath(xGroupDropDown),DROPDOWN.VISIBLETEXT.toString(), group));
	    getBrowserDriver().selectDropDown(selectDropDownValue(byXpath(xRoutingDropDown), DROPDOWN.VISIBLETEXT.toString(), routing));
	    getBrowserDriver().selectDropDown(selectDropDownValue(byName(xInvoiceDropDown),DROPDOWN.VISIBLETEXT.toString(), invoiceFrom));
	    getBrowserDriver().selectDropDown(selectDropDownValue(byName(nTemplateDropDown),DROPDOWN.VISIBLETEXT.toString(), templateName));
	    getBrowserDriver().selectDropDown(selectDropDownValue(byName(nCustomerDropDown),DROPDOWN.VISIBLETEXT.toString(), customer));
	    getBrowserDriver().selectDropDown(selectDropDownValue(byXpath(xShipToDropDown),DROPDOWN.VISIBLETEXT.toString(), shipTo));

	    if (omsDataModelHelperFactory.getProject().toLowerCase().equals("topco")) {
	    	this.enterOCnumber();
	    }

		LOG.info("Click Create Order Button");
		this.clickCreateOrderButton();
	}

	public void clickCreateOrderButton() throws Exception {
		getBrowserDriver().click(byName(nSubmit));
	}

	public void enterOCnumber() throws Exception {
		long randomNumber = (long) Math.floor(Math.random() * 900000000L) + 10000000L;
		LOG.info("Add the OC number:" + randomNumber);
		getBrowserDriver().sendValue(withText(byName(nCustomerRefNumTextBox), "Auto" + randomNumber));
	}
}
