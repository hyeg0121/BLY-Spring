package com.mirim.byeolukyee.domain.wish.entity;

import com.mirim.byeolukyee.global.base.BaseEntity;
import com.mirim.byeolukyee.domain.post.entity.Post;
import com.mirim.byeolukyee.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Wish extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(targetEntity = Post.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    private boolean isLiked;    // true -> 좋아요, false -> 좋아요가 아닌 상태

    public void toggleLiked() {
        this.isLiked = !isLiked;
    }
}
