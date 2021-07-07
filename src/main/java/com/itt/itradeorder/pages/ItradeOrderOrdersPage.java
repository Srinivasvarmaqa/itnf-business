package com.itt.itradeorder.pages;

import static com.itt.browser.common.BrowserLocator.byClass;
import static com.itt.browser.common.BrowserLocator.byCssSelector;
import static com.itt.browser.common.BrowserLocator.byId;
import static com.itt.browser.common.BrowserLocator.byName;
import static com.itt.browser.common.BrowserLocator.byXpath;
import static com.itt.browser.common.BrowserLocator.withClearOption;
import static com.itt.browser.common.BrowserLocator.withText;
import static com.itt.browser.common.BrowserLocator.withWaitForVisibility;
import static com.itt.factoryhelper.BrowserHelperFactory.getBrowserDriver;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itt.itradeorder.datamodelhelper.ItradeOrderDataModelHelperFactory;
import com.itt.itradeorder.datamodelhelper.ItradeOrderDataModelProducts;
import com.itt.itradeorder.helper.ItradeOrderHelperFactory;

public class ItradeOrderOrdersPage {
	private static final Logger LOG = LoggerFactory.getLogger(ItradeOrderOrdersPage.class);
	private static String classSearchIcon = "itn-icon-search";
	private static String cssSearchInputText = ".search-toolbar div.search-input > input";
	private static String xSearchButton = "//span[@class='itn-icon-search']";
	private static String xSearchInput = "//input[@placeholder='Search']";

	public void searchPO(String poNumber) throws Exception {
		LOG.info("Look for PO Number:" + poNumber);
		ItradeOrderHelperFactory.waitForloaderToDisapper();
		getBrowserDriver().click(byClass(classSearchIcon));
		ItradeOrderHelperFactory.waitForloaderToDisapper();
		getBrowserDriver().sendValue(withText(byCssSelector(cssSearchInputText), poNumber));
	}
	
	public void RefreshPO(String poNumber) throws Exception {
		LOG.info("Look for PO Number:" + poNumber);
		getBrowserDriver().sendValue(withText(withClearOption(byCssSelector(cssSearchInputText), true), poNumber));
	}

	public String getPOStatus(String poNumber) throws Exception {
		String xPOStatus = String.format(" //span[contains(text(), '%s')]/ancestor::div[contains(@class, 'wj-row')]/div[contains(@class,'wj-cell')]/following-sibling::div//div[@class='status-badge']", poNumber);
		return getBrowserDriver().getText(byXpath(xPOStatus)).trim();
	}

	public void searchPOOrder(String ponumber) throws Exception {
		LOG.info("Click on Search Button");
		getBrowserDriver().click(byXpath(xSearchButton));
		LOG.info("Enter PO Number");
		getBrowserDriver().sendValue(withText(byXpath(xSearchInput),ponumber));
	}
}
