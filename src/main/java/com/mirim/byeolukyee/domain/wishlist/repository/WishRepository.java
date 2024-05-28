package com.mirim.byeolukyee.domain.wishlist.repository;

import com.mirim.byeolukyee.domain.wishlist.entity.Wish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishRepository extends JpaRepository<Wish, Long> {
    List<Wish> findByUserId(Long userId);
    List<Wish> findByPostId(Long postId);
}
