package com.mirim.byeolukyee.dto.image;

import com.mirim.byeolukyee.domain.image.PostImage;
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
