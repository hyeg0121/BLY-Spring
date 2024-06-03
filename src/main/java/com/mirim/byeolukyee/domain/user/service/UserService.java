package com.mirim.byeolukyee.domain.user.service;

import com.mirim.byeolukyee.domain.post.dto.PostResponse;
import com.mirim.byeolukyee.domain.post.dto.buyingpost.BuyingPostResponse;
import com.mirim.byeolukyee.domain.post.dto.sellingcomment.SellingCommentResponse;
import com.mirim.byeolukyee.domain.post.dto.sellingpost.SellingPostResponse;
import com.mirim.byeolukyee.domain.post.entity.BuyingPost;
import com.mirim.byeolukyee.domain.post.entity.Post;
import com.mirim.byeolukyee.domain.post.entity.SellingPost;
import com.mirim.byeolukyee.domain.user.dto.AddUserRequest;
import com.mirim.byeolukyee.domain.user.dto.SignInUserRequest;
import com.mirim.byeolukyee.domain.user.dto.UserResponse;
import com.mirim.byeolukyee.domain.user.entity.User;
import com.mirim.byeolukyee.domain.user.repository.UserRepository;
import com.mirim.byeolukyee.domain.wish.entity.Wish;
import com.mirim.byeolukyee.global.exception.user.DuplicateEmailException;
import com.mirim.byeolukyee.global.exception.user.IncorrectPasswordException;
import com.mirim.byeolukyee.global.exception.user.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    public List<UserResponse> findAllUser() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(UserResponse::from)
                .collect(Collectors.toList());
    }

    public UserResponse findUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> UserNotFoundException.EXCEPTION);
        return UserResponse.from(user);
    }

    @Transactional
    public UserResponse createUser(AddUserRequest addUserRequestDto) {
        // 이메일 중복 체크
        checkDuplicateEmail(addUserRequestDto.getEmail());

        // 비밀번호 해싱
        String encodedPassword = encoder.encode(addUserRequestDto.getPassword());

        // 사용자 생성
        User user = User.builder()
                .name(addUserRequestDto.getName())
                .email(addUserRequestDto.getEmail())
                .password(encodedPassword)
                .studentId(addUserRequestDto.getStudentId())
                .build();

        User savedUser = userRepository.save(user);

        // UserResponseDto 생성
        return UserResponse.from(savedUser);
    }

    @Transactional
    public UserResponse signIn(SignInUserRequest signInUserRequestDto) {
        // 이메일로 유저 존재 확인
        User user = userRepository.findByEmail(signInUserRequestDto.getEmail())
                .orElseThrow(()-> UserNotFoundException.EXCEPTION);

        // 비밀번호 확인
        if (BCrypt.checkpw(signInUserRequestDto.getPassword(), user.getPassword()))
            return UserResponse.from(user);
        else
            throw IncorrectPasswordException.EXCEPTION;


    }

    private void checkDuplicateEmail(String email) {
        if (userRepository.existsUserByEmail(email)) throw DuplicateEmailException.EXCEPTION;
    }

    @Transactional
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
        user.setIsDeleted(true);
        userRepository.save(user);
    }


    public List<SellingPostResponse> findSellingPostsByUserId(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        return user.getSellingPosts()
                .stream()
                .map(SellingPostResponse::from)
                .collect(Collectors.toList());
    }

    public List<BuyingPostResponse> findBuyingPostsByUserId(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        return user.getBuyingPosts()
                .stream()
                .map(BuyingPostResponse::from)
                .collect(Collectors.toList());
    }


    public List<SellingCommentResponse> findSellingCommentsByUserId(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        return user.getSellingComments()
                .stream()
                .map(SellingCommentResponse::from)
                .collect(Collectors.toList());
    }

    public List<PostResponse> findWishesByUserId(Long id, String type) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        return user.getWishes()
                .stream()
                .filter(Wish::isLiked)
                .map(Wish::getPost)
                .map(PostResponse::from)
                .collect(Collectors.toList());
    }
}
