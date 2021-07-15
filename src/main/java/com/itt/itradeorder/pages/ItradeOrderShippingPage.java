package com.itt.itradeorder.pages;

import static com.itt.browser.common.BrowserLocator.byCssSelector;
import static com.itt.browser.common.BrowserLocator.byXpath;
import static com.itt.factoryhelper.BrowserHelperFactory.getBrowserDriver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itt.itradeorder.helper.ItradeOrderHelperFactory;

public class ItradeOrderShippingPage {
	private static final Logger LOG = LoggerFactory.getLogger(ItradeOrderShippingPage.class);
	private static String xShippingTodayCount = "//div[contains(text(), 'Shipping Today')]/following-sibling::div";
	private static String xShippingToday = "//div[contains(text(), 'Shipping Today')]";
	private static String xShippingTommorow = "//div[contains(text(), 'Shipping Tomorrow')]";
	private static String xShippingTommorowCount = "//div[contains(text(), 'Shipping Tomorrow')]/following-sibling::div";
	private static String xInTransitCount = "//div[contains(text(), 'In Transit')]/following-sibling::div";
	private static String xAllShippingCount = "//div[contains(text(), 'All')]/following-sibling::div";
	private static String cssShippingPage = "span.mi-icon.itn-icon-shipping-wh";
	private static String cssClickOnShip = "button.action-primary[type='submit']";;
	
	
	public Integer getShipTodayCount() throws Exception {
		LOG.info("Get Ship Today Count");	
		return Integer.parseInt(getBrowserDriver().getText(byXpath(xShippingTodayCount)).trim());
	}
	
	public void clickOnShipToday() throws Exception {
		LOG.info("Click on ship today");	
		getBrowserDriver().click(byXpath(xShippingToday));
		ItradeOrderHelperFactory.waitForloaderToDisapper();
	}
	
	public void clickOnShipTomorrow() throws Exception {
		LOG.info("Click on ship tommorow");	
		getBrowserDriver().click(byXpath(xShippingTommorow));
		ItradeOrderHelperFactory.waitForloaderToDisapper();
	}
	
	public Integer getShipAllCount() throws Exception {
		LOG.info("Get Ship Today Count");	
		return Integer.parseInt(getBrowserDriver().getText(byXpath(xAllShippingCount)).trim());
	}
	
	public Integer getShipTransitCount() throws Exception {
		LOG.info("Get Ship Transit Count");	
		return Integer.parseInt(getBrowserDriver().getText(byXpath(xInTransitCount)).trim());
	}
	
	public Integer getShipTommorowCount() throws Exception {
		LOG.info("Get Ship Tomorrow Count");	
		return Integer.parseInt(getBrowserDriver().getText(byXpath(xShippingTommorowCount)).trim());
	}
	
	public void clickOnShippingPage() throws Exception {
		LOG.debug("Click on Shipping Page");
		getBrowserDriver().click(byCssSelector(cssShippingPage));
		ItradeOrderHelperFactory.clickOnBlankArea();
		ItradeOrderHelperFactory.waitForloaderToDisapper();
	}
	
	public void clickOnShipPO() throws Exception {
		LOG.debug("Click on Ship PO");
		getBrowserDriver().click(byCssSelector(cssClickOnShip));
		ItradeOrderHelperFactory.waitForloaderToDisapper();
	}
}
