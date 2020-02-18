
package com.ms.ndcinstructions.transfer;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlType;

import org.w3c.dom.Element;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IntegrationExtensionType", namespace = "", propOrder = {
    "any"
})
public class IntegrationExtensionTypeBack {

    @XmlAnyElement
    protected List<Element> any;

    public List<Element> getAny() {
        if (any == null) {
            any = new ArrayList<Element>();
        }
        return this.any;
    }

}
