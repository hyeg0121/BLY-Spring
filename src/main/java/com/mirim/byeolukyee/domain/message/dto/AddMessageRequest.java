package com.mirim.byeolukyee.domain.message.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddMessageRequest {
    private Long chatRoomId;
    private Long userId;
    private String content;
}
