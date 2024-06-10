package com.mirim.byeolukyee.domain.chatroom.repository;

import com.mirim.byeolukyee.domain.chatroom.entity.ChatRoom;
import com.mirim.byeolukyee.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    Optional<ChatRoom> findByUser1AndUser2(User user1, User user2);
    Optional<ChatRoom> findByUser2AndUser1(User user2, User user1);
}
