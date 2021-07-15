package com.itt.itradeorder.datamodelhelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.Data;

@Data
public class ItradeOrderDataModelLoginUsers {
	private static final Logger LOG = LoggerFactory.getLogger(ItradeOrderDataModelLoginUsers.class);

	private String buyerusername;
	private String vendorusername;
	private String carrierusername;
	private String xdockusername;
	private String password;
	private String operatorusername;
}
