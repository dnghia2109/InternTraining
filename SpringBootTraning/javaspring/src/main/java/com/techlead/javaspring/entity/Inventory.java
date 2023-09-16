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
//@Table(name = "inventory")
//public class Inventory {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "inventory_id", nullable = false, columnDefinition = "mediumint unsigned")
//    private Integer inventoryId;
//
//    @ManyToOne
//    @JoinColumn(name = "film_id", nullable = false)
//    private Film film;
//
//    @ManyToOne
//    @JoinColumn(name = "store_id", nullable = false)
//    private Store store;
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
//
//}