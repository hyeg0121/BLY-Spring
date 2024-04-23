package com.mirim.byeolukyee.service;

import com.mirim.byeolukyee.config.jwt.TokenProvider;
import com.mirim.byeolukyee.domain.User;
import com.mirim.byeolukyee.exception.InvalidTokenException;
import com.mirim.byeolukyee.exception.UserNotFoundException;
import com.mirim.byeolukyee.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class TokenService {
    private final TokenProvider tokenProvider;
    private final RefreshTokenService refreshTokenService;
    private final UserRepository userRepository;

    public String createNewAccessToken(String refreshToken) {
        // 토큰 유효성 검사에 실패하면 예외 발생
        if (!tokenProvider.validToken(refreshToken)) throw InvalidTokenException.EXCEPTION;

        Long userId = refreshTokenService.findByRefreshToken(refreshToken).getUserId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        return tokenProvider.generateToken(user, Duration.ofHours(2));
    }
}
