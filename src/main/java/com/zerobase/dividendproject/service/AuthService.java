package com.zerobase.dividendproject.service;

import com.zerobase.dividendproject.domain.MemberEntity;
import com.zerobase.dividendproject.exception.impl.AlreadyExistUserException;
import com.zerobase.dividendproject.model.Auth;
import com.zerobase.dividendproject.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class AuthService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return memberRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }

    public MemberEntity register(Auth.SignUp member) {
        boolean exist = memberRepository.existsByUsername(member.getUsername());
        if (exist) {
            throw new AlreadyExistUserException();
        }
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        MemberEntity memberEntity = memberRepository.save(member.toEntity());
        return memberEntity;
    }

    public MemberEntity authenticate(Auth.SignIn member) {
        MemberEntity user = memberRepository.findByUsername(member.getUsername())
                .orElseThrow(() -> new RuntimeException("Invalid username"));
        if (!passwordEncoder.matches(member.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return user;
    }
}
