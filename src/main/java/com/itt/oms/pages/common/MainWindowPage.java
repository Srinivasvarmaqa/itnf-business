package com.itt.oms.pages.common;

import static com.itt.browser.common.BrowserLocator.*;
import static com.itt.factoryhelper.BrowserHelperFactory.getBrowserDriver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itt.common.Timeout;

/**
 * 
 * @author
 *
 *         MainWindowPage
 */

public class MainWindowPage {
	private static final Logger LOG = LoggerFactory.getLogger(MainWindowPage.class);

	private static String xPONumberTxt = "//input[@name='ponum']";
	private static String cssHeaderFrame = "frame[name='headerFrame']";
	private static String xLeftFrame = "//frame[1])[2]";
	private static String cssMainFrameWindow = "frame[name='mainFrame']";
	private static String cssPPortal = ".noactivelink table tr td:nth-child(3) a >img";
	private static String cssPPortalOffbutton = ".noactivelink table tbody tr td:nth-child(4) a font";
	private static String cssMPortalOffbutton = ".noactivelink table tbody tr td:nth-child(2) a font";

	public MainWindowPage() { }

	public String getPPortal() throws Exception {
		return getBrowserDriver().getAttributeValue(withAttributeName(byCssSelector(cssPPortal), "alt"));
	}

	public void clickOffbutton() throws Exception {
		getBrowserDriver().click(byCssSelector(cssPPortalOffbutton));
	}

	public void clickMOffbutton() throws Exception {
		getBrowserDriver().click(byCssSelector(cssMPortalOffbutton));
	}

	public String getPortalMOffValue() throws Exception {
		return getBrowserDriver().getText(byCssSelector(cssMPortalOffbutton));
	}

	public String getPortalPOffValue() throws Exception {
		return getBrowserDriver().getText(byCssSelector(cssPPortalOffbutton));
	}

	public void clickOffPortalP() throws Exception {
		getBrowserDriver().switchToFrame(byCssSelector(cssHeaderFrame));
		try {
			if (getBrowserDriver().waitForElement(withCustomTimeout(byCssSelector(cssPPortalOffbutton), Timeout.FIVE_SECONDS_TIMEOUT))) {
				String portalValue = getPortalPOffValue();
				if ("Off".equalsIgnoreCase(portalValue)) {
					clickOffbutton();
					LOG.info("click off button on portal P");
				}
			}
		} catch (Exception e) {
			LOG.info("Exception clicking off P portal" + e.getMessage());
		}
	}
}
