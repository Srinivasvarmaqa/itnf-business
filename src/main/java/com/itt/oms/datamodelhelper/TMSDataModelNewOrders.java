package com.itt.oms.datamodelhelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class TMSDataModelNewOrders {
	private static final Logger LOG = LoggerFactory.getLogger(TMSDataModelNewOrders.class);

	@JsonProperty("carrier")
	private String carrier;
}
