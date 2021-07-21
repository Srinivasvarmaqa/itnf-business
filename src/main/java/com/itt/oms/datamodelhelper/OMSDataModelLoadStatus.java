package com.itt.oms.datamodelhelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class OMSDataModelLoadStatus {
	private static final Logger LOG = LoggerFactory.getLogger(OMSDataModelLoadStatus.class);

	@JsonProperty("driver")
	private String driver;
	@JsonProperty("vehicle")
	private String vehicle;
}
