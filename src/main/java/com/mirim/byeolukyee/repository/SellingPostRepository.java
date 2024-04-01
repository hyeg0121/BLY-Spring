package com.mirim.byeolukyee.repository;

import com.mirim.byeolukyee.domain.SellingPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellingPostRepository extends JpaRepository<SellingPost, Long> {
}
