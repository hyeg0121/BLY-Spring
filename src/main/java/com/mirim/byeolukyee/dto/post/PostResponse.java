package com.mirim.byeolukyee.dto.post;

import com.mirim.byeolukyee.dto.Response;
import com.mirim.byeolukyee.dto.user.UserResponse;
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
}
