package com.mirim.byeolukyee.domain.viewhistory.dto;

import com.mirim.byeolukyee.global.base.Response;
import com.mirim.byeolukyee.domain.user.dto.UserResponse;
import com.mirim.byeolukyee.domain.post.dto.PostResponse;
import com.mirim.byeolukyee.domain.viewhistory.entity.ViewHistory;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ViewHistoryResponse extends Response {
    private final Long id;
    private final UserResponse user;
    private final PostResponse post;

    @Builder
    public ViewHistoryResponse(Boolean isDeleted, LocalDateTime createdAt, LocalDateTime updatedAt, Long id, UserResponse user, PostResponse post) {
        super(isDeleted, createdAt, updatedAt);
        this.id = id;
        this.user = user;
        this.post = post;
    }

    public static ViewHistoryResponse from(ViewHistory viewHistory) {
        return ViewHistoryResponse.builder()
                .isDeleted(viewHistory.getIsDeleted())
                .createdAt(viewHistory.getCreatedAt())
                .updatedAt(viewHistory.getUpdatedAt())
                .id(viewHistory.getId())
                .user(UserResponse.from(viewHistory.getUser()))
                .post(PostResponse.from(viewHistory.getPost()))
                .build();
    }
}
