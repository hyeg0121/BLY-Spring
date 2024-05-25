package com.mirim.byeolukyee.domain.post.dto.sellingpost;

import com.mirim.byeolukyee.global.constant.status.SellingPostStatus;
import com.mirim.byeolukyee.domain.post.dto.PostResponse;
import com.mirim.byeolukyee.domain.image.dto.ImageResponse;
import com.mirim.byeolukyee.domain.user.dto.UserResponse;
import com.mirim.byeolukyee.domain.post.entity.SellingPost;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class SellingPostResponse extends PostResponse {

    private final SellingPostStatus status;
    private final String krStatus;
    private final List<ImageResponse> images;

    @Builder
    public SellingPostResponse(Boolean isDeleted, LocalDateTime createdAt, LocalDateTime updatedAt, Long id, UserResponse user, String title, String description, Integer price, String location, SellingPostStatus status, List<ImageResponse> images) {
        super(isDeleted, createdAt, updatedAt, id, user, title, description, price, location);
        this.status = status;
        this.krStatus = status.getKrName();
        this.images = images;
    }

    public static SellingPostResponse from(SellingPost sellingPost) {

        List<ImageResponse> postImageList = new ArrayList<>();

        if (sellingPost.getPostImageList() != null)
            postImageList = sellingPost.getPostImageList().stream().map(ImageResponse::from).collect(Collectors.toList());


        return SellingPostResponse.builder()
                .isDeleted(sellingPost.getIsDeleted())
                .createdAt(sellingPost.getCreatedAt())
                .updatedAt(sellingPost.getUpdatedAt())
                .id(sellingPost.getId())
                .user(UserResponse.from(sellingPost.getUser()))
                .title(sellingPost.getTitle())
                .description(sellingPost.getDescription())
                .price(sellingPost.getPrice())
                .location(sellingPost.getLocation())
                .status(sellingPost.getStatus())
                .images(postImageList)
                .build();
    }

}
