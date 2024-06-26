package com.mirim.byeolukyee.domain.post.repository;

import com.mirim.byeolukyee.domain.post.entity.SellingComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellingCommentRepository extends JpaRepository<SellingComment, Long> {
}
