package com.mirim.byeolukyee.domain.image.repository;

import com.mirim.byeolukyee.domain.image.entity.PostImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostImageRepository extends JpaRepository<PostImage, Long> {
}
