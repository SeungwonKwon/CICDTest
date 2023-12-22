package com.hacker.hacker.common.exception;

import com.hacker.hacker.common.response.ErrorMessage;
import lombok.Getter;

@Getter
public class BadRequestException extends BaseException {
    public BadRequestException(ErrorMessage error) {
        super(error, "[Exception] "+ error.getMessage());
    }

}
