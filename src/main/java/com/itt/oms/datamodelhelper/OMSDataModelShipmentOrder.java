package com.itt.oms.datamodelhelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class OMSDataModelShipmentOrder {
	private static final Logger LOG = LoggerFactory.getLogger(OMSDataModelShipmentOrder.class);

	@JsonProperty("cool")
	private String cool;
	@JsonProperty("bioEngineer")
	private String bioengineer;
}
