package com.mirim.byeolukyee.service.token;

import com.mirim.byeolukyee.domain.token.RefreshToken;
import com.mirim.byeolukyee.exception.user.UserNotFoundException;
import com.mirim.byeolukyee.repository.token.RefreshTokenRepository;
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
