/*
 *
 */
package com.ms.ndcinstructions.transfer;

import java.io.Serializable;
import java.sql.Timestamp;





public class AllocationProdDestResponse implements Serializable {

    private static final long serialVersionUID = -4709056909243880098L;
    
    private Integer allocatedQuantity;

    private String allocationType;

    private String intoStoreDate;
    
    private String storeId;
    
	private String upcEan;
    
    private Integer upt;
    
    private String receiptDate;
    
    private String depotId;

    private String cross_dock;
    
    
	public String getCross_dock() {
		return cross_dock;
	}


	public void setCross_dock(String cross_dock) {
		this.cross_dock = cross_dock;
	}


	public String getUpcEan() {
		return upcEan;
	}


	public void setUpcEan(String upcEan) {
		this.upcEan = upcEan;
	}


	public Integer getUpt() {
		return upt;
	}


	public void setUpt(Integer upt) {
		this.upt = upt;
	}


	public String getReceiptDate() {
		return receiptDate;
	}


	public void setReceiptDate(String receiptDate) {
		this.receiptDate = receiptDate;
	}


	public String getDepotId() {
		return depotId;
	}


	public void setDepotId(String depotId) {
		this.depotId = depotId;
	}


	public Integer getAllocatedQuantity() {
		return allocatedQuantity;
	}


	public void setAllocatedQuantity(Integer allocatedQuantity) {
		this.allocatedQuantity = allocatedQuantity;
	}


	public String getAllocationType() {
		return allocationType;
	}


	public void setAllocationType(String allocationType) {
		this.allocationType = allocationType;
	}


	public String getIntoStoreDate() {
		return intoStoreDate;
	}


	public void setIntoStoreDate(String intoStoreDate) {
		this.intoStoreDate = intoStoreDate;
	}


	public String getStoreId() {
		return storeId;
	}


	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}


	@Override
	public String toString() {
		return "AllocationProdDestResponse [allocatedQuantity=" + allocatedQuantity + ", allocationType="
				+ allocationType + ", intoStoreDate=" + intoStoreDate + ", storeId=" + storeId + ", upcEan=" + upcEan
				+ ", upt=" + upt + ", receiptDate=" + receiptDate + ", depotId=" + depotId + ", cross_dock="
				+ cross_dock + "]";
	}
    

}