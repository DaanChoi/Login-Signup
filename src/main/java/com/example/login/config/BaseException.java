package com.example.login.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
// BaseException 클래스를 throw 할 수 있도록 Exception 상속
// 즉 BaseException 클래스를 Exception 처럼 사용하도록 상속하는거임
// 서비스단에서 던진 에러(BaseResponseStatus) 가져올 수 있음
public class BaseException extends Exception{
    private BaseResponseStatus status;
}
