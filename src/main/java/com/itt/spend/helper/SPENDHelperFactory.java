package com.itt.spend.helper;

import static com.itt.browser.common.BrowserLocator.byCssSelector;
import static com.itt.browser.common.BrowserLocator.byFrame;
import static com.itt.browser.common.BrowserLocator.byId;
import static com.itt.browser.common.BrowserLocator.byName;
import static com.itt.browser.common.BrowserLocator.byXpath;
import static com.itt.browser.common.BrowserLocator.selectDropDownValue;
import static com.itt.browser.common.BrowserLocator.withAttributeName;
import static com.itt.browser.common.BrowserLocator.withCustomTimeout;
import static com.itt.browser.common.BrowserLocator.withSwitchToMainWindow;
import static com.itt.browser.common.BrowserLocator.withText;
import static com.itt.browser.common.BrowserLocator.withWaitForVisibility;
import static com.itt.factoryhelper.BrowserHelperFactory.getBrowserDriver;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itt.common.Timeout;
import com.itt.factoryhelper.BrowserHelperFactory.DROPDOWN;
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
import com.itt.spend.pages.common.InsightsMainWindowPage;
import com.itt.spend.pages.inventory.InventoryWidgetPage;
import com.itt.spend.pages.login.SPENDLoginPage;
import com.itt.spend.pages.top10.Top10WidgetPage;

public class SPENDHelperFactory {

	private static final Logger LOG = LoggerFactory.getLogger(SPENDHelperFactory.class);

	private static SPENDHelperFactory sPENDHelperFactory = new SPENDHelperFactory();
	
	private SPENDLoginPage sPENDLoginPage = new SPENDLoginPage();
	private InsightsMainWindowPage insightsmainWindowPage = new InsightsMainWindowPage();
	private InventoryWidgetPage invenotrywidgetPage = new InventoryWidgetPage();
	private Top10WidgetPage top10widgetPage = new Top10WidgetPage();

	private static final String leftFrameName = "leftFrame";
	private static final String headerFrameName = "headerFrame";
	private static final String mainFrameName = "mainFrame";
	private static String cssLoader = "mat-progress-bar.spend.mat-progress-bar";
	private static String project;

	public String getProject() {
		return this.project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public SPENDLoginPage getSPENDLoginPage() {
		return this.sPENDLoginPage;
	}
	public InsightsMainWindowPage getinsightsMainWindowPage() {
		return this.insightsmainWindowPage;
	}
	public InventoryWidgetPage getinventoryWidgetPage() {
		return this.invenotrywidgetPage;
	}
	public Top10WidgetPage gettop10WidgetPage() {
		return this.top10widgetPage;
	}
	
	public boolean hasErrorMessage() throws Exception {
		boolean hasError = false;
		SPENDHelperFactory.switchToMainFrame();
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



public static void waitForloaderToDisapper() throws Exception {
	int count = 0;
	getBrowserDriver().waitForElement(withCustomTimeout(byCssSelector(cssLoader), Timeout.FIVE_SECONDS_TIMEOUT));
	getBrowserDriver().waitForElement(withWaitForVisibility(byCssSelector(cssLoader), "false"));
	while(getBrowserDriver().findElements(byCssSelector(cssLoader)).size()!=0 && count<60) {
		LOG.info("WAIT FOR LOADER TO DISAPPER - " + count);
		Thread.sleep(1000);
		count++;
	}
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
