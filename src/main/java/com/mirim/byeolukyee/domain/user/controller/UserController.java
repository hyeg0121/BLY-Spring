package com.mirim.byeolukyee.domain.user.controller;

import com.mirim.byeolukyee.domain.post.dto.buyingpost.BuyingPostResponse;
import com.mirim.byeolukyee.domain.post.dto.sellingcomment.SellingCommentResponse;
import com.mirim.byeolukyee.domain.post.dto.sellingpost.SellingPostResponse;
import com.mirim.byeolukyee.domain.user.dto.AddUserRequest;
import com.mirim.byeolukyee.domain.user.dto.SignInUserRequest;
import com.mirim.byeolukyee.domain.user.dto.UserResponse;
import com.mirim.byeolukyee.domain.user.service.UserService;
import com.mirim.byeolukyee.domain.wish.service.WishService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final WishService wishService;

    @GetMapping
    public List<UserResponse> getAllUsers() {
        return userService.findAllUser();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.findUserById(id));
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody AddUserRequest addUserRequestDto) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.createUser(addUserRequestDto));
    }

    @PostMapping("/signin")
    public ResponseEntity<UserResponse> signInUser(@RequestBody SignInUserRequest signInUserRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.signIn(signInUserRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBuyingPost(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/selling-posts")
    public ResponseEntity<List<SellingPostResponse>> getSellingPosts(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.findSellingPostsByUserId(id));
    }

    @GetMapping("/{id}/buying-posts")
    public ResponseEntity<List<BuyingPostResponse>> getBuyingPosts(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.findBuyingPostsByUserId(id));
    }

    @GetMapping("/{id}/selling-comments")
    public ResponseEntity<List<SellingCommentResponse>> getSellingComments(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.findSellingCommentsByUserId(id));
    }
//
//    @GetMapping("{id}/wishes")
//    public ResponseEntity<List<PostResponse>> getWishes(@PathVariable("id") Long id) {
//        return ResponseEntity.status(HttpStatus.OK)
//                .body(userService.findWishesByUserId(id));
//    }
}

