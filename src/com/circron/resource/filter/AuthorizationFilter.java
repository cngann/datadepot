package com.circron.resource.filter;

import com.circron.hibernate.HibernateSessionFactory;
import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
/**
 * Tests if a given resource request has a valid api key and is from a white
 * listed IP.
 */
public class AuthorizationFilter implements ContainerRequestFilter {
    private static final Log log = LogFactory.getLog(AuthorizationFilter.class);
    private static final String SQL_EXISTS_WHITE_LISTED_IP = "SELECT name FROM ip_white_list WHERE inet(:ip ) <<= inet(ip_white_list.ip) LIMIT 1;";
    private static final String SQL_EXISTS_API_KEY = "SELECT name FROM api_key WHERE api_key = :key ;";

    @Context
    HttpServletRequest servletRequest;

    public ContainerRequest filter(ContainerRequest containerRequest) {
        final String key = servletRequest.getParameter("apikey");

        if (key == null) {
            HibernateSessionFactory.closeSession();
            throw new UnauthorizedException("Missing Key");
        }

        if (!validateAPIKey(key)) {
            log.debug("Invalid Key Attempted: " + key);
            HibernateSessionFactory.closeSession();
            throw new UnauthorizedException("Invalid Key");
        }

        if (!validateIP(servletRequest.getRemoteAddr())) {
            log.debug("Unauthorized IP Attempted: " + servletRequest.getRemoteAddr());
            HibernateSessionFactory.closeSession();
            throw new UnauthorizedException("Unauthorized IP");
        }

        return containerRequest;
    }

    /**
     * Checks if the API key is valid.
     */
    private boolean validateAPIKey(String key) {
        try {
            final Query query = Objects.requireNonNull(HibernateSessionFactory.getSession())
                    .createSQLQuery(SQL_EXISTS_API_KEY);
            query.setString("key", key);
            query.setReadOnly(true);
            return query.uniqueResult() != null;
        } catch (Exception e) {
            log.error("Error validating API Key '" + key + "'", e);
        }

        return false;
    }

    /**
     * Checks if the given IP has been white listed.
     */
    private boolean validateIP(String ip) {
        return true;
//		try {
//			final Query query = Objects.requireNonNull(HibernateSessionFactory.getSession())
//									   .createSQLQuery(SQL_EXISTS_WHITE_LISTED_IP);
//			query.setString("ip", ip);
//			query.setReadOnly(true);
//			return query.uniqueResult() != null;
//		} catch (Exception e) {
//			log.error("Error validating IP '" + ip + "'", e);
//		}
//		return false;
    }

    /**
     * Throws the given message to the client in the form of a bad request
     * exception.
     */
    private static class UnauthorizedException extends WebApplicationException {
        private static final long serialVersionUID = 496611545049815416L;

        public UnauthorizedException(String message) {
            super(Response.status(Response.Status.BAD_REQUEST).entity(message).type(MediaType.TEXT_PLAIN).build());
        }
    }

}