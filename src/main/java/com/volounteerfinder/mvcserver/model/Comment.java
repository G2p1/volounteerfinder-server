package com.volounteerfinder.mvcserver.model;
import lombok.Data;
import jakarta.persistence.*;
import java.util.Set;
@Entity
@Data
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "from_id", nullable = false)
    private User fromUser;

    @ManyToOne
    @JoinColumn(name = "to_id", nullable = false)
    private UserProfile toUser;

    @Column(nullable = false)
    private String text;
}