package steps;

import io.qameta.allure.Step;
import pages.LoginPage;
import pages.MainPage;

public class MainPageSteps {
    MainPage mainPage;
    LoginPage loginPage;

    public MainPageSteps() {
        mainPage = new MainPage();
        loginPage = new LoginPage();
    }

    @Step("Check, that main page is opened!")
    public MainPageSteps mainPageOpened() {
        mainPage.
                isOpened();
        return this;
    }

    @Step("Adding item --> '{goodName}' into the cart")
    public MainPageSteps addGoodInCart(String goodName) {
        mainPage
                .addingGoodInCart(goodName);
        return this;
    }

    @Step("Check, that number of added items displays correct on the cart icon")
    public MainPageSteps checkingNumberOnTheCart(Integer numberOfAddedItems) {
        mainPage
                .isNumberOnTheCartIconEqualsAddedItems(numberOfAddedItems);
        return this;
    }

    @Step("Check, that 'Add to cart' button is changed to 'Remove' button")
    public MainPageSteps checkingButton(String goodName) {
        mainPage
                .isAddToCartButtonChanged(goodName);
        return this;
    }

    @Step("Check, that sorting by Java equals App sorting")
    public void sorting(String whatSort, String sortType) {
        mainPage
                .sortingByJava(whatSort);
        mainPage
                .chooseSorting(sortType)
                .gettingAllItemsFromThePage(whatSort);
        mainPage
                .sortingByAppsEqualsJava(whatSort);
    }
}
