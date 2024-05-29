package com.mirim.byeolukyee.domain.post.repository;

import com.mirim.byeolukyee.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
