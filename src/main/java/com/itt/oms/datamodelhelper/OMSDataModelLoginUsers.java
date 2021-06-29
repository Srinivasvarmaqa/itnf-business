package com.itt.oms.datamodelhelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.Data;

@Data
public class OMSDataModelLoginUsers {
	private static final Logger LOG = LoggerFactory.getLogger(OMSDataModelLoginUsers.class);

	private String buyerusername;
	private String vendorusername;
	private String carrierusername;
	private String xdockusername;
	private String password;
	private String operatorusername;
}
