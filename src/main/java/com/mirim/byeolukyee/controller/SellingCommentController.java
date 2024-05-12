package com.mirim.byeolukyee.controller;

import com.mirim.byeolukyee.dto.post.sellingcomment.AddSellingCommentRequest;
import com.mirim.byeolukyee.dto.post.sellingcomment.SellingCommentResponse;
import com.mirim.byeolukyee.dto.post.sellingcomment.UpdateSellingCommentRequest;
import com.mirim.byeolukyee.service.post.SellingCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/selling-comments")
@RequiredArgsConstructor
public class SellingCommentController {

    private final SellingCommentService sellingCommentService;

    @GetMapping
    public ResponseEntity<List<SellingCommentResponse>> findAllSellingComments() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(sellingCommentService.findAllSellingComments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SellingCommentResponse> findSellingCommentById(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(sellingCommentService.findSellingCommentById(id));
    }

    @PostMapping
    public ResponseEntity<SellingCommentResponse> createSellingComment(@RequestBody AddSellingCommentRequest addSellingCommentRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(sellingCommentService.createSellingComment(addSellingCommentRequestDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SellingCommentResponse> updateSellingComment(
            @PathVariable("id") Long id,
            @RequestBody UpdateSellingCommentRequest updateSellingCommentRequestDto
    ) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(sellingCommentService.updateSellingComment(id, updateSellingCommentRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBuyingPost(@PathVariable("id") Long id) {
        sellingCommentService.deleteSellingComment(id);
        return ResponseEntity.noContent().build();
    }

}
