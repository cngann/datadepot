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
@XmlRootElement(name = "long")
@XmlAccessorType(XmlAccessType.FIELD)
public class XMLLong extends XMLWrapper<Long> {
	@XmlElement
	private Long value;

	public XMLLong() {}

	public XMLLong(Long value) {
		this.value = value;
	}

	public Long get() {
		return value;
	}

	public void set(Long value) {
		this.value = value;
	}
}
