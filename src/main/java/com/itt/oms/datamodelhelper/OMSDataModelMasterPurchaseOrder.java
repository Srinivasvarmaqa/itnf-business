package com.itt.oms.datamodelhelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.Data;

@Data
public class OMSDataModelMasterPurchaseOrder {
	private static final Logger LOG = LoggerFactory.getLogger(OMSDataModelMasterPurchaseOrder.class);

	private String mode;
	private String group;
	private String routing;
	private String shipToWarehouse;
	private String invoiceFrom;
	private String templatename;
	private String customer;
}
