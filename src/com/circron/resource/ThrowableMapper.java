package com.circron.resource;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Catches any and all exceptions thrown by resources or filters and adapts it
 * to a workable response for the client.
 * 
 * @author cngann
 * 
 */
@Provider
public class ThrowableMapper implements ExceptionMapper<Throwable> {
	private static final Log log = LogFactory.getLog(ThrowableMapper.class);

	@Override
	public Response toResponse(Throwable t) {
		if (t instanceof WebApplicationException) {
			log.error("", t);
			return ((WebApplicationException) t).getResponse();
		}
		log.error("Internal exception caught in mapper.", t);
		return Response.status(Status.INTERNAL_SERVER_ERROR).build();
	}
}
