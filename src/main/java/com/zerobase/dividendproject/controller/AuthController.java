package com.zerobase.dividendproject.controller;

import com.zerobase.dividendproject.auth.TokenProvider;
import com.zerobase.dividendproject.domain.MemberEntity;
import com.zerobase.dividendproject.model.Auth;
import com.zerobase.dividendproject.repository.MemberRepository;
import com.zerobase.dividendproject.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Member;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final TokenProvider tokenProvider;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody Auth.SignUp request) {
        MemberEntity memberEntity = authService.register(request);
        return ResponseEntity.ok(memberEntity);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody Auth.SignIn request) {
        MemberEntity memberEntity = authService.authenticate(request);
        String token = tokenProvider.generateToken(memberEntity.getUsername(), memberEntity.getRoles());
        return ResponseEntity.ok(token);
    }
}
