package com.mirim.byeolukyee.dto.post.postimage;

import com.mirim.byeolukyee.domain.post.PostImage;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PostImageResponse {
    private final Long id;
    private final String uploadedFilename;

    @Builder
    public PostImageResponse(Long id, String uploadedFilename) {
        this.id = id;
        this.uploadedFilename = uploadedFilename;
    }

    public static PostImageResponse from(PostImage postImage) {
        return PostImageResponse.builder()
                .id(postImage.getId())
                .uploadedFilename(postImage.getUploadedFilename())
                .build();
    }
}
