package ru.netology.test;

import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DBHelper;
import ru.netology.data.DataHelper;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;

public class LoginTest {

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @Test
    void shouldSuccessLogin() {
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getValidAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
    }

    @Test
    void shouldNotSuccessLoginWithInvalidData() {
        var loginPage = new LoginPage();
        loginPage.invalidLogin(DataHelper.getInvalidAuthInfo());

    }

    @Test
    void shouldBlockSystemIfInvalidDataTrice() {
        var loginPage = new LoginPage();
        loginPage.TriceInvalidLogin(DataHelper.getInvalidAuthInfo(), DataHelper.getValidAuthInfo());
    }

    @AfterAll
    @SneakyThrows
    public static void cleanOut() {
        DBHelper.getDBHelper().cleanOut();
    }

}
