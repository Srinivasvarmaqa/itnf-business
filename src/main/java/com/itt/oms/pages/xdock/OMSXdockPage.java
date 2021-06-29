package com.itt.oms.pages.xdock;

import static com.itt.browser.common.BrowserLocator.byCssSelector;
import static com.itt.browser.common.BrowserLocator.byFrame;
import static com.itt.browser.common.BrowserLocator.byXpath;
import static com.itt.browser.common.BrowserLocator.selectDropDownValue;
import static com.itt.browser.common.BrowserLocator.withSwitchToMainWindow;
import static com.itt.browser.common.BrowserLocator.withText;
import static com.itt.factoryhelper.BrowserHelperFactory.getBrowserDriver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itt.factoryhelper.BrowserHelperFactory.DROPDOWN;
import com.itt.oms.datamodelhelper.OMSDataModelHelperFactory;
import com.itt.oms.helper.OMSHelperFactory;

public class OMSXdockPage {

	private static final Logger LOG = LoggerFactory.getLogger(OMSXdockPage.class);
	private static String idFrameModalContainer = "ModalContainerDivframe";
	private static String frameTopName = "frameTop";
	private static String xPickUpDropDown = "//select[@name='pickuploc']";
	private static String xSelectCheckBox = "//input[@name='vendors']";
	private static String cssXdockMenu = "a[title='Xdock']";
	private static String xOrderStatus = "//a[@title='Xdock']/following-sibling::ul//a[contains(@title, 'Order')]";
	private static String cssPONumberlistedLink = "a[href*='details']";
	private static String cssSubmitButton = "input.buttoncss[name*='Submit']";
	private static String cssShipDateText = "input[name^='shipdate']";

	public void selectXdockOrderStatusFromMenu() throws Exception {
		LOG.debug("Switch to Left Frame");
		OMSHelperFactory.switchToLeftFrame();

		LOG.info("Click on Xdock Menu from the left menu");
		getBrowserDriver().click(byCssSelector(cssXdockMenu));

		LOG.info("Click on Order Status menu");
		getBrowserDriver().click(byXpath(xOrderStatus));
	}
	
	public void selectXdock(OMSDataModelHelperFactory omsDataModelHelperFactory) throws Exception {
		String pickUpLoc = omsDataModelHelperFactory.getOmsDataModelXdock().getPickuploc();
		
		LOG.debug("Switch to modal container frame");
		getBrowserDriver().switchToFrame(withSwitchToMainWindow(byFrame(idFrameModalContainer), false));

		LOG.debug("Switch to frame top");
		getBrowserDriver().switchToFrame(withSwitchToMainWindow(byFrame(frameTopName), false));
		
		LOG.info("Select xdock");
		getBrowserDriver().selectDropDown(selectDropDownValue(byXpath(xPickUpDropDown),DROPDOWN.VISIBLETEXT.toString(), pickUpLoc));
		
		LOG.info("Click on Select Checkbox");
		getBrowserDriver().click(byXpath(xSelectCheckBox));
		
		if (getBrowserDriver().getText(byCssSelector(cssShipDateText)) != null) { 
			LOG.info("Enter send shipdate");
			this.sendShipDate();
		}

		LOG.info("Click on Submit button");
		this.clickSubmitButton();
		
		LOG.debug("Switch to main frame");
		OMSHelperFactory.switchToMainFrame();
		
		getBrowserDriver().waitForPageLoad();
		getBrowserDriver().closeAllChildWindowPopups();
	}

	public void confirmFromXdock(String poNumber) throws Exception {
		LOG.info("Navigate to Order Status menu");
		this.selectXdockOrderStatusFromMenu();
	
		LOG.debug("Switch to main frame");
		OMSHelperFactory.switchToMainFrame();
		
		LOG.info("Look for poNumber" + poNumber);
		OMSHelperFactory.lookUpPO(poNumber);
		
		LOG.info("Find the PO number from the list");
		getBrowserDriver().click(byCssSelector(cssPONumberlistedLink));
		
		LOG.info("Click on Submit button");
		this.clickSubmitButton();
	}

	public void clickSubmitButton() throws Exception {
		LOG.info("Click On Submit Button");
		getBrowserDriver().click(byCssSelector(cssSubmitButton));
	}

	public void sendShipDate() throws Exception {
		String shipdate = OMSHelperFactory.getDate();
		getBrowserDriver().sendValue(withText(byCssSelector(cssShipDateText), shipdate));
	}
}
