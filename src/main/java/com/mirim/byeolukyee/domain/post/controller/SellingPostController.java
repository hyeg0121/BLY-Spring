package com.mirim.byeolukyee.domain.post.controller;

import com.mirim.byeolukyee.domain.post.dto.sellingpost.AddSellingPostRequest;
import com.mirim.byeolukyee.domain.post.dto.sellingpost.SellingPostResponse;
import com.mirim.byeolukyee.domain.post.dto.sellingpost.UpdateSellingPostRequest;
import com.mirim.byeolukyee.domain.post.service.SellingPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/selling-posts")
@RequiredArgsConstructor
public class SellingPostController {

    private final SellingPostService sellingPostService;

    @GetMapping
    public ResponseEntity<List<SellingPostResponse>> getAllSellingPosts() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(sellingPostService.findAllSellingPosts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SellingPostResponse> getSellingPostById(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(sellingPostService.findSellingPostById(id));
    }

    @PostMapping
    public ResponseEntity<SellingPostResponse> createSellingPost(@RequestBody AddSellingPostRequest addSellingPostRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(sellingPostService.createSellingPost(addSellingPostRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SellingPostResponse> updateSellingPost(
            @PathVariable("id") Long id,
            @RequestBody UpdateSellingPostRequest updateSellingPostRequest
    ) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(sellingPostService.updateSellingPost(id, updateSellingPostRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBuyingPost(@PathVariable("id") Long id) {
        sellingPostService.deleteSellingPost(id);
        return ResponseEntity.noContent().build();
    }
}
