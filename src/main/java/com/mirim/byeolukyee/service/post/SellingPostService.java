package com.mirim.byeolukyee.service.post;

import com.mirim.byeolukyee.dto.post.sellingpost.AddSellingPostRequest;
import com.mirim.byeolukyee.dto.post.sellingpost.SellingPostResponse;
import com.mirim.byeolukyee.dto.post.sellingpost.UpdateSellingPostRequest;
import com.mirim.byeolukyee.domain.post.SellingPost;
import com.mirim.byeolukyee.domain.user.User;
import com.mirim.byeolukyee.exception.post.PostNotFoundException;
import com.mirim.byeolukyee.exception.user.UserNotFoundException;
import com.mirim.byeolukyee.repository.post.SellingPostRepository;
import com.mirim.byeolukyee.repository.user.UserRepository;
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

    public void deleteSellingPost(Long id) {
        SellingPost sellingPost = sellingPostRepository.findById(id)
                .orElseThrow(() -> PostNotFoundException.EXCEPTION);
        sellingPost.setIsDeleted(true);
        sellingPostRepository.save(sellingPost);
    }
}
