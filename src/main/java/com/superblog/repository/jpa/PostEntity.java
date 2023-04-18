package com.superblog.repository.jpa;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.OffsetDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "post")
@Table(name = "post")
public class PostEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "post_id", nullable = false, unique = true)
    private String postId;

    @Column(name = "title")
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "date_created", nullable = false)
    private OffsetDateTime dateCreated;

    @Column(name = "date_modified")
    private OffsetDateTime dateModified;

    @PrePersist
    private void prePersist() {
        if (dateCreated == null) {
            dateCreated = OffsetDateTime.now();
        }
    }

    @PreUpdate
    private void preUpdate() {
        if (dateModified == null) {
            dateModified = OffsetDateTime.now();
        }
    }
}
