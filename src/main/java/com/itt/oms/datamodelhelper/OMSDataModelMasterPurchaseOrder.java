package com.itt.oms.datamodelhelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class OMSDataModelMasterPurchaseOrder {
	private static final Logger LOG = LoggerFactory.getLogger(OMSDataModelMasterPurchaseOrder.class);

	@JsonProperty("mode")
	private String mode;
	@JsonProperty("group")
	private String group;
	@JsonProperty("routing")
	private String routing;
	@JsonProperty("shipTo")
	private String shipToWarehouse;
	@JsonProperty("invoiceFrom")
	private String invoiceFrom;
	@JsonProperty("template")
	private String templatename;
	@JsonProperty("customer")
	private String customer;
}
