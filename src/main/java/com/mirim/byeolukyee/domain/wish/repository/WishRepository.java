package com.mirim.byeolukyee.domain.wish.repository;

import com.mirim.byeolukyee.domain.wish.entity.Wish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WishRepository extends JpaRepository<Wish, Long> {
    Optional<Wish> findByUserIdAndPostId(Long userId, Long postId);
}
