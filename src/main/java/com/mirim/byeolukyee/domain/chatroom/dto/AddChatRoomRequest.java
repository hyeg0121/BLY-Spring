package com.mirim.byeolukyee.domain.chatroom.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddChatRoomRequest {
    private Long user1;
    private Long user2;

}
