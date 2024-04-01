package com.mirim.byeolukyee.service;

import com.mirim.byeolukyee.constant.SellingCommentStatus;
import com.mirim.byeolukyee.dto.sellingcomment.AddSellingCommentRequestDto;
import com.mirim.byeolukyee.dto.sellingcomment.SellingCommentResponseDto;
import com.mirim.byeolukyee.dto.sellingcomment.UpdateSellingCommentRequestDto;
import com.mirim.byeolukyee.domain.BuyingPost;
import com.mirim.byeolukyee.domain.SellingComment;
import com.mirim.byeolukyee.domain.User;
import com.mirim.byeolukyee.exception.PostNotFoundException;
import com.mirim.byeolukyee.exception.UserNotFoundException;
import com.mirim.byeolukyee.repository.BuyingPostRepository;
import com.mirim.byeolukyee.repository.SellingCommentRepository;
import com.mirim.byeolukyee.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SellingCommentService {

    private final SellingCommentRepository sellingCommentRepository;
    private final BuyingPostRepository buyingPostRepository;
    private final UserRepository userRepository;

    public List<SellingCommentResponseDto> findAllSellingComments() {
        List<SellingComment> sellingComments = sellingCommentRepository.findAll();
        return sellingComments.stream()
                .map(SellingCommentResponseDto::from)
                .collect(Collectors.toList());
    }

    public SellingCommentResponseDto findSellingCommentById(Long id) {
        SellingComment sellingComment = sellingCommentRepository.findById(id)
                .orElseThrow(() -> PostNotFoundException.EXCEPTION);
        return SellingCommentResponseDto.from(sellingComment);
    }

    @Transactional
    public SellingCommentResponseDto createSellingComment(AddSellingCommentRequestDto addSellingCommentRequestDto) {

        User user = userRepository.findById(addSellingCommentRequestDto.getUserId())
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        BuyingPost buyingPost = buyingPostRepository.findById(addSellingCommentRequestDto.getReferenceItemId())
                .orElseThrow(() -> PostNotFoundException.EXCEPTION);

        SellingComment sellingComment = SellingComment.builder()
                .isDeleted(false)
                .user(user)
                .title(addSellingCommentRequestDto.getTitle())
                .description(addSellingCommentRequestDto.getDescription())
                .price(addSellingCommentRequestDto.getPrice())
                .location(addSellingCommentRequestDto.getLocation())
                .referenceItem(buyingPost)
                .status(SellingCommentStatus.NOT_WON)
                .build();

        SellingComment savedSellingComment = sellingCommentRepository.save(sellingComment);

        return SellingCommentResponseDto.from(savedSellingComment);
    }

    @Transactional
    public SellingCommentResponseDto updateSellingComment(Long id, UpdateSellingCommentRequestDto updateSellingCommentRequestDto) {
        SellingComment sellingComment = sellingCommentRepository.findById(id)
                .orElseThrow(() -> PostNotFoundException.EXCEPTION);

        sellingComment.updatePost(
                updateSellingCommentRequestDto.getTitle(),
                updateSellingCommentRequestDto.getDescription(),
                updateSellingCommentRequestDto.getPrice(),
                updateSellingCommentRequestDto.getLocation(),
                updateSellingCommentRequestDto.getStatus()
        );

        SellingComment updatedSellingComment = sellingCommentRepository.save(sellingComment);

        return SellingCommentResponseDto.from(updatedSellingComment);
    }
}
