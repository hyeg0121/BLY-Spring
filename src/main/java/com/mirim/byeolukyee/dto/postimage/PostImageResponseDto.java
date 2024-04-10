package com.mirim.byeolukyee.dto.postimage;

import com.mirim.byeolukyee.domain.PostImage;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PostImageResponseDto {
    private final Long id;
    private final String uploadedFilename;

    @Builder
    public PostImageResponseDto(Long id, String uploadedFilename) {
        this.id = id;
        this.uploadedFilename = uploadedFilename;
    }

    public static PostImageResponseDto from(PostImage postImage) {
        return PostImageResponseDto.builder()
                .id(postImage.getId())
                .uploadedFilename(postImage.getUploadedFilename())
                .build();
    }
}
