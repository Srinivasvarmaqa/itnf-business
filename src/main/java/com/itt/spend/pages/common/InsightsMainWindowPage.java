package com.itt.spend.pages.common;

import static com.itt.browser.common.BrowserLocator.*;
import static com.itt.factoryhelper.BrowserHelperFactory.getBrowserDriver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itt.common.Timeout;
import com.itt.spend.helper.SPENDHelperFactory;

/**
 * 
 * @author
 *
 *         MainWindowPage
 */

public class InsightsMainWindowPage {
	private static final Logger LOG = LoggerFactory.getLogger(InsightsMainWindowPage.class);


	private static String xFrame = "//iframe[@class='ng-star-inserted']";
	private static String cssLogoText = ".top-logo.ng-tns-c2-1.ng-star-inserted";
	private static String cssWidgetLibrarybutton = ".toggle.library";
	private static String xUnconfirmedPoWidget = "//span[contains(text(),'Unconfirmed PO’s')]";
	private static String xInventoryWidget="//span[contains(text(),'Inventory')]";
	public String getLogoText(String text) throws Exception {
		
			String logoText = getBrowserDriver().getText(byCssSelector(cssLogoText));
		getBrowserDriver().waitForPageLoad();
		return logoText;
	}
		public void clickWidgetLibraryF() throws Exception {
		SPENDHelperFactory.waitForloaderToDisapper();
		getBrowserDriver().switchToFrame(byXpath(xFrame));
		SPENDHelperFactory.waitForloaderToDisapper();
		getBrowserDriver().click(byCssSelector(cssWidgetLibrarybutton));
		
			}
		public String clickUnconfirmedPoWidget(String menu) throws Exception {
		  
			getBrowserDriver().click(byXpath(xUnconfirmedPoWidget));
			String textmenu=getBrowserDriver().getText(byXpath(xUnconfirmedPoWidget));
			return textmenu;
		}
		
	
}


