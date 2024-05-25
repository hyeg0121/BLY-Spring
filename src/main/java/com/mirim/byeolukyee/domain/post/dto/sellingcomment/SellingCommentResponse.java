package com.mirim.byeolukyee.domain.post.dto.sellingcomment;

import com.mirim.byeolukyee.global.constant.status.SellingCommentStatus;
import com.mirim.byeolukyee.domain.post.dto.buyingpost.BuyingPostResponse;
import com.mirim.byeolukyee.domain.post.dto.PostResponse;
import com.mirim.byeolukyee.domain.user.dto.UserResponse;
import com.mirim.byeolukyee.domain.post.entity.SellingComment;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SellingCommentResponse extends PostResponse {
    private final SellingCommentStatus status;
    private final String krStatus;

    @Builder
    public SellingCommentResponse(Boolean isDeleted, LocalDateTime createdAt, LocalDateTime updatedAt, Long id, UserResponse user, String title, String description, Integer price, String location, BuyingPostResponse referenceItem, SellingCommentStatus status) {
        super(isDeleted, createdAt, updatedAt, id, user, title, description, price, location);
        this.status = status;
        this.krStatus = status.getKrName();
    }

    public static SellingCommentResponse from(SellingComment sellingComment) {
        return SellingCommentResponse.builder()
                .isDeleted(sellingComment.getIsDeleted())
                .createdAt(sellingComment.getCreatedAt())
                .updatedAt(sellingComment.getUpdatedAt())
                .id(sellingComment.getId())
                .user(UserResponse.from(sellingComment.getUser()))
                .title(sellingComment.getTitle())
                .description(sellingComment.getDescription())
                .price(sellingComment.getPrice())
                .location(sellingComment.getLocation())
                .status(sellingComment.getStatus())
                .referenceItem(BuyingPostResponse.from(sellingComment.getReferenceItem()))
                .build();
    }
}
