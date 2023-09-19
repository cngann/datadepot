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
@EntityType(value = 18L, recordEvents = true, recordStatus = true)
@Table(name = "`key`")
public class Key {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "int(11) NOT NULL AUTO_INCREMENT")
    private Long id;
    @Column(name = "key_type_id", columnDefinition = "int(11) NOT NULL DEFAULT 1")
    private Long keyTypeId;

    public Long getId() {
        return id;
    }

    public Long getKeyTypeId() {
        return keyTypeId;
    }

    public void setKeyTypeId(Long keyTypeId) {
        this.keyTypeId = keyTypeId;
    }
}
