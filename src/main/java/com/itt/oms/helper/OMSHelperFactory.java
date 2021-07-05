package com.itt.oms.helper;

import org.slf4j.LoggerFactory;

import com.itt.common.Timeout;
import com.itt.factoryhelper.BrowserHelperFactory.DROPDOWN;
import com.itt.oms.pages.common.MainWindowPage;
import com.itt.oms.pages.invoice.OMSSubmitInvoicePage;
import com.itt.oms.pages.loadstatus.OMSLoadStatusPage;
import com.itt.oms.pages.login.OMSLoginPage;
import com.itt.oms.pages.masterorder.OMSMasterPurchaseOrderPage;
import com.itt.oms.pages.navigation.OMSMenuNavigationPage;
import com.itt.oms.pages.purchaseorder.OMSPurchaseOrderPage;
import com.itt.oms.pages.purchaseorderdetails.OMSPurchaseOrderDetailsPage;
import com.itt.oms.pages.receiveorder.OMSReceiveOrderPage;
import com.itt.oms.pages.shipmentorder.OMSShipmentOrderPage;
import com.itt.oms.pages.tmsneworders.TMSNewOrderPage;
import com.itt.oms.pages.vendor.OMSVendorOrderManagementPage;
import com.itt.oms.pages.xdock.OMSXdockPage;

import static com.itt.factoryhelper.BrowserHelperFactory.getBrowserDriver;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.itt.browser.common.BrowserLocator.*;

import org.openqa.selenium.NoSuchWindowException;
import org.slf4j.Logger;

public class OMSHelperFactory {

	private static final Logger LOG = LoggerFactory.getLogger(OMSHelperFactory.class);

	private static OMSHelperFactory oMSHelperFactory = new OMSHelperFactory();
	private OMSLoginPage oMSLoginPage = new OMSLoginPage();
	private MainWindowPage mainWindowPage = new MainWindowPage();
	private OMSPurchaseOrderPage oMSPurchaseOrderPage = new OMSPurchaseOrderPage();
	private OMSPurchaseOrderDetailsPage oMSPurchaseOrderDetailsPage = new OMSPurchaseOrderDetailsPage();
	private OMSReceiveOrderPage oMSReceiveOrderPage = new OMSReceiveOrderPage();
	private OMSShipmentOrderPage oMSShipmentOrderPage = new OMSShipmentOrderPage();
	private OMSVendorOrderManagementPage oMSVendorOrderManagementPage = new OMSVendorOrderManagementPage();
	private OMSSubmitInvoicePage oMSSubmitInvoicePage = new OMSSubmitInvoicePage();
	private OMSMasterPurchaseOrderPage oMSMasterPurchaseOrderPage = new OMSMasterPurchaseOrderPage();
	private OMSXdockPage oMSXdockPage = new OMSXdockPage();
	private TMSNewOrderPage tMSNewOrderPage = new TMSNewOrderPage();
	private OMSLoadStatusPage oMSLoadStatusPage = new OMSLoadStatusPage();
	private OMSMenuNavigationPage oMSMenuNavigationPage = new OMSMenuNavigationPage();
	private static final String cssHeaderFrame = "frame[name='headerFrame']";
	private static final String cssLogoutButton = "a[href*='logout.cfm']";
	private static final String leftFrameName = "leftFrame";
	private static final String headerFrameName = "headerFrame";
	private static final String mainFrameName = "mainFrame";
	private static final String xPONumberText = "//div[@id='headerinfodiv']//font[text()='PO Number']//following-sibling::font";
	private static final String cssPONumberInput = "input[name='ponum']";
	private static final String idOrderStatus = "TitleStatusDiv";
	private static final String nPONumberSearchTextBox = "txtsearch";
	private static final String nButtonSearch = "search";
	private static final String cssButtonSearch = "input[name='search' i]";
	private static final String cssPONumberlistedLink = "a[href*='receive_details']";
	private static final String cssSearchDropDown = "select[name='SearchPOSO']";
	private static final String cssOrderInvoiceStatus = "#headerinfodiv > table td:nth-child(2) font:nth-child(2)";
	private static final String xPONumberLink = "//a[@href='%s']";
	private static final String iDUserName = "UserName";
	private static String project;

	public String getProject() {
		return this.project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public OMSLoginPage getOMSLoginPage() {
		return this.oMSLoginPage;
	}

	public MainWindowPage getmainWindowPage() {
		return this.mainWindowPage;
	}

	public OMSPurchaseOrderPage getoMSPurchaseOrderPage() {
		return this.oMSPurchaseOrderPage;
	}

	public OMSPurchaseOrderDetailsPage getoMSPurchaseOrderDetailsPage() {
		return this.oMSPurchaseOrderDetailsPage;
	}

	public OMSReceiveOrderPage getoMSReceiveOrderPage() {
		return this.oMSReceiveOrderPage;
	}

	public OMSShipmentOrderPage getoMSShipmentOrderPage() {
		return this.oMSShipmentOrderPage;
	}

	public OMSVendorOrderManagementPage getoMSVendorOrderManagementPage() {
		return this.oMSVendorOrderManagementPage;
	}

	public OMSSubmitInvoicePage getoMSSubmitInvoicePage() {
		return this.oMSSubmitInvoicePage;
	}

	public OMSMasterPurchaseOrderPage getOmsMasterPurchaseOrderPage() {
		return this.oMSMasterPurchaseOrderPage;
	}

	public OMSXdockPage getOmsXdockPage( ) {
		return this.oMSXdockPage;
	}

	public TMSNewOrderPage gettMSNewOrderPage() {
		return this.tMSNewOrderPage;
	}

	public OMSLoadStatusPage getOmsLoadStatusPage() {
		return this.oMSLoadStatusPage;
	}

	public OMSMenuNavigationPage getoMSMenuNavigationPage() {
		return this.oMSMenuNavigationPage;
	}

	public boolean hasErrorMessage() throws Exception {
		boolean hasError = false;
		OMSHelperFactory.switchToMainFrame();
		String pagesource = getBrowserDriver().getPageSource();
		if (pagesource.contains("Error Occurred While Processing Request"))
			hasError = true;
		else if (pagesource.contains("unexpected error"))
			hasError = true;
		else if (pagesource.contains("Not Found"))
			hasError = true;
		else if (pagesource.contains("Internal Server Error"))
			hasError = true;
		else if (getBrowserDriver().getPageTitle().contains("404"))
			hasError = true;
		return hasError;
	}

	public void logout() throws Exception {
		LOG.info("LOGOUT FROM OMS");
		getBrowserDriver().switchToFrame(byFrame(headerFrameName));
		getBrowserDriver().click(byCssSelector(cssLogoutButton));
		getBrowserDriver().waitForElement(byId(iDUserName));
	}

	public String getPONumber() throws Exception {
		String poNumber = null;
		try {
			if (getBrowserDriver().isElementPresent(byCssSelector(cssPONumberInput))) {
				poNumber = getBrowserDriver().getAttributeValue(withAttributeName(byCssSelector(cssPONumberInput), "value"));
			} else {
				poNumber = getBrowserDriver().getText(byXpath(xPONumberText));
			}
		} catch (Exception e) {
			poNumber = getBrowserDriver().getAttributeValue(withAttributeName(byXpath(xPONumberText), "value"));
		}
		return poNumber;
	}

	public String getOrderStatus() throws Exception {
		OMSHelperFactory.switchToMainFrame();
		if (getBrowserDriver().waitForElement(withCustomTimeout(byId(idOrderStatus), Timeout.TEN_SECONDS_TIMEOUT))) {
			return getBrowserDriver().getText(byId(idOrderStatus));
		} else if (getBrowserDriver().isElementPresent(byCssSelector(cssOrderInvoiceStatus))) {
			return getBrowserDriver().getText(byCssSelector(cssOrderInvoiceStatus));
		} else {
			return null;
		}
	}

	public void closeAllChildWindowPopups() throws Exception {
		getBrowserDriver().closeAllChildWindowPopups();
	}

	public static void lookUpPO(String poNumber) throws Exception {
		LOG.info("Look for poNumber - " + poNumber);
		if (getBrowserDriver().waitForElement(withCustomTimeout(byCssSelector(cssSearchDropDown), Timeout.THREE_SECONDS_TIMEOUT))) {
			getBrowserDriver().selectDropDown(selectDropDownValue(byCssSelector(cssSearchDropDown), DROPDOWN.VALUE.toString(), "PO"));
		}
		getBrowserDriver().sendValue(withText(byName(nPONumberSearchTextBox), poNumber));
		getBrowserDriver().click(byCssSelector(cssButtonSearch));
	}

	public static void switchToLeftFrame() throws Exception {
		getBrowserDriver().switchToFrame(byFrame(leftFrameName));
	}

	public static void switchToMainFrame() throws Exception {
		getBrowserDriver().switchToFrame(byFrame(mainFrameName));
	}

	public static void switchToHeaderFrame() throws Exception {
		getBrowserDriver().switchToFrame(byFrame(headerFrameName));
	}

	public static String getDate() {
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy ");
		Date date = new Date();
		return dateFormat.format(date);
	}
}
