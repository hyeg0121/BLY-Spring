package com.mirim.byeolukyee.controller;

import com.mirim.byeolukyee.dto.token.CreateAccessTokenRequestDto;
import com.mirim.byeolukyee.dto.token.CreateAccessTokenResponseDto;
import com.mirim.byeolukyee.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/token")
@RequiredArgsConstructor
public class TokenApiContoller {

    private final TokenService tokenService;

    @PostMapping
    public ResponseEntity<CreateAccessTokenResponseDto> createNewAccessToken(
            @RequestBody CreateAccessTokenRequestDto request
    ) {
        String newAccessToken = tokenService.createNewAccessToken(request.getRefreshToken());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new CreateAccessTokenResponseDto(newAccessToken));
    }
}
