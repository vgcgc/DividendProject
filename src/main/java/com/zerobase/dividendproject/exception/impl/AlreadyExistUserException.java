package com.zerobase.dividendproject.exception.impl;

import com.zerobase.dividendproject.exception.AbstractExeption;
import org.springframework.http.HttpStatus;

public class AlreadyExistUserException extends AbstractExeption {

    @Override
    public int getStatusCode() {
        return HttpStatus.BAD_REQUEST.value();
    }

    @Override
    public String getMessage() {
        return "이미 존재하는 사용자명입니다.";
    }
}
