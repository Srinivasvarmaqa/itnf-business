package com.itt.itradeorder.datamodelhelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ItradeOrderDataModelLoginUsers {
	private static final Logger LOG = LoggerFactory.getLogger(ItradeOrderDataModelLoginUsers.class);
	@JsonProperty("buyer")
	private String buyerusername;
	@JsonProperty("vendor")
	private String vendorusername;
	@JsonProperty("carrier")
	private String carrierusername;
	@JsonProperty("xdock")
	private String xdockusername;
	@JsonProperty("operator")
	private String operatorusername;
	@JsonProperty("password")
	private String password;
}
