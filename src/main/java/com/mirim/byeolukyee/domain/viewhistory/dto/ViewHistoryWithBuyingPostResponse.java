package com.mirim.byeolukyee.domain.viewhistory.dto;

import com.mirim.byeolukyee.domain.post.dto.buyingpost.BuyingPostResponse;
import com.mirim.byeolukyee.domain.post.entity.BuyingPost;
import com.mirim.byeolukyee.global.base.Response;
import com.mirim.byeolukyee.domain.user.dto.UserResponse;
import com.mirim.byeolukyee.domain.post.dto.PostResponse;
import com.mirim.byeolukyee.domain.viewhistory.entity.ViewHistory;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ViewHistoryWithBuyingPostResponse extends Response {
    private final Long id;
    private final UserResponse user;
    private final BuyingPostResponse post;

    @Builder
    public ViewHistoryWithBuyingPostResponse(Boolean isDeleted, LocalDateTime createdAt, LocalDateTime updatedAt, Long id, UserResponse user, BuyingPostResponse post) {
        super(isDeleted, createdAt, updatedAt);
        this.id = id;
        this.user = user;
        this.post = post;
    }


    public static ViewHistoryWithBuyingPostResponse from(ViewHistory viewHistory) {
        return ViewHistoryWithBuyingPostResponse.builder()
                .isDeleted(viewHistory.getIsDeleted())
                .createdAt(viewHistory.getCreatedAt())
                .updatedAt(viewHistory.getUpdatedAt())
                .id(viewHistory.getId())
                .user(UserResponse.from(viewHistory.getUser()))
                .post(BuyingPostResponse.from((BuyingPost) viewHistory.getPost()))
                .build();
    }
}
