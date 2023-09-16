//package com.techlead.javaspring.entity;
//
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//import org.hibernate.annotations.JdbcTypeCode;
//import org.hibernate.annotations.UpdateTimestamp;
//import org.hibernate.type.SqlTypes;
//
//import java.sql.Timestamp;
//import java.time.LocalDateTime;
//
//@Getter
//@Setter
//@Entity
//@Table(name = "address")
//public class Address {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "address_id", nullable = false, columnDefinition = "smallint unsigned")
//    private Integer addressId;
//
//    @Column(name = "address", length = 50, nullable = false)
//    private String address;
//
//    @Column(name = "address2", length = 50)
//    private String address2;
//
//    @Column(name = "district", length = 20, nullable = false)
//    private String district;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "city_id", nullable = false)
//    private City city;
//
//    @Column(name = "postal_code", length = 10)
//    private String postalCode;
//
//    @Column(name = "phone", length = 20, nullable = false)
//    private String phone;
//
//    @Column(name = "location", columnDefinition = "geometry")
//    private String location;
//
//    @UpdateTimestamp
//    @Column(name = "last_update", columnDefinition = "TIMESTAMP")
//    private LocalDateTime lastUpdate;
//
//    @PreUpdate
//    public void preUpdate() {
//        lastUpdate = LocalDateTime.now();
//    }
//
//}