//package com.techlead.javaspring.entity;
//
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//import org.hibernate.annotations.CreationTimestamp;
//import org.hibernate.annotations.UpdateTimestamp;
//
//import java.time.LocalDateTime;
//
//@Getter
//@Setter
//@Entity
//@Table(name = "country")
//public class Country {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "country_id", nullable = false, columnDefinition = "SMALLINT UNSIGNED")
//    private Integer countryId;
//
//    @Column(name = "country", length = 50, nullable = false)
//    private String country;
//
////    @CreationTimestamp
//    @UpdateTimestamp
//    @Column(name = "last_update", columnDefinition = "TIMESTAMP", nullable = false)
//    private LocalDateTime lastUpdate;
//
////    @PreUpdate
////    public void preUpdate() {
////        lastUpdate = LocalDateTime.now();
////    }
//}