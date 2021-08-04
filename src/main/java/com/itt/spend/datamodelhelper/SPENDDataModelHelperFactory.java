package com.itt.spend.datamodelhelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;


public class SPENDDataModelHelperFactory {
	private static final Logger LOG = LoggerFactory.getLogger(SPENDDataModelHelperFactory.class);

	private static SPENDDataModelHelperFactory spendDataModelHelperFactory = new SPENDDataModelHelperFactory();
	@JsonProperty("LoginUsers")
	private SPENDDataModelLoginUsers spendDataModelLoginUsers = new SPENDDataModelLoginUsers();
	
	@Getter
	@Setter
	private String project;

	@JsonProperty("APP_URL")
	@Getter
	@Setter
	private String APP_URL;

	public SPENDDataModelLoginUsers getSpendDataModelLoginUsers() {
		return this.spendDataModelLoginUsers;
	}

	
}
