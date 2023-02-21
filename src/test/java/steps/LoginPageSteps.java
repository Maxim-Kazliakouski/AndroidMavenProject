package steps;

import com.codeborne.selenide.Condition;
import io.appium.java_client.MobileBy;
import lombok.extern.log4j.Log4j2;
import pages.LoginPage;

import static com.codeborne.selenide.Selenide.$;
@Log4j2
public class LoginPageSteps {
    LoginPage loginPage;

    public LoginPageSteps() {
        loginPage = new LoginPage();
    }

    public LoginPageSteps loginPageOpened() {
        loginPage
                .isOpened();
        log.info("Login page is opened");
        return this;
    }

    public void userLogged() {
        loginPage
                .login();
        log.info("User is logged");
    }

    public void isUserLogged(String getText){
        loginPage
                .isUserLogged(getText);
    }

    public LoginPageSteps userLoggedWithDifferCreds(String username){
        loginPage
                .loginWithDifferCreds(username);
        return this;
    }
}
