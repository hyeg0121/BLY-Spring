package com.mirim.byeolukyee.domain.post.dto;

import com.mirim.byeolukyee.domain.post.entity.Post;
import com.mirim.byeolukyee.global.base.Response;
import com.mirim.byeolukyee.domain.user.dto.UserResponse;
import lombok.*;

import java.time.LocalDateTime;

@Getter
public class PostResponse extends Response {
    private final Long id;
    private final UserResponse user;
    private final String title;
    private final String description;
    private final Integer price;
    private final String location;


    public PostResponse(Boolean isDeleted, LocalDateTime createdAt, LocalDateTime updatedAt, Long id, UserResponse user, String title, String description, Integer price, String location) {
        super(isDeleted, createdAt, updatedAt);
        this.id = id;
        this.user = user;
        this.title = title;
        this.description = description;
        this.price = price;
        this.location = location;
    }

    public static PostResponse from(Post post) {
        return new PostResponse(
                post.getIsDeleted(),
                post.getCreatedAt(),
                post.getUpdatedAt(),
                post.getId(),
                UserResponse.from(post.getUser()),
                post.getTitle(),
                post.getDescription(),
                post.getPrice(),
                post.getLocation()
        );
    }
}
