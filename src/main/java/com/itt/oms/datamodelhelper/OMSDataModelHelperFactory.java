package com.itt.oms.datamodelhelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;


public class OMSDataModelHelperFactory {
	private static final Logger LOG = LoggerFactory.getLogger(OMSDataModelHelperFactory.class);

	private static OMSDataModelHelperFactory omsDataModelHelperFactory = new OMSDataModelHelperFactory();
	@JsonProperty("LoginUsers")
	private OMSDataModelLoginUsers omsDataModelLoginUsers = new OMSDataModelLoginUsers();
	@JsonProperty("PurchaseOrderDetails")
	private OMSDataModelPurchaseOrderDetails omsDataModelPurchaseOrderDetails = new OMSDataModelPurchaseOrderDetails();
	@JsonProperty("PurchaseOrder")
	private OMSDataModelPurchaseOrder omsDataModelPurchaseOrder = new OMSDataModelPurchaseOrder();
	@JsonProperty("ReceiveOrder")
	private OMSDataModelReceiveOrderModel omsDataModelReceiveOrder = new OMSDataModelReceiveOrderModel();
	@JsonProperty("ShipmentOrder")
	private OMSDataModelShipmentOrder omsDataModelShipmentOrder = new OMSDataModelShipmentOrder();
	@JsonProperty("InvoiceOrder")
	private OMSDataModelInvoiceOrder omsDataModelInvoiceOrder = new OMSDataModelInvoiceOrder();
	@JsonProperty("VendorOrderManagement")
	private OMSDataModelVendorOrderManagement omsDataModelVendorOrderManagement = new OMSDataModelVendorOrderManagement();
	@JsonProperty("MasterPurchaseOrder")
	private OMSDataModelMasterPurchaseOrder omsDataModelMasterPurchaseOrder = new OMSDataModelMasterPurchaseOrder();
	@JsonProperty("Xdock")
	private OMSDataModelXdock omsDataModelXdock = new OMSDataModelXdock();
	@JsonProperty("TmsNewOrders")
	private TMSDataModelNewOrders tmsDataModelNewOrders = new TMSDataModelNewOrders();
	@JsonProperty("LoadStatus")
	private OMSDataModelLoadStatus omsDataModelLoadStatus = new OMSDataModelLoadStatus();

	@Getter
	@Setter
	private String project;

	@JsonProperty("APP_URL")
	@Getter
	@Setter
	private String APP_URL;

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
