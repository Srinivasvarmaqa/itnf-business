package com.itt.omsrewrite.helper;

import static com.itt.browser.common.BrowserLocator.byCssSelector;
import static com.itt.browser.common.BrowserLocator.byFrame;
import static com.itt.browser.common.BrowserLocator.byId;
import static com.itt.browser.common.BrowserLocator.withCustomTimeout;
import static com.itt.browser.common.BrowserLocator.withSwitchToMainWindow;
import static com.itt.browser.common.BrowserLocator.withWaitForVisibility;
import static com.itt.factoryhelper.BrowserHelperFactory.getBrowserDriver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itt.common.Timeout;

import lombok.Getter;
import lombok.Setter;

public class OMSRewriteHelperFactory {

	private static final Logger LOG = LoggerFactory.getLogger(OMSRewriteHelperFactory.class);
	private static final String mainFrameName = "mainFrame";
	private static final String iFrameName = "frameSP";
	private static String cssLoader = "div.loader-overlay";

	private static OMSRewriteHelperFactory OMSRewriteHelperFactory = new OMSRewriteHelperFactory();
	
	@Getter
	@Setter
	private static String project;

	public void switchToEnhancedOMSPage() throws Exception {
		LOG.debug("Switch to Main Frame");
		this.switchToMainFrame();
		long startTime = System.currentTimeMillis();
		LOG.debug("Switch to IFrame");
		getBrowserDriver().waitForElement(byId(iFrameName));
		getBrowserDriver().switchToFrame(withSwitchToMainWindow(byFrame(iFrameName), false));
		this.waitForloaderToDisapper();
		long endTime = System.currentTimeMillis();
		LOG.info("Time Taken to load the Enhanced OMS Page in SECONDS:" +  (endTime - startTime) / 1000);
	}

	public void switchToMainFrame() throws Exception {
		getBrowserDriver().switchToFrame(byFrame(mainFrameName));
	}

	public void waitForloaderToDisapper() throws Exception {
		int count = 0;
		getBrowserDriver().waitForElement(withCustomTimeout(byCssSelector(cssLoader), Timeout.TWO_SECONDS_TIMEOUT));
		getBrowserDriver().waitForElement(withWaitForVisibility(byCssSelector(cssLoader), "false"));
		while(getBrowserDriver().findElements(byCssSelector(cssLoader)).size()!=0 && count<60) {
			LOG.info("WAIT FOR LOADER TO DISAPPER - " + count);
			Thread.sleep(1000);
			count++;
		}
	}
}
