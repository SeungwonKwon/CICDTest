package com.hacker.hacker.common.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum SuccessMessage {
    VIDEOS_BY_CATEGORY_GET_SUCCESS(OK,"카테고리별 동영상 반환에 성공했습니다"),
    GET_ALL_VIDEOS_SUCCESS(OK, "전체 동영상 반환에 성공했습니다."),
    POST_NEW_VIDEO_SUCCESS(CREATED, "새로운 동영상 업로드에 성공했습니다."),
    TRANSCRIPT_BY_VIDEO_GET_SUCCESS(OK, "동영상별 개별 자막 반환에 성공했습니다")
   ;

    private final HttpStatus httpStatus;
    private final String message;

    public int getHttpStatusCode() {
        return httpStatus.value();
    }
}
