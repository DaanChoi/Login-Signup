package com.example.login.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BaseResponseStatus {

    /**
     *  2XX : 성공
     */
    GET_SUCCESS(true, 200, "Ok"),
    POST_SUCCESS(true, 201, "Created"),

    /**
     * 4XX : 요청 오류
     */
    BAD_REQUEST(false, 400, "Bad Request"),
    REGEX_ERROR(false, 460, "Regex Error"),
    DUPLICATION_ERROR(false, 461, "Existing Value In DB Already"),

    /**
     * 5XX : 서버 오류
     */
    INTERNAL_SERVER_ERROR(false, 500, "Internal Server Error"),
    FAIL_TO_ENCRYPTION(false, 520, "Encryption Fail"),
    FAIL_TO_DECRYPTION(false, 520, "Decryption Fail");

    private final boolean isSuccess;
    private final int code;
    private final String message;
}
