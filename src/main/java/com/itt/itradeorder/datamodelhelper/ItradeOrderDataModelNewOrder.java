package com.itt.itradeorder.datamodelhelper;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ItradeOrderDataModelNewOrder {
	private static final Logger LOG = LoggerFactory.getLogger(ItradeOrderDataModelNewOrder.class);
	@JsonProperty("vendor")
	private String vendor;
	@JsonProperty("shipTo")
	private String shipToWarehouse;
	@JsonProperty("shipFrom")
	private String shipFromWarehouse;
	@JsonProperty("routing")
	private String routing;
	@JsonProperty("mode")
	private String mode;
	@JsonProperty("currency")
	private String currency;
	@JsonProperty("template")
	private String templatename;
	@JsonProperty("transportMode")
	private String transportmode;
	@JsonProperty("buyer")
	private String buyer;
}
