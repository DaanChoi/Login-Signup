package com.example.login.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    INTERNAL_SERVER_ERROR(false, 500, "Internal Server Error");

    private final boolean isSuccess;
    private final int code;
    private final String message;
}
