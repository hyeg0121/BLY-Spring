package com.mirim.byeolukyee.domain.message.dto;

import com.mirim.byeolukyee.domain.message.entity.Message;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MessageResponse {
    private final Long chatRoomId;
    private final Long userId;
    private final String content;

    @Builder
    public MessageResponse(Long chatRoomId, Long userId, String content) {
        this.chatRoomId = chatRoomId;
        this.userId = userId;
        this.content = content;
    }

    public static MessageResponse from(Message message) {
        return MessageResponse
                .builder()
                .chatRoomId(message.getChatRoom().getId())
                .userId(message.getUser().getId())
                .content(message.getContent())
                .build();
    }
}
