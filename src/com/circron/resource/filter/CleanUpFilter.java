package com.circron.resource.filter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.circron.hibernate.HibernateSessionFactory;
import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerResponse;
import com.sun.jersey.spi.container.ContainerResponseFilter;

/**
 * Cleans up request by closing the database session.
 * 
 * @author cngann
 */
public class CleanUpFilter implements ContainerResponseFilter {
	private static final Log log = LogFactory.getLog(CleanUpFilter.class);

	@Override
	public ContainerResponse filter(ContainerRequest request, ContainerResponse response) {
		try {
			HibernateSessionFactory.closeSession();
		} catch (Throwable e) {
			log.error("Error closing session.", e);
		}
		return response;
	}
}
