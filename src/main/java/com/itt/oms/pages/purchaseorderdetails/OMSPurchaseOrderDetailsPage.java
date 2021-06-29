
package com.itt.oms.pages.purchaseorderdetails;

import static com.itt.browser.common.BrowserLocator.*;
import static com.itt.factoryhelper.BrowserHelperFactory.getBrowserDriver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itt.common.Timeout;
import com.itt.factoryhelper.BrowserHelperFactory.DROPDOWN;
import com.itt.oms.datamodelhelper.OMSDataModelHelperFactory;
import com.itt.oms.helper.OMSHelperFactory;

public class OMSPurchaseOrderDetailsPage {

	private static final Logger LOG = LoggerFactory.getLogger(OMSPurchaseOrderDetailsPage.class);
	private static String xQuantity = "//div[@id='lineitemsdiv']/table/tbody/tr[2]/td[4]//td[1]//input";
	private static String cssQuantity = "input[name^='quantity']";
	private static String xPrice = "//div[@id='lineitemsdiv']/table/tbody/tr[2]/td[5]//input";
	private static String cssPrice = "input[name^='price']";
	private static String nSubmit = "MySubmit";
	private static String cssVendorDropDownLobLaws = "select[name^='vendor']";
	private static String cssVendorDropDown = "#lineitemsdiv select[name^='vendor']";
	private static String nRoutingType = "RoutingType";
	private static String nCustfrtCostTextBox = "custfrt";
	private static String cssVendorTopcoLink ="#lineitemsdiv a[href*='po_details.cfm']";
	private static String xVendorPONumber = "//div[@id='headerinfodiv']//font[contains(text(), 'Vendor PO')]/following-sibling::font";
    private static String xCustomerQtyText = "//*[@id='lineitemsdiv']/table/tbody//font[contains(text(), 'Customer Qty/Price')]/following-sibling::font[1]";
    private static String xCustomerPriceText = "//*[@id='lineitemsdiv']/table/tbody//font[contains(text(), 'Customer Qty/Price')]/following-sibling::font[2]";
    private static String xBrokerQtyText = "//*[@id='lineitemsdiv']/table/tbody//font[contains(text(), 'Broker Qty')]/following-sibling::font[1]";
    private static String xBrokerPriceText = "//*[@id='lineitemsdiv']/table/tbody//font[contains(text(), 'Broker Qty')]/following-sibling::input";
    private static String idXdockDialog = "ModalContainerDivDialog";
    private static String xMONumber = "//div[@id='headerinfodiv']//font[contains(text(), 'PO Number')]/font";
    private static String idCustomerReferenceNumber = "conum";


	public void submitPurchaseOrder(OMSDataModelHelperFactory omsDataModelHelperFactory) throws Exception {

		int quantity = omsDataModelHelperFactory.getOmsDataModelPurchaseOrderDetails().getQuantity();
		double price = omsDataModelHelperFactory.getOmsDataModelPurchaseOrderDetails().getPrice();
		String vendorname = omsDataModelHelperFactory.getOmsDataModelPurchaseOrderDetails().getVendorname();
		String routing = omsDataModelHelperFactory.getOmsDataModelPurchaseOrderDetails().getRouting();

		LOG.debug("Switch to Main frame");
		OMSHelperFactory.switchToMainFrame();

		if (quantity != 0) {
			LOG.info("Enter the Quantity");
			getBrowserDriver().sendValue(withText(withClearOption(byCssSelector(cssQuantity), true), Integer.toString(quantity)));
		}

		if(price != 0) {
			LOG.info("Enter the Price");
			getBrowserDriver().sendValue(withText(withClearOption(byCssSelector(cssPrice), true), Double.toString(price)));
		}

		LOG.info("Enter the vendor name");
		getBrowserDriver().selectDropDown(selectDropDownValue(byCssSelector(cssVendorDropDown), DROPDOWN.VISIBLETEXT.toString(), vendorname));

		LOG.info("Enter the routing");
		getBrowserDriver().selectDropDown(selectDropDownValue(byName(nRoutingType), DROPDOWN.VISIBLETEXT.toString(), routing));
		
		LOG.info("Submit the Purchase Order");
		getBrowserDriver().click(byName(nSubmit));
		
		if (!getBrowserDriver().isElementPresent(withCustomTimeout(byCssSelector(idXdockDialog), Timeout.TWO_SECONDS_TIMEOUT))) {
			LOG.info("Click on submit button");
			clickOnSubmitButton();
		}
	}
	
	public String getCustomerQuantity() throws Exception {
		OMSHelperFactory.switchToMainFrame();
	    return getBrowserDriver().getText(byXpath(xCustomerQtyText));
	}
	
	public String getCustomerPrice() throws Exception {
		OMSHelperFactory.switchToMainFrame();
		String customerprice = getBrowserDriver().getText(byXpath(xCustomerPriceText));
		customerprice = customerprice.replaceAll("\\/", "");
		customerprice = customerprice.trim();
		LOG.info("Customer Price value is:" + customerprice);
		return customerprice;
	}

	public String getBrokerQuantity() throws Exception {
		OMSHelperFactory.switchToMainFrame();
		return getBrowserDriver().getText(byXpath(xBrokerQtyText));
	}
	
	public String getBrokerPrice() throws Exception {
		OMSHelperFactory.switchToMainFrame();
		return getBrowserDriver().getAttributeValue(withAttributeName(byXpath(xBrokerPriceText), "value"));
	}
	
	public void addCustfrtCost(OMSDataModelHelperFactory omsDataModelHelperFactory) throws Exception {
		String custfrtcost = omsDataModelHelperFactory.getOmsDataModelPurchaseOrderDetails().getCustfrtcost();
		if(custfrtcost != null) {
			LOG.info("Enter the custfrtcost");
			getBrowserDriver().sendValue(withText(byName(nCustfrtCostTextBox), custfrtcost));
		}
	}
	
	public void clickOnSubmitButton() throws Exception {
		LOG.info("Submit the Purchase Order");
		getBrowserDriver().click(byName(nSubmit));
	}

	public void clickVendorlinktopco() throws Exception {
	    getBrowserDriver().click(byCssSelector(cssVendorTopcoLink));
	}
	
	public String getVendorPOForTopco() throws Exception {
		LOG.info("test,,," + getBrowserDriver().getText(byXpath(xVendorPONumber)));
	    return getBrowserDriver().getText(byXpath(xVendorPONumber));
	}
	
	public String getMONumber() throws Exception {
		LOG.info("Get MO Number");
		return getBrowserDriver().getText(byXpath(xMONumber));
	}

	public String getCustomerReferenceNumber() throws Exception {
		LOG.info("Get Customer reference Number");
	    return getBrowserDriver().getAttributeValue(withAttributeName(byId(idCustomerReferenceNumber), "value"));
	}
}
