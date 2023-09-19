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
@EntityType(value = 9L, recordEvents = true, recordStatus = true)
@Table(name = "session")
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "int(11) NOT NULL AUTO_INCREMENT")
    private Long id;
    @Column(name = "session_type_id", columnDefinition = "int(11) NOT NULL DEFAULT 1")
    private Long sessionTypeId;
    @Column(name = "session_id", columnDefinition = "varchar(255) NOT NULL")
    private String sessionId;
    @Column(name = "login_time", columnDefinition = "TIMESTAMP DEFAULT 0")
    private String loginTime;
    @Column(name = "logout_time", columnDefinition = "TIMESTAMP DEFAULT 0")
    private String logoutTime;

    public Long getId() {
        return id;
    }

    public Long getSessionTypeId() {
        return sessionTypeId;
    }

    public void setSessionTypeId(Long sessionTypeId) {
        this.sessionTypeId = sessionTypeId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public String getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(String logoutTime) {
        this.logoutTime = logoutTime;
    }
}
