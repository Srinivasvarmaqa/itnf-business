package com.itt.oms.datamodelhelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class OMSDataModelInvoiceOrder {
	private static final Logger LOG = LoggerFactory.getLogger(OMSDataModelInvoiceOrder.class);

	@JsonProperty("searchType")
	private String searchtype;
}
