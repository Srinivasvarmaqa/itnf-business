package com.itt.itradeorder.pages;

import static com.itt.browser.common.BrowserLocator.byCssSelector;
import static com.itt.browser.common.BrowserLocator.byXpath;
import static com.itt.browser.common.BrowserLocator.withClearOption;
import static com.itt.browser.common.BrowserLocator.withCustomTimeout;
import static com.itt.browser.common.BrowserLocator.withText;
import static com.itt.factoryhelper.BrowserHelperFactory.getBrowserDriver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itt.common.Timeout;
import com.itt.itradeorder.helper.ItradeOrderHelperFactory;
import com.itt.itradeorder.pages.ItradeOrderLoginPage.USER;

public class ItradeOrderOrdersPage {
	private static final Logger LOG = LoggerFactory.getLogger(ItradeOrderOrdersPage.class);
	private static String cssSearchInputText = ".search-toolbar div.search-input > input";
	private static String xSearchButton = "//span[@class='itn-icon-search']";
	private static String xSearchInput = "//input[@placeholder='Search']";
	private static String cssSearchButton = "span.mat-button-wrapper span.itn-icon-search";
	private static String xNewOrdersCount = "//div[contains(text(), 'New')]/following-sibling::div";
	private static String xAllOrdersCount = "//div[contains(text(), 'All')]/following-sibling::div";
	private static String xUpdatesCount = "//div[contains(text(), 'Updates')]/following-sibling::div";
	private static String xAlertsCount = "//div[contains(text(), 'Alerts')]/following-sibling::div";
	private static String xCommentsCount = "//div[contains(text(), 'Comments')]/following-sibling::div";
	private static String xInProgressCount = "//div[contains(text(), 'In Progress')]/following-sibling::div";
	private static String cssOrdersPage = "div.menu-item.ng-star-inserted span.mi-icon.itn-icon-order";
	private static String cssLoader = "div.loader-overlay";
	
	public void searchPO(String poNumber) throws Exception {
		LOG.info("Look for PO Number:" + poNumber);
		ItradeOrderHelperFactory.waitForloaderToDisapper();
		if (getBrowserDriver().findElements(byCssSelector(cssLoader)).size()!=0) {
			getBrowserDriver().click(byCssSelector(cssSearchButton));
		} 
		getBrowserDriver().sendValue(withText(byCssSelector(cssSearchInputText), poNumber));
	}
	
	public void RefreshPO(String poNumber) throws Exception {
		ItradeOrderHelperFactory.waitForloaderToDisapper();
		getBrowserDriver().sendValue(withText(withClearOption(byCssSelector(cssSearchInputText), true), poNumber));
	}

	public String getPOStatus(String poNumber) throws Exception {
		String xPOStatus = String.format(" //span[contains(text(), '%s')]/ancestor::div[contains(@class, 'wj-row')]/div[contains(@class,'wj-cell')]/following-sibling::div//div[@class='status-badge']", poNumber);
		return getBrowserDriver().getText(byXpath(xPOStatus)).trim();
	}

	public void searchPOOrder(String ponumber) throws Exception {
		LOG.debug("Click on Search Button");
		getBrowserDriver().click(byXpath(xSearchButton));
		LOG.debug("Enter PO Number");
		getBrowserDriver().sendValue(withText(byXpath(xSearchInput),ponumber));
	}
	
	public Integer getAllOrdersCount() throws Exception {
		LOG.info("Get All Orders Count");	
		return Integer.parseInt(getBrowserDriver().getText(byXpath(xAllOrdersCount)).trim());
	}
	
	public Integer getNewOrdersCount() throws Exception {
		LOG.info("Get New Orders Count");	
		return Integer.parseInt(getBrowserDriver().getText(byXpath(xNewOrdersCount)).trim());
	}
	
	public Integer getAlertsCount() throws Exception {
		LOG.info("Get Alerts Count");	
		return Integer.parseInt(getBrowserDriver().getText(byXpath(xAlertsCount)).trim());
	}
	
	public Integer getCommentsCount() throws Exception {
		LOG.info("Get Comments Count");	
		return Integer.parseInt(getBrowserDriver().getText(byXpath(xCommentsCount)).trim());
	}
	
	public Integer getInProgressCount() throws Exception {
		LOG.info("Get InProgress Count");	
		return Integer.parseInt(getBrowserDriver().getText(byXpath(xInProgressCount)).trim());
	}
	
	public Integer getUpdatesCount() throws Exception {
		LOG.info("Get Updates Count");	
		return Integer.parseInt(getBrowserDriver().getText(byXpath(xUpdatesCount)).trim());
	}
	
	public void clickOnOrdersPage() throws Exception {
		LOG.debug("Click on Orders Page");
		getBrowserDriver().click(byCssSelector(cssOrdersPage));
		ItradeOrderHelperFactory.waitForloaderToDisapper();
		ItradeOrderHelperFactory.mouseOverToSomeElement();
	}
}
