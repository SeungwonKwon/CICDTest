package com.hacker.hacker.common.exception;

import com.hacker.hacker.common.response.ErrorMessage;
import lombok.Getter;

@Getter
public class UnauthorizedException extends BaseException {
    public UnauthorizedException(ErrorMessage error) {
        super(error, "[UnauthorizedException] "+ error.getMessage());
    }

}
