package com.itt.oms.datamodelhelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.Data;

@Data
public class OMSDataModelXdock {
	private static final Logger LOG = LoggerFactory.getLogger(OMSDataModelXdock.class);

	private String pickuploc;
}