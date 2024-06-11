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

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ViewHistoryService {

    private final ViewHistoryRepository viewHistoryRepository;
    private final UserRepository userRepository;
    private final SellingPostRepository sellingPostRepository;
    private final BuyingPostRepository buyingPostRepository;

    public List<ViewHistoryWithBuyingPostResponse> findAllViewHistory() {
        List<ViewHistory> viewHistories = viewHistoryRepository.findAll();
        return viewHistories.stream()
                .map(ViewHistoryWithBuyingPostResponse::from)
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
            return ViewHistoryWithBuyingPostResponse.from(savedViewHistory);

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

            return ViewHistoryWithSellingPostResponse.from(savedViewHistory);

    }

    public List<ViewHistoryWithSellingPostResponse> findViewSellingHistoriesByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

            return viewHistoryRepository.findByUser(user).stream()
                    .map(ViewHistoryWithSellingPostResponse::from)
                    .collect(Collectors.toList());

    }

    public List<ViewHistoryWithBuyingPostResponse> findViewBuyingHistoriesByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

            return viewHistoryRepository.findByUser(user).stream()
                    .map(ViewHistoryWithBuyingPostResponse::from)
                    .collect(Collectors.toList());

    }

    public List<ViewHistoryWithBuyingPostResponse> findViewHistoriesByPost(Long postId) {
        Post post = sellingPostRepository.findById(postId)
                .orElseThrow(() -> PostNotFoundException.EXCEPTION);
        return viewHistoryRepository.findByPost(post).stream()
                .map(ViewHistoryWithBuyingPostResponse::from)
                .collect(Collectors.toList());
    }

}
