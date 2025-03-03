package com.zerobase.dividendproject.model;

import com.zerobase.dividendproject.domain.MemberEntity;
import lombok.Data;

import java.util.List;

public class Auth {

    @Data
    public static class SignIn {
        private String username;
        private String password;
    }

    @Data
    public static class SignUp {
        private String username;
        private String password;
        private List<String> roles;

        public MemberEntity toEntity(){
            return MemberEntity.builder()
                                .username(this.username)
                                .password(this.password)
                                .roles(this.roles)
                                .build();
        }
    }
}
