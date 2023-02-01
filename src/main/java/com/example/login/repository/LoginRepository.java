package com.example.login.repository;

import com.example.login.model.PostLoginReq;
import com.example.login.model.PostUserReq;
import com.example.login.model.User;

import java.util.Optional;

public interface LoginRepository {
    Long createUser(PostUserReq postUserReq);
    Optional<User> getInfoByEmail(PostLoginReq postLoginReq);
}
