package com.itt.itradeorder.datamodelhelper;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.Data;

@Data
public class ItradeOrderDataModelNewOrder {
	private static final Logger LOG = LoggerFactory.getLogger(ItradeOrderDataModelNewOrder.class);

	private String vendor;
	private String shipToWarehouse;
	private String shipFromWarehouse;
	private String routing;
	private String mode;
	private String currency;
	private String templatename;
}
