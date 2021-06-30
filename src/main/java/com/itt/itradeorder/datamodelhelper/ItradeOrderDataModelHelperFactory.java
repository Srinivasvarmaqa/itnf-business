package com.itt.itradeorder.datamodelhelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ItradeOrderDataModelHelperFactory {
	private static final Logger LOG = LoggerFactory.getLogger(ItradeOrderDataModelHelperFactory.class);

	private static ItradeOrderDataModelHelperFactory itradeOrderModelHelperFactory = new ItradeOrderDataModelHelperFactory();
	private ItradeOrderDataModelLoginUsers itradeOrderDataModelLoginUsers = new ItradeOrderDataModelLoginUsers();

	private String project;
	public String getProject() { return this.project; }

	public void setProject(String project) {
		this.project = project;
	}

	public ItradeOrderDataModelLoginUsers getItradeOrderDataModelLoginUsers() {
		return this.itradeOrderDataModelLoginUsers;
	}
}
