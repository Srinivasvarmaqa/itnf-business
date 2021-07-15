package com.itt.itradeorder.datamodelhelper;
import static com.itt.browser.common.BrowserLocator.byCssSelector;
import static com.itt.browser.common.BrowserLocator.byXpath;
import static com.itt.browser.common.BrowserLocator.withCustomTimeout;
import static com.itt.factoryhelper.BrowserHelperFactory.getBrowserDriver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itt.common.Timeout;

import lombok.Getter;
import lombok.Setter;


public class ItradeOrderDataModelHelperFactory {
	private static final Logger LOG = LoggerFactory.getLogger(ItradeOrderDataModelHelperFactory.class);

	private static ItradeOrderDataModelHelperFactory itradeOrderModelHelperFactory = new ItradeOrderDataModelHelperFactory();
	private ItradeOrderDataModelLoginUsers itradeOrderDataModelLoginUsers = new ItradeOrderDataModelLoginUsers();
	private ItradeOrderDataModelNewOrder itradeOrderDataModelNewOrder = new ItradeOrderDataModelNewOrder();
	private ItradeOrderDataModelOrderDetails  itradeOrderDataModelOrderDetails = new  ItradeOrderDataModelOrderDetails();

	@Getter
	@Setter
	private String project;

	public ItradeOrderDataModelLoginUsers getItradeOrderDataModelLoginUsers() {
		return this.itradeOrderDataModelLoginUsers;
	}

	public ItradeOrderDataModelNewOrder getItradeOrderDataModelNewOrder() {
		return this.itradeOrderDataModelNewOrder;
	}
	
	public  ItradeOrderDataModelOrderDetails getItradeOrderDataModelOrderDetails() {
		return this. itradeOrderDataModelOrderDetails;
	}
}
