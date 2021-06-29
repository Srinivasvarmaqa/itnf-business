package com.itt.oms.datamodelhelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.Data;

@Data
public class OMSDataModelLoadStatus {
	private static final Logger LOG = LoggerFactory.getLogger(OMSDataModelLoadStatus.class);

	private String driver;
	private String vehicle;
}
