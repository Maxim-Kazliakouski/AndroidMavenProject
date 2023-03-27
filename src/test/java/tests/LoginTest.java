package tests;

import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tests.base.BaseTest;

public class LoginTest extends BaseTest {
    @DataProvider()
    public Object[][] loginCreds() {
        return new Object[][]{
                {"standard_user", "PRODUCTS"},
                {"locked_out_user", "Sorry, this user has been locked out."},
                {"problem_user", "PRODUCTS"}
        };
    }

    @TmsLink("case=1")
    @Description("Test for checking that app is opened")
    @Test
    public void testAppOpen() {
        loginPageSteps.
                loginPageOpened();
    }

    @TmsLink("case=2")
    @Description("Test for checking that user can login")
    @Test
    public void testLogin() {
        loginPageSteps
                .loginPageOpened()
                .userLogged();
    }

    @TmsLink("case=3")
    @Description("Login by clicking on list of users")
    @Test(dataProvider = "loginCreds")
    public void loginWithDifferCreds(String username, String textAfterLogin) {
        loginPageSteps
                .loginPageOpened()
                .userLoggedWithDifferCreds(username)
                .isUserLogged(textAfterLogin);
    }
}
