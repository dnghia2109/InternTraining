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
//@Table(name = "language")
//public class Language {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "language_id", nullable = false, columnDefinition = "tinyint unsigned")
//    private Integer languageId;
//
//    @Column(name = "name", columnDefinition = "char(20)")
//    private String name;
//
//    //@CreationTimestamp
//    @UpdateTimestamp
//    @Column(name = "last_update", columnDefinition = "TIMESTAMP", nullable = false)
//    private LocalDateTime lastUpdate;
//
////    @PreUpdate
////    public void preUpdate() {
////        lastUpdate = LocalDateTime.now();
////    }
//}