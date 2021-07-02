package com.itt.oms.pages.navigation;

import org.slf4j.LoggerFactory;

import com.itt.common.Timeout;
import com.itt.oms.helper.OMSHelperFactory;

import static com.itt.factoryhelper.BrowserHelperFactory.getBrowserDriver;
import static com.itt.browser.common.BrowserLocator.*;

import org.slf4j.Logger;

public class OMSMenuNavigationPage {

	private static final Logger LOG = LoggerFactory.getLogger(OMSMenuNavigationPage.class);
	private static final String cssGoRightArrow = ".go_right[title='Scroll Right']";
	private static final String cssGoLeftArrow = ".go_left[title='Scroll Left']";
	
	public String openLeftMainMenu(String menu) throws Exception {
		OMSHelperFactory.switchToLeftFrame();
		String cssMenu = String.format("a[title='%s']:not([href*='submenu'])", menu);
		getBrowserDriver().click(byCssSelector(cssMenu));
		getBrowserDriver().waitForPageLoad();
		return getBrowserDriver().getText(byCssSelector(cssMenu));
	}

	public String openLeftSubMenu(String mainMenu, String subMenu) throws Exception {
		OMSHelperFactory.switchToLeftFrame();
		String cssMenu = String.format("a[title='%s']:not([href*='submenu']) + ul a[title='%s']", mainMenu, subMenu);
		getBrowserDriver().click(withCustomTimeout(byCssSelector(cssMenu), Timeout.TEN_SECONDS_TIMEOUT));
		String menuHeader = getBrowserDriver().getText(byCssSelector(cssMenu));
		getBrowserDriver().waitForPageLoad();
		return menuHeader;
	}
	
	public String openHeaderMenu(String menu) throws Exception {
		OMSHelperFactory.switchToHeaderFrame();
		String cssMenu = String.format("a[title='%s']", menu);
		try {
			getBrowserDriver().click(withCustomTimeout(byCssSelector(cssMenu), Timeout.FIVE_SECONDS_TIMEOUT));
		} catch (Exception e) {
			this.clickRightArrow(2);
			getBrowserDriver().click(withCustomTimeout(byCssSelector(cssMenu), Timeout.FIVE_SECONDS_TIMEOUT));
		}
		String menuHeader = getBrowserDriver().getText(byCssSelector(cssMenu));
		getBrowserDriver().waitForPageLoad();
		return menuHeader;
	}

	public void clickRightArrow(int number) {
		for (int i = 0; i <= number; i++) {
			try {
				OMSHelperFactory.switchToHeaderFrame();
				getBrowserDriver().click(byCssSelector(cssGoRightArrow));
			} catch (Exception e) {
				LOG.debug("Couldn't find the right arrow button");
			}
		}
	}
	
	public void clickLeftArrow(int number) {
		for (int i = 0; i <= number; i++) {
			try {
				OMSHelperFactory.switchToHeaderFrame();
				getBrowserDriver().click(byCssSelector(cssGoLeftArrow));
			} catch (Exception e) {
				LOG.debug("Couldn't find the Left arrow button");
			}
		}
	}
}