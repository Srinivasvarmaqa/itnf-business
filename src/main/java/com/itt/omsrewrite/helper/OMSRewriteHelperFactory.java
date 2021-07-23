package com.itt.omsrewrite.helper;

import static com.itt.browser.common.BrowserLocator.byFrame;
import static com.itt.browser.common.BrowserLocator.withSwitchToMainWindow;
import static com.itt.factoryhelper.BrowserHelperFactory.getBrowserDriver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.Getter;
import lombok.Setter;

public class OMSRewriteHelperFactory {

	private static final Logger LOG = LoggerFactory.getLogger(OMSRewriteHelperFactory.class);
	private static final String mainFrameName = "mainFrame";
	private static final String iFrameName = "frameSP";

	private static OMSRewriteHelperFactory OMSRewriteHelperFactory = new OMSRewriteHelperFactory();
	
	@Getter
	@Setter
	private static String project;

	public void switchToEnhancedOMSPage() throws Exception {
		this.switchToMainFrame();
		int i=0;
		Boolean isEnhancedOMSPageLoaded = false;
		while(i < 60) {
			try {
				getBrowserDriver().switchToFrame(withSwitchToMainWindow(byFrame(iFrameName), false));
				isEnhancedOMSPageLoaded = true;
				break;
			} catch (Exception e) {
				LOG.debug("Retrying until Enhanced OMS Page to load");
				Thread.sleep(1000);
			}
			i++;
		}
		if(isEnhancedOMSPageLoaded)
			LOG.info("Time Taken to load the Enhanced OMS Page in SECONDS:" + i);
		else {
			LOG.info("Enhanced OMS Page not loaded even after waiting for 60 seconds");
			throw new Exception("Enhanced OMS Page not loaded even after waiting for 60 seconds");
		}
	}

	public void switchToMainFrame() throws Exception {
		getBrowserDriver().switchToFrame(byFrame(mainFrameName));
	}
}
