package com.mirim.byeolukyee.domain.wish.dto;

import com.mirim.byeolukyee.domain.wish.entity.Wish;
import lombok.Getter;

@Getter
public class WishResponse {
    private final Long id;
    private final Long userId;
    private final Long postId;


    public WishResponse(Long id, Long userId, Long postId) {
        this.id = id;
        this.userId = userId;
        this.postId = postId;
    }

    public static WishResponse from(Wish wish) {
        return new WishResponse(wish.getId(), wish.getUser().getId(), wish.getPost().getId());
    }
}
