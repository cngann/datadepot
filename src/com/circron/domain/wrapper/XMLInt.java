package com.circron.domain.wrapper;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * A utility class for wrapping single values in an XML compatible object.
 * 
 * @author cngann
 */
@XmlRootElement(name = "int")
@XmlAccessorType(XmlAccessType.FIELD)
public class XMLInt extends XMLWrapper<Integer> {
	@XmlElement
	private Integer value;

	public XMLInt() {}

	public XMLInt(Integer value) {
		this.value = value;
	}

	public Integer get() {
		return value;
	}

	public void set(Integer value) {
		this.value = value;
	}
}
