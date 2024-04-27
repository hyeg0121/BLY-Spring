package com.mirim.byeolukyee.dto.sellingpost;

import com.mirim.byeolukyee.constant.SellingPostStatus;
import com.mirim.byeolukyee.dto.post.PostResponse;
import com.mirim.byeolukyee.dto.postimage.PostImageResponse;
import com.mirim.byeolukyee.dto.user.UserResponse;
import com.mirim.byeolukyee.domain.SellingPost;
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
    private final List<PostImageResponse> images;

    @Builder
    public SellingPostResponse(Boolean isDeleted, LocalDateTime createdAt, LocalDateTime updatedAt, Long id, UserResponse user, String title, String description, Integer price, String location, SellingPostStatus status, List<PostImageResponse> images) {
        super(isDeleted, createdAt, updatedAt, id, user, title, description, price, location);
        this.status = status;
        this.krStatus = status.getKrName();
        this.images = images;
    }

    public static SellingPostResponse from(SellingPost sellingPost) {

        List<PostImageResponse> postImageList = new ArrayList<>();

        if (sellingPost.getPostImageList() != null)
            postImageList = sellingPost.getPostImageList().stream().map(PostImageResponse::from).collect(Collectors.toList());


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
