package com.mirim.byeolukyee.domain.wish.service;

import com.mirim.byeolukyee.domain.user.repository.UserRepository;
import com.mirim.byeolukyee.domain.wish.dto.WishResponse;
import com.mirim.byeolukyee.domain.wish.repository.WishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class WishService {

    private final WishRepository wishRepository;
    private final UserRepository userRepository;

    public List<WishResponse> findAll() {
        return wishRepository.findAll()
                .stream()
                .map(WishResponse::from)
                .collect(Collectors.toList());
    }


}
