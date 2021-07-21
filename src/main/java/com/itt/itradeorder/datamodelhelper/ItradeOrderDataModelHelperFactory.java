package com.itt.itradeorder.datamodelhelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;


public class ItradeOrderDataModelHelperFactory {
	private static final Logger LOG = LoggerFactory.getLogger(ItradeOrderDataModelHelperFactory.class);

	private static ItradeOrderDataModelHelperFactory itradeOrderModelHelperFactory = new ItradeOrderDataModelHelperFactory();
	@JsonProperty("LoginUsers")
	private ItradeOrderDataModelLoginUsers itradeOrderDataModelLoginUsers = new ItradeOrderDataModelLoginUsers();
	@JsonProperty("NewOrder")
	private ItradeOrderDataModelNewOrder itradeOrderDataModelNewOrder = new ItradeOrderDataModelNewOrder();
	@JsonProperty("OrderDetails")
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
