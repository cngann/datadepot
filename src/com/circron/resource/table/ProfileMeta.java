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
@EntityType(value = 37L, recordEvents = true, recordStatus = true)
@Table(name = "profile_meta")
public class ProfileMeta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "int(11) NOT NULL AUTO_INCREMENT")
    private Long id;
    @Column(name = "profile_id", columnDefinition = "int(11) NOT NULL DEFAULT 1")
    private String profileId;
    @Column(name = "rule_id", columnDefinition = "int(11) NOT NULL DEFAULT 1")
    private String ruleId;
    @Column(name = "key", columnDefinition = "varchar(255) NOT NULL")
    private String key;
    @Column(name = "value", columnDefinition = "varchar(255) NOT NULL")
    private String value;

    public Long getId() {
        return id;
    }

    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    public String getRuleId() {
        return ruleId;
    }

    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
