package com.itt.datamodelfactoryhelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itt.oms.datamodelhelper.OMSDataModelHelperFactory;
import com.itt.itradeorder.datamodelhelper.ItradeOrderDataModelHelperFactory;

public class ITTDataModelHelperFactory {
	private static final Logger LOG = LoggerFactory.getLogger(ITTDataModelHelperFactory.class);
	private static ITTDataModelHelperFactory ittModelHelperFactory = new ITTDataModelHelperFactory();
	private OMSDataModelHelperFactory omsModelHelperFactory = new OMSDataModelHelperFactory();
	private ItradeOrderDataModelHelperFactory itradeOrderModelHelperFactory = new ItradeOrderDataModelHelperFactory();
	
	private ITTDataModelHelperFactory() { }

	public static ITTDataModelHelperFactory getInstance() {
		return ittModelHelperFactory;
	}

	public OMSDataModelHelperFactory getOmsDataModelHelperFactory() {
		return this.omsModelHelperFactory;
	}
	
	public ItradeOrderDataModelHelperFactory getItradeOrderDataModelHelperFactory() {
		return this.itradeOrderModelHelperFactory;
	}
}
