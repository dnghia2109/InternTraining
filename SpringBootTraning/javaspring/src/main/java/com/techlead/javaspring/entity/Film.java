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
//@Table(name = "film")
//public class Film {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "film_id", nullable = false)
//    private Integer filmId;
//
//    @Column(name = "title", length = 128, nullable = false)
//    private String title;
//
//    @Column(name = "description", columnDefinition = "TEXT")
//    private String description;
//
//    @Column(name = "release_year", columnDefinition = "year")
//    private Integer releaseYear;
//
//    @ManyToOne
//    @JoinColumn(name = "language_id", nullable = false)
//    private Language language;
//
//    @ManyToOne
//    @JoinColumn(name = "original_language_id")
//    private Language originalLanguage;
//
//    @Column(name = "rental_duration" ,columnDefinition = "tinyint unsigned", nullable = false)
//    private Integer rentalDuration;
//
//    @Column(name = "rental_rate", columnDefinition = "decimal(4,2) default 4.99", nullable = false)
//    private Double rentalRate;
//
//    @Column(name = "length", columnDefinition = "smallint unsigned")
//    private Integer length;
//
//    @Column(name = "replacement_cost", columnDefinition = "decimal(5,2) default 19.99")
//    private Double replacementCost;
//
//    @Column(name = "rating")
//    @Enumerated(value = EnumType.STRING)
//    private ERate rating;
//
//
//    @Column(name = "special_features", columnDefinition = "set('Trailers','Commentaries','Deleted Scenes','Behind the Scenes')")
//    private String specialFeatures;
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