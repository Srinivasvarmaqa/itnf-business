package com.itt.oms.datamodelhelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class OMSDataModelPurchaseOrder {
	private static final Logger LOG = LoggerFactory.getLogger(OMSDataModelPurchaseOrder.class);

	@JsonProperty("vendor")
	private String vendor;
	@JsonProperty("shipTo")
	private String shipToWarehouse;
	@JsonProperty("routing")
	private String routing;
	@JsonProperty("mode")
	private String mode;
	@JsonProperty("currency")
	private String currency;
	@JsonProperty("template")
	private String templatename;
}
