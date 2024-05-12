package com.mirim.byeolukyee.dto.user;

import com.mirim.byeolukyee.dto.Response;
import com.mirim.byeolukyee.domain.user.User;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserResponse extends Response {
    private final Long id;
    private final String name;
    private final String email;
    private final String studentId;

    @Builder
    public UserResponse(Boolean isDeleted, LocalDateTime createdAt, LocalDateTime updatedAt, Long id, String name, String email, String studentId) {
        super(isDeleted, createdAt, updatedAt);
        this.id = id;
        this.name = name;
        this.email = email;
        this.studentId = studentId;
    }

    public static UserResponse from(User user) {
        return UserResponse.builder()
                .isDeleted(user.getIsDeleted())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .studentId(user.getStudentId())
                .build();
    }
}
