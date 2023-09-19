package com.circron.resource;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.ws.rs.Path;
import javax.xml.bind.annotation.XmlRootElement;

import com.circron.resource.EntityTypeResource.EntityType;
import com.sun.jersey.spi.resource.Singleton;

@Path("entitytype")
@Singleton
public class EntityTypeResource extends AbstractModelResource<EntityType> {

	public EntityTypeResource() {
		super(EntityType.class);
	}

	@XmlRootElement
	@Entity
	@Table(name = "entity_type")
	static class EntityType {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "entity_type_id")
		private Long entityTypeId;
		@Column(name = "table_name")
		private Long tableName;

		public EntityType() {}

		public Long getEntityTypeId() {
			return entityTypeId;
		}

		public void setEntityTypeId(Long entityTypeId) {
			this.entityTypeId = entityTypeId;
		}

		public Long getTableName() {
			return tableName;
		}

		public void setTableName(Long tableName) {
			this.tableName = tableName;
		}

	}
}