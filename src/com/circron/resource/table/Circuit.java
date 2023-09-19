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
@EntityType(value = 24L, recordEvents = true, recordStatus = true)
@Table(name = "circuit")
public class Circuit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "int(11) NOT NULL AUTO_INCREMENT")
    private Long id;
    @Column(name = "circuit_type_id", columnDefinition = "int(11) NOT NULL DEFAULT 1")
    private Long circuitTypeId;

    public Long getId() {
        return id;
    }

    public Long getCircuitTypeId() {
        return circuitTypeId;
    }

    public void setCircuitTypeId(Long circuitTypeId) {
        this.circuitTypeId = circuitTypeId;
    }
}
