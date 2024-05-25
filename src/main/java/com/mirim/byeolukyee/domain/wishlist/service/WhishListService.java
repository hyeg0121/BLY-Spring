package com.mirim.byeolukyee.domain.wishlist.service;

import com.mirim.byeolukyee.domain.wishlist.repository.WishListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class WhishListService {

    private final WishListRepository whishListRepository;

}
