package com.itt.itradeorder.datamodelhelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.Data;

@Data
public class ItradeOrderDataModelNewOrderDetails {
	private static final Logger LOG = LoggerFactory.getLogger(ItradeOrderDataModelNewOrderDetails.class);

	private String addproduct;
}
