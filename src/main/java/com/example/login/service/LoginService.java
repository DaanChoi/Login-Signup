package com.example.login.service;

import com.example.login.config.BaseException;
import com.example.login.model.PostLoginReq;
import com.example.login.model.PostLoginRes;
import com.example.login.model.PostUserReq;
import com.example.login.model.PostUserRes;
import com.example.login.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.login.config.BaseResponseStatus.INTERNAL_SERVER_ERROR;

@Service
public class LoginService {

    private final LoginRepository loginRepository;

    @Autowired
    public LoginService(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    public PostUserRes signup(PostUserReq postUserReq) throws BaseException {
        /**
         * 회원가입 로직
         * 1. (유효성 검사 후) 이메일, 비번, 닉네임 받음
         * 2. (중복 검사 후) 비번은 암호화하여 넘김
         * 3. db 저장 후 돌려받은 userIdx 를 RES
         */
        try {
            loginRepository.signup(postUserReq);
            return new PostUserRes(1L);
        } catch (Exception exception) {
            throw new BaseException(INTERNAL_SERVER_ERROR);
        }
    }


    public PostLoginRes login(PostLoginReq postLoginReq) throws BaseException {
        /**
         * 로그인 로직
         * 1. 이메일, 비번 입력 받음
         * 2. 비번 db에 존재하는 것과 같은지 확인 (복호화)
         * 3. 같다면 userIdx 담아서 RES / 다르면 BaseExecption 던짐
         */

        System.out.println(postLoginReq.getEmail() + "   " + postLoginReq.getPassword());

        if (!postLoginReq.getEmail().equals("abc") || !postLoginReq.getPassword().equals("123")) {
            throw new BaseException(INTERNAL_SERVER_ERROR);
        }

        try{
            loginRepository.login(postLoginReq);
            return new PostLoginRes(1L);
        } catch (Exception exception) {
            throw new BaseException(INTERNAL_SERVER_ERROR);
        }
    }
}
