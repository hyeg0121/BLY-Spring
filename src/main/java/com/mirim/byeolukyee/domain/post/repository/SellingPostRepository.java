package com.mirim.byeolukyee.domain.post.repository;

import com.mirim.byeolukyee.domain.post.entity.SellingPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellingPostRepository extends JpaRepository<SellingPost, Long> {
}
