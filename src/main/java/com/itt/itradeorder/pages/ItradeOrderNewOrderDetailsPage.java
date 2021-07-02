package com.itt.itradeorder.pages;

import static com.itt.browser.common.BrowserLocator.byCssSelector;
import static com.itt.browser.common.BrowserLocator.byName;
import static com.itt.browser.common.BrowserLocator.byXpath;
import static com.itt.factoryhelper.BrowserHelperFactory.getBrowserDriver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ItradeOrderNewOrderDetailsPage {
	private static final Logger LOG = LoggerFactory.getLogger(ItradeOrderNewOrderDetailsPage.class);
	private static String nAddProduct = "add";
	private static String xDiscardButton = "//span[contains(text(),'Discard')]";
	private static String cssCrossButton = "div.close-button span.itn-icon-close-x";
	
	public boolean isAddItemExists() throws Exception {
		return getBrowserDriver().isElementPresent(byName(nAddProduct));
	}
	
	public boolean isDiscardButtonExists() throws Exception {
		return getBrowserDriver().isElementPresent(byXpath(xDiscardButton));		
	}
	
	public boolean isCloseButtonExists() throws Exception {
		return getBrowserDriver().isElementPresent(byCssSelector(cssCrossButton));		
	}
	
	public void clickOnCloseButton() throws Exception {
		LOG.debug("Click on close button");
		getBrowserDriver().click(byCssSelector(cssCrossButton));
	}
}
