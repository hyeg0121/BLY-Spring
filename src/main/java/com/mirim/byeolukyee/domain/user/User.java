package com.mirim.byeolukyee.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mirim.byeolukyee.domain.BaseEntity;
import com.mirim.byeolukyee.domain.image.ProfileImage;
import com.mirim.byeolukyee.domain.post.Post;
import com.mirim.byeolukyee.domain.image.PostImage;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String studentId;   // 학번

    @Builder.Default
    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Post> items = new ArrayList<>();  // 아이템 리스트

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_image_id", referencedColumnName = "id")
    private ProfileImage profileImage;

    public User update(String name) {
        this.name = name;
        this.studentId = studentId;

        return this;
    }
}
