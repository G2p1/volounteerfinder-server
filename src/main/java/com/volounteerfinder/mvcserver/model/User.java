package com.volounteerfinder.mvcserver.model;
import lombok.Data;
import jakarta.persistence.*;
import java.util.Set;
@Entity
@Data
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @OneToOne(mappedBy = "user")
    private UserProfile userProfile;

    @OneToOne(mappedBy = "user")
    private UserAddress userAddress;

    @OneToMany(mappedBy = "fromUser")
    private Set<Comment> comments;

    @OneToMany(mappedBy = "author")
    private Set<Posting> postings;

    public enum Role {
        VOLUNTEER, CLIENT
    }
}