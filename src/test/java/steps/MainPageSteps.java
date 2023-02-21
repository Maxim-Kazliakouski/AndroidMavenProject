package steps;

import pages.LoginPage;
import pages.MainPage;

public class MainPageSteps {
    MainPage mainPage;
    LoginPage loginPage;

    public MainPageSteps() {
        mainPage = new MainPage();
        loginPage = new LoginPage();
    }

    public MainPageSteps mainPageOpened() {
        mainPage.
                isOpened();
        return this;
    }

    public MainPageSteps addGoodInCart(String goodName){
        mainPage
                .addingGoodInCart(goodName);
        return this;
    }
}
