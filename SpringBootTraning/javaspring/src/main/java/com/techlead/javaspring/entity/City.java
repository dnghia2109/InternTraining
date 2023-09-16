//package com.techlead.javaspring.entity;
//
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//
//import java.time.LocalDateTime;
//
//@Getter
//@Setter
//@Entity
//@Table(name = "city")
//public class City {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "city_id", nullable = false, columnDefinition = "SMALLINT UNSIGNED")
//    private Integer cityId;
//
//    @Column(name = "city", length = 50, nullable = false)
//    private String city;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "country_id", nullable = false)
//    private Country country;
//
//    @Column(name = "last_update", columnDefinition = "TIMESTAMP", nullable = false)
//    private LocalDateTime lastUpdate;
//
//    @PreUpdate
//    public void preUpdate() {
//        lastUpdate = LocalDateTime.now();
//    }
//
//}