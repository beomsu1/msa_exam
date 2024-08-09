package com.sparta.msa_exam.auth.controller;

import com.sparta.msa_exam.auth.dto.SignInRequestDTO;
import com.sparta.msa_exam.auth.dto.SignUpRequestDTO;
import com.sparta.msa_exam.auth.service.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Log4j2(topic = "AuthController")
public class AuthController {

    private final AuthService authService;

    @Value("${server.port}")
    private String port;
    
    // 회원가입
    @PostMapping("/signUp")
    public ResponseEntity<?> postSignUp(@RequestBody SignUpRequestDTO signUpRequestDTO, HttpServletResponse response){

        log.info("AuthController : Post SignUp");

        authService.signUp(signUpRequestDTO);
        response.setHeader("Server-Port", port);
        return ResponseEntity.ok("회원가입이 되었습니다.");
    }

    // 로그인 -> 토큰 발급
    @PostMapping("/signIn")
    public ResponseEntity<?> createJwtTokenForLogin(@RequestBody SignInRequestDTO signInRequestDTO, HttpServletResponse response){

        log.info("AuthController : createJwtTokenForLogin");

        String token = authService.signIn(signInRequestDTO);
        response.setHeader("Server-Port", port);
        return ResponseEntity.ok(token);
    }
}
