package com.itt.oms.datamodelhelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.Data;

@Data
public class OMSDataModelPurchaseOrderDetails {
	private static final Logger LOG = LoggerFactory.getLogger(OMSDataModelPurchaseOrderDetails.class);

	private int quantity;
	private double price;
	private String vendorname;
	private String routing;
	private String custfrtcost;
	
}
