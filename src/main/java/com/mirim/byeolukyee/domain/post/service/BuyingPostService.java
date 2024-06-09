package com.mirim.byeolukyee.domain.post.service;

import com.mirim.byeolukyee.global.constant.status.BuyingPostStatus;
import com.mirim.byeolukyee.global.constant.status.SellingCommentStatus;
import com.mirim.byeolukyee.domain.post.entity.SellingComment;
import com.mirim.byeolukyee.domain.post.dto.buyingpost.AddBuyingPostRequest;
import com.mirim.byeolukyee.domain.post.dto.buyingpost.BuyingPostResponse;
import com.mirim.byeolukyee.domain.post.dto.buyingpost.UpdateBuyingPostRequest;
import com.mirim.byeolukyee.domain.post.entity.BuyingPost;
import com.mirim.byeolukyee.domain.user.entity.User;
import com.mirim.byeolukyee.domain.post.dto.sellingcomment.SellingCommentResponse;
import com.mirim.byeolukyee.global.exception.post.PostNotFoundException;
import com.mirim.byeolukyee.global.exception.user.UserNotFoundException;
import com.mirim.byeolukyee.domain.post.repository.BuyingPostRepository;
import com.mirim.byeolukyee.domain.post.repository.SellingCommentRepository;
import com.mirim.byeolukyee.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BuyingPostService {

    private final BuyingPostRepository buyingPostRepository;
    private final SellingCommentRepository sellingCommentRepository;
    private final UserRepository userRepository;

    public List<BuyingPostResponse> findALlBuyingPosts() {
        List<BuyingPost> buyingPosts = buyingPostRepository.findAll();
        return buyingPosts.stream()
                .filter(buyingPost -> !buyingPost.getIsDeleted())
                .map(BuyingPostResponse::from)
                .collect(Collectors.toList());
    }

    public BuyingPostResponse findBuyingPostById(Long id) {
        BuyingPost buyingPost = buyingPostRepository.findById(id)
                .orElseThrow(() -> PostNotFoundException.EXCEPTION);
        return BuyingPostResponse.from(buyingPost);
    }

    @Transactional
    public BuyingPostResponse createBuyingPost(@RequestBody AddBuyingPostRequest addBuyingPostRequestDto) {
        User user = userRepository.findById(addBuyingPostRequestDto.getUserId())
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        BuyingPost buyingPost = BuyingPost.builder()
                .isDeleted(false)
                .user(user)
                .title(addBuyingPostRequestDto.getTitle())
                .description(addBuyingPostRequestDto.getDescription())
                .price(addBuyingPostRequestDto.getPrice())
                .location(addBuyingPostRequestDto.getLocation())
                .build();

        BuyingPost savedBuyingPost = buyingPostRepository.save(buyingPost);

        return BuyingPostResponse.from(savedBuyingPost);
    }

    @Transactional
    public BuyingPostResponse updateBuyingPost(Long id, UpdateBuyingPostRequest updateBuyingPostRequestDto) {
        BuyingPost buyingPost = buyingPostRepository.findById(id)
                .orElseThrow(() -> PostNotFoundException.EXCEPTION);

        buyingPost.updatePost(
                updateBuyingPostRequestDto.getTitle(),
                updateBuyingPostRequestDto.getDescription(),
                updateBuyingPostRequestDto.getPrice(),
                updateBuyingPostRequestDto.getLocation(),
                updateBuyingPostRequestDto.getStatus()
        );

        BuyingPost updatedBuyingPost = buyingPostRepository.save(buyingPost);

        return BuyingPostResponse.from(updatedBuyingPost);
    }

    @Transactional
    public void makeBidAndWinAuction(Long buyingPostId, Long sellingCommentId) {
        BuyingPost buyingPost = buyingPostRepository.findById(buyingPostId)
                .orElseThrow(() -> PostNotFoundException.EXCEPTION);

        SellingComment sellingComment = buyingPost.getReferencingItems().stream()
                .filter(comment-> comment.getId().equals(sellingCommentId))
                .findFirst().orElseThrow(() -> PostNotFoundException.EXCEPTION);

        buyingPost.setStatus(BuyingPostStatus.COMPLETE);
        sellingComment.setStatus(SellingCommentStatus.WON);

        buyingPostRepository.save(buyingPost);
        sellingCommentRepository.save(sellingComment);
    }


    public List<SellingCommentResponse> findSellingComments(Long id) {
        BuyingPost buyingPost = buyingPostRepository.findById(id)
                .orElseThrow(() -> PostNotFoundException.EXCEPTION);

        return buyingPost.getReferencingItems().stream()
                .map(SellingCommentResponse::from)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteBuyingPost(Long id) {
        BuyingPost buyingPost = buyingPostRepository.findById(id)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
        buyingPost.setIsDeleted(true);
        buyingPostRepository.save(buyingPost);
    }
}
