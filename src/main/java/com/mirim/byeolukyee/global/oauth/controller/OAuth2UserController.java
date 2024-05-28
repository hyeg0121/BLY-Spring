package com.mirim.byeolukyee.global.oauth.controller;

import com.mirim.byeolukyee.domain.user.dto.UserResponse;
import com.mirim.byeolukyee.domain.user.entity.User;
import com.mirim.byeolukyee.domain.user.repository.UserRepository;
import com.mirim.byeolukyee.global.exception.user.UserNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;


@RestController
public class OAuth2UserController {

    private final WebClient webClient;
    private final ClientRegistrationRepository clientRegistrationRepository;
    private final OAuth2AuthorizedClientRepository authorizedClientRepository;
    private final UserRepository userRepository;

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.google.client-secret}")
    private String clientSecret;

    @Value("${spring.security.oauth2.client.registration.google.redirect-uri}")
    private String redirectUri;

    public OAuth2UserController(WebClient.Builder webClientBuilder,
                                ClientRegistrationRepository clientRegistrationRepository,
                                OAuth2AuthorizedClientRepository authorizedClientRepository, UserRepository userRepository) {
        this.webClient = webClientBuilder.build();
        this.clientRegistrationRepository = clientRegistrationRepository;
        this.authorizedClientRepository = authorizedClientRepository;
        this.userRepository = userRepository;
    }

    @PostMapping("/oauth2/token")
    public Map<String, Object> getToken(@RequestBody Map<String, String> body) {
        String code = body.get("code");

        // 구글과 통신하여 액세스 토큰을 얻는 로직
        String tokenUri = UriComponentsBuilder.fromUriString("https://oauth2.googleapis.com/token")
                .queryParam("code", code)
                .queryParam("client_id", clientId)
                .queryParam("client_secret", clientSecret)
                .queryParam("redirect_uri", redirectUri)
                .queryParam("grant_type", "authorization_code")
                .toUriString();

        Map<String, String> tokenResponse = webClient.post()
                .uri(tokenUri)
                .retrieve()
                .bodyToMono(Map.class)
                .block();

        String accessToken = tokenResponse.get("access_token");

        // 액세스 토큰을 사용하여 구글 사용자 정보 가져오기
        Map<String, Object> userInfo = webClient.get()
                .uri("https://www.googleapis.com/oauth2/v1/userinfo?alt=json&access_token=" + accessToken)
                .retrieve()
                .bodyToMono(Map.class)
                .block();

        User user = userRepository.findByEmail((String) userInfo.get("email"))
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        Map<String, Object> response = new HashMap<>();
        response.put("accessToken", accessToken);
        response.put("userInfo", UserResponse.from(user));

        return response;
    }

}
