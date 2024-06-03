package com.mirim.byeolukyee.domain.wish.dto;

import com.mirim.byeolukyee.domain.wish.entity.Wish;
import lombok.Getter;

@Getter
public class WishResponse {
    private final Long id;
    private final Long userId;
    private final Long postId;
    private final boolean isLiked;


    public WishResponse(Long id, Long userId, Long postId, boolean isLiked) {
        this.id = id;
        this.userId = userId;
        this.postId = postId;
        this.isLiked = isLiked;
    }

    public static WishResponse from(Wish wish) {
        return new WishResponse(wish.getId(), wish.getUser().getId(), wish.getPost().getId(), wish.isLiked());
    }
}
