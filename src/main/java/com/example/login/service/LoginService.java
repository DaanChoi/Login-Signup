package com.example.login.service;

import com.example.login.config.BaseException;
import com.example.login.model.*;
import com.example.login.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

import static com.example.login.config.BaseResponseStatus.BAD_REQUEST;
import static com.example.login.config.BaseResponseStatus.INTERNAL_SERVER_ERROR;

@Service
public class LoginService {

    private final LoginRepository loginRepository;

    @Autowired
    public LoginService(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    public PostUserRes createUser(PostUserReq postUserReq) throws BaseException {
        /**
         * 회원가입 로직
         * 1. (유효성 검사 후) 이메일, 비번, 닉네임 받음
         * 2. (중복 검사 후) 비번은 암호화하여 넘김
         * 3. db 저장 후 돌려받은 userIdx 를 RES
         */
        try {
            Long userIdx = loginRepository.createUser(postUserReq);
            return new PostUserRes(userIdx);
        } catch (Exception exception) {
            throw new BaseException(INTERNAL_SERVER_ERROR);
        }
    }

    public PostLoginRes login(PostLoginReq postLoginReq) throws BaseException {
        /**
         * 로그인 로직
         * 1. 이메일, 비번이 입력된 객체 받음
         * 2. DB에서 입력받은 이메일에 해당하는 비번 가져옴 (복호화해야 됨)
         * 2. 입력받은 비번과 DB에서 가져온 비번이 같은지 확인
         * 3. 같다면 userIdx 담아서 RES / 다르면 BaseExecption 던짐
         */
        String pwd;
        try {
            Optional<User> user = loginRepository.getInfoByEmail(postLoginReq);
            pwd = user.orElseThrow(() -> new NoSuchElementException()).getPassword();
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new BaseException(INTERNAL_SERVER_ERROR);
        }

        if (postLoginReq.getPassword().equals(pwd)) {
            Long userIdx = loginRepository.getInfoByEmail(postLoginReq)
                    .orElseThrow(() -> new NoSuchElementException())
                    .getUserIdx();
            return new PostLoginRes(userIdx);
        } else {
            throw new BaseException(BAD_REQUEST);
        }

    }
}
