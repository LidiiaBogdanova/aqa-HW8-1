package ru.netology.data;

import lombok.SneakyThrows;
import lombok.Value;
import org.apache.commons.dbutils.QueryRunner;

import org.apache.commons.dbutils.handlers.ScalarHandler;

import javax.print.DocFlavor;
import java.sql.Connection;
import java.sql.DriverManager;

public class DataHelper {
    private DataHelper() {
    }

//    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
//    static final String DB_URL = "jdbc:mysql://localhost:3306/app";
//    static final String USER = "app";
//    static final String PASS = "pass";

    @Value
    public static class AuthInfo {
        String login;
        String password;
        String id;
    }


    @SneakyThrows
    public static AuthInfo getValidAuthInfo() {
        var runner = new QueryRunner();
        var dataSQL = "SELECT id FROM users WHERE login='vasya';";
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/app", "app", "pass");
        var userInfo = new AuthInfo("vasya", "qwerty123", runner.query(connection, dataSQL, new ScalarHandler<String>()));
        return userInfo;
    }

    @Value
    public static class VerificationCode {
        private String code;
    }
    @SneakyThrows
    public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {
        var runner = new QueryRunner();
        var dataSQL = "SELECT code FROM auth_codes INNER JOIN users ON users.id=auth_codes.user_id ;";
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/app", "app", "pass");
        return new VerificationCode(runner.query(connection,dataSQL, new ScalarHandler<String>()));
    }
}
