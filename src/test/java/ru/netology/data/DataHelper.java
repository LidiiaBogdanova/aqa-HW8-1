package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.SneakyThrows;
import lombok.Value;

public class DataHelper {
    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        String login;
        String password;
    }

    @SneakyThrows
    public static AuthInfo getValidAuthInfo() {
        var userInfo = new AuthInfo("vasya", "qwerty123");
        return userInfo;
    }
    @SneakyThrows
    public static AuthInfo getInvalidAuthInfo() {
        Faker faker=new Faker();
       var userInfo = new AuthInfo(faker.name().username(), faker.internet().password());
        return userInfo;
    }

    @SneakyThrows
    public static String getVerificationCodeFor(AuthInfo authInfo) {
       return DBHelper.getDBHelper().getCode(authInfo.login);
    }
}