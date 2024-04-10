package com.mirim.byeolukyee.service;

import com.mirim.byeolukyee.domain.Post;
import com.mirim.byeolukyee.domain.PostImage;
import com.mirim.byeolukyee.dto.postimage.PostImageResponseDto;
import com.mirim.byeolukyee.exception.ImageNotFoundException;
import com.mirim.byeolukyee.exception.PostNotFoundException;
import com.mirim.byeolukyee.repository.SellingPostRepository;
import com.mirim.byeolukyee.repository.PostImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostImageService {

    private final PostImageRepository postImageRepository;
    private final SellingPostRepository sellingPostRepository;

    @Transactional
    public List<PostImageResponseDto> uploadPostImage(Long id, List<MultipartFile> images) throws IOException {

        List<PostImageResponseDto> postImageResponseDtos = new ArrayList<>();

        for (MultipartFile image : images) {
            // 파일 이름 (+확장자)
            String originalFileName = image.getOriginalFilename();
            int dotIndex = originalFileName.lastIndexOf('.');

            // 파일 이름과 확장자 분리
            String filename = originalFileName.substring(0, dotIndex);
            String extension = originalFileName.substring(dotIndex + 1);

            System.out.println("filename : " + originalFileName + ", ext : " + extension);

            // 새로운 파일 이름 생성
            long unixTimeMillis = System.currentTimeMillis();
            String uploadedFileName = filename + "_" + unixTimeMillis + "." + extension;

            // uploaded 폴더에 업로드
            File uploadedFile = new File("uploaded/" + uploadedFileName);
            FileOutputStream fos = new FileOutputStream(uploadedFile);
            fos.write(image.getBytes());

            fos.close();


            // 엔티티 저장
            Post post = sellingPostRepository.findById(id)
                    .orElseThrow(() -> PostNotFoundException.EXCEPTION);

            PostImage postImage = PostImage.builder()
                    .post(post)
                    .orignalFilename(originalFileName)
                    .uploadedFilename(uploadedFileName)
                    .filePath("/uploaded/" + uploadedFileName)
                    .build();

            postImageResponseDtos.add(PostImageResponseDto.from(postImageRepository.save(postImage)));
        }


        return postImageResponseDtos;
    }

    public Resource getImageResource(String uploadedFileName) throws IOException {
        // 이미지 파일의 경로를 가져옴
        Path imagePath = Paths.get("uploaded", uploadedFileName);

        // 해당 경로에 파일이 존재하는지 확인
        if (!Files.exists(imagePath) || Files.isDirectory(imagePath)) {
            // 파일이 존재하지 않거나 디렉토리인 경우 Exception 발생
            throw ImageNotFoundException.EXCEPTION;
        }

        // 이미지 파일을 Resource 객체로 읽어와 반환
        return new FileSystemResource(imagePath.toFile());
    }

}
