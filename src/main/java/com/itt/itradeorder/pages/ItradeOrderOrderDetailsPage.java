package com.itt.itradeorder.pages;

import static com.itt.browser.common.BrowserLocator.byCssSelector;
import static com.itt.browser.common.BrowserLocator.byId;
import static com.itt.browser.common.BrowserLocator.byName;
import static com.itt.browser.common.BrowserLocator.byXpath;
import static com.itt.browser.common.BrowserLocator.withClearOption;
import static com.itt.browser.common.BrowserLocator.withText;
import static com.itt.factoryhelper.BrowserHelperFactory.getBrowserDriver;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itt.itradeorder.datamodelhelper.ItradeOrderDataModelHelperFactory;
import com.itt.itradeorder.datamodelhelper.ItradeOrderDataModelProducts;
import com.itt.itradeorder.helper.ItradeOrderHelperFactory;
import com.itt.itradeorder.pages.ItradeOrderLoginPage.USER;

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
	private static String cssOrderDetailCrossButtonSideNavigator = ".black .itn-icon-close-x";
	private static String cssPOStatus = ".itn-jumbo-card .status-badge";
	private static String xBellButton = "//span[contains(@class, 'itn-icon-notification ng-tns')]";
	private static String xSOInput = "//mat-label[contains(text(),'Sales Order')]/../../../input";
	private static String xAcceptButton = "//span[contains(text(),'Accept')]";
	private static String xUpdateButton = "//span[contains(text(),'Update')]";
	private static String cssItemSize = ".flex-layout-table-body-inner div.flex-layout-table-row[fxlayout='row']";
	private static String xItemsCount = "//itn-file-card//div[@class='left-col']//div[contains(text(), 'ITEMS')]/following-sibling::div";
	private static String xChargesCount = "//itn-file-card//div[@class='left-col']//div[contains(text(), 'CHARGES')]/following-sibling::div";
	private static String xHistoryCount = "//itn-file-card//div[@class='left-col']//div[contains(text(), 'HISTORY')]/following-sibling::div";
	private static String xCasesCount = "//div[@class='units-badge']/div[contains(text(), 'Cases')]/following-sibling::div[@class='value']";
	
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

	public void addProducts(USER user, ItradeOrderDataModelHelperFactory itradeOrderDataModelHelperFactory) throws Exception {
		LOG.info("Add Product");
		List<ItradeOrderDataModelProducts> products;
		
		if (user.equals(USER.BUYER)) {
			products = itradeOrderDataModelHelperFactory.getItradeOrderDataModelOrderDetails().getBuyeraddproducts();
		} else if (user.equals(USER.VENDOR)) {
			products = itradeOrderDataModelHelperFactory.getItradeOrderDataModelOrderDetails().getVendoraddproducts();
		} else {
			throw new Exception("Incorrect user" + user.toString());
		}
		if (products != null) {
			int i = getBrowserDriver().findElements(byCssSelector(cssItemSize)).size();
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
		ItradeOrderHelperFactory.waitForloaderToDisapper();
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
		int i = 0;
		while(getBrowserDriver().getText(byCssSelector(cssPOStatus)).trim() == "--" && i<=5) {
			Thread.sleep(1000);
			i++;
		}
		return getBrowserDriver().getText(byCssSelector(cssPOStatus)).trim();
	}
	
	public void clickonBell() throws Exception {
		LOG.info("Click on Bell Button");
		getBrowserDriver().click(byXpath(xBellButton));
	}
	
	public void enterSONumber() throws Exception {
		LOG.info("Enter SO Number");
		long randomNumber = (long) Math.floor(Math.random() * 900000L) + 10000L;
		getBrowserDriver().sendValue(withText(byXpath(xSOInput), "SOAuto" + randomNumber));
	}
	
	public void clickOnAcceptButton() throws Exception {
		LOG.debug("Click on Accept button");
		getBrowserDriver().click(byXpath(xAcceptButton));
	}
	
	public void clickOnOrderDetailPageCrossIcon() throws Exception {
		LOG.debug("Click on side navigationclose button");
		getBrowserDriver().click(byCssSelector(cssOrderDetailCrossButtonSideNavigator));
		ItradeOrderHelperFactory.waitForloaderToDisapper();
	}
	
	public void updateProductItem(USER user, ItradeOrderDataModelHelperFactory itradeOrderDataModelHelperFactory) throws Exception {
		LOG.info("Update Product");
		List<ItradeOrderDataModelProducts> products;
		if (user.equals(USER.BUYER)) {
			products = itradeOrderDataModelHelperFactory.getItradeOrderDataModelOrderDetails().getBuyerupdateproducts();
		} else if (user.equals(USER.VENDOR)) {
			products = itradeOrderDataModelHelperFactory.getItradeOrderDataModelOrderDetails().getVendorupdateproducts();
		} else {
			throw new Exception("Incorrect user" + user.toString());
		}
		if (products != null) {
			for (ItradeOrderDataModelProducts product: products) {
	
				if (product.getName() != null && product.getPrice() != 0.0) {
					LOG.debug("Update Price");
					String xUpdatePrice = String.format("//span[contains(text(), '%s')]/ancestor::div[@fxlayout='row']//input[@name='price']", product.getName());
					getBrowserDriver().sendValue(withText(withClearOption(byXpath(xUpdatePrice), true), Double.toString(product.getPrice())));
				}
				if (product.getName() != null && product.getQuantity() != 0 ) {
					LOG.debug("Update Quantity");
					String xUpdateQuantity = String.format("//span[contains(text(), '%s')]/ancestor::div[@fxlayout='row']//input[@name='quantity']", product.getName());
					getBrowserDriver().sendValue(withText(withClearOption(byXpath(xUpdateQuantity), true), Integer.toString(product.getQuantity())));
				}
			}
		}
		clickOnUpdateButton();
	}
	
	public void clickOnUpdateButton() throws Exception {
		LOG.debug("Click on Update button");
		getBrowserDriver().click(byXpath(xUpdateButton));
	}
	
	public void updateSalesOrder() throws Exception {	
		clickOnSideNavCrossIcon();	
		clickonBell();
		enterSONumber();
		clickOnSideNavCrossIcon();
		clickOnAcceptButton();
	}
	
	public Integer getPOItemsCount() throws Exception {
		LOG.info("Get PO Items Count");	
		return Integer.parseInt(getBrowserDriver().getText(byXpath(xItemsCount)).trim());
	}
	
	public Integer getPOChargesCount() throws Exception {
		LOG.info("Get PO charges Count");	
		return Integer.parseInt(getBrowserDriver().getText(byXpath(xChargesCount)).trim());
	}
	
	public Integer getPOHistoryCount() throws Exception {
		LOG.info("Get PO history Count");	
		return Integer.parseInt(getBrowserDriver().getText(byXpath(xHistoryCount)).trim());
	}
	
	public Integer getAllItemlineCount() throws Exception {
		LOG.info("Get All Item line Count");	
		return getBrowserDriver().findElements(byCssSelector(cssItemSize)).size();
	}
	
	public Double getPOCasesCount() throws Exception {
		LOG.info("Get PO Item cases Count");
		return Double.parseDouble(getBrowserDriver().getText(byXpath(xCasesCount)).trim());
	}
	
	public Boolean verifyPOItemCount() throws Exception {
		LOG.info("Verify Po Items count");	
		Integer POItemsCount = this.getPOItemsCount();
		Integer AllItemLineCount = this.getAllItemlineCount();
		if (POItemsCount == AllItemLineCount - 1) {
			return true;
		}		
		else {
			return false;
		}	
	}
	
	public Boolean verifyPOItemHistoryCount() throws Exception {
		LOG.info("Verify Po Items History count");	
		Integer POItemsHistoryCount = this.getPOHistoryCount();
		Integer AllItemLineCount = this.getAllItemlineCount();
		if (POItemsHistoryCount == AllItemLineCount - 1) {
			return true;
		}		
		else {
			return false;
		}	
	}
	
	public boolean verifyPOItemsCasesCount() throws Exception {
		LOG.info("Verify Po Items History count");	
		String idQuantity;
		Double quantityCount = 0.0;
		int i = getBrowserDriver().findElements(byCssSelector(cssItemSize)).size();
		for(int j=1 ; j<i; j++)
		{
			idQuantity = "quantity" + j;
			quantityCount += Double.parseDouble(getBrowserDriver().getText(byXpath(idQuantity)).trim());			
		}
		if (this.getPOCasesCount() == quantityCount) {
			return true;
		}		
		else {
			return false;
		}
	}
}
