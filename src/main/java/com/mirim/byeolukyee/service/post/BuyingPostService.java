package com.mirim.byeolukyee.service.post;

import com.mirim.byeolukyee.constant.status.BuyingPostStatus;
import com.mirim.byeolukyee.constant.status.SellingCommentStatus;
import com.mirim.byeolukyee.domain.post.SellingComment;
import com.mirim.byeolukyee.dto.post.buyingpost.AddBuyingPostRequest;
import com.mirim.byeolukyee.dto.post.buyingpost.BuyingPostResponse;
import com.mirim.byeolukyee.dto.post.buyingpost.UpdateBuyingPostRequest;
import com.mirim.byeolukyee.domain.post.BuyingPost;
import com.mirim.byeolukyee.domain.user.User;
import com.mirim.byeolukyee.dto.post.sellingcomment.SellingCommentResponse;
import com.mirim.byeolukyee.exception.post.PostNotFoundException;
import com.mirim.byeolukyee.exception.user.UserNotFoundException;
import com.mirim.byeolukyee.repository.post.BuyingPostRepository;
import com.mirim.byeolukyee.repository.post.SellingCommentRepository;
import com.mirim.byeolukyee.repository.user.UserRepository;
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

    public void deleteBuyingPost(Long id) {
        BuyingPost buyingPost = buyingPostRepository.findById(id)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
        buyingPost.setIsDeleted(true);
        buyingPostRepository.save(buyingPost);
    }
}
