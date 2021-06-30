package com.itt.itradeorder.helper;

import static com.itt.browser.common.BrowserLocator.byCssSelector;
import static com.itt.browser.common.BrowserLocator.byXpath;
import static com.itt.factoryhelper.BrowserHelperFactory.getBrowserDriver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itt.itradeorder.pages.ItradeOrderLoginPage;

import lombok.Getter;
import lombok.Setter;

public class ItradeOrderHelperFactory {

	private static final Logger LOG = LoggerFactory.getLogger(ItradeOrderHelperFactory.class);

	private static ItradeOrderHelperFactory itradeOrderHelperFactory = new ItradeOrderHelperFactory();
	private ItradeOrderLoginPage itradeOrderLoginPage = new ItradeOrderLoginPage();
	private static final String cssUserMenuArrowButton = "button.btn-user-menu.mat-icon-button";
	private static final String xLogoutButton = "//span[contains(@class, 'itn-icon-logout')]/following-sibling::span[contains(text(), 'Logout')]";
	
	@Getter
	@Setter
	private static String project;

	public ItradeOrderLoginPage getItradeOrderLoginPage() {
		return this.itradeOrderLoginPage;
	}

	public void logout() throws Exception {
		LOG.info("LOGOUT FROM ItradeOrder");
		try {
				getBrowserDriver().click(byCssSelector(cssUserMenuArrowButton));  
				getBrowserDriver().click(byXpath(xLogoutButton));
		}catch (Exception e) {
			LOG.debug("Logout element is not found, might have already logged out");
		}
	}
}
