package com.mirim.byeolukyee.controller;

import com.mirim.byeolukyee.dto.postimage.PostImageResponseDto;
import com.mirim.byeolukyee.service.PostImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;


@RestController
@RequestMapping("/post-images")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
public class PostImageController {
    private final PostImageService postImageService;

    @PostMapping(value = "/{id}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<List<PostImageResponseDto>> uploadPostImage(
            @PathVariable Long id,
            @RequestParam("file") List<MultipartFile> files
    ) throws IOException {
        return ResponseEntity.status(HttpStatus.OK)
                .body(postImageService.uploadPostImage(id, files));
    }

    @GetMapping("/{uploadedFileName}")
    @ResponseBody
    public ResponseEntity<Resource> getImage(@PathVariable String uploadedFileName) throws IOException {
        Resource imageResource = postImageService.getImageResource(uploadedFileName);

        // 이미지 파일의 MIME 타입 추정
        String contentTypeStr = Files.probeContentType(imageResource.getFile().toPath());
        MediaType contentType = MediaType.parseMediaType(contentTypeStr);

        // ResponseEntity에 Resource 객체와 헤더 정보 설정하여 반환
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(contentType);
        return ResponseEntity.ok()
                .headers(headers)
                .body(imageResource);
    }

}
