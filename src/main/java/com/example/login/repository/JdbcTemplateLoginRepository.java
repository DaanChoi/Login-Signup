package com.example.login.repository;

import com.example.login.model.PostLoginReq;
import com.example.login.model.PostUserReq;
import com.example.login.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class JdbcTemplateLoginRepository implements LoginRepository{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public  JdbcTemplateLoginRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * 일반 회원가입
     * @param postUserReq
     * @return
     */
    @Override
    public Long signup(PostUserReq postUserReq) {
        return null;
    }

    /**
     * 일반 로그인
     * @param postLoginReq
     * @return
     */
    @Override
    public User login(PostLoginReq postLoginReq) {
        return null;
    }
}
