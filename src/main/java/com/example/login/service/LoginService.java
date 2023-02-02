package com.example.login.service;

import com.example.login.config.BaseException;
import com.example.login.model.*;
import com.example.login.repository.LoginRepository;
import com.example.login.utils.AES128;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

import static com.example.login.config.BaseResponseStatus.*;
import static com.example.login.config.secret.Secret.AES128_SECRET_KEY;

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
        // 비번 암호화
        try {
            AES128 aes128 = new AES128(AES128_SECRET_KEY);
            postUserReq.setPassword(aes128.encrypt(postUserReq.getPassword()));
        } catch (Exception e) {
            throw new BaseException(FAIL_TO_ENCRYPTION);
        }

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

        // DB 에서 해당 이메일의 비번 가져옴
        try {
            Optional<User> user = loginRepository.getInfoByEmail(postLoginReq);
            pwd = user.orElseThrow(() -> new NoSuchElementException()).getPassword();
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new BaseException(INTERNAL_SERVER_ERROR);
        }

        // 가져온 비번 복호화
        try {
            AES128 aes128 = new AES128(AES128_SECRET_KEY);
            pwd = aes128.decrypt(pwd);
        } catch (Exception e) {
            throw new BaseException(FAIL_TO_DECRYPTION);
        }

        // 비번 비교 : 비번 일치하면 로그인 / 불일치면 에러
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
