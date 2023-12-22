package com.hacker.hacker.common.exception;

import com.hacker.hacker.common.response.ErrorMessage;
import lombok.Getter;

@Getter
public class UserConflictException extends BaseException {
    public UserConflictException(ErrorMessage error) {
        super(error, "[UserConflictException] "+ error.getMessage());
    }

}
