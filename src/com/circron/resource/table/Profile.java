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
@EntityType(value = 36L, recordEvents = true, recordStatus = true)
@Table(name = "profile")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "int(11) NOT NULL AUTO_INCREMENT")
    private Long id;
    @Column(name = "profile_type_id", columnDefinition = "int(11) NOT NULL DEFAULT 1")
    private Long profileTypeId;
    @Column(name = "name", columnDefinition = "varchar(255) NOT NULL")
    private String name;

    public Long getId() {
        return id;
    }

    public Long getProfileTypeId() {
        return profileTypeId;
    }

    public void setProfileTypeId(Long profileTypeId) {
        this.profileTypeId = profileTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}