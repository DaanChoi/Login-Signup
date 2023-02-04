package com.example.login.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationRegex {
    public static boolean isRegexEmail(String email) {
        String regex = "^[A-Z0-9+-\\_.]+@[A-Z0-9-]+\\.[A-Z0-9-.]+$";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE); // 정규표현식으로부터 패턴 생성 (대소문자 구분X Pattern 플래그값)
        Matcher matcher = pattern.matcher(email); // 대상 문자열이 패턴과 일치하면 true 반환
        return matcher.find();
    }
    public static boolean isRegexPassword(String password) {
        String regex = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@!%*#?&])[A-Z\\d@!%*#?&]{8,16}$";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE); // 정규표현식으로부터 패턴 생성 (대소문자 구분X Pattern 플래그값)
        Matcher matcher = pattern.matcher(password); // 대상 문자열이 패턴과 일치하면 true 반환
        return matcher.find();
    }
    public static boolean isRegexNickname(String nickname) {
        String regex = "^[A-Z0-9]{2,10}$";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE); // 정규표현식으로부터 패턴 생성 (대소문자 구분X Pattern 플래그값)
        Matcher matcher = pattern.matcher(nickname); // 대상 문자열이 패턴과 일치하면 true 반환
        return matcher.find();
    }
}
/* 정규표현식 검사하기 : https://coding-factory.tistory.com/529 */