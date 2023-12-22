package com.hacker.hacker.common.exception;

import com.hacker.hacker.common.response.ErrorMessage;
import lombok.Getter;

@Getter
public class NotFoundException extends BaseException {
    public NotFoundException(ErrorMessage error) {
        super(error, "[NotFoundException] "+ error.getMessage());
    }

}
