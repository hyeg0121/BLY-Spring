package com.mirim.byeolukyee.domain.wish.service;

import com.mirim.byeolukyee.domain.post.entity.Post;
import com.mirim.byeolukyee.domain.post.repository.PostRepository;
import com.mirim.byeolukyee.domain.user.entity.User;
import com.mirim.byeolukyee.domain.user.repository.UserRepository;
import com.mirim.byeolukyee.domain.wish.dto.AddWishRequest;
import com.mirim.byeolukyee.domain.wish.dto.WishResponse;
import com.mirim.byeolukyee.domain.wish.entity.Wish;
import com.mirim.byeolukyee.domain.wish.repository.WishRepository;
import com.mirim.byeolukyee.global.exception.post.PostNotFoundException;
import com.mirim.byeolukyee.global.exception.user.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class WishService {

    private final WishRepository wishRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public List<WishResponse> findAll() {
        return wishRepository.findAll()
                .stream()
                .map(WishResponse::from)
                .collect(Collectors.toList());
    }

    @Transactional
    public WishResponse createWish(AddWishRequest addWishRequest) {
        User user = userRepository.findById(addWishRequest.getUserId())
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        Post post = postRepository.findById(addWishRequest.getPostId())
                .orElseThrow(() -> PostNotFoundException.EXCEPTION);

        Optional<Wish> existingWishOpt = wishRepository.findByUserIdAndPostId(addWishRequest.getUserId(), addWishRequest.getPostId());

        Wish wish;
        if (existingWishOpt.isPresent()) {
            wish = existingWishOpt.get();
            wish.toggleLiked();
            System.out.println(wish.isLiked());
            wish = wishRepository.save(wish);
        } else {
            wish = wishRepository.save(
                    Wish.builder()
                            .user(user)
                            .post(post)
                            .isLiked(true)
                            .build()
            );
        }

        return WishResponse.from(wish);
    }

    public Map<String, Boolean> hasUserLikedPost(Long userId, Long postId) {
        Optional<Wish> existingWishOpt = wishRepository.findByUserIdAndPostId(userId, postId);
        Map<String, Boolean> response = new HashMap<>();

        if (existingWishOpt.isPresent() && existingWishOpt.get().isLiked()) {
            response.put("isLiked", true);
        } else {
            response.put("isLiked", false);
        }

        return response;
    }
}
