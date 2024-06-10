package com.mirim.byeolukyee.domain.chatroom.controller;

import com.mirim.byeolukyee.domain.chatroom.dto.AddChatRoomRequest;
import com.mirim.byeolukyee.domain.chatroom.dto.ChatRoomResponse;
import com.mirim.byeolukyee.domain.chatroom.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chatrooms")
@RequiredArgsConstructor
public class ChatRoomController {

    private final ChatRoomService chatRoomService;

    @GetMapping
    public ResponseEntity<List<ChatRoomResponse>> getAllChatRooms() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(chatRoomService.findAllChatRooms());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChatRoomResponse> getChatRoomById(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(chatRoomService.findChatRoomById(id));
    }

    @PostMapping
    public ResponseEntity<ChatRoomResponse> createChatRoom(@RequestBody AddChatRoomRequest addChatRoomRequest) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(chatRoomService.createChatRoom(addChatRoomRequest));
    }
}
