package com.mirim.byeolukyee.service.user;

import com.mirim.byeolukyee.dto.user.AddUserRequest;
import com.mirim.byeolukyee.dto.user.SignInUserRequest;
import com.mirim.byeolukyee.dto.user.UserResponse;
import com.mirim.byeolukyee.domain.user.User;
import com.mirim.byeolukyee.exception.user.DuplicateEmailException;
import com.mirim.byeolukyee.exception.user.IncorrectPasswordException;
import com.mirim.byeolukyee.exception.user.UserNotFoundException;
import com.mirim.byeolukyee.repository.user.UserRepository;
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

    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
        user.setIsDeleted(true);
        userRepository.save(user);
    }

}