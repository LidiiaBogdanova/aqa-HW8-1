package ru.netology.test;

import com.codeborne.selenide.Condition;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.CleanHelper;
import ru.netology.data.DataHelper;
import ru.netology.page.LoginPage;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginTest {

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }


    @Test
    void shouldSuccessLogin(){
        var loginPage =new LoginPage();
        var authInfo = DataHelper.getValidAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
            }
    @Test
    void shouldNotSuccessLoginWithInvalidData(){
        var loginPage =new LoginPage();
        var authInfo = DataHelper.getValidAuthInfo();
       loginPage.invalidLogin(authInfo);

    }
    @Test
    void shouldBlockSystemIfInvalidDataTrice(){
        var loginPage =new LoginPage();
        var authInfo = DataHelper.getValidAuthInfo();
        loginPage.invalidLogin(authInfo);
        loginPage.click();
        loginPage.click();
        $("[data-test-id=error-notification]").shouldHave(Condition.text("Ошибка! Превышено количество попыток ввода пароля"));

    }
    @AfterAll
    @SneakyThrows
    public static void cleanOut(){
        CleanHelper.cleanOut();
    }

}
