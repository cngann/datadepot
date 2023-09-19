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
@EntityType(value = 27L, recordEvents = true, recordStatus = true)
@Table(name = "`partition`")
public class Partition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "int(11) NOT NULL AUTO_INCREMENT")
    private Long id;
    @Column(name = "partition_type_id", columnDefinition = "int(11) NOT NULL DEFAULT 1")
    private Long partitionTypeId;
    @Column(name = "name", columnDefinition = "varchar(255) NOT NULL")
    private String name;
    @Column(name = "description", columnDefinition = "varchar(255) NOT NULL")
    private String description;

    public Long getId() {
        return id;
    }

    public Long getPartitionTypeId() {
        return partitionTypeId;
    }

    public void setPartitionTypeId(Long partitionTypeId) {
        this.partitionTypeId = partitionTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
