package com.itt.oms.datamodelhelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.Data;

@Data
public class OMSDataModelPurchaseOrder {
	private static final Logger LOG = LoggerFactory.getLogger(OMSDataModelPurchaseOrder.class);

	private String vendor;
	private String shipToWarehouse;
	private String routing;
	private String mode;
	private String currency;
	private String templatename;
}
