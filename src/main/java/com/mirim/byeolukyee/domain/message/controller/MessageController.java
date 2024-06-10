package com.mirim.byeolukyee.domain.message.controller;

import com.mirim.byeolukyee.domain.message.dto.AddMessageRequest;
import com.mirim.byeolukyee.domain.message.dto.MessageResponse;
import com.mirim.byeolukyee.domain.message.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @GetMapping
    public ResponseEntity<List<MessageResponse>> getMessages() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(messageService.findAllMessages());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MessageResponse> getMessageById(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(messageService.findMessageById(id));
    }

    @PostMapping
    public ResponseEntity<MessageResponse> createMessage(@RequestBody AddMessageRequest addMessageRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(messageService.createMessage(addMessageRequest));
    }


}
