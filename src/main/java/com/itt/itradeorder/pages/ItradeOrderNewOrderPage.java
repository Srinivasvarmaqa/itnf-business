package com.itt.itradeorder.pages;

import static com.itt.browser.common.BrowserLocator.*;
import static com.itt.factoryhelper.BrowserHelperFactory.getBrowserDriver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itt.itradeorder.datamodelhelper.ItradeOrderDataModelHelperFactory;
import com.itt.itradeorder.helper.ItradeOrderHelperFactory;
import com.itt.oms.helper.OMSHelperFactory;

public class ItradeOrderNewOrderPage {
	private static final Logger LOG = LoggerFactory.getLogger(ItradeOrderNewOrderPage.class);

	private static String nNewOrder = "newOrder";
	private static String idPoNumber = "poInput";
	private static String nSeller = "Seller";
	private static String nRouting = "Routing*";
	private static String nShipDate = "shipDate";
	private static String nShippingArrivalDate = "arrivalDate";
	private static String nShipFrom = "Ship From";
	private static String nShipTo = "Ship To *";
	private static String xCreateButton = "//span[contains(text(),'CREATE')]";
	private static String cssCrossButton = "div.close-button span.itn-icon-close-x";
	private static String cssSubmitButton = "button.action-primary[type='submit']";
	
	
	public void openNewOrder() throws Exception {
		LOG.info("Click on New Order");
		ItradeOrderHelperFactory.waitForloaderToDisapper();
		getBrowserDriver().click(byName(nNewOrder));
	}

	public boolean isPoNumberExists() throws Exception {
		return getBrowserDriver().isElementPresent(byId(idPoNumber));
	}

	public boolean isSellerExists() throws Exception {
		return getBrowserDriver().isElementPresent(byName(nSeller));
	}

	public boolean isRoutingExists() throws Exception {
		return getBrowserDriver().isElementPresent(byName(nRouting));
	}

	public boolean isShipDateExists() throws Exception {
		return getBrowserDriver().isElementPresent(byName(nShipDate));
	}

	public boolean isArrivalDateExists() throws Exception {
		return getBrowserDriver().isElementPresent(byName(nShipDate));
	}

	public boolean isShipFromExists() throws Exception {
		return getBrowserDriver().isElementPresent(byName(nShipFrom));
	}

	public boolean isShipToExists() throws Exception {
		return getBrowserDriver().isElementPresent(byName(nShipTo));
	}
	
	public void createPurchaseOrder(ItradeOrderDataModelHelperFactory itradeOrderDataModelHelperFactory) throws Exception {
		String vendor = itradeOrderDataModelHelperFactory.getItradeOrderDataModelNewOrder().getVendor();
		String shipTo = itradeOrderDataModelHelperFactory.getItradeOrderDataModelNewOrder().getShipToWarehouse();
		String shipFrom = itradeOrderDataModelHelperFactory.getItradeOrderDataModelNewOrder().getShipFromWarehouse();
		String routing = itradeOrderDataModelHelperFactory.getItradeOrderDataModelNewOrder().getRouting();
		String templateName = itradeOrderDataModelHelperFactory.getItradeOrderDataModelNewOrder().getTemplatename();

		LOG.info("Open Buyer Menu");
		this.openNewOrder();

		LOG.info("Enter PO Number");
		this.enterPONumber();

		if (vendor != null) {
			this.selectVendor(vendor);
		}

		if (routing != null) {
			this.selectRouting(routing);
		}

		this.addShipDate();
		this.addArrivalDate();
		if (shipFrom != null) {
			this.shipFrom(shipFrom);
		}

		if (shipTo != null) {
			this.shipTo(shipTo);
		}

		LOG.info("Click Create Order Button");
		getBrowserDriver().click(withScroll(byXpath(xCreateButton)));

		ItradeOrderHelperFactory.waitForloaderToDisapper();
	}

	public void selectVendor(String vendor) throws Exception {
		String xVendorOptions = String.format("//div[contains(@id,'cdk-overlay-')]//mat-option[@role='option']/span[contains(text(), '%s')]", vendor);
		LOG.debug("Click on the Seller field");
		getBrowserDriver().click(byName(nSeller));

		LOG.debug("Select the vendor:" + vendor);
		getBrowserDriver().click(byXpath(xVendorOptions));
	}

	public void enterPONumber() throws Exception {
		LOG.debug("Enter PO Number");
		ItradeOrderHelperFactory.waitForloaderToDisapper();
		long randomNumber = (long) Math.floor(Math.random() * 900000L) + 10000L;
		getBrowserDriver().sendValue(withText(byId(idPoNumber), "Auto" + randomNumber));
	}

	public void selectRouting(String routing) throws Exception {
		String xRoutingOptins = String.format("//div[contains(@id,'cdk-overlay-')]//mat-option[@role='option']/span[contains(text(), '%s')]", routing);
		LOG.debug("Click on the Routing field");
		getBrowserDriver().click(byName(nRouting));

		LOG.debug("Select the routing option:" + routing);
		getBrowserDriver().click(byXpath(xRoutingOptins));
	}

	public void addShipDate() throws Exception {
		LOG.debug("Enter Ship Date");
		String shipdate = OMSHelperFactory.getDate();
		getBrowserDriver().sendValue(withText(byName(nShipDate), shipdate));
	}

	public void addArrivalDate() throws Exception {
		LOG.debug("Enter Arrival Date");
		String arrivaldate = OMSHelperFactory.getDate();
		getBrowserDriver().sendValue(withText(byName(nShippingArrivalDate), arrivaldate));
	}

	public void shipTo(String shipTo) throws Exception {
		String xShipToOptions = String.format("//div[contains(@id,'cdk-overlay-')]//mat-option[@role='option']/span[contains(text(), '%s')]", shipTo);
		LOG.debug("Click on the Ship To field");
		getBrowserDriver().click(byName(nShipTo));

		LOG.debug("Select the ShipTo option:" + shipTo);
		getBrowserDriver().click(byXpath(xShipToOptions));
	}

	public void shipFrom(String shipFrom) throws Exception {
		String xShipFromOptions = String.format("//div[contains(@id,'cdk-overlay-')]//mat-option[@role='option']/span[contains(text(), '%s')]", shipFrom);
		LOG.debug("Click on the ShipFrom field");
		getBrowserDriver().click(byName(nShipFrom));

		LOG.debug("Select the shipFrom option:" + shipFrom);
		getBrowserDriver().click(byXpath(xShipFromOptions));
	}

	public void clickOnCloseButton() throws Exception {
		LOG.debug("Click on close button");
		getBrowserDriver().click(byCssSelector(cssCrossButton));
	}

	public void clickOnSubmitButton() throws Exception {
		LOG.debug("Click on Submit Button");
		getBrowserDriver().click(byCssSelector(cssSubmitButton));
		ItradeOrderHelperFactory.waitForloaderToDisapper();
	}
	
	public void clickOnCreateButton() throws Exception {
		LOG.info("Click on Create Order Button");
		getBrowserDriver().click(withScroll(byXpath(xCreateButton)));	
		ItradeOrderHelperFactory.waitForloaderToDisapper();
	}

}
