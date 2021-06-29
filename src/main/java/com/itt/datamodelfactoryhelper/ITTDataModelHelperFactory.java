package com.itt.datamodelfactoryhelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itt.oms.datamodelhelper.OMSDataModelHelperFactory;

public class ITTDataModelHelperFactory {
	private static final Logger LOG = LoggerFactory.getLogger(ITTDataModelHelperFactory.class);
	private static ITTDataModelHelperFactory ittModelHelperFactory = new ITTDataModelHelperFactory();
	private OMSDataModelHelperFactory omsModelHelperFactory = new OMSDataModelHelperFactory();

	private ITTDataModelHelperFactory() { }

	public static ITTDataModelHelperFactory getInstance() {
		return ittModelHelperFactory;
	}

	public OMSDataModelHelperFactory getOmsDataModelHelperFactory() {
		return this.omsModelHelperFactory;
	}
}
