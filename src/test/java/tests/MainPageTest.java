package tests;

import io.qameta.allure.Description;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.Test;
import tests.base.BaseTest;

import static com.codeborne.selenide.Selenide.sleep;
@Log4j2
public class MainPageTest extends BaseTest {

    @Description("Adding good in to the shop-cart")
    @Test
    public void addingGoodInCart() {
        loginPageSteps
                .loginPageOpened()
                .userLogged();
        mainPageSteps
                .mainPageOpened()
                .addGoodInCart("Sauce Labs Backpack")
                .addGoodInCart("Sauce Labs Onesie");
    }
}
