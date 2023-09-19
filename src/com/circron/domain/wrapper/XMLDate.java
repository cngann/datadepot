package com.circron.domain.wrapper;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * A utility class for wrapping single values in an XML compatible object.
 * 
 * @author cngann
 */
@XmlRootElement(name = "date")
@XmlAccessorType(XmlAccessType.FIELD)
public class XMLDate extends XMLWrapper<Date> {
	@XmlElement
	private Date value;

	public XMLDate() {}

	public XMLDate(Date value) {
		this.value = value;
	}

	public Date get() {
		return value;
	}

	public void set(Date value) {
		this.value = value;
	}
}
