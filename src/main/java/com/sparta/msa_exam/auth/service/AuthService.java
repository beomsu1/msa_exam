package com.sparta.msa_exam.auth.service;

import com.sparta.msa_exam.auth.dto.SignInRequestDTO;
import com.sparta.msa_exam.auth.dto.SignUpRequestDTO;
import com.sparta.msa_exam.auth.entity.User;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface AuthService {

    // 회원가입
    public User signUp(SignUpRequestDTO signUpRequestDTO);

    // 로그인
    public String signIn(SignInRequestDTO signInRequestDTO);

}
