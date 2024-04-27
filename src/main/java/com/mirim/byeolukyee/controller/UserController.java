package com.mirim.byeolukyee.controller;

import com.mirim.byeolukyee.dto.user.AddUserRequest;
import com.mirim.byeolukyee.dto.user.SignInUserRequest;
import com.mirim.byeolukyee.dto.user.UserResponse;
import com.mirim.byeolukyee.service.UserService;
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
}
