package com.itt.businesshelperfactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itt.oms.helper.OMSHelperFactory;

public class ITTBusinessHelperFactory {

	private static final Logger LOG = LoggerFactory.getLogger(ITTBusinessHelperFactory.class);

	private static ITTBusinessHelperFactory ittBusinessHelperFactory = new ITTBusinessHelperFactory();
	private OMSHelperFactory oMSHelperFactory = new OMSHelperFactory();

	private ITTBusinessHelperFactory() { }

	public static ITTBusinessHelperFactory getInstance() {
		return ittBusinessHelperFactory;
	}

	public OMSHelperFactory getOMSHelperFactory() {
		return this.oMSHelperFactory;
	}
}
