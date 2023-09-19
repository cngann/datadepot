package com.circron.resource;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;
import javax.xml.bind.annotation.XmlRootElement;

import com.circron.meta.EntityType;
import com.circron.resource.EntityUserHistoryResource.EntityUserHistory;
import com.sun.jersey.spi.resource.Singleton;

@Path("entityuserhistory")
@Singleton
public class EntityUserHistoryResource extends AbstractResource<EntityUserHistory> {

	public EntityUserHistoryResource() {
		super(EntityUserHistory.class);
	}

	@GET
	@Path("/byentity")
	public EntityUserHistory get(@QueryParam("etid") Long entityTypeId, @QueryParam("eid") Long entityId) {

		if (entityTypeId == null || entityId == null) {
			throw new WebApplicationException(Status.BAD_REQUEST);
		}

		return super.getEqualsSingle(Arrays.asList("entityTypeId", "entityId"), Arrays.asList(entityTypeId, entityId));
	}

	@GET
	@Path("/bymultipleentities")
	public List<EntityUserHistory> get(@QueryParam("etid") Long entityTypeId, @QueryParam("eid") List<Long> entityIds, @QueryParam("fr") Long first, @QueryParam("rc") Long count) {

		if (entityIds == null || entityIds.isEmpty()) {
			throw new WebApplicationException(Status.BAD_REQUEST);
		}

		return super.getEquals(Arrays.asList("entityTypeId", "entityId"), Arrays.asList(entityTypeId, entityIds), first, count, "createDate");
	}

	@PUT
	@Path("/add")
	public EntityUserHistory add(EntityUserHistory element, @QueryParam("slid") Long systemLoginId) throws IllegalArgumentException {
		return super.add(element);
	}

	@PUT
	@Path("/addall")
	public List<EntityUserHistory> add(List<EntityUserHistory> elements, @QueryParam("slid") Long systemLoginId) throws IllegalArgumentException {
		return super.add(elements);
	}

	@XmlRootElement
	@Entity
	@EntityType(value = 9997L, recordEvents = false, recordStatus = false)
	@Table(name = "entity_status")
	public static class EntityUserHistory {

		@Id
		@Column(name = "entity_userhistory_id")
		private Long entityUserHistoryId;
		@Column(name = "entity_type_id")
		private Long entityTypeId;
		@Column(name = "entity_id")
		private Long entityId;
		@Column(name = "create_date")
		private Date createDate;
		@Column(name = "create_system_login_id")
		private Long createSystemLoginId;

		public EntityUserHistory() {}

		public Long getEntityUserHistoryId() {
			return entityUserHistoryId;
		}

		public void setEntityUserHistoryId(Long entityUserHistoryId) {
			this.entityUserHistoryId = entityUserHistoryId;
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

		public Date getCreateDate() {
			return createDate;
		}

		public void setCreateDate(Date createDate) {
			this.createDate = createDate;
		}

		public Long getCreateSystemLoginId() {
			return createSystemLoginId;
		}

		public void setCreateSystemLoginId(Long createSystemLoginId) {
			this.createSystemLoginId = createSystemLoginId;
		}

	}
}