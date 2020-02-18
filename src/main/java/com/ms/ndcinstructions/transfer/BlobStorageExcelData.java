package com.ms.ndcinstructions.transfer;

public class BlobStorageExcelData implements java.io.Serializable {

	
	private static final long serialVersionUID = 7783409597280223624L;
	
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

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getReceiptDate() {
		return receiptDate;
	}

	public void setReceiptDate(String receiptDate) {
		this.receiptDate = receiptDate;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getIntoStoreDate() {
		return intoStoreDate;
	}

	public void setIntoStoreDate(String intoStoreDate) {
		this.intoStoreDate = intoStoreDate;
	}

	public String getAllocationType() {
		return allocationType;
	}

	public void setAllocationType(String allocationType) {
		this.allocationType = allocationType;
	}

	/*public String getAllocatedQuantity() {
		return allocatedQuantity;
	}

	public void setAllocatedQuantity(String allocatedQuantity) {
		this.allocatedQuantity = allocatedQuantity;
	}*/
	
	public void setAllocatedQuantity(Integer allocatedQuantity) {
			this.allocatedQuantity = allocatedQuantity;
		}
	
	public Integer getAllocatedQuantity() {
			return allocatedQuantity;
	}


	public String getCrossDock() {
		return crossDock;
	}

	public void setCrossDock(String crossDock) {
		this.crossDock = crossDock;
	}

	private String upcEan;
	
    private Integer upt;
    
    private Integer quantity;
    
    private String receiptDate;
    
    private String storeId;
    
    private String intoStoreDate;
    
    private String allocationType;
	
	private Integer allocatedQuantity;

 
	private String crossDock;
	
	public BlobStorageExcelData() {
		super();
	}
	
	/**
	 * @param serialNo
	 * @param siteId
	 * @param upc
	 * @param upt
	 * @param status
	 * @param code
	 * @param quantity
	 */
	public BlobStorageExcelData(String upcEan, Integer upt, Integer quantity, String receiptDate,String storeId,String intoStoreDate, String allocationType, Integer allocatedQuantity,
			String crossDock) {
		super();
		this.upcEan = upcEan;
		this.upt = upt;
		this.quantity = quantity;
		this.receiptDate = receiptDate;
		this.storeId = storeId;
		this.intoStoreDate = intoStoreDate;
		this.allocationType = allocationType;
		this.allocatedQuantity = allocatedQuantity;
		this.crossDock = crossDock;
	}
	
	   @Override
	    public String toString() {
	        return "BlobStorageExcelData [upcEan=" + upcEan + ", upt=" + upt + ", quantity="
	                + quantity + ", receiptDate=" + receiptDate + ", storeId=" + storeId +", intoStoreDate=" + intoStoreDate +", allocationType=" 
	        		+ allocationType + ", allocatedQuantity=" + allocatedQuantity + ", crossDock=" 
	        	      + crossDock + "]";
	    }
}
