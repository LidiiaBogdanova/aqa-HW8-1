package ru.netology.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.DriverManager;

public class CleanHelper {

    @SneakyThrows
    public static void cleanOut(){
        var emptyUsers="DELETE FROM app.users;";
        var emptyAuthCodes="DELETE FROM app.auth_codes;";
        var emptyTransactions="DELETE FROM app.card_transactions;";
        var emptyCards="DELETE FROM app.cards;";
        var runner = new QueryRunner();
        DbUtils.loadDriver("com.mysql.jdbc.Driver");
        var conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "pass"
        );
        try {

            runner.execute(conn, emptyAuthCodes);
            runner.execute(conn, emptyTransactions);
            runner.execute(conn, emptyCards);
            runner.execute(conn, emptyUsers);
        } finally {
            DbUtils.close(conn);
        }
    }
}
