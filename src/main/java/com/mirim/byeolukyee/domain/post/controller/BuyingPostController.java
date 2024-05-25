package com.mirim.byeolukyee.domain.post.controller;

import com.mirim.byeolukyee.domain.post.dto.buyingpost.AddBuyingPostRequest;
import com.mirim.byeolukyee.domain.post.dto.buyingpost.BuyingPostResponse;
import com.mirim.byeolukyee.domain.post.dto.buyingpost.UpdateBuyingPostRequest;
import com.mirim.byeolukyee.domain.post.dto.sellingcomment.SellingCommentResponse;
import com.mirim.byeolukyee.domain.post.service.BuyingPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/buying-posts")
@RequiredArgsConstructor
public class BuyingPostController {

    private final BuyingPostService buyingPostService;

    @GetMapping
    public ResponseEntity<List<BuyingPostResponse>> getAllBuyingPosts() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(buyingPostService.findALlBuyingPosts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BuyingPostResponse> getBuyingPostById(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(buyingPostService.findBuyingPostById(id));
    }

    @PostMapping
    public ResponseEntity<BuyingPostResponse> createBuyingRequest(@RequestBody AddBuyingPostRequest addBuyingPostRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(buyingPostService.createBuyingPost(addBuyingPostRequestDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BuyingPostResponse> updateBuyingRequeest(
            @PathVariable("id") Long id,
            @RequestBody UpdateBuyingPostRequest updateBuyingPostRequestDto
    ) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(buyingPostService.updateBuyingPost(id, updateBuyingPostRequestDto));
    }

    @PutMapping("/bid")
    public ResponseEntity<Void> makeBidAndWinAuction(@RequestParam("buyingPostId") Long buyingPostId,
                                                        @RequestParam("sellingCommentId") Long sellingCommentId) {
        buyingPostService.makeBidAndWinAuction(buyingPostId, sellingCommentId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/selling-comments")
    public ResponseEntity<List<SellingCommentResponse>> findSellingComments(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(buyingPostService.findSellingComments(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBuyingPost(@PathVariable("id") Long id) {
        buyingPostService.deleteBuyingPost(id);
        return ResponseEntity.noContent().build();
    }
}
