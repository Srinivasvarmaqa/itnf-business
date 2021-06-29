package com.itt.oms.pages.invoice;

import org.slf4j.LoggerFactory;

import com.itt.factoryhelper.BrowserHelperFactory.ALERT;
import com.itt.oms.helper.OMSHelperFactory;

import org.slf4j.Logger;
import static com.itt.factoryhelper.BrowserHelperFactory.getBrowserDriver;
import static com.itt.browser.common.BrowserLocator.*;

public class OMSSubmitInvoicePage {

	private static final Logger LOG = LoggerFactory.getLogger(OMSSubmitInvoicePage.class);
	private static String xAccountingMenu = "//a[@title='Accounting']";
	private static String cssInvoiceStatusMenu = "a[title*='Invoice']";
	private static String cssPONumberlistedLink = "a[href*='invoice_s_bs.cfm?']";
	private static String xshipDateText = "//*[@id='headerinfodiv']/table[2]/tbody/tr[1]/td[5]/font[2]";
	private static String idInvoiceDate = "FInvoiceDate";
	private static String cssSaveButton = "input[name='Save']";
	private static String cssSubmitButton = "input[name^='MySubmit']";
	private static String cssOrderInvoiceStatus = "#headerinfodiv > table td:nth-child(2) font:nth-child(2)";

	
	public void openInvoiceMenu() throws Exception {
		LOG.debug("Switch to Left Frame");
		OMSHelperFactory.switchToLeftFrame();

		LOG.info("Click on Shipping Menu from the left menu");
		getBrowserDriver().click(byXpath(xAccountingMenu));

		LOG.info("Click on Vendor menu");
		getBrowserDriver().click(byCssSelector(cssInvoiceStatusMenu));
	}

	public void submitInvoiceOrder(String poNumber) throws Exception {
		this.openInvoiceMenu();

		LOG.debug("Switch to Main Frame");
		OMSHelperFactory.switchToMainFrame();

		OMSHelperFactory.lookUpPO(poNumber);

		LOG.info("Find the PO number from the list");
		getBrowserDriver().click(byCssSelector(cssPONumberlistedLink));

		String shipdate = this.getTxtShipDate();
		LOG.info("The shipdate on invoice submission page is  " + shipdate);
		this.setTxtInvoiceDate(shipdate);

		LOG.info("Click on Submit Button");
		try {
			this.clickSubmitButton();
		} catch (Exception e) {
			LOG.info("Alert pop-up is found");
		}

		try {
			getBrowserDriver().handleAlerts(withAlertAction(ALERT.ACCEPT.toString()));
		} catch (Exception e) {
			LOG.info("Alert Pop-up is not found");
		}
		getBrowserDriver().waitForPageLoad();
	}

	public void clickSaveButton() throws Exception {
		LOG.info("Click Save Button");
		getBrowserDriver().click(byCssSelector(cssSaveButton));
	}

	public void clickSubmitButton() throws Exception {
		LOG.info("Click on Submit Button");
		getBrowserDriver().click(byCssSelector(cssSubmitButton));
	}

	public String getTxtShipDate() throws Exception {
	    return getBrowserDriver().getText(byXpath(xshipDateText));
	}

	public void setTxtInvoiceDate(String shipDate) throws Exception {
		getBrowserDriver().sendValue(withText(byId(idInvoiceDate), shipDate));
	}
}
