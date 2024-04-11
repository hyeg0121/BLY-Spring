package com.mirim.byeolukyee.service;

import com.mirim.byeolukyee.constant.BuyingPostStatus;
import com.mirim.byeolukyee.constant.SellingCommentStatus;
import com.mirim.byeolukyee.domain.SellingComment;
import com.mirim.byeolukyee.dto.buyingpost.AddBuyingPostRequestDto;
import com.mirim.byeolukyee.dto.buyingpost.BuyingPostResponseDto;
import com.mirim.byeolukyee.dto.buyingpost.UpdateBuyingPostRequestDto;
import com.mirim.byeolukyee.domain.BuyingPost;
import com.mirim.byeolukyee.domain.User;
import com.mirim.byeolukyee.dto.sellingcomment.SellingCommentResponseDto;
import com.mirim.byeolukyee.exception.PostNotFoundException;
import com.mirim.byeolukyee.exception.UserNotFoundException;
import com.mirim.byeolukyee.repository.BuyingPostRepository;
import com.mirim.byeolukyee.repository.SellingCommentRepository;
import com.mirim.byeolukyee.repository.UserRepository;
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

    public List<BuyingPostResponseDto> findALlBuyingPosts() {
        List<BuyingPost> buyingPosts = buyingPostRepository.findAll();
        return buyingPosts.stream()
                .map(BuyingPostResponseDto::from)
                .collect(Collectors.toList());
    }

    public BuyingPostResponseDto findBuyingPostById(Long id) {
        BuyingPost buyingPost = buyingPostRepository.findById(id)
                .orElseThrow(() -> PostNotFoundException.EXCEPTION);
        return BuyingPostResponseDto.from(buyingPost);
    }

    @Transactional
    public BuyingPostResponseDto createBuyingPost(@RequestBody AddBuyingPostRequestDto addBuyingPostRequestDto) {
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

        return BuyingPostResponseDto.from(savedBuyingPost);
    }

    @Transactional
    public BuyingPostResponseDto updateBuyingPost(Long id, UpdateBuyingPostRequestDto updateBuyingPostRequestDto) {
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

        return BuyingPostResponseDto.from(updatedBuyingPost);
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


    public List<SellingCommentResponseDto> findSellingComments(Long id) {
        BuyingPost buyingPost = buyingPostRepository.findById(id)
                .orElseThrow(() -> PostNotFoundException.EXCEPTION);

        return buyingPost.getReferencingItems().stream()
                .map(SellingCommentResponseDto::from)
                .collect(Collectors.toList());
    }
}
