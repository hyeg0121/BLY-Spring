package com.mirim.byeolukyee.repository;

import com.mirim.byeolukyee.domain.BuyingPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuyingPostRepository extends JpaRepository<BuyingPost, Long> {
    List<BuyingPost> findByIsDeletedFalse();
}
