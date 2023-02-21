package tests;

import com.codeborne.selenide.SelenideElement;
import io.appium.java_client.MobileBy;
import io.qameta.allure.Description;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tests.base.BaseTest;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;
import static java.lang.String.format;
import static org.openqa.selenium.By.xpath;


public class LoginTest extends BaseTest {
    @DataProvider()
    public Object[][] loginCreds() {
        return new Object[][]{
                {"standard_user", "PRODUCTS"},
                {"locked_out_user", "Sorry, this user has been locked out."},
                {"problem_user", "PRODUCTS"}
        };
    }

    @Description("Test for checking that app is opened")
    @Test
    public void testAppOpen() {
        loginPageSteps.
                loginPageOpened();
    }

//    @Description("Test for checking that user can login")
//    @Test
//    public void testLogin() {
//        loginPageSteps
//                .loginPageOpened()
//                .userLogged();
//    }
//
//    @Description("Login by clicking on list of users")
//    @Test(dataProvider = "loginCreds")
//    public void loginWithDifferCreds(String username, String textAfterLogin) {
//        loginPageSteps
//                .loginPageOpened()
//                .userLoggedWithDifferCreds(username)
//                .isUserLogged(textAfterLogin);
//    }
}
