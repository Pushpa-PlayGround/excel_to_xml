/*
 *
 */
package com.ms.ndcinstructions.transfer;

import java.io.Serializable;
import java.util.List;

/**
 * The persistent class for the alloc_req_product database table.
 *
 */

public class AllocationProdResponse implements Serializable {

    private static final long serialVersionUID = 8229688326063636410L;

    private String sourceLocationId;

    private Integer minTradeableQty;

    private Integer quantity;

    private String upcEan;

    private String productRequestId;
    
    private List<AllocationProdResponse> allocReqProdList; 
    


	private List<AllocationProdDestResponse> allocReqProdDest;
    
	public List<AllocationProdResponse> getAllocReqProdList() {
		return allocReqProdList;
	}

	public void setAllocReqProdList(List<AllocationProdResponse> allocReqProdList) {
		this.allocReqProdList = allocReqProdList;
	}

	public String getSourceLocationId() {
		return sourceLocationId;
	}

	public void setSourceLocationId(String sourceLocationId) {
		this.sourceLocationId = sourceLocationId;
	}


	public Integer getMinTradeableQty() {
		return minTradeableQty;
	}


	public void setMinTradeableQty(Integer minTradeableQty) {
		this.minTradeableQty = minTradeableQty;
	}


	public Integer getQuantity() {
		return quantity;
	}


	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}


	public String getUpcEan() {
		return upcEan;
	}


	public void setUpcEan(String upcEan) {
		this.upcEan = upcEan;
	}


	public String getProductRequestId() {
		return productRequestId;
	}


	public void setProductRequestId(String productRequestId) {
		this.productRequestId = productRequestId;
	}


	public List<AllocationProdDestResponse> getAllocReqProdDest() {
		return allocReqProdDest;
	}


	public void setAllocReqProdDest(List<AllocationProdDestResponse> allocReqProdDest) {
		this.allocReqProdDest = allocReqProdDest;
	}

	@Override
	public String toString() {
		return "AllocationProdResponse [sourceLocationId=" + sourceLocationId + ", minTradeableQty=" + minTradeableQty
				+ ", quantity=" + quantity + ", upcEan=" + upcEan + ", productRequestId=" + productRequestId
				+ ", allocReqProdList=" + allocReqProdList + ", allocReqProdDest=" + allocReqProdDest + "]";
	}
    
}