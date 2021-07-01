package com.itt.itradeorder.datamodelhelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.Getter;
import lombok.Setter;


public class ItradeOrderDataModelHelperFactory {
	private static final Logger LOG = LoggerFactory.getLogger(ItradeOrderDataModelHelperFactory.class);

	private static ItradeOrderDataModelHelperFactory itradeOrderModelHelperFactory = new ItradeOrderDataModelHelperFactory();
	private ItradeOrderDataModelLoginUsers itradeOrderDataModelLoginUsers = new ItradeOrderDataModelLoginUsers();
	private ItradeOrderDataModelNewOrder itradeOrderDataModelNewOrder = new ItradeOrderDataModelNewOrder();

	@Getter
	@Setter
	private String project;

	public ItradeOrderDataModelLoginUsers getItradeOrderDataModelLoginUsers() {
		return this.itradeOrderDataModelLoginUsers;
	}

	public ItradeOrderDataModelNewOrder getItradeOrderDataModelNewOrder() {
		return this.itradeOrderDataModelNewOrder;
	}
}
