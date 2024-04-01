package com.mirim.byeolukyee.controller;

import com.mirim.byeolukyee.dto.sellingcomment.AddSellingCommentRequestDto;
import com.mirim.byeolukyee.dto.sellingcomment.SellingCommentResponseDto;
import com.mirim.byeolukyee.dto.sellingcomment.UpdateSellingCommentRequestDto;
import com.mirim.byeolukyee.service.SellingCommentService;
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
    public ResponseEntity<List<SellingCommentResponseDto>> findAllSellingComments() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(sellingCommentService.findAllSellingComments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SellingCommentResponseDto> findSellingCommnetById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(sellingCommentService.findSellingCommentById(id));
    }

    @PostMapping
    public ResponseEntity<SellingCommentResponseDto> createSellingComment(@RequestBody AddSellingCommentRequestDto addSellingCommentRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(sellingCommentService.createSellingComment(addSellingCommentRequestDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SellingCommentResponseDto> updateSellingComment(
            @PathVariable Long id,
            @RequestBody UpdateSellingCommentRequestDto updateSellingCommentRequestDto
    ) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(sellingCommentService.updateSellingComment(id, updateSellingCommentRequestDto));
    }

}
