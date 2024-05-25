package com.mirim.byeolukyee.domain.post.repository;

import com.mirim.byeolukyee.domain.post.entity.BuyingPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuyingPostRepository extends JpaRepository<BuyingPost, Long> {
    List<BuyingPost> findByIsDeletedFalse();
}
