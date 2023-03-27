package steps;

import io.qameta.allure.Step;
import pages.CartPage;

public class CartPageSteps {
    CartPage cartPage;

    public CartPageSteps() {
        cartPage = new CartPage();
    }

    @Step("Check, that added items match with items in the cart")
    public void checkingItemsInTheCart(String... itemNames) {
        cartPage
                .isAddedItemsMatchWithItemsInCart(itemNames);
    }
}
