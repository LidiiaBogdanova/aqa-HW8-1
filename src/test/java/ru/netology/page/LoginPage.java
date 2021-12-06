package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private SelenideElement loginField = $("[data-test-id=login] input");
    private SelenideElement passwordField = $("[data-test-id=password] input");
    private SelenideElement loginButton = $("[data-test-id=action-login]");
    private SelenideElement errorNotification = $("[data-test-id=error-notification]");

    public VerificationPage validLogin(DataHelper.AuthInfo info) {
        performLogin(info);
        return new VerificationPage();


    }

    public void  invalidLogin(DataHelper.AuthInfo info) {
        performLogin(info);
        errorNotification.shouldHave(Condition.text("Ошибка! Неверно указан логин или пароль"));
     }

    private void performLogin(DataHelper.AuthInfo info) {
        loginField.setValue(info.getLogin());
        passwordField.setValue(info.getPassword());
        loginButton.click();
    }
    public void TriceInvalidLogin(DataHelper.AuthInfo invalidAuthInfo, DataHelper.AuthInfo validAuthInfo){
     performLogin(invalidAuthInfo);
     loginButton.click();
        loginButton.click();
        performLogin(validAuthInfo);
        errorNotification.shouldHave(Condition.text("Ошибка! Превышено допустимое количество попыток авторизации. Попробуйте позже."));
    }


}
