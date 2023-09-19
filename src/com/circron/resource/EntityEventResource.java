package com.circron.resource;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.Criteria;
import org.hibernate.annotations.GeneratorType;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.circron.hibernate.HibernateSessionFactory;
import com.circron.meta.EntityType;
import com.circron.resource.EntityEventResource.EntityEvent;
import com.sun.jersey.spi.resource.Singleton;

@Path("entityevent")
@Singleton
public class EntityEventResource extends AbstractResource<EntityEvent> {

	public EntityEventResource() {
		super(EntityEvent.class);
	}

	@GET
	@Path("/byentity")
	public List<EntityEvent> get(@QueryParam("etid") Long entityTypeId, @QueryParam("eid") List<Long> entityIds, @QueryParam("fr") Long first, @QueryParam("rc") Long count) {

		if (entityIds == null || entityIds.isEmpty()) {
			throw new WebApplicationException(Status.BAD_REQUEST);
		}

		return super.getEquals(Arrays.asList("entityTypeId", "entityId"), Arrays.asList(entityTypeId, entityIds), first, count, "date");
	}

	@PUT
	@Path("/add")
	public EntityEvent add(EntityEvent ip, @QueryParam("slid") Long systemLoginId) throws IllegalArgumentException {
		getLog().debug("Adding one " + getModelClass().getName() + ".");

		return super.add(ip);
	}

	@PUT
	@Path("/addall")
	public List<EntityEvent> add(List<EntityEvent> ips, @QueryParam("slid") Long systemLoginId) throws IllegalArgumentException {
		getLog().debug("Adding " + ips.size() + "x " + getModelClass().getName() + ".");

		return super.add(ips);
	}

	@SuppressWarnings("unchecked")
	@GET
	@Path("/byentityid")
	public List<EntityEvent> getByEntityId(@QueryParam("etid") Long entityTypeId, @QueryParam("eid") Long entityId, @QueryParam("fr") Long firstRecord, @QueryParam("rc") Long recordCount) {

		if (entityTypeId == null || entityId == null) {
			throw new WebApplicationException(Status.BAD_REQUEST);
		}

		final Criteria crit = Objects.requireNonNull(HibernateSessionFactory.getSession())
									 .createCriteria(EntityEvent.class);
		crit.add(Restrictions.eq("entityTypeId", entityTypeId));
		crit.add(Restrictions.eq("entityId", entityId));

		if (recordCount != null) {
			crit.setFetchSize(recordCount.intValue());
		}
		if (firstRecord != null) {
			crit.setFirstResult(firstRecord.intValue());
		}
		crit.addOrder(Order.asc("date"));
		return crit.list();
	}

	@XmlRootElement
	@Entity
	@EntityType(value = 200L, recordEvents = false, recordStatus = false)
	@Table(name = "entity_event")
	public static class EntityEvent {
		public static final String ENTITY_EVENT_CREATED = "CREATED";
		public static final String ENTITY_EVENT_UPDATED = "UPDATED";
		public static final String ENTITY_EVENT_DELETED = "DELETED";
		public static final String ENTITY_EVENT_RESTORED = "RESTORED";
		public static final String ENTITY_EVENT_ARCHIVED = "ARCHIVED";

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "entity_event_id")
		private Long entityEventId;
		@Column(name = "entity_type_id")
		private Long entityTypeId;
		@Column(name = "entity_id")
		private Long entityId;
		@Column(name = "application")
		private String application;
		@Column(name = "system_login_id")
		private Long systemLoginId;
		@Column(name = "event")
		private String event;
		@Column(name = "date")
		private Date date;

		public EntityEvent() {}

		public EntityEvent(long entityTypeId, long entityId, String application, Long systemLoginId, String event) {
			this.entityTypeId = entityTypeId;
			this.entityId = entityId;
			this.systemLoginId = systemLoginId;
			this.event = event;
			this.application = application;
			date = new Date();
		}

		public Long getEntityEventId() {
			return entityEventId;
		}

		public void setEntityEventId(Long entityEventId) {
			this.entityEventId = entityEventId;
		}

		public Long getEntityTypeId() {
			return entityTypeId;
		}

		public void setEntityTypeId(Long entityTypeId) {
			this.entityTypeId = entityTypeId;
		}

		public Long getEntityId() {
			return entityId;
		}

		public void setEntityId(Long entityId) {
			this.entityId = entityId;
		}

		public String getApplication() {
			return application;
		}

		public void setApplication(String application) {
			this.application = application;
		}

		public Long getSystemLoginId() {
			return systemLoginId;
		}

		public void setSystemLoginId(Long systemLoginId) {
			this.systemLoginId = systemLoginId;
		}

		public String getEvent() {
			return event;
		}

		public void setEvent(String event) {
			this.event = event;
		}

		public Date getDate() {
			return date;
		}

		public void setDate(Date date) {
			this.date = date;
		}

	}
}