package com.mirim.byeolukyee.domain.viewhistory.service;

import com.mirim.byeolukyee.domain.post.entity.BuyingPost;
import com.mirim.byeolukyee.domain.post.entity.Post;
import com.mirim.byeolukyee.domain.post.entity.SellingPost;
import com.mirim.byeolukyee.domain.post.repository.BuyingPostRepository;
import com.mirim.byeolukyee.domain.post.repository.SellingPostRepository;
import com.mirim.byeolukyee.domain.user.entity.User;
import com.mirim.byeolukyee.domain.user.repository.UserRepository;
import com.mirim.byeolukyee.domain.viewhistory.dto.CreateViewHistoryRequest;
import com.mirim.byeolukyee.domain.viewhistory.dto.ViewHistoryWithBuyingPostResponse;
import com.mirim.byeolukyee.domain.viewhistory.dto.ViewHistoryWithSellingPostResponse;
import com.mirim.byeolukyee.domain.viewhistory.entity.ViewHistory;
import com.mirim.byeolukyee.domain.viewhistory.repository.ViewHistoryRepository;
import com.mirim.byeolukyee.global.exception.post.PostNotFoundException;
import com.mirim.byeolukyee.global.exception.user.UserNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.View;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ViewHistoryService {

    private final ViewHistoryRepository viewHistoryRepository;
    private final UserRepository userRepository;
    private final SellingPostRepository sellingPostRepository;
    private final BuyingPostRepository buyingPostRepository;

    public List<ViewHistoryWithBuyingPostResponse> findAllViewHistoryWithBuyingPosts() {

        return viewHistoryRepository.findAll()
                .stream()
                .filter(viewHistory -> isBuyingPostHistory(viewHistory.getPost().getId()))
                .map(this::createResponseWithBuyingPosts)
                .collect(Collectors.toList());
    }

    public List<ViewHistoryWithSellingPostResponse> findAllViewHistoryWithSellingPosts() {

        return viewHistoryRepository.findAll()
                .stream()
                .filter(viewHistory -> isSellingPostHistory(viewHistory.getPost().getId()))
                .map(this::createResponseWithSellingPosts)
                .collect(Collectors.toList());
    }

    @Transactional
    public ViewHistoryWithBuyingPostResponse createBuyingViewHistory(CreateViewHistoryRequest request){
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
            BuyingPost buyingPost = buyingPostRepository.findById(request.getPostId())
                    .orElseThrow(() -> PostNotFoundException.EXCEPTION);
            ViewHistory viewHistory = ViewHistory.builder()
                    .user(user)
                    .post(buyingPost)
                    .build();

            ViewHistory savedViewHistory = viewHistoryRepository.save(viewHistory);
            return ViewHistoryWithBuyingPostResponse.from(savedViewHistory, buyingPost);

    }

    @Transactional
    public ViewHistoryWithSellingPostResponse createSellingViewHistory(CreateViewHistoryRequest request){
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

            SellingPost sellingPost = sellingPostRepository.findById(request.getPostId())
                    .orElseThrow(() -> PostNotFoundException.EXCEPTION);

            ViewHistory viewHistory = ViewHistory.builder()
                    .user(user)
                    .post(sellingPost)
                    .build();
            ViewHistory savedViewHistory = viewHistoryRepository.save(viewHistory);

            return ViewHistoryWithSellingPostResponse.from(savedViewHistory, sellingPost);

    }

    public List<ViewHistoryWithSellingPostResponse> findViewSellingHistoriesByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

            return viewHistoryRepository.findByUser(user).stream()
                    .filter(viewHistory -> isSellingPostHistory(viewHistory.getPost().getId()))
                    .map(this::createResponseWithSellingPosts)
                    .collect(Collectors.toList());
    }

    public List<ViewHistoryWithBuyingPostResponse> findViewBuyingHistoriesByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

            return viewHistoryRepository.findByUser(user).stream()
                    .filter(viewHistory -> isBuyingPostHistory(viewHistory.getPost().getId()))
                    .map(this::createResponseWithBuyingPosts)
                    .collect(Collectors.toList());

    }

    public ViewHistoryWithBuyingPostResponse createResponseWithBuyingPosts(ViewHistory viewHistory) {
        BuyingPost buyingPost = buyingPostRepository.findById(viewHistory.getPost().getId())
                .orElseThrow(() -> PostNotFoundException.EXCEPTION);

        return ViewHistoryWithBuyingPostResponse.from(viewHistory, buyingPost);
    }

    public ViewHistoryWithSellingPostResponse createResponseWithSellingPosts(ViewHistory viewHistory) {
        SellingPost sellingPost = sellingPostRepository.findById(viewHistory.getPost().getId())
                .orElseThrow(() -> PostNotFoundException.EXCEPTION);

        return ViewHistoryWithSellingPostResponse.from(viewHistory, sellingPost);
    }

    public boolean isBuyingPostHistory(Long postId) {
        BuyingPost buyingPost = buyingPostRepository.findById(postId)
                .orElse(null);

        return buyingPost != null;
    }

    public boolean isSellingPostHistory(Long postId) {
        SellingPost sellingPost = sellingPostRepository.findById(postId)
                .orElse(null);

        return sellingPost != null;
    }

}
