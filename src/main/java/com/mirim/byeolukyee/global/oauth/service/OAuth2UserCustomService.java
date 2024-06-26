package com.mirim.byeolukyee.global.oauth.service;


import com.mirim.byeolukyee.domain.user.entity.User;
import com.mirim.byeolukyee.domain.user.repository.UserRepository;
import com.mirim.byeolukyee.global.exception.user.DuplicateEmailException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
public class OAuth2UserCustomService extends DefaultOAuth2UserService {
    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User user = super.loadUser(userRequest);
        saveOrUpdate(user);
        return user;
    }

    private User saveOrUpdate(OAuth2User oAuth2User) {
        Map<String, Object> attributes = oAuth2User.getAttributes();
        String hd = (String) attributes.get("hd");

        if (!hd.equals("e-mirim.hs.kr")) {
            throw DuplicateEmailException.EXCEPTION;
        }

        String email = (String) attributes.get("email");
        String name = (String) attributes.get("given_name");
        String studentId = ((String) attributes.get("family_name")).split("_")[0];

        String profileUrl = (String) attributes.get("picture");

        System.out.println(attributes.toString());

        User user = userRepository.findByEmail(email)
                .map(entity -> entity.update(name))
                .orElse(User.builder()
                        .email(email)
                        .name(name)
                        .password("password12!")
                        .profileUrl(profileUrl)
                        .studentId(studentId)
                        .build());

        return userRepository.save(user);
    }
}

