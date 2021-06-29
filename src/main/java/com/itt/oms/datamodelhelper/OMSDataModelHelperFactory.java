package com.itt.oms.datamodelhelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.Getter;
import lombok.Setter;


public class OMSDataModelHelperFactory {
	private static final Logger LOG = LoggerFactory.getLogger(OMSDataModelHelperFactory.class);

	private static OMSDataModelHelperFactory omsDataModelHelperFactory = new OMSDataModelHelperFactory();
	private OMSDataModelLoginUsers omsDataModelLoginUsers = new OMSDataModelLoginUsers();
	private OMSDataModelPurchaseOrderDetails omsDataModelPurchaseOrderDetails = new OMSDataModelPurchaseOrderDetails();
	private OMSDataModelPurchaseOrder omsDataModelPurchaseOrder = new OMSDataModelPurchaseOrder();
	private OMSDataModelReceiveOrderModel omsDataModelReceiveOrder = new OMSDataModelReceiveOrderModel();
	private OMSDataModelShipmentOrder omsDataModelShipmentOrder = new OMSDataModelShipmentOrder();
	private OMSDataModelInvoiceOrder omsDataModelInvoiceOrder = new OMSDataModelInvoiceOrder();
	private OMSDataModelVendorOrderManagement omsDataModelVendorOrderManagement = new OMSDataModelVendorOrderManagement();
	private OMSDataModelMasterPurchaseOrder omsDataModelMasterPurchaseOrder = new OMSDataModelMasterPurchaseOrder();
	private OMSDataModelXdock omsDataModelXdock = new OMSDataModelXdock();
	private TMSDataModelNewOrders tmsDataModelNewOrders = new TMSDataModelNewOrders();
	private OMSDataModelLoadStatus omsDataModelLoadStatus = new OMSDataModelLoadStatus();

	@Getter
	@Setter
	private String project;
//	public String getProject() { return this.project; }
//
//	public void setProject(String project) {
//		this.project = project;
//	}

	public OMSDataModelLoginUsers getOmsDataModelLoginUsers() {
		return this.omsDataModelLoginUsers;
	}

	public OMSDataModelPurchaseOrderDetails getOmsDataModelPurchaseOrderDetails() {
		return this.omsDataModelPurchaseOrderDetails;
	}

	public OMSDataModelPurchaseOrder getOmsDataModelPurchaseOrder() {
		return this.omsDataModelPurchaseOrder;
	}

	public OMSDataModelReceiveOrderModel getOmsDataModelReceiveOrderModel() {
		return this.omsDataModelReceiveOrder;
	}

	public OMSDataModelShipmentOrder getOmsDataModelShipmentOrder() {
		return this.omsDataModelShipmentOrder;
	}

	public OMSDataModelInvoiceOrder getOmsDataModelInvoiceOrder() {
		return this.omsDataModelInvoiceOrder;
	}

	public OMSDataModelVendorOrderManagement getOmsDataModelVendorOrderManagement() {
		return this.omsDataModelVendorOrderManagement;
	}

	public OMSDataModelMasterPurchaseOrder getOmsDataModelMasterPurchaseOrder() {
		return this.omsDataModelMasterPurchaseOrder;
	}

	public OMSDataModelXdock getOmsDataModelXdock() {
		return this.omsDataModelXdock;
	}

	public TMSDataModelNewOrders getTmsDataModelNewOrders() {
		return this.tmsDataModelNewOrders;
	}

	public OMSDataModelLoadStatus getOmsDataModelLoadStatus() {
		return this.omsDataModelLoadStatus;
	}
}
