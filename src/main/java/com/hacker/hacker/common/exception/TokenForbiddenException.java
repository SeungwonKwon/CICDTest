package com.hacker.hacker.common.exception;

import com.hacker.hacker.common.response.ErrorMessage;
import lombok.Getter;

@Getter
public class TokenForbiddenException extends BaseException {
    public TokenForbiddenException(ErrorMessage error) {
        super(error, "[TokenForbiddenException] "+ error.getMessage());
    }

}
