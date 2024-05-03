package com.mirim.byeolukyee.config;

import com.mirim.byeolukyee.config.jwt.TokenProvider;
import com.mirim.byeolukyee.config.oauth.OAuth2AuthorizationRequestBasedOnCookieRepository;
import com.mirim.byeolukyee.config.oauth.OAuth2SuccessHandler;
import com.mirim.byeolukyee.repository.RefreshTokenRepository;
import com.mirim.byeolukyee.repository.UserRepository;
import com.mirim.byeolukyee.service.OAuth2UserCustomService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@RequiredArgsConstructor
public class WebOauthSecurityConfig {

    private final OAuth2UserCustomService oAuth2UserCustomService;
    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                .requestMatchers(
                        // TODO: AntPathRequestMatcher 수정
                        new AntPathRequestMatcher("/**")
                );
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        OAuth2AuthorizationRequestBasedOnCookieRepository oAuth2AuthorizationRequestBasedOnCookieRepository = oAuth2AuthorizationRequestBasedOnCookieRepository();

        return http
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .logout(AbstractHttpConfigurer::disable)
                .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests(auth -> auth
                        .requestMatchers(new AntPathRequestMatcher("/token")).permitAll()
                        .anyRequest().permitAll())
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/login")
                        .authorizationEndpoint(authorizationEndpoint -> authorizationEndpoint.authorizationRequestRepository(oAuth2AuthorizationRequestBasedOnCookieRepository))
                        .userInfoEndpoint(userInfoEndpoint -> userInfoEndpoint.userService(oAuth2UserCustomService))
                        .successHandler(oAuth2SuccessHandler(oAuth2AuthorizationRequestBasedOnCookieRepository))
                )
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .defaultAuthenticationEntryPointFor(
                                new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED),
                                new AntPathRequestMatcher("/**")
                        ))
                .build();
    }

    @Bean
    public OAuth2SuccessHandler oAuth2SuccessHandler(OAuth2AuthorizationRequestBasedOnCookieRepository oAuth2AuthorizationRequestBasedOnCookieRepository) {
        return new OAuth2SuccessHandler(tokenProvider,
                refreshTokenRepository,
                oAuth2AuthorizationRequestBasedOnCookieRepository,
                userRepository
        );
    }

    @Bean
    public TokenAuthenticationFilter tokenAuthenticationFilter() {
        return new TokenAuthenticationFilter(tokenProvider);
    }

    @Bean
    public OAuth2AuthorizationRequestBasedOnCookieRepository oAuth2AuthorizationRequestBasedOnCookieRepository() {
        return new OAuth2AuthorizationRequestBasedOnCookieRepository();
    }

    @Bean
    public static BCryptPasswordEncoder bcryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
