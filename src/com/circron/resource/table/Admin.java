//package com.circron.resource.table;
//
//import com.circron.meta.EntityType;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Table;
//import javax.ws.rs.DefaultValue;
//import javax.xml.bind.annotation.XmlRootElement;
//@XmlRootElement
//@Entity
//@EntityType(value = 1L, recordEvents = true, recordStatus = true)
//@Table(name = "admin")
//public class Admin {
//    @Column(name = "admin_level")
//    private String adminLevel;
//    //    @NotNull
//    @DefaultValue(value = "")
//    @Column(name = "auth_level_id")
//    private Integer authLevelId;
//    @Column(name = "restricted")
//    private Integer restricted;
//    @Column(name = "max_concurrent_sessions")
//    private Integer maxConcurrentSessions;
//
//    public Admin() {}
//
//    public String getAdminLevel() {
//        return adminLevel;
//    }
//
//    public void setAdminLevel(String adminLevel) {
//        this.adminLevel = adminLevel;
//    }
//
//    public Integer getAuthLevelId() {
//        return authLevelId;
//    }
//
//    public void setAuthLevelId(Integer authLevelId) {
//        this.authLevelId = authLevelId;
//    }
//
//    public Integer getRestricted() {
//        return restricted;
//    }
//
//    public void setRestricted(Integer restricted) {
//        this.restricted = restricted;
//    }
//
//    public Integer getMaxConcurrentSessions() {
//        return maxConcurrentSessions;
//    }
//
//    public void setMaxConcurrentSessions(Integer maxConcurrentSessions) {
//        this.maxConcurrentSessions = maxConcurrentSessions;
//    }
//
//    public enum AuthMode {
//        USE_DB              ("DB"),
//        USE_RSA             ("RSA"),
//        USE_GLOBAL          ("Use Global");
//
//        private final String authMode;
//
//        AuthMode(final String authMode) {
//            this.authMode = authMode;
//        }
//
//        @Override public String toString() {
//            return authMode;
//        }
//    }
//}
