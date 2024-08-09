package com.sparta.msa_exam.auth.service;

import com.sparta.msa_exam.auth.dto.SignInRequestDTO;
import com.sparta.msa_exam.auth.dto.SignUpRequestDTO;
import com.sparta.msa_exam.auth.entity.User;
import com.sparta.msa_exam.auth.repository.UserRepository;
import com.sparta.msa_exam.auth.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2(topic = "Auth Service Impl")
public class AuthServiceImpl implements AuthService {

    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;


    /**
     * 회원가입
     * @param signUpRequestDTO
     * @return
     */
    @Override
    public User signUp(SignUpRequestDTO signUpRequestDTO) {

        log.info("AuthService : signUp");

        User user = User.builder()
                .id(signUpRequestDTO.getId())
                .password(passwordEncoder.encode(signUpRequestDTO.getPassword()))
                .username(signUpRequestDTO.getUsername())
                .role(signUpRequestDTO.getRole())
                .build();

        return userRepository.save(user);

    }

    /**
     * 로그인
     * @param signInRequestDTO
     * @return JWT Token
     */
    @Override
    public String signIn(SignInRequestDTO signInRequestDTO) {

        log.info("AuthService : signIn");

        User user = userRepository.findById(signInRequestDTO.getId()).orElseThrow(
                () -> new IllegalArgumentException("아이디 또는 비밀번호를 확인해주세요."));

        if (!passwordEncoder.matches(signInRequestDTO.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("아이디 또는 비밀번호를 확인해주세요.");
        }

        return jwtUtil.createJwtToken(user.getId(), user.getRole());
    }
}
