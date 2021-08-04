package com.itt.spend.datamodelhelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class SPENDDataModelLoginUsers {
	private static final Logger LOG = LoggerFactory.getLogger(SPENDDataModelLoginUsers.class);


	@JsonProperty("operator")
	private String operatorusername;
	@JsonProperty("password")
	private String password;
	@JsonProperty("proxyid")
	private String proxyid;
}
