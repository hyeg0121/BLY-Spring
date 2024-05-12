package com.mirim.byeolukyee.repository.post;

import com.mirim.byeolukyee.domain.post.PostImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostImageRepository extends JpaRepository<PostImage, Long> {
}
