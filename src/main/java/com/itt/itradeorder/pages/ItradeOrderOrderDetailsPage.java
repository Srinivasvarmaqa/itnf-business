package com.itt.itradeorder.pages;

import static com.itt.browser.common.BrowserLocator.byCssSelector;
import static com.itt.browser.common.BrowserLocator.byId;
import static com.itt.browser.common.BrowserLocator.byName;
import static com.itt.browser.common.BrowserLocator.byXpath;
import static com.itt.browser.common.BrowserLocator.withText;
import static com.itt.factoryhelper.BrowserHelperFactory.getBrowserDriver;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itt.itradeorder.datamodelhelper.ItradeOrderDataModelHelperFactory;
import com.itt.itradeorder.datamodelhelper.ItradeOrderDataModelProducts;
import com.itt.itradeorder.helper.ItradeOrderHelperFactory;

public class ItradeOrderOrderDetailsPage {
	private static final Logger LOG = LoggerFactory.getLogger(ItradeOrderOrderDetailsPage.class);
	private static String nAddProduct = "add";
	private static String xDiscardButton = "//span[contains(text(),'Discard')]";
	private static String cssCrossButton = "div.close-button span.itn-icon-close-x";
	private static String xSubmitButton = "//span[contains(text(),'Submit')]";
	private static String xCancelButton = "//span[contains(text(),'Cancel')]";
	private static String cssPONumber = ".itn-jumbo-card .primary-color";
	private static String cssSearchCrossButton = "div.icon-container.itn-icon-close-x";
	private static String cssSearchCrossButtonSideNavigator = ".sidenav-header .itn-icon-close-x";
	private static String cssPOStatus = ".itn-jumbo-card .status-badge";
	
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
		ItradeOrderHelperFactory.waitForloaderToDisapper();
	}

	public void addProducts(ItradeOrderDataModelHelperFactory itradeOrderDataModelHelperFactory) throws Exception {
		LOG.info("Add Product");
		List<ItradeOrderDataModelProducts> products = itradeOrderDataModelHelperFactory.getItradeOrderDataModelOrderDetails().getProducts();
		int i = 1;
		String productName;
		String xProductDropDown;
		String idPrice;
		String idQuantity;
		for (ItradeOrderDataModelProducts product: products) {
			LOG.debug("Add product");
			productName = product.getName();
			getBrowserDriver().sendValue(withText(byName(nAddProduct), productName));
			xProductDropDown = String.format("//div[contains(@id,'cdk-overlay-')]//mat-option[@role='option']/span[contains(text(), '%s')]", productName);
			getBrowserDriver().click(byXpath(xProductDropDown));

			LOG.debug("Enter Quantity");
			idQuantity = "quantity" + i;
			getBrowserDriver().sendValue(withText(byId(idQuantity), Integer.toString(product.getQuantity())));

			LOG.debug("Enter Price");
			idPrice = "price" + i;
			getBrowserDriver().sendValue(withText(byId(idPrice), Double.toString(product.getPrice())));

			i++;
		}
		this.clickOnSubmitButton();
	}

	public void clickOnSubmitButton() throws Exception {
		LOG.debug("Click on Submit Button");
		getBrowserDriver().click(byXpath(xSubmitButton));
	}

	public String getPONumber() throws Exception {
		LOG.debug("Get PO Number");
		return getBrowserDriver().getText(byCssSelector(cssPONumber));
	}

	public void clickOnSearchCloseButton() throws Exception {
		LOG.debug("Click on Search close button");
		getBrowserDriver().click(byCssSelector(cssSearchCrossButton));
	}

	public void clickOnSideNavCrossIcon() throws Exception {
		LOG.debug("Click on side navigationclose button");
		getBrowserDriver().click(byCssSelector(cssSearchCrossButtonSideNavigator));
	}

	public void clickOnCancelButton() throws Exception {
		LOG.debug("Click on PO Cancel button");
		getBrowserDriver().click(byXpath(xCancelButton));
	}

	public void cancelPO() throws Exception {
		LOG.debug("Cancel the PO");
		this.clickOnCancelButton();
		ItradeOrderHelperFactory.clickOnOkConfirmationDialogButton();
	}

	public String getPOStatus() throws Exception {
		LOG.debug("Get the PO Status");
		return getBrowserDriver().getText(byCssSelector(cssPOStatus)).trim();
	}

}
