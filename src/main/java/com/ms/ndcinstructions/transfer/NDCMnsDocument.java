package com.ms.ndcinstructions.transfer;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;



@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "header",
    "payload"
})
@XmlRootElement(name = "MnSDocument")
public class NDCMnsDocument {

    @XmlElement(name = "Header")
    protected NDCRequestHeader header;
    @XmlElement(name = "Payload")
    protected NDCPayLoad payload;
   
    public NDCRequestHeader getHeader() {
        return header;
    }
    
    public void setHeader(NDCRequestHeader value) {
        this.header = value;
    }

    public NDCPayLoad getPayload() {
    	if(payload==null)
    		payload = new NDCPayLoad();
        return payload;
    }

    public void setPayload(NDCPayLoad value) {
        this.payload = value;
    }
}
