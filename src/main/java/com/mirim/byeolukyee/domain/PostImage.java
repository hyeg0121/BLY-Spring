package com.mirim.byeolukyee.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class PostImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @Column(nullable = false)
    private String orignalFilename;

    @Column(nullable = false)
    private String uploadedFilename;

    @Column(nullable = false)
    private String filePath;

    @Builder
    public PostImage(Long id, Post post, String orignalFilename, String uploadedFilename, String filePath) {
        this.id = id;
        this.post = post;
        this.orignalFilename = orignalFilename;
        this.uploadedFilename = uploadedFilename;
        this.filePath = filePath;
    }
}
