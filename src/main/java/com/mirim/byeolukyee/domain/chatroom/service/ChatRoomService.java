package com.mirim.byeolukyee.domain.chatroom.service;

import com.mirim.byeolukyee.domain.chatroom.dto.AddChatRoomRequest;
import com.mirim.byeolukyee.domain.chatroom.dto.ChatRoomResponse;
import com.mirim.byeolukyee.domain.chatroom.entity.ChatRoom;
import com.mirim.byeolukyee.domain.chatroom.repository.ChatRoomRepository;
import com.mirim.byeolukyee.domain.user.entity.User;
import com.mirim.byeolukyee.domain.user.repository.UserRepository;
import com.mirim.byeolukyee.global.exception.chat.ChatRoomNotFoundException;
import com.mirim.byeolukyee.global.exception.chat.DuplicateChatRoomException;
import com.mirim.byeolukyee.global.exception.user.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final UserRepository userRepository;

    public List<ChatRoomResponse> findAllChatRooms() {
        return chatRoomRepository.findAll()
                .stream()
                .map(ChatRoomResponse::from)
                .collect(Collectors.toList());
    }

    public ChatRoomResponse findChatRoomById(Long id) {
        ChatRoom chatRoom = chatRoomRepository.findById(id)
                .orElseThrow(() -> ChatRoomNotFoundException.EXCEPTION);

        return ChatRoomResponse.from(chatRoom);
    }

    @Transactional
    public ChatRoomResponse createChatRoom(AddChatRoomRequest addChatRoomRequest) {
        User user1 = userRepository.findById(addChatRoomRequest.getUser1())
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        User user2 = userRepository.findById(addChatRoomRequest.getUser2())
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        boolean chatRoomExists = chatRoomRepository.findByUser1AndUser2(user1, user2).isPresent() ||
                chatRoomRepository.findByUser2AndUser1(user1, user2).isPresent();

        if (chatRoomExists) {
            throw DuplicateChatRoomException.EXCEPTION;
        }

        ChatRoom createdChatRoom = chatRoomRepository.save(
                ChatRoom.builder()
                        .user1(user1)
                        .user2(user2)
                        .build()
        );

        return ChatRoomResponse.from(createdChatRoom);
    }

    public List<ChatRoomResponse> findChatRoomsByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        List<ChatRoom> chatRooms = user.getChatRoomsAsUser1();
        chatRooms.addAll(user.getChatRoomsAsUser2());

        return chatRooms.stream()
                .map(ChatRoomResponse::from)
                .collect(Collectors.toList());
    }
}
