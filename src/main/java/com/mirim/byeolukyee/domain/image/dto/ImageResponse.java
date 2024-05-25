package com.mirim.byeolukyee.domain.image.dto;

import com.mirim.byeolukyee.domain.image.entity.PostImage;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ImageResponse {
    private final String uploadedFilename;

    @Builder
    public ImageResponse(String uploadedFilename) {
        this.uploadedFilename = uploadedFilename;
    }

    public static ImageResponse from(PostImage postImage) {
        return ImageResponse.builder()
                .uploadedFilename(postImage.getUploadedFilename())
                .build();
    }
}
