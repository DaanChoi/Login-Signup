package com.example.login.repository;

import com.example.login.model.PostLoginReq;
import com.example.login.model.PostUserReq;
import com.example.login.model.User;
import org.springframework.stereotype.Repository;

public interface LoginRepository {
    Long signup(PostUserReq postUserReq);
    User login(PostLoginReq postLoginReq);
}
