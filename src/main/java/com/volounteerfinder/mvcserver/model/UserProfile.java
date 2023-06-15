package com.volounteerfinder.mvcserver.model;
import lombok.Data;
import jakarta.persistence.*;
import java.util.Set;
@Entity
@Data
@Table(name = "user_profile")
public class UserProfile {
    @Id
    private Long id;

    private String title;

    private String about;

    private String skills;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private User user;
}
