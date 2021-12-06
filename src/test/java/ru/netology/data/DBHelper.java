package ru.netology.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBHelper {

    private static DBHelper _dbHelper;
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/app";
    static final String USER = "app";
    static final String PASS = "pass";

    private DBHelper()
    {
        DbUtils.loadDriver(JDBC_DRIVER);
    }

    public static DBHelper getDBHelper()
    {
        if(_dbHelper == null)
            _dbHelper = new DBHelper();
        return _dbHelper;
    }

    @SneakyThrows
    public void cleanOut(){
        var emptyUsers="DELETE FROM app.users;";
        var emptyAuthCodes="DELETE FROM app.auth_codes;";
        var emptyTransactions="DELETE FROM app.card_transactions;";
        var emptyCards="DELETE FROM app.cards;";
        var runner = new QueryRunner();
        var conn = DriverManager.getConnection(DB_URL, USER, PASS);
        try {

            runner.execute(conn, emptyAuthCodes);
            runner.execute(conn, emptyTransactions);
            runner.execute(conn, emptyCards);
            runner.execute(conn, emptyUsers);
        } finally {
            DbUtils.close(conn);
        }
    }
    @SneakyThrows
    public String getCode(String login){
        var runner = new QueryRunner();
        var dataSQL = "SELECT code FROM auth_codes INNER JOIN users ON users.id=auth_codes.user_id WHERE login='"+login+"' ORDER BY auth_codes.created DESC LIMIT 1;";
        Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
        String code;
        try {
         code=runner.query(connection, dataSQL, new ScalarHandler<String>()); }
        finally {
            DbUtils.close(connection);
        }
        return code;
    }
}
