//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.09.27 at 02:47:52 AM IST 
//


package com.ms.ndcinstructions.transfer;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import org.w3c.dom.Element;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Header", propOrder = {
		"envelopeVersion",
		"messageId",
		"correlationId",
		"createdTimestamp",
		"sourceApplicationName",
		"interfaceId",
		"interfaceName",
		"payloadName",
		"batchMessageCount",
		"batchRecordCount",
		"payloadVersion",
		"integrationExtension",
		"language",
		"sequenceId",
		"securityClassification",
		"payloadKey",
		"batchRecordSum",
		"filter",
		"etag"
})
public class NDCRequestHeader {

	@XmlElement(name = "EnvelopeVersion", defaultValue = "2.0")
	protected String envelopeVersion;
	@XmlElement(name = "Language")
	protected String language;
	@XmlElement(name = "MessageId", required = true)
	protected String messageId;
	@XmlElement(name = "CorrelationId")
	protected String correlationId;
	@XmlElement(name = "CreatedTimestamp", required = true)
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar createdTimestamp;
	@XmlElement(name = "SequenceId")
	protected BigInteger sequenceId;
	@XmlElement(name = "SourceApplicationName", required = true)
	protected String sourceApplicationName;
	@XmlElement(name = "InterfaceId", required = true)
	protected String interfaceId;
	@XmlElement(name = "InterfaceName")
	protected String interfaceName;
	@XmlElement(name = "SecurityClassification")
	protected String securityClassification;
	@XmlElement(name = "PayloadName", required = true)
	protected String payloadName;
	@XmlElement(name = "PayloadVersion")
	protected BigDecimal payloadVersion;
	@XmlElement(name = "PayloadKey")
	protected String payloadKey;
	@XmlElement(name = "BatchMessageCount")
	protected int batchMessageCount;
	@XmlElement(name = "BatchRecordCount")
	protected int batchRecordCount;
	@XmlElement(name = "BatchRecordSum")
	protected Integer batchRecordSum;
	@XmlElement(name = "Filter")
	protected FilterType filter;
	@XmlElement(name = "IntegrationExtension")
	protected IntegrationExtensionType integrationExtension;
	@XmlElement(name = "ETAG")
	protected String etag;


	public String getEnvelopeVersion() {
		return envelopeVersion;
	}


	public void setEnvelopeVersion(String value) {
		this.envelopeVersion = value;
	}

	public String getLanguage() {
		return language;
	}


	public void setLanguage(String value) {
		this.language = value;
	}

	public String getMessageId() {
		return messageId;
	}


	public void setMessageId(String value) {
		this.messageId = value;
	}


	public String getCorrelationId() {
		return correlationId;
	}


	public void setCorrelationId(String value) {
		this.correlationId = value;
	}


	public XMLGregorianCalendar getCreatedTimestamp() {
		return createdTimestamp;
	}


	public void setCreatedTimestamp(XMLGregorianCalendar value) {
		this.createdTimestamp = value;
	}


	public BigInteger getSequenceId() {
		return sequenceId;
	}


	public void setSequenceId(BigInteger value) {
		this.sequenceId = value;
	}


	public String getSourceApplicationName() {
		return sourceApplicationName;
	}


	public void setSourceApplicationName(String value) {
		this.sourceApplicationName = value;
	}


	public String getInterfaceId() {
		return interfaceId;
	}

	public void setInterfaceId(String value) {
		this.interfaceId = value;
	}


	public String getInterfaceName() {
		return interfaceName;
	}


	public void setInterfaceName(String value) {
		this.interfaceName = value;
	}


	public String getSecurityClassification() {
		return securityClassification;
	}


	public void setSecurityClassification(String value) {
		this.securityClassification = value;
	}

	public String getPayloadName() {
		return payloadName;
	}


	public void setPayloadName(String value) {
		this.payloadName = value;
	}


	public BigDecimal getPayloadVersion() {
		return payloadVersion;
	}


	public void setPayloadVersion(BigDecimal value) {
		this.payloadVersion = value;
	}


	public String getPayloadKey() {
		return payloadKey;
	}


	public void setPayloadKey(String value) {
		this.payloadKey = value;
	}


	public int getBatchMessageCount() {
		return batchMessageCount;
	}


	public void setBatchMessageCount(int value) {
		this.batchMessageCount = value;
	}


	public int getBatchRecordCount() {
		return batchRecordCount;
	}

	public void setBatchRecordCount(int value) {
		this.batchRecordCount = value;
	}


	public Integer getBatchRecordSum() {
		return batchRecordSum;
	}


	public void setBatchRecordSum(Integer value) {
		this.batchRecordSum = value;
	}



	public String getETAG() {
		return etag;
	}


	public void setETAG(String value) {
		this.etag = value;
	}
	
	
	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "FilterType", propOrder = {
	    "level1",
	    "level2",
	    "level3"
	})
	public static class FilterType {

	    @XmlElement(name = "Level1", required = true)
	    protected String level1;
	    @XmlElement(name = "Level2")
	    protected String level2;
	    @XmlElement(name = "Level3")
	    protected String level3;


	    public String getLevel1() {
	        return level1;
	    }

	    public void setLevel1(String value) {
	        this.level1 = value;
	    }


	    public String getLevel2() {
	        return level2;
	    }


	    public void setLevel2(String value) {
	        this.level2 = value;
	    }

	    public String getLevel3() {
	        return level3;
	    }


	    public void setLevel3(String value) {
	        this.level3 = value;
	    }

	}
	
	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "IntegrationExtensionType", propOrder = {
	    "any"
	})
	public static class IntegrationExtensionType {

	    @XmlAnyElement
	    protected List<Element> any;

	  
	    public List<Element> getAny() {
	        if (any == null) {
	            any = new ArrayList<Element>();
	        }
	        return this.any;
	    }

	}
}