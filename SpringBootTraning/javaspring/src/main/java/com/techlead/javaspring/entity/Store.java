//package com.techlead.javaspring.entity;
//
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//import org.hibernate.annotations.UpdateTimestamp;
//
//import java.time.LocalDateTime;
//
//@Getter
//@Setter
//@Entity
//@Table(name = "store")
//public class Store {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "store_id", nullable = false)
//    private Integer storeId;
//
////  orphanRemoval là một đặc tả trong ORM. Nó đánh dấu rằng các
////  phần tử con sẽ bị xóa khi bạn xóa nó khỏi collection của phần tử cha
//    @ManyToOne()
//    @JoinColumn(name = "manager_staff_id")
//    private Staff staff;
//
//    @OneToOne()
//    @JoinColumn(name = "address_id")
//    private Address address;
//
//    @UpdateTimestamp
//    @Column(name = "last_update", columnDefinition = "TIMESTAMP CURRENT", nullable = false)
//    private LocalDateTime lastUpdate = LocalDateTime.now();
//
//
//}