package com.mirim.byeolukyee.domain.viewhistory.dto;


import com.mirim.byeolukyee.domain.post.dto.PostResponse;
import com.mirim.byeolukyee.domain.post.dto.sellingpost.SellingPostResponse;
import com.mirim.byeolukyee.domain.post.entity.SellingPost;
import com.mirim.byeolukyee.domain.user.dto.UserResponse;
import com.mirim.byeolukyee.domain.viewhistory.entity.ViewHistory;
import com.mirim.byeolukyee.global.base.Response;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ViewHistoryWithSellingPostResponse extends Response {
    private final Long id;
    private final UserResponse user;
    private final SellingPostResponse post;

    @Builder
    public ViewHistoryWithSellingPostResponse(Boolean isDeleted, LocalDateTime createdAt, LocalDateTime updatedAt, Long id, UserResponse user, SellingPostResponse post) {
        super(isDeleted, createdAt, updatedAt);
        this.id = id;
        this.user = user;
        this.post = post;
    }


    public static ViewHistoryWithSellingPostResponse from(ViewHistory viewHistory) {
        return ViewHistoryWithSellingPostResponse.builder()
                .isDeleted(viewHistory.getIsDeleted())
                .createdAt(viewHistory.getCreatedAt())
                .updatedAt(viewHistory.getUpdatedAt())
                .id(viewHistory.getId())
                .user(UserResponse.from(viewHistory.getUser()))
                .post(SellingPostResponse.from((SellingPost) viewHistory.getPost()))
                .build();
    }
}
