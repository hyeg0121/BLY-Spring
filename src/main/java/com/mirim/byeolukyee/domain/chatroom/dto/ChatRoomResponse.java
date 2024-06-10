package com.mirim.byeolukyee.domain.chatroom.dto;

import com.mirim.byeolukyee.domain.chatroom.entity.ChatRoom;
import com.mirim.byeolukyee.domain.user.dto.UserResponse;
import lombok.Builder;
import lombok.Getter;

@Getter

public class ChatRoomResponse {
    private final Long id;
    private final UserResponse user1;
    private final UserResponse user2;

    @Builder
    public ChatRoomResponse(Long id, UserResponse user1, UserResponse user2) {
        this.id = id;
        this.user1 = user1;
        this.user2 = user2;
    }

    public static ChatRoomResponse from(ChatRoom chatRoom) {
        return ChatRoomResponse.builder()
                .id(chatRoom.getId())
                .user1(UserResponse.from(chatRoom.getUser1()))
                .user2(UserResponse.from(chatRoom.getUser2()))
                .build();
    }
}
