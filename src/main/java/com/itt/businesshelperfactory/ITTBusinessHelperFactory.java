package com.itt.businesshelperfactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itt.itradeorder.helper.ItradeOrderHelperFactory;
import com.itt.oms.helper.OMSHelperFactory;
import com.itt.omsrewrite.helper.OMSRewriteHelperFactory;
import com.itt.spend.helper.SPENDHelperFactory;

public class ITTBusinessHelperFactory {

	private static final Logger LOG = LoggerFactory.getLogger(ITTBusinessHelperFactory.class);

	private static ITTBusinessHelperFactory ittBusinessHelperFactory = new ITTBusinessHelperFactory();
	private OMSHelperFactory oMSHelperFactory = new OMSHelperFactory();
	private SPENDHelperFactory sPENDHelperFactory = new SPENDHelperFactory();
	private ItradeOrderHelperFactory itradeOrderHelperFactory = new ItradeOrderHelperFactory();
	private OMSRewriteHelperFactory oMSRewriteHelperFactory = new OMSRewriteHelperFactory();

	private ITTBusinessHelperFactory() { }

	public static ITTBusinessHelperFactory getInstance() {
		return ittBusinessHelperFactory;
	}

	public OMSHelperFactory getOMSHelperFactory() {
		return this.oMSHelperFactory;
	}
	public SPENDHelperFactory getSPENDHelperFactory() {
		return this.sPENDHelperFactory;
	}
	
	public ItradeOrderHelperFactory getItradeOrderHelperFactory() {
		return this.itradeOrderHelperFactory;
	}

	public OMSRewriteHelperFactory getOMSRewriteHelperFactory() {
		return this.oMSRewriteHelperFactory;
	}
}
