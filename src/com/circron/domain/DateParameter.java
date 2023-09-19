package com.circron.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Accepts Date string as rest parameters using the FULL date format.
 *
 * @author cngann
 *
 */
public class DateParameter {
	private static final Log log = LogFactory.getLog(DateParameter.class);
	private static final SimpleDateFormat parserSDF = new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy");
	private static final SimpleDateFormat utcDF = new SimpleDateFormat("MM/dd/yyyy KK:mm:ss a");
	private Date date;

	public DateParameter(String dateStr) {
		try {
			this.date = dateStr == null || dateStr.isEmpty() ? null : parserSDF.parse(dateStr);
		} catch (ParseException e) {
			try {
				this.date = utcDF.parse(dateStr);
			} catch (ParseException e2) {
				try {
					this.date = new Date(Long.parseLong(dateStr));
				} catch (NumberFormatException e3) {
					log.error("Error parsing the date parameter string.");
					throw new WebApplicationException(Status.BAD_REQUEST);
				}
			}
		}
	}

	public DateParameter(Date date) {
		this.date = date;
	}

	public Date value() {
		return this.date;
	}
}
