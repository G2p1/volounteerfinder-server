package com.volounteerfinder.mvcserver.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Set;
@Entity
@Data
@Table(name = "posting_address")
public class PostingAddress {
    @Id
    private Integer id;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private String housenumber;

    @Column(nullable = false)
    private Float lat;

    @Column(nullable = false)
    private Float lon;

    @OneToOne
    @MapsId
    @JoinColumn(name = "posting_id")
    private Posting posting;
}