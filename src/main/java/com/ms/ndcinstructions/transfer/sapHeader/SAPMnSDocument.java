package com.ms.ndcinstructions.transfer.sapHeader;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.ms.ndcinstructions.transfer.NDCPayLoad;
import com.ms.ndcinstructions.transfer.SAPHeader;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "header",
    "payload"
})
@XmlRootElement(name = "MnSDocument")
public class SAPMnSDocument {

    @XmlElement(name = "Header")
    protected com.ms.ndcinstructions.transfer.SAPHeader header;
    @XmlElement(name = "Payload")
    protected NDCPayLoad payload;
   
    public SAPHeader getHeader() {
        return header;
    }
    
    public void setHeader(SAPHeader value) {
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
