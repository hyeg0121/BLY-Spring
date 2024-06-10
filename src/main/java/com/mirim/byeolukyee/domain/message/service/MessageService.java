package com.mirim.byeolukyee.domain.message.service;

import com.mirim.byeolukyee.domain.chatroom.entity.ChatRoom;
import com.mirim.byeolukyee.domain.chatroom.repository.ChatRoomRepository;
import com.mirim.byeolukyee.domain.message.dto.AddMessageRequest;
import com.mirim.byeolukyee.domain.message.dto.MessageResponse;
import com.mirim.byeolukyee.domain.message.entity.Message;
import com.mirim.byeolukyee.domain.message.repository.MessageRepository;
import com.mirim.byeolukyee.domain.user.entity.User;
import com.mirim.byeolukyee.domain.user.repository.UserRepository;
import com.mirim.byeolukyee.global.exception.chat.ChatRoomNotFoundException;
import com.mirim.byeolukyee.global.exception.chat.MessageNotFoundException;
import com.mirim.byeolukyee.global.exception.user.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MessageService {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final ChatRoomRepository chatRoomRepository;

    public List<MessageResponse> findAllMessages() {
        return messageRepository.findAll()
                .stream()
                .map(MessageResponse::from)
                .collect(Collectors.toList());
    }

    public MessageResponse findMessageById(Long id) {
        Message message = messageRepository.findById(id)
                .orElseThrow(() -> MessageNotFoundException.EXCEPTION);

        return MessageResponse.from(message);
    }

    @Transactional
    public MessageResponse createMessage(AddMessageRequest addMessageRequest) {
        User user = userRepository.findById(addMessageRequest.getUserId())
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        ChatRoom chatRoom = chatRoomRepository.findById(addMessageRequest.getChatRoomId())
                .orElseThrow(() -> ChatRoomNotFoundException.EXCEPTION);

        Message message = messageRepository.save(
                Message.builder()
                .chatRoom(chatRoom)
                .user(user)
                .content(addMessageRequest.getContent())
                .build()
        );

        return MessageResponse.from(message);
    }
}
