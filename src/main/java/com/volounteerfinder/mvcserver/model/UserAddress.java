package com.volounteerfinder.mvcserver.model;
import jakarta.persistence.*;
import lombok.Data;
import java.util.Set;
@Entity
@Data
@Table(name = "user_address")
public class UserAddress {
    @Id
    private Integer id;

    @Column(nullable = false)
    private Boolean busy;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private String housenumber;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private User user;
}
