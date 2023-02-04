package com.example.login.repository;

import com.example.login.model.PostLoginReq;
import com.example.login.model.PostUserReq;
import com.example.login.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Optional;

@Repository
public class JdbcTemplateLoginRepository implements LoginRepository{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public  JdbcTemplateLoginRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * 일반 회원가입
     */
    @Override
    public Long createUser(PostUserReq postUserReq) {
        String query = "insert into User (email, nickname, password)" +
                                    "values (?,?,?)";
        Object[] params = new Object[]{postUserReq.getEmail(), postUserReq.getNickname(), postUserReq.getPassword()};

        this.jdbcTemplate.update(query, params);
        return this.jdbcTemplate.queryForObject("select last_insert_id()", Long.class);
    }

    /**
     * 일반 로그인
     */
    @Override
    public Optional<User> getInfoByEmail(PostLoginReq postLoginReq) {
        String query = "select userIdx, email, nickname, password from User where email = ?";
        String param = postLoginReq.getEmail();
        return Optional.ofNullable(this.jdbcTemplate.queryForObject(query,
                (rs, rowNum) -> new User(rs.getLong("userIdx"), rs.getString("email"),
                        rs.getString("nickname"), rs.getString("password")),
                param));
    }

    /**
     * 이메일 중복 검사
     */
    @Override
    public int checkEmail(String email) {
        String query = "select exists(select email from User " +
                                    "where status = 'A' and email = ?)";
        String param = email;
        return this.jdbcTemplate.queryForObject(query, int.class, param);
    }

    /**
     * 닉네임 중복 검사
     */
    @Override
    public int checkNickname(String nickname) {
        String query = "select exists(select nickname from User " +
                                    "where status = 'A' and nickname = ?)";
        String param = nickname;
        return this.jdbcTemplate.queryForObject(query, int.class, param);
    }
}
