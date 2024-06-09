package com.mirim.byeolukyee.domain.post.service;

import com.mirim.byeolukyee.domain.post.dto.sellingpost.AddSellingPostRequest;
import com.mirim.byeolukyee.domain.post.dto.sellingpost.SellingPostResponse;
import com.mirim.byeolukyee.domain.post.dto.sellingpost.UpdateSellingPostRequest;
import com.mirim.byeolukyee.domain.post.entity.SellingPost;
import com.mirim.byeolukyee.domain.user.entity.User;
import com.mirim.byeolukyee.global.exception.post.PostNotFoundException;
import com.mirim.byeolukyee.global.exception.user.UserNotFoundException;
import com.mirim.byeolukyee.domain.post.repository.SellingPostRepository;
import com.mirim.byeolukyee.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SellingPostService {

    private final SellingPostRepository sellingPostRepository;
    private final UserRepository userRepository;


    public List<SellingPostResponse> findAllSellingPosts() {
        List<SellingPost> sellingPosts = sellingPostRepository.findAll();
        return sellingPosts.stream()
                .filter(sellingPost -> !sellingPost.getIsDeleted())
                .map(SellingPostResponse::from)
                .collect(Collectors.toList());
    }

    public SellingPostResponse findSellingPostById(Long id) {
        SellingPost sellingPost = sellingPostRepository.findById(id).orElseThrow(() -> PostNotFoundException.EXCEPTION);
        return SellingPostResponse.from(sellingPost);
    }

    @Transactional
    public SellingPostResponse createSellingPost(AddSellingPostRequest addSellingPostRequest) {

        User user = userRepository.findById(addSellingPostRequest.getUserId())
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        SellingPost sellingPost = SellingPost.builder()
                .isDeleted(false)
                .user(user)
                .title(addSellingPostRequest.getTitle())
                .description(addSellingPostRequest.getDescription())
                .price(addSellingPostRequest.getPrice())
                .location(addSellingPostRequest.getLocation())
                .build();

        SellingPost savedSellingPost = sellingPostRepository.save(sellingPost);

        return SellingPostResponse.from(savedSellingPost);
    }

    @Transactional
    public SellingPostResponse updateSellingPost(Long id, UpdateSellingPostRequest updateSellingPostRequest) {
        SellingPost sellingPost = sellingPostRepository.findById(id)
                .orElseThrow(() -> PostNotFoundException.EXCEPTION);

        sellingPost.updatePost(
                updateSellingPostRequest.getTitle(),
                updateSellingPostRequest.getDescription(),
                updateSellingPostRequest.getPrice(),
                updateSellingPostRequest.getLocation(),
                updateSellingPostRequest.getStatus()
        );

        SellingPost updatedSellingPost = sellingPostRepository.save(sellingPost);

        return SellingPostResponse.from(updatedSellingPost);
    }

    @Transactional
    public void deleteSellingPost(Long id) {
        SellingPost sellingPost = sellingPostRepository.findById(id)
                .orElseThrow(() -> PostNotFoundException.EXCEPTION);
        sellingPost.setIsDeleted(true);
        sellingPostRepository.save(sellingPost);
    }
}
