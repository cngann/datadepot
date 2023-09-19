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
@EntityType(value = 33L, recordEvents = true, recordStatus = true)
@Table(name = "button")
public class Button {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "int(11) NOT NULL AUTO_INCREMENT")
    private Long id;
    @Column(name = "button_type_id", columnDefinition = "int(11) NOT NULL DEFAULT 1")
    private Long buttonTypeId;

    public Long getId() {
        return id;
    }

    public Long getButtonTypeId() {
        return buttonTypeId;
    }

    public void setButtonTypeId(Long buttonTypeId) {
        this.buttonTypeId = buttonTypeId;
    }
}
