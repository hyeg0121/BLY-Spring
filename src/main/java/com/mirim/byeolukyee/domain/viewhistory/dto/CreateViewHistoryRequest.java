package com.mirim.byeolukyee.domain.viewhistory.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateViewHistoryRequest {
    private Long userId;
    private Long postId;

    public CreateViewHistoryRequest(Long userId, Long postId) {
        this.userId = userId;
        this.postId = postId;
    }
}
