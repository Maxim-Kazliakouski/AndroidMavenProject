package steps;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import pages.LoginPage;

@Log4j2
public class LoginPageSteps {
    LoginPage loginPage;

    public LoginPageSteps() {
        loginPage = new LoginPage();
    }

    @Step("Check that Login page is opened")
    public LoginPageSteps loginPageOpened() {
        loginPage
                .isOpened();
        log.info("Login page is opened");
        return this;
    }

    @Step("Login user")
    public void userLogged() {
        loginPage
                .login();
        log.info("User is logged");
    }

    @Step("Check, that user is logged")
    public void isUserLogged(String getText) {
        loginPage
                .isUserLogged(getText);
    }

    @Step("Check user logging with the username --> {username}")
    public LoginPageSteps userLoggedWithDifferCreds(String username) {
        loginPage
                .loginWithDifferCreds(username);
        return this;
    }
}
