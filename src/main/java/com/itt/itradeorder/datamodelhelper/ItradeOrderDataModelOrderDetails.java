package com.itt.itradeorder.datamodelhelper;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.Data;

@Data
public class ItradeOrderDataModelOrderDetails {
	private static final Logger LOG = LoggerFactory.getLogger(ItradeOrderDataModelOrderDetails.class);

	List<ItradeOrderDataModelProducts> buyeraddproducts;
	List<ItradeOrderDataModelProducts> vendoraddproducts;
	List<ItradeOrderDataModelProducts> buyerupdateproducts;
	List<ItradeOrderDataModelProducts> vendorupdateproducts;
	List<ItradeOrderDataModelProducts> buyerlinelevelcharges;
	List<ItradeOrderDataModelProducts> buyerorderlevelcharges;
	List<ItradeOrderDataModelProducts> vendorlinelevelcharges;
	List<ItradeOrderDataModelProducts> vendororderlevelcharges;
}
