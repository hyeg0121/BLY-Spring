package com.mirim.byeolukyee.domain.viewhistory.repository;

import com.mirim.byeolukyee.domain.post.entity.Post;
import com.mirim.byeolukyee.domain.user.entity.User;
import com.mirim.byeolukyee.domain.viewhistory.entity.ViewHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ViewHistoryRepository extends JpaRepository<ViewHistory, Long> {
    List<ViewHistory> findByUser(User user);
    List<ViewHistory> findByPost(Post post);
}
