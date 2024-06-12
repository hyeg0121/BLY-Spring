package com.mirim.byeolukyee.domain.viewhistory.controller;

import com.mirim.byeolukyee.domain.viewhistory.dto.CreateViewHistoryRequest;
import com.mirim.byeolukyee.domain.viewhistory.dto.ViewHistoryWithBuyingPostResponse;
import com.mirim.byeolukyee.domain.viewhistory.dto.ViewHistoryWithSellingPostResponse;
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

    @PostMapping
    public ResponseEntity<Object> createViewHistory(@RequestBody CreateViewHistoryRequest request, @RequestParam("type") String type) {
        if(type.equals("selling")){
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(viewHistoryService.createSellingViewHistory(request));
        }else{
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(viewHistoryService.createBuyingViewHistory(request));
        }

    }


    @GetMapping
    public ResponseEntity<List<ViewHistoryWithBuyingPostResponse>> getAllViewHistory() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(viewHistoryService.findAllViewHistory());
    }


    @GetMapping("/{postId}")
    public ResponseEntity<List<ViewHistoryWithBuyingPostResponse>> getViewHistoriesByPost(@PathVariable("postId") Long postId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(viewHistoryService.findViewHistoriesByPost(postId));
    }

}
