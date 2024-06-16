package com.zerobase.dividendproject.exception;

public abstract class AbstractExeption extends RuntimeException {

    abstract public int getStatusCode();
    abstract public String getMessage();
}
