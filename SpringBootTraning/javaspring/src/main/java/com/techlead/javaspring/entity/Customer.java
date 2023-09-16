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
//@Table(name = "customer", indexes = @Index(columnList = "last_name"))
//public class Customer {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "customer_id", nullable = false)
//    private Integer customerId;
//
//    @Column(name = "first_name", length = 45, nullable = false)
//    private String firstName;
//
//    @Column(name = "last_name", length = 45, nullable = false)
//    private String lastName;
//
//    @Column(name = "email", length = 50)
//    private String email;
//
//    @Column(name = "active", columnDefinition = "TINYINT(1) default 1")
//    private Boolean active = true;
//
//    @ManyToOne
//    @JoinColumn(name = "store_id")
//    private Store store;
//
//    @ManyToOne
//    @JoinColumn(name = "address_id")
//    private Address address;
//
//    @CreationTimestamp
//    @Column(name = "create_date")
//    private LocalDateTime createDate;
//
//    @UpdateTimestamp
//    @Column(name = "last_update")
//    private LocalDateTime lastUpdate;
//
//}