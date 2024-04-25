package com.mirim.byeolukyee.service;

import com.mirim.byeolukyee.domain.RefreshToken;
import com.mirim.byeolukyee.exception.UserNotFoundException;
import com.mirim.byeolukyee.repository.RefreshTokenRepository;
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
