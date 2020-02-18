package com.ms.ndcinstructions.transfer;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;




@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
		"allocationResponse"
})
//@XmlRootElement(name = "allocationResponse")
public class NDCPayLoad {
	@XmlElement(required = true)
	protected List<AllocationResponse> allocationResponse;
	//protected AllocationResponse allocationResponse;

	public List<AllocationResponse> getAllocationResponse() {
		if(allocationResponse==null)
			allocationResponse = new ArrayList<AllocationResponse>();
		return allocationResponse;
	}

	public void setAllocationResponse(List<AllocationResponse> allocationResponse) {
		this.allocationResponse = allocationResponse;
	}
	
	/*public AllocationResponse getAllocationResponse() {
		return allocationResponse;
	}

	public void setAllocationResponse(AllocationResponse allocationResponse) {
		this.allocationResponse = allocationResponse;
	}*/
	
}