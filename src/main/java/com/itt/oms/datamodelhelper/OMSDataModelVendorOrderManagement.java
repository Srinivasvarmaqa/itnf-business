package com.itt.oms.datamodelhelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.Data;

@Data
public class OMSDataModelVendorOrderManagement {
	private static final Logger LOG = LoggerFactory.getLogger(OMSDataModelVendorOrderManagement.class);

	private String shippingwarehouse;
}