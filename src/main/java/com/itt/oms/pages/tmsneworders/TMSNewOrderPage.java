package com.itt.oms.pages.tmsneworders;

import static com.itt.browser.common.BrowserLocator.byCssSelector;
import static com.itt.browser.common.BrowserLocator.byFrame;
import static com.itt.browser.common.BrowserLocator.byId;
import static com.itt.browser.common.BrowserLocator.byName;
import static com.itt.browser.common.BrowserLocator.selectDropDownValue;
import static com.itt.browser.common.BrowserLocator.withCustomTimeout;
import static com.itt.browser.common.BrowserLocator.withSwitchToMainWindow;
import static com.itt.factoryhelper.BrowserHelperFactory.getBrowserDriver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itt.common.Timeout;
import com.itt.factoryhelper.BrowserHelperFactory.DROPDOWN;
import com.itt.oms.datamodelhelper.OMSDataModelHelperFactory;
import com.itt.oms.helper.OMSHelperFactory;

public class TMSNewOrderPage {

	private static final Logger LOG = LoggerFactory.getLogger(TMSNewOrderPage.class);
	private static String cssLogisticsMenu = "a[title='Logistics']";
	private static String cssLoadBuilderMenu = "a[title='Load Builder'],[title='Load Manager']";
	private static String nSearchPODropdown = "SearchPOLoad";
	private static String idSelectCheckBox = "selections";
	private static String nSelectCarrierDropDown = "carrier";
	private static String cssSelectCarrierDropDown = "select[name*=carrier]";
	private static String idFrameModalContainer = "ContainerDivframe";
	private static String cssSubmitButton = "input[name*='Submit']";
	private static String cssSubmitBuildLoadsButton = "input[name='BuildLoads']";
	private static String cssLoadNumber = "tr[id^='trsh'] td:nth-child(2)";
	private static String cssSelectCarrierLink = "a[href*=OpenCarrierPopup]";
	private static String nPOSelections = "selections";
	
	
	public void openLogisticsFromMenu() throws Exception {

		LOG.info("Switch to Left Frame");
		OMSHelperFactory.switchToLeftFrame();

		LOG.info("Click on Logistics Menu from the left menu");
		getBrowserDriver().click(byCssSelector(cssLogisticsMenu));
	}

	public void selectCarrier(String poNumber, OMSDataModelHelperFactory omsDataModelHelperFactory) throws Exception {
		String carrier= omsDataModelHelperFactory.getTmsDataModelNewOrders().getCarrier();
		LOG.info("Open Receviced Order Menu");
		this.openLogisticsFromMenu();

		LOG.info("Switch to Left Frame");
		OMSHelperFactory.switchToLeftFrame();

		LOG.info("Click on Load Builder menu from Left Frame");
		getBrowserDriver().click(byCssSelector(cssLoadBuilderMenu));
		
		LOG.info("Switch to Main Frame");
		OMSHelperFactory.switchToMainFrame();

		if (isSearchByPODropDownPresent()) {
			getBrowserDriver().selectDropDown(selectDropDownValue(byName(nSearchPODropdown),DROPDOWN.VISIBLETEXT.toString(), "PO"));
		} else {
			LOG.info("PO drop down not present");
		}

		LOG.info("Switch to Main Frame");
		OMSHelperFactory.switchToMainFrame();
		OMSHelperFactory.lookUpPO(poNumber);

		LOG.info("Select the PO Checkbox");
		
		getBrowserDriver().click(byName(nPOSelections));

		LOG.info("Click on Select Carrier link");
		getBrowserDriver().click(byCssSelector(cssSelectCarrierLink));

		LOG.debug("Switch to modal container frame");
		getBrowserDriver().switchToFrame(withSwitchToMainWindow(byFrame(idFrameModalContainer), false));

		LOG.info("Select the carrier: " + carrier);
		getBrowserDriver().selectDropDown(selectDropDownValue(byCssSelector(cssSelectCarrierDropDown), DROPDOWN.VISIBLETEXT.toString(), carrier));

		getBrowserDriver().waitForPageLoad();

		LOG.info("Switch to Main Frame");
		OMSHelperFactory.switchToMainFrame();

		this.clickSubmitButton();
	}
	
	public void clickSubmitButton() throws Exception {
		LOG.info("Click on Submit Button");
		if (getBrowserDriver().isElementPresent(withCustomTimeout(byCssSelector(cssSubmitBuildLoadsButton), Timeout.TWO_SECONDS_TIMEOUT))) {
			getBrowserDriver().click(byCssSelector(cssSubmitBuildLoadsButton));
		} else {
			getBrowserDriver().click(byCssSelector(cssSubmitButton));
		}
	}

	public boolean isSearchByPODropDownPresent() throws Exception {
		return getBrowserDriver().findElements(byName(nSearchPODropdown)).size() > 0;
	}

    public void clickCarrier() throws Exception {
    	getBrowserDriver().click(byId(idSelectCheckBox));
	    getBrowserDriver().click(byName(nSelectCarrierDropDown));
    }

    public String getLoadNumber() throws Exception {
    	return getBrowserDriver().getText(byCssSelector(cssLoadNumber));
    }
    
}
