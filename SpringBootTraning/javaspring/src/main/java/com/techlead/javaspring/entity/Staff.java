//package com.techlead.javaspring.entity;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import org.hibernate.annotations.CreationTimestamp;
//import org.hibernate.annotations.UpdateTimestamp;
//
//import java.time.LocalDateTime;
//
//@NoArgsConstructor
//@AllArgsConstructor
//@Getter
//@Setter
//@Entity
//@Table(name = "staff")
//public class Staff {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "staff_id", nullable = false, columnDefinition = "TINYINT UNSIGNED", unique = true)
//    private Integer staffId;
//
//    @Column(name = "first_name",length = 45, nullable = false)
//    private String firstName;
//
//    @Column(name = "last_name", length = 45, nullable = false)
//    private String lastName;
//
//    @Column(name = "email", length = 50)
//    private String email;
//
//    @Column(name = "username", length = 16)
//    private String username;
//
//    @Column(name = "password", length = 50)
//    private String password;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "address_id")
//    private Address address;
//
//    @ManyToOne()
//    @JoinColumn(name = "store_id")
//    private Store store;
//
//    @Lob
//    @Column(name = "picture", columnDefinition = "blob")
//    private byte[] picture;
//
//    @Column(name = "active", columnDefinition = "TINYINT(1) default 1")
//    private Boolean active = true;
//
//    //@CreationTimestamp
//    @UpdateTimestamp
//    @Column(name = "last_update", columnDefinition = "TIMESTAMP")
//    private LocalDateTime lastUpdate;
//
////    @PreUpdate
////    public void preUpdate() {
////        lastUpdate = LocalDateTime.now();
////    }
//
//}