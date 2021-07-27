package com.itt.businesshelperfactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itt.itradeorder.helper.ItradeOrderHelperFactory;
import com.itt.oms.helper.OMSHelperFactory;
import com.itt.omsrewrite.helper.OMSRewriteHelperFactory;

public class ITTBusinessHelperFactory {

	private static final Logger LOG = LoggerFactory.getLogger(ITTBusinessHelperFactory.class);

	private static ITTBusinessHelperFactory ittBusinessHelperFactory = new ITTBusinessHelperFactory();
	private OMSHelperFactory oMSHelperFactory = new OMSHelperFactory();
	private ItradeOrderHelperFactory itradeOrderHelperFactory = new ItradeOrderHelperFactory();
	private OMSRewriteHelperFactory oMSRewriteHelperFactory = new OMSRewriteHelperFactory();

	private ITTBusinessHelperFactory() { }

	public static ITTBusinessHelperFactory getInstance() {
		return ittBusinessHelperFactory;
	}

	public OMSHelperFactory getOMSHelperFactory() {
		return this.oMSHelperFactory;
	}
	
	public ItradeOrderHelperFactory getItradeOrderHelperFactory() {
		return this.itradeOrderHelperFactory;
	}

	public OMSRewriteHelperFactory getOMSRewriteHelperFactory() {
		return this.oMSRewriteHelperFactory;
	}
}
