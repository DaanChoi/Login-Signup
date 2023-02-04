package com.example.login.repository;

import com.example.login.model.PostLoginReq;
import com.example.login.model.PostUserReq;
import com.example.login.model.User;

import java.util.Optional;

public interface LoginRepository {
    Long createUser(PostUserReq postUserReq); // 일반 회원가입
    Optional<User> getInfoByEmail(PostLoginReq postLoginReq); // 일반 로그인
    int checkEmail(String email); // 이메일 중복 검사
    int checkNickname(String nickname); // 닉네임 중복 검사
}
