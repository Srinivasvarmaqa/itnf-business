package com.itt.itradeorder.pages;

import static com.itt.browser.common.BrowserLocator.byCssSelector;
import static com.itt.browser.common.BrowserLocator.byXpath;
import static com.itt.factoryhelper.BrowserHelperFactory.getBrowserDriver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itt.itradeorder.helper.ItradeOrderHelperFactory;

public class ItradeOrderDashboardPage {
	private static final Logger LOG = LoggerFactory.getLogger(ItradeOrderDashboardPage.class);
	private static String xShippedshortCount = "//span[contains(text(),'Shipped Short')]/following-sibling::div[@class='dashCardContent']/div[@class='newOrDaily']/span";
	private static String cssDashboardPage = "span.mi-icon.itn-icon-insights-dashboard";
	
	public Integer getShippedShortCount() throws Exception {
		LOG.info("Get Shipped short Count");
		ItradeOrderHelperFactory.waitForloaderToDisapper();
		return Integer.parseInt(getBrowserDriver().getText(byXpath(xShippedshortCount)).trim());
	}
	
	public void clickOnDashboardPage() throws Exception {
		LOG.debug("Click on Dashboard Page");
		getBrowserDriver().click(byCssSelector(cssDashboardPage));
		ItradeOrderHelperFactory.waitForloaderToDisapper();
	}
}
