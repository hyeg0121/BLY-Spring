package com.mirim.byeolukyee.repository.post;

import com.mirim.byeolukyee.domain.post.SellingPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellingPostRepository extends JpaRepository<SellingPost, Long> {
}
