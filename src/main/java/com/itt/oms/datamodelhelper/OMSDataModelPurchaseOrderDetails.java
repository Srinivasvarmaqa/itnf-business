package com.itt.oms.datamodelhelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class OMSDataModelPurchaseOrderDetails {
	private static final Logger LOG = LoggerFactory.getLogger(OMSDataModelPurchaseOrderDetails.class);

	@JsonProperty("quantity")
	private int quantity;
	@JsonProperty("price")
	private double price;
	@JsonProperty("vendor")
	private String vendorname;
	@JsonProperty("routing")
	private String routing;
	@JsonProperty("custFrtCost")
	private String custfrtcost;
	
}
