package com.mirim.byeolukyee.domain.wish.controller;

import com.mirim.byeolukyee.domain.wish.dto.AddWishRequest;
import com.mirim.byeolukyee.domain.wish.dto.WishResponse;
import com.mirim.byeolukyee.domain.wish.service.WishService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishes")
@RequiredArgsConstructor
public class WishController {

    private final WishService wishService;

    @GetMapping
    public ResponseEntity<List<WishResponse>> getAllWishes() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(wishService.findAll());
    }

    @PostMapping
    public ResponseEntity<WishResponse> createWish(@RequestBody AddWishRequest addWishRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(wishService.createWish(addWishRequest));
    }
}
