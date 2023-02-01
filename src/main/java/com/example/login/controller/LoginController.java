package com.example.login.controller;

import com.example.login.config.BaseException;
import com.example.login.config.BaseResponse;
import com.example.login.model.*;
import com.example.login.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.example.login.config.BaseResponseStatus.POST_SUCCESS;

@Controller
public class LoginController {

    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    /**
     * 일반 회원가입
     */
    @PostMapping("signup")
    @ResponseBody
    public BaseResponse<PostUserRes> createUser(@RequestBody PostUserReq postUserReq) {
        try {
            PostUserRes postUserRes = loginService.createUser(postUserReq);
            return new BaseResponse<>(postUserRes, POST_SUCCESS);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /**
     * 일반 로그인
     */
    @PostMapping("login")
    @ResponseBody
    public BaseResponse<PostLoginRes> login(@RequestBody PostLoginReq postLoginReq) {
        try {
            PostLoginRes postLoginRes = loginService.login(postLoginReq);
            return new BaseResponse<>(postLoginRes, POST_SUCCESS);
        } catch (BaseException exception) {
            exception.printStackTrace();
            return new BaseResponse<>(exception.getStatus());
        }
    }

}
