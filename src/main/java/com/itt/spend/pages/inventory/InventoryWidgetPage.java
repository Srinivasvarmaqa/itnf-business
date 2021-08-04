package com.itt.spend.pages.inventory;

import static com.itt.browser.common.BrowserLocator.byCssSelector;
import static com.itt.browser.common.BrowserLocator.byId;
import static com.itt.browser.common.BrowserLocator.byXpath;
import static com.itt.factoryhelper.BrowserHelperFactory.getBrowserDriver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itt.spend.helper.SPENDHelperFactory;
import com.itt.spend.pages.common.InsightsMainWindowPage;

public class InventoryWidgetPage {
	
	private static final Logger LOG = LoggerFactory.getLogger(InventoryWidgetPage.class);


	private static String xFrame = "//iframe[@class='ng-star-inserted']";
	private static String xCheckBox = "//p[contains(text(),' SELECT ALL ')]";
	private static String xNext = "//span[contains(text(),'Next')]";
	private static String xDOH = "//mat-radio-button[@id='mat-radio-2']";
	private static String xCreate = "//span[contains(text(),'Create')]";
	private static String cssWidgetLibrarybutton = ".toggle.library";
	private static String cssSelectDC = ".mat-button.mat-button-base.action.action-secondary.ng-star-inserted";
	private static String xUnconfirmedPoWidget = "//span[contains(text(),'Unconfirmed PO’s')]";
	private static String xInventoryWidget="//span[contains(text(),'Inventory')]";
	private static String idClickDCCheckBox = "mat-checkbox-7-input";
	private static String cssClickDCCheckBox1 = ".mat-checkbox-inner-container";

	
	public void clickOnInventoryWidget() throws Exception {
		LOG.debug("Click on Inventory Widget");
		SPENDHelperFactory.waitForloaderToDisapper();
		getBrowserDriver().switchToFrame(byXpath(xFrame));
		SPENDHelperFactory.waitForloaderToDisapper();
		getBrowserDriver().click(byCssSelector(cssWidgetLibrarybutton));
		//getBrowserDriver().getText(byXpath(xInventoryWidget));
		getBrowserDriver().click(byXpath(xInventoryWidget));
	
		
	}
	
	public String selectDcFromSupplyChain(String menu) throws Exception {
		LOG.info("click on DistributionCenter");
		
		getBrowserDriver().click(byCssSelector(cssSelectDC));
		String textmenu=getBrowserDriver().getText(byCssSelector(cssSelectDC));
		SPENDHelperFactory.waitForloaderToDisapper();
		return textmenu;
		
	}
	public String clickDIstributionCenter(String menu) throws Exception {
		LOG.info("click on CheckBox Of DistributionCenter");
	SPENDHelperFactory.waitForloaderToDisapper();

	getBrowserDriver().click(byXpath(xCheckBox));
	getBrowserDriver().click(byXpath(xNext));
		return menu;
	
	}
	public String clickInventoryAlertThreshold(String menu) throws Exception {
		LOG.info("click on Days On Hand Radio Button");
	SPENDHelperFactory.waitForloaderToDisapper();
	getBrowserDriver().click(byXpath(xDOH));
	String textmenu=getBrowserDriver().getText(byXpath(xDOH));
	getBrowserDriver().click(byXpath(xCreate));

		return textmenu;
	
	}
}
