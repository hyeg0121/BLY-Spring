package com.mirim.byeolukyee.domain.token.service;

import com.mirim.byeolukyee.domain.token.entity.RefreshToken;
import com.mirim.byeolukyee.domain.token.repository.RefreshTokenRepository;
import com.mirim.byeolukyee.global.exception.user.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshToken findByRefreshToken(String refreshToken) {
        // TODO: TokenNotFoundException으로 만들기
        return refreshTokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }


}
