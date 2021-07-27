package com.itt.itradeorder.datamodelhelper;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ItradeOrderDataModelOrderDetails {
	private static final Logger LOG = LoggerFactory.getLogger(ItradeOrderDataModelOrderDetails.class);
	@JsonProperty("buyerAddProducts")
	List<ItradeOrderDataModelProducts> buyeraddproducts;
	@JsonProperty("vendorAddProducts")
	List<ItradeOrderDataModelProducts> vendoraddproducts;
	@JsonProperty("buyerUpdateProducts")
	List<ItradeOrderDataModelProducts> buyerupdateproducts;
	@JsonProperty("vendorUpdateProducts")
	List<ItradeOrderDataModelProducts> vendorupdateproducts;
	@JsonProperty("buyerLineLevelCharges")
	List<ItradeOrderDataModelProducts> buyerlinelevelcharges;
	@JsonProperty("buyerOrderLevelCharges")
	List<ItradeOrderDataModelProducts> buyerorderlevelcharges;
	@JsonProperty("vendorLineLevelCharges")
	List<ItradeOrderDataModelProducts> vendorlinelevelcharges;
	@JsonProperty("vendorOrderLevelCharges")
	List<ItradeOrderDataModelProducts> vendororderlevelcharges;
}
