package com.mirim.byeolukyee.service;

import com.mirim.byeolukyee.domain.Post;
import com.mirim.byeolukyee.domain.PostImage;
import com.mirim.byeolukyee.dto.postimage.PostImageResponseDto;
import com.mirim.byeolukyee.exception.PostNotFoundException;
import com.mirim.byeolukyee.repository.SellingPostRepository;
import com.mirim.byeolukyee.repository.PostImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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

}
