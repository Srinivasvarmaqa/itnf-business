package com.itt.itradeorder.pages;

import static com.itt.browser.common.BrowserLocator.byCssSelector;
import static com.itt.browser.common.BrowserLocator.byId;
import static com.itt.browser.common.BrowserLocator.byName;
import static com.itt.browser.common.BrowserLocator.byXpath;
import static com.itt.browser.common.BrowserLocator.withClearOption;
import static com.itt.browser.common.BrowserLocator.withCustomTimeout;
import static com.itt.browser.common.BrowserLocator.withScroll;
import static com.itt.browser.common.BrowserLocator.withText;
import static com.itt.factoryhelper.BrowserHelperFactory.getBrowserDriver;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itt.common.Timeout;
import com.itt.itradeorder.datamodelhelper.ItradeOrderDataModelHelperFactory;
import com.itt.itradeorder.datamodelhelper.ItradeOrderDataModelProducts;
import com.itt.itradeorder.helper.ItradeOrderHelperFactory;
import com.itt.itradeorder.pages.ItradeOrderLoginPage.USER;

public class ItradeOrderOrderDetailsPage {
	private static final Logger LOG = LoggerFactory.getLogger(ItradeOrderOrderDetailsPage.class);
	private static String nAddProduct = "add";
	private static String xDiscardButton = "//span[contains(text(),'Discard')]";
	private static String cssCrossButton = "div.close-button span.itn-icon-close-x";
	private static String xSubmitButton = "//button[@type='submit']";
	private static String xCancelButton = "//span[contains(text(),'Cancel')]";
	private static String cssPONumber = ".itn-jumbo-card .primary-color";
	private static String cssSearchCrossButton = "div.icon-container.itn-icon-close-x[fxlayout='row']";
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
	private static String xAddCharges = "//span[contains(text(), 'Add Charges')]";
	private static String xEditCharges = "//span[contains(text(), 'Edit Charges')]";
	private static String xViewCharges = "//span[contains(text(), 'View Charges')]";
	private static String xAddCharge = "span.itn-icon-plus.add-item-btn-icon";
	private static String xDoneWithCharges = "//span[contains(text(), 'DONE WITH CHARGES')]";
	private static String xTotalPOCost = "//div[@class='footer-content']/itn-panel-button//div[text()='Total PO Cost']/following-sibling::div";
	private static String xLineItemTotalCharge = "//div[text()='Total']/following-sibling::div";
	private static String cssLoader = "div.loader-overlay";

	public Boolean isSubmitButtonEnabled() throws Exception {
		return getBrowserDriver().isElementEnabled(withScroll(byXpath(xSubmitButton)));
	}
	
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
		if (user.equals(USER.BUYER)) {
			LOG.info("Click on Submit button");
			getBrowserDriver().click(byXpath(xSubmitButton));
		} else if (user.equals(USER.VENDOR)) {
			LOG.info("Click on Update button");
			getBrowserDriver().click(byXpath(xUpdateButton));
		} else {
			throw new Exception("Incorrect user" + user.toString());
		}
	}
	
	public void addProductsWithoutSubmitPO(USER user, ItradeOrderDataModelHelperFactory itradeOrderDataModelHelperFactory) throws Exception {
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
	}

	public void addProductsWithCharges(USER user, ItradeOrderDataModelHelperFactory itradeOrderDataModelHelperFactory)
			throws Exception {
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
			for (ItradeOrderDataModelProducts product : products) {
				LOG.debug("Add product");
				productName = product.getName();
				getBrowserDriver().sendValue(withText(byName(nAddProduct), productName));
				xProductDropDown = String.format("//div[contains(@id,'cdk-overlay-')]//mat-option[@role='option']/span[contains(text(), '%s')]",productName);
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
		this.addLinelevelCharges(user, itradeOrderDataModelHelperFactory);
		this.clickOnSubmitButton();
	}

	public void clickOnSubmitButton() throws Exception {
		LOG.debug("Click on Submit Button");
		getBrowserDriver().click(byXpath(xSubmitButton));
	}

	public void clickOnDiscardButton() throws Exception {
		LOG.debug("Click on Discrad Button");
		getBrowserDriver().click(byXpath(xDiscardButton));
	}

	public String getPONumber() throws Exception {
		LOG.debug("Get PO Number");
		return getBrowserDriver().getText(byCssSelector(cssPONumber));
	}

	public void clickOnSearchCloseButton() throws Exception {
		LOG.debug("Click on Search close button");
		getBrowserDriver().waitForElement(byCssSelector(cssSearchCrossButton));
		ItradeOrderHelperFactory.waitForloaderToDisapper();
		getBrowserDriver().click(byCssSelector(cssSearchCrossButton));
		ItradeOrderHelperFactory.waitForloaderToDisapper();
	}

	public void clickOnSideNavCrossIcon() throws Exception {
		LOG.debug("Click on side navigationclose button");
		if (getBrowserDriver().waitForElement(withCustomTimeout(byCssSelector(cssSearchCrossButtonSideNavigator), Timeout.FIFTEEN_SECONDS_TIMEOUT))) {
			getBrowserDriver().click(byCssSelector(cssSearchCrossButtonSideNavigator));
			ItradeOrderHelperFactory.waitForloaderToDisapper();
		}
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
		while(!(getBrowserDriver().findElements(byCssSelector(cssLoader)).size()==0 && getBrowserDriver().getText(byCssSelector(cssPOStatus)).trim() != "--" && i<=60)) {
			Thread.sleep(1000);
			i++;
		}
		return getBrowserDriver().getText(byCssSelector(cssPOStatus)).trim();
	}
	
	public void clickonBell() throws Exception {
		LOG.info("Click on Bell Button");
		ItradeOrderHelperFactory.waitForloaderToDisapper();
		getBrowserDriver().click(byXpath(xBellButton));
	}
	
	public void enterSONumber() throws Exception {
		LOG.info("Enter SO Number");
		long randomNumber = (long) Math.floor(Math.random() * 900000L) + 10000L;
		if (getBrowserDriver().isElementPresent(byXpath(xSOInput))) {
			getBrowserDriver().sendValue(withText(byXpath(xSOInput), "SOAuto" + randomNumber));
		}
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
		LOG.debug("Get PO Items Count");
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
		LOG.debug("Get All Item line Count");
		return getBrowserDriver().findElements(byCssSelector(cssItemSize)).size();
	}
	
	public Double getPOCasesCount() throws Exception {
		LOG.info("Get PO Item cases Count");
		return Double.parseDouble(getBrowserDriver().getText(byXpath(xCasesCount)).trim());
	}
	
	public Boolean verifyPOItemCount() throws Exception {
		LOG.debug("Verify Po Items count");
		ItradeOrderHelperFactory.waitForloaderToDisapper();
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
		ItradeOrderHelperFactory.waitForloaderToDisapper();
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

	public void addLinelevelCharges(USER user, ItradeOrderDataModelHelperFactory itradeOrderDataModelHelperFactory) throws Exception {
		LOG.info("Add line level charges");
		List<ItradeOrderDataModelProducts> charges;
		if (user.equals(USER.BUYER)) {
			charges = itradeOrderDataModelHelperFactory.getItradeOrderDataModelOrderDetails().getBuyerlinelevelcharges();
		} else if (user.equals(USER.VENDOR)) {
			charges = itradeOrderDataModelHelperFactory.getItradeOrderDataModelOrderDetails().getVendorlinelevelcharges();
		} else {
			throw new Exception("Incorrect user" + user.toString());
		}
		if (charges != null) {
			for (ItradeOrderDataModelProducts charge : charges) {
				if (charge.getChargetype() != null && charge.getCalculationtype() != null && charge.getAmount() != 0.0) {
					LOG.debug("Add charges");
					String xClickDot = String.format("//span[contains(text(), '%s')]/ancestor::div[@fxlayout='row']//span[contains(@class,'itn-icon-more-horizontal')]",charge.getName());
					getBrowserDriver().click(byXpath(xClickDot));
					getBrowserDriver().click(byXpath(xAddCharges));
					getBrowserDriver().waitForElement(withCustomTimeout(byXpath(xAddCharge), Timeout.TWO_SECONDS_TIMEOUT));
					getBrowserDriver().click(byCssSelector(xAddCharge));

					String xChargeType = String.format("//mat-select[@id='chargeType_%s']", charges.size() - 1);
					getBrowserDriver().click(byXpath(xChargeType));

					String xChargeTypeValue = String.format("//span[contains(text(), '%s')]", charge.getChargetype());
					getBrowserDriver().click(byXpath(xChargeTypeValue));

					String xCalcType = String.format("//mat-select[@id='charge_%s']", charges.size() - 1);
					getBrowserDriver().click(byXpath(xCalcType));

					String xCalcTypeValue = String.format("//span[contains(text(), '%s')]",charge.getCalculationtype());
					getBrowserDriver().click(byXpath(xCalcTypeValue));

					String xChargeAmount = String.format("//label[@aria-owns='chargeType_%s']/ancestor::div[contains(@class,'flex-layout-table-row')]//input[@name='calcValue']",charges.size() - 1);
					getBrowserDriver().sendValue(withText(byXpath(xChargeAmount), Double.toString(charge.getAmount())));
					getBrowserDriver().click(byXpath(xDoneWithCharges));
				}
			}
		}
	}
	
			public Boolean isAddChargesButtonEnabled(USER user, ItradeOrderDataModelHelperFactory itradeOrderDataModelHelperFactory) throws Exception {
				LOG.info("Get Add charges status");
				Boolean isAddCharge = true;
				List<ItradeOrderDataModelProducts> charges;
				if (user.equals(USER.BUYER)) {
					charges = itradeOrderDataModelHelperFactory.getItradeOrderDataModelOrderDetails().getBuyerlinelevelcharges();
				} else if (user.equals(USER.VENDOR)) {
					charges = itradeOrderDataModelHelperFactory.getItradeOrderDataModelOrderDetails().getVendorlinelevelcharges();
				} else {
					throw new Exception("Incorrect user" + user.toString());
				}
				if (charges != null) {
					for (ItradeOrderDataModelProducts charge : charges) {
						if (charge.getChargetype() != null && charge.getCalculationtype() != null
								&& charge.getAmount() != 0.0) {
							LOG.debug("Is Add charges Enabled");
							String xClickDot = String.format("//span[contains(text(), '%s')]/ancestor::div[@fxlayout='row']//span[contains(@class,'itn-icon-more-horizontal')]", charge.getName());
							getBrowserDriver().click(byXpath(xClickDot));
							ItradeOrderHelperFactory.waitForloaderToDisapper();
							isAddCharge = getBrowserDriver().isElementPresent(byXpath(xAddCharges));
							getBrowserDriver().click(byXpath(xEditCharges));
							ItradeOrderHelperFactory.waitForloaderToDisapper();
							getBrowserDriver().click(byXpath(xDoneWithCharges));
							ItradeOrderHelperFactory.waitForloaderToDisapper();
						}
						break;
					}
				}
				return isAddCharge;
			}
			
			public Boolean isViewChargesEnabledAfterShipPO(USER user, ItradeOrderDataModelHelperFactory itradeOrderDataModelHelperFactory) throws Exception {
				LOG.info("Get view charges status");
				Boolean isViewCharge = true;
				List<ItradeOrderDataModelProducts> charges;
				if (user.equals(USER.BUYER)) {
					charges = itradeOrderDataModelHelperFactory.getItradeOrderDataModelOrderDetails().getBuyerlinelevelcharges();
				} else if (user.equals(USER.VENDOR)) {
					charges = itradeOrderDataModelHelperFactory.getItradeOrderDataModelOrderDetails().getVendorlinelevelcharges();
				} else {
					throw new Exception("Incorrect user" + user.toString());
				}
				if (charges != null) {
					for (ItradeOrderDataModelProducts charge : charges) {
						if (charge.getChargetype() != null && charge.getCalculationtype() != null
								&& charge.getAmount() != 0.0) {
							LOG.debug("Is Add charges Enabled");
							String xClickDot = String.format("//span[contains(text(), '%s')]/ancestor::div[@fxlayout='row']//span[contains(@class,'itn-icon-more-horizontal')]", charge.getName());
							ItradeOrderHelperFactory.waitForloaderToDisapper();
							getBrowserDriver().click(byXpath(xClickDot));
							isViewCharge = getBrowserDriver().isElementPresent(byXpath(xViewCharges));
							getBrowserDriver().click(byXpath(xViewCharges));
							ItradeOrderHelperFactory.waitForloaderToDisapper();
							getBrowserDriver().click(byXpath(xDoneWithCharges));
							ItradeOrderHelperFactory.waitForloaderToDisapper();
						}
						break;
					}
				}
				return isViewCharge;
			}
			
			public Boolean isAddChargesDisabledAfterShipPO(USER user, ItradeOrderDataModelHelperFactory itradeOrderDataModelHelperFactory) throws Exception {
				LOG.info("Get view charges status");
				Boolean isAddCharge = true;
				List<ItradeOrderDataModelProducts> charges;
				if (user.equals(USER.BUYER)) {
					charges = itradeOrderDataModelHelperFactory.getItradeOrderDataModelOrderDetails().getBuyerlinelevelcharges();
				} else if (user.equals(USER.VENDOR)) {
					charges = itradeOrderDataModelHelperFactory.getItradeOrderDataModelOrderDetails().getVendorlinelevelcharges();
				} else {
					throw new Exception("Incorrect user" + user.toString());
				}
				if (charges != null) {
					for (ItradeOrderDataModelProducts charge : charges) {
						if (charge.getChargetype() != null && charge.getCalculationtype() != null
								&& charge.getAmount() != 0.0) {
							LOG.debug("Is Add charges Enabled");
							String xClickDot = String.format("//span[contains(text(), '%s')]/ancestor::div[@fxlayout='row']//span[contains(@class,'itn-icon-more-horizontal')]", charge.getName());
							ItradeOrderHelperFactory.waitForloaderToDisapper();
							getBrowserDriver().click(byXpath(xClickDot));
							isAddCharge = getBrowserDriver().isElementPresent(byXpath(xAddCharges));
							getBrowserDriver().click(byXpath(xViewCharges));
							ItradeOrderHelperFactory.waitForloaderToDisapper();
							getBrowserDriver().click(byXpath(xDoneWithCharges));
							ItradeOrderHelperFactory.waitForloaderToDisapper();
						}
						break;
					}
				}
				return isAddCharge;
			}

			public Boolean verifyTotalPOCost(ItradeOrderDataModelHelperFactory itradeOrderDataModelHelperFactory) throws Exception {
				LOG.debug("Get PO Total Cost");
				String TotalPoCost1 = getBrowserDriver().getText(byXpath(xTotalPOCost)).trim();
				Double TotalPoCost = Double.parseDouble(TotalPoCost1.replaceAll("[^\\d.]", ""));
				LOG.debug("Get Total Price");
				Double TotalPrice = 0.0;
				List<ItradeOrderDataModelProducts> buyerProducts;
				List<ItradeOrderDataModelProducts> buyerCharges;
				List<ItradeOrderDataModelProducts> vendorProducts;
				List<ItradeOrderDataModelProducts> vendorCharges;
				List<ItradeOrderDataModelProducts> allProducts = null;
				List<ItradeOrderDataModelProducts> allCharges = null;
				buyerProducts = itradeOrderDataModelHelperFactory.getItradeOrderDataModelOrderDetails().getBuyeraddproducts();
				buyerCharges = itradeOrderDataModelHelperFactory.getItradeOrderDataModelOrderDetails().getBuyerlinelevelcharges();
				vendorProducts = itradeOrderDataModelHelperFactory.getItradeOrderDataModelOrderDetails().getVendoraddproducts();
				vendorCharges = itradeOrderDataModelHelperFactory.getItradeOrderDataModelOrderDetails().getVendorlinelevelcharges();
				if (buyerProducts !=null && vendorProducts !=null) {
					allProducts = Stream.concat(buyerProducts.stream(), vendorProducts.stream()).collect(Collectors.toList());
				}else if(buyerProducts !=null && vendorProducts ==null ){
					allProducts = buyerProducts;
				}else {
					allProducts = vendorProducts;
				}
				if (buyerCharges !=null && vendorCharges !=null) {
					allCharges = Stream.concat(buyerCharges.stream(), vendorCharges.stream()).collect(Collectors.toList());
				}else if(buyerCharges !=null && vendorCharges ==null){
					allCharges = buyerCharges;
				}else {
					allCharges = vendorCharges;
				}

				if (allProducts != null) {
					for (ItradeOrderDataModelProducts product: allProducts) {
						if (product.getName() != null && product.getPrice() != 0.0 && product.getQuantity() != 0) {
							LOG.debug("Get Total Price of each item");
							String xGetPrice = String.format("//span[contains(text(), '%s')]/ancestor::div[@fxlayout='row']//span//span[contains(@class,'showProductDetail ng-star-inserted')]", product.getName());
							String TotalPriceInCurrency = getBrowserDriver().getText(byXpath(xGetPrice)).trim();
							TotalPrice += Double.parseDouble(TotalPriceInCurrency.replaceAll("[^\\d.]", ""));
							if (allCharges != null) {
								String xClickDot = String.format("//span[contains(text(), '%s')]/ancestor::div[@fxlayout='row']//span[contains(@class,'itn-icon-more-horizontal')]", product.getName());
								getBrowserDriver().click(byXpath(xClickDot));
								if (getBrowserDriver().isElementPresent(byXpath(xEditCharges))) {
									getBrowserDriver().click(byXpath(xEditCharges));
									String LineItemTotalPoCost1 = getBrowserDriver().getText(byXpath(xLineItemTotalCharge)).trim();
									TotalPrice += Double.parseDouble(LineItemTotalPoCost1.replaceAll("[^\\d.]", ""));
									getBrowserDriver().click(byXpath(xDoneWithCharges));
								}else {
									ItradeOrderHelperFactory.clickOnBlankArea();
								}
							}
						}
					}
				}
				if (Double.compare(TotalPoCost, TotalPrice) == 0) {
					return true;
				}else {
					return false;
				}
			}
}
