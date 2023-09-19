package com.circron.resource;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

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

import com.circron.meta.EntityType;
import com.circron.resource.EntityStatusResource.EntityStatus;
import com.sun.jersey.spi.resource.Singleton;

@Path("entitystatus")
@Singleton
public class EntityStatusResource extends AbstractResource<EntityStatus> {

	public EntityStatusResource() {
		super(EntityStatus.class);
	}

	@GET
	@Path("/byentity")
	public EntityStatus get(@QueryParam("etid") Long entityTypeId, @QueryParam("eid") Long entityId) {

		if (entityTypeId == null || entityId == null) {
			throw new WebApplicationException(Status.BAD_REQUEST);
		}

		return super.getEqualsSingle(Arrays.asList("entityTypeId", "entityId"), Arrays.asList(entityTypeId, entityId));
	}

	@GET
	@Path("/bymultipleentities")
	public List<EntityStatus> get(@QueryParam("etid") Long entityTypeId, @QueryParam("eid") List<Long> entityIds, @QueryParam("fr") Long first, @QueryParam("rc") Long count) {

		if (entityIds == null || entityIds.isEmpty()) {
			throw new WebApplicationException(Status.BAD_REQUEST);
		}

		return super.getEquals(Arrays.asList("entityTypeId", "entityId"), Arrays.asList(entityTypeId, entityIds), first, count, "updateDate");
	}

	@PUT
	@Path("/add")
	public EntityStatus add(EntityStatus element, @QueryParam("slid") Long systemLoginId) throws IllegalArgumentException {
		return super.add(element);
	}

	@PUT
	@Path("/addall")
	public List<EntityStatus> add(List<EntityStatus> elements, @QueryParam("slid") Long systemLoginId) throws IllegalArgumentException {
		return super.add(elements);
	}

	@XmlRootElement
	@Entity
	@EntityType(value = 201L, recordEvents = false, recordStatus = false)
	@Table(name = "entity_status")
	public static class EntityStatus {
		public static final String STATE_ACTIVE = "ACTIVE";
		public static final String STATE_INACTIVE = "INACTIVE";
		public static final String STATE_ARCHIVED = "ARCHIVED";

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "entity_status_id")
		private Long entityStatusId;
		@Column(name = "entity_type_id")
		private Long entityTypeId;
		@Column(name = "entity_id")
		private Long entityId;
		@Column(name = "state")
		private String state;
		@Column(name = "create_date")
		private Date createDate;
		@Column(name = "create_system_login_id")
		private Long createSystemLoginId;
		@Column(name = "update_date")
		private Date updateDate;
		@Column(name = "update_system_login_id")
		private Long updateSystemLoginId;

		public EntityStatus() {}

		public Long getEntityStatusId() {
			return entityStatusId;
		}

		public void setEntityStatusId(Long entityStatusId) {
			this.entityStatusId = entityStatusId;
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

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
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

		public Date getUpdateDate() {
			return updateDate;
		}

		public void setUpdateDate(Date updateDate) {
			this.updateDate = updateDate;
		}

		public Long getUpdateSystemLoginId() {
			return updateSystemLoginId;
		}

		public void setUpdateSystemLoginId(Long updateSystemLoginId) {
			this.updateSystemLoginId = updateSystemLoginId;
		}

	}
}