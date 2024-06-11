package com.mirim.byeolukyee.domain.viewhistory.service;

import com.mirim.byeolukyee.domain.post.entity.BuyingPost;
import com.mirim.byeolukyee.domain.post.entity.Post;
import com.mirim.byeolukyee.domain.post.repository.BuyingPostRepository;
import com.mirim.byeolukyee.domain.user.entity.User;
import com.mirim.byeolukyee.domain.user.repository.UserRepository;
import com.mirim.byeolukyee.domain.viewhistory.dto.CreateViewHistoryRequest;
import com.mirim.byeolukyee.domain.viewhistory.dto.ViewHistoryResponse;
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
    private final BuyingPostRepository buyingPostRepository;

    public List<ViewHistoryResponse> findAllViewHistory() {
        List<ViewHistory> viewHistories = viewHistoryRepository.findAll();
        return viewHistories.stream()
                .map(ViewHistoryResponse::from)
                .collect(Collectors.toList());
    }

    @Transactional
    public ViewHistoryResponse createViewHistory(CreateViewHistoryRequest request){
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
        Post post = buyingPostRepository.findById(request.getPostId())
                .orElseThrow(() -> PostNotFoundException.EXCEPTION);

        ViewHistory viewHistory = ViewHistory.builder()
                .user(user)
                .post(post)
                .build();

        ViewHistory savedViewHistory = viewHistoryRepository.save(viewHistory);

        return ViewHistoryResponse.from(savedViewHistory);
    }

    public List<ViewHistoryResponse> findViewHistoriesByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
        return viewHistoryRepository.findByUser(user).stream()
                .map(ViewHistoryResponse::from)
                .collect(Collectors.toList());
    }

    public List<ViewHistoryResponse> findViewHistoriesByPost(Long postId) {
        Post post = buyingPostRepository.findById(postId)
                .orElseThrow(() -> PostNotFoundException.EXCEPTION);
        return viewHistoryRepository.findByPost(post).stream()
                .map(ViewHistoryResponse::from)
                .collect(Collectors.toList());
    }

}
