package com.itt.itradeorder.datamodelhelper;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.Data;

@Data
public class ItradeOrderDataModelOrderDetails {
	private static final Logger LOG = LoggerFactory.getLogger(ItradeOrderDataModelOrderDetails.class);

	List<ItradeOrderDataModelProducts> products;
}
