//package com.circron.resource.table;
//import com.circron.meta.EntityType;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Table;
//import javax.xml.bind.annotation.XmlRootElement;
//@XmlRootElement
//@Entity
//@EntityType(value = 2L, recordEvents = true, recordStatus = true)
//@Table(name = "admin_priv")
//public class AdminPriv {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
//    private Long id;
//    @Column(name = "admin_priv")
//    private String adminPriv;
//    @Column(name = "admin_id")
//    private Long userId;
//    @Column(name = "grantor_id")
//    private Integer grantorId;
//    @Column(name = "partition_id")
//    private Integer partitionId;
//
//    public AdminPriv() {}
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public Long getUserId() { return userId; }
//
//    public void setUserId(Long userId) { this.userId = userId; }
//
//    public String getAdminPriv() {
//        return adminPriv;
//    }
//
//    public void setAdminPriv(String adminPriv) {
//        this.adminPriv = adminPriv;
//    }
//
//    public Integer getGrantorId() {
//        return grantorId;
//    }
//
//    public void setGrantorId(Integer grantorId) {
//        this.grantorId = grantorId;
//    }
//
//    public Integer getPartitionId() {
//        return partitionId;
//    }
//
//    public void setPartitionId(Integer partitionId) {
//        this.partitionId = partitionId;
//    }
//}
