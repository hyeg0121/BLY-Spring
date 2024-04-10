package com.mirim.byeolukyee.dto.postimage;

import com.mirim.byeolukyee.domain.PostImage;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PostImageResponseDto {
    private final Long id;
    private final String filePath;

    @Builder
    public PostImageResponseDto(Long id, String filePath) {
        this.id = id;
        this.filePath = filePath;
    }

    public static PostImageResponseDto from(PostImage postImage) {
        return PostImageResponseDto.builder()
                .id(postImage.getId())
                .filePath(postImage.getFilePath())
                .build();
    }
}
