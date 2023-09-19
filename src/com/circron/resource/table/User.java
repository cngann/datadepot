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
@EntityType(value = 3L, recordEvents = true, recordStatus = true)
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "int(11) NOT NULL AUTO_INCREMENT")
    private Long id;
    @Column(name = "user_type_id", columnDefinition = "int(11) NOT NULL DEFAULT 1")
    private Long userTypeId;
    @Column(name = "login_id", columnDefinition = "varchar(255) NOT NULL")
    private String loginId;
    @Column(name = "password", columnDefinition = "varchar(255) NOT NULL")
    private String password;
    @Column(name = "first_name", columnDefinition = "varchar(255) NOT NULL")
    private String firstName;
    @Column(name = "last_name", columnDefinition = "varchar(255) NOT NULL")
    private String lastName;
    @Column(name = "auth_mode", columnDefinition = "enum('DB','LDAP','PAM','RSA','WinAD','Use Global') NOT NULL DEFAULT 'DB'")
    private String authMode;
    @Column(name = "email", columnDefinition = "varchar(1024) NOT NULL DEFAULT 'N/A'")
    private String email;

    public Long getId() {
        return id;
    }

    public Long getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(Long userTypeId) {
        this.userTypeId = userTypeId;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAuthMode() {
        return authMode;
    }

    public void setAuthMode(String authMode) {
        this.authMode = authMode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
