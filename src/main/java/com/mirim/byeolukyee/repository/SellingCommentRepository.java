package com.mirim.byeolukyee.repository;

import com.mirim.byeolukyee.domain.SellingComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellingCommentRepository extends JpaRepository<SellingComment, Long> {
}
