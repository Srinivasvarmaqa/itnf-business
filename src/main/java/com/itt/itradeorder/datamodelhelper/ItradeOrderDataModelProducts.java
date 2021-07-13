package com.itt.itradeorder.datamodelhelper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.Data;

@Data
public class ItradeOrderDataModelProducts {
	private static final Logger LOG = LoggerFactory.getLogger(ItradeOrderDataModelProducts.class);

	private int quantity;
	private double price;
	private String name;
	private String chargetype;
	private String calculationtype;
	private Double amount;
}
