package tests;

import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.Test;
import tests.base.BaseTest;

@Log4j2
public class MainPageTest extends BaseTest {

    @TmsLink("case=4")
    @Description("Adding good in to the shop-cart")
    @Test
    public void addingGoodInCart() {
        loginPageSteps
                .loginPageOpened()
                .userLogged();
        mainPageSteps
                .mainPageOpened()
                .addGoodInCart("Sauce Labs Backpack")
                .addGoodInCart("Sauce Labs Onesie")
                .checkingNumberOnTheCart(2);
        cartPageSteps
                .checkingItemsInTheCart("Sauce Labs Backpack", "Sauce Labs Onesie");
    }

    @TmsLink("case=5")
    @Description("Checking that button 'Add to cart' changes on 'Remove' after clicking on it")
    @Test
    public void changeAddToCartButtonOnRemove() {
        loginPageSteps
                .loginPageOpened()
                .userLogged();
        mainPageSteps
                .mainPageOpened()
                .addGoodInCart("Sauce Labs Onesie")
                .checkingButton("Sauce Labs Onesie");
    }

    @TmsLink("case=6")
    @Description("Checking sorting from A to Z")
    @Test
    public void sortingNameAtoZ() {
        loginPageSteps
                .loginPageOpened()
                .userLogged();
        mainPageSteps
                .sorting("Name", "Name (Z to A)");
    }

    @TmsLink("case=7")
    @Description("Checking sorting Price from low to high")
    @Test
    public void sortingPriceLtoH() {
        loginPageSteps
                .loginPageOpened()
                .userLogged();
        mainPageSteps
                .sorting("Price", "Price (low to high)");
    }
}
