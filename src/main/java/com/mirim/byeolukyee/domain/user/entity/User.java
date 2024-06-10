package com.mirim.byeolukyee.domain.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mirim.byeolukyee.domain.chatroom.entity.ChatRoom;
import com.mirim.byeolukyee.domain.wish.entity.Wish;
import com.mirim.byeolukyee.global.base.BaseEntity;
import com.mirim.byeolukyee.domain.post.entity.BuyingPost;
import com.mirim.byeolukyee.domain.post.entity.SellingComment;
import com.mirim.byeolukyee.domain.post.entity.SellingPost;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @Column(nullable = false)
    private String profileUrl; // 프로필 사진

    @Builder.Default
    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<SellingPost> sellingPosts = new ArrayList<>();

    @Builder.Default
    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<BuyingPost> buyingPosts = new ArrayList<>();

    @Builder.Default
    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<SellingComment> sellingComments = new ArrayList<>();

    @Builder.Default
    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Wish> wishes = new HashSet<>();


    @OneToMany(mappedBy = "user1", fetch = FetchType.LAZY)
    private List<ChatRoom> chatRoomsAsUser1 = new ArrayList<>();

    @OneToMany(mappedBy = "user2", fetch = FetchType.LAZY)
    private List<ChatRoom> chatRoomsAsUser2 = new ArrayList<>();

    public User update(String name) {
        this.name = name;
        this.studentId = studentId;

        return this;
    }
}
