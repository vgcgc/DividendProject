package com.zerobase.dividendproject.exception.impl;

import com.zerobase.dividendproject.exception.AbstractExeption;
import org.springframework.http.HttpStatus;

public class NoCompanyException extends AbstractExeption {

    @Override
    public int getStatusCode() {
        return HttpStatus.BAD_REQUEST.value();
    }

    @Override
    public String getMessage() {
        return "존재하지 않는 회사명입니다";
    }
}
