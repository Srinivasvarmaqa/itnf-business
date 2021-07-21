package com.itt.itradeorder.datamodelhelper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ItradeOrderDataModelProducts {
	private static final Logger LOG = LoggerFactory.getLogger(ItradeOrderDataModelProducts.class);

	@JsonProperty("quantity")
	private int quantity;
	@JsonProperty("price")
	private double price;
	@JsonProperty("name")
	private String name;
	@JsonProperty("chargeType")
	private String chargetype;
	@JsonProperty("calculationType")
	private String calculationtype;
	@JsonProperty("amount")
	private Double amount;
}
