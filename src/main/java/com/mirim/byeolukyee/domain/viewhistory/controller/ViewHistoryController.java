package com.mirim.byeolukyee.domain.viewhistory.controller;

import com.mirim.byeolukyee.domain.viewhistory.dto.CreateViewHistoryRequest;
import com.mirim.byeolukyee.domain.viewhistory.dto.ViewHistoryResponse;
import com.mirim.byeolukyee.domain.viewhistory.service.ViewHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/view-history")
@RequiredArgsConstructor
public class ViewHistoryController {

    private final ViewHistoryService viewHistoryService;

    @GetMapping
    public ResponseEntity<List<ViewHistoryResponse>> getAllViewHistory() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(viewHistoryService.findAllViewHistory());
    }

    @PostMapping
    public ResponseEntity<ViewHistoryResponse> createViewHistory(@RequestBody CreateViewHistoryRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(viewHistoryService.createViewHistory(request));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ViewHistoryResponse>> getViewHistoriesByUser(@PathVariable("userId") Long userId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(viewHistoryService.findViewHistoriesByUser(userId));
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<List<ViewHistoryResponse>> getViewHistoriesByPost(@PathVariable("postId") Long postId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(viewHistoryService.findViewHistoriesByPost(postId));
    }

}
