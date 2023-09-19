package com.circron.resource.table;

import com.circron.meta.EntityType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
@Entity
@EntityType(value = 39L, recordEvents = true, recordStatus = true)
@Table(name = "permission")
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "int(11) NOT NULL AUTO_INCREMENT")
    private Long id;
    @Column(name = "permission_type_id", columnDefinition = "int(11) NOT NULL DEFAULT 1")
    private Long permissionTypeId;
    @Column(name = "name", columnDefinition = "varchar(255) NOT NULL")
    private String name;
    @Column(name = "value", columnDefinition = "varchar(255) NOT NULL")
    private String value;

    public Long getId() {
        return id;
    }

    public Long getPermissionTypeId() {
        return permissionTypeId;
    }

    public void setPermissionTypeId(Long permissionTypeId) {
        this.permissionTypeId = permissionTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
