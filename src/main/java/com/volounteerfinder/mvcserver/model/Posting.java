package com.volounteerfinder.mvcserver.model;
import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "posting")
public class Posting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Boolean active;

    @OneToOne(mappedBy = "posting")
    private PostingAddress postingAddress;
}