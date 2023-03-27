package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import driver.EmulatorHelper;
import io.appium.java_client.MobileBy;
import lombok.extern.log4j.Log4j2;

import java.util.*;

import static com.codeborne.selenide.Selenide.*;
import static java.lang.String.format;
import static org.testng.Assert.assertTrue;

@Log4j2
public class MainPage {
    ArrayList<String> listUniItems = new ArrayList<>();
    ArrayList<String> javaSortingItems = new ArrayList<>();
    ArrayList<Double> withoutCurrency = new ArrayList<>();
    ArrayList<String> sortedWithCurrency = new ArrayList<>();
    ElementsCollection list;

    public void isOpened() {
        SelenideElement productsTitle = $(MobileBy.xpath("//android.widget.TextView[@text='PRODUCTS']"));
        productsTitle.shouldBe(Condition.visible);
    }

    public void addingGoodInCart(String goodName) {
        EmulatorHelper.androidScrollToAnElementByText(goodName);
        SelenideElement addToCart = $(MobileBy.xpath(format("//android.widget.TextView[@text='%s']//..//android.view.ViewGroup[@content-desc='test-ADD TO CART']", goodName)));
        addToCart.click();
        log.info(format("The good --> '%s' has been added", goodName));
    }

    public void isAddToCartButtonChanged(String goodName) {
        SelenideElement buttonText = $(MobileBy.xpath(format("//android.widget.TextView[@text='%s']//..//android.view.ViewGroup[@content-desc='test-REMOVE']/android.widget.TextView", goodName)));
        buttonText.shouldHave(Condition.text("REMOVE"));
        log.info("The 'Add to Cart' button is changed to 'Remove'");
    }

    public MainPage chooseSorting(String sortingType) {
        SelenideElement sortButton = $(MobileBy.xpath("//android.view.ViewGroup[@content-desc='test-Modal Selector Button']"));
        sortButton.click();
        SelenideElement sortingTypeText = $(MobileBy.xpath(format("//android.widget.TextView[@text='%s']", sortingType)));
        sortingTypeText.click();
        log.info(format("The '%s' sorting type has been chosen", sortingType));
        log.info("Sorting by the App...");
        return this;
    }

    public void isNumberOnTheCartIconEqualsAddedItems(Integer amountOfAddedItems) {
        SelenideElement numberOnTheCart = $(MobileBy.xpath("//android.view.ViewGroup[@content-desc='test-Cart']/android.view.ViewGroup/android.widget.TextView"));
        boolean isAmountOfAddedItemsOnTheCartCorrect = numberOnTheCart.getText().equals(String.valueOf(amountOfAddedItems));
        assertTrue(isAmountOfAddedItemsOnTheCartCorrect, format("The number of added items on the cart don't match with added items: got --> %s, but expected --> %s", numberOnTheCart.getText(), amountOfAddedItems));
    }

    public ArrayList<String> gettingAllItemsFromThePage(String whatGet) {
        String endText = "© 2023 Sauce Labs. All Rights Reserved.";
        SelenideElement endScreen = $(MobileBy.xpath(format("//android.widget.TextView[@text='%s']", endText)));
        ArrayList<String> items = new ArrayList<>();
        while (!endScreen.isDisplayed()) {
            switch (whatGet) {
                case ("Name"):
                    list = $$(MobileBy.xpath("//android.widget.TextView[@content-desc='test-Item title']"));
                    break;
                case ("Price"):
                    list = $$(MobileBy.xpath("//android.widget.TextView[@content-desc='test-Price']"));
                    break;
            }
            for (SelenideElement eachItem : list) {
                items.add(eachItem.getText());
            }
            EmulatorHelper.swipeScreen("UP", 4);
        }
        Set<String> uniItems = new LinkedHashSet<>(items);
        listUniItems = new ArrayList<>(uniItems);
        EmulatorHelper.swipeScreen("DOWN", 1);
        EmulatorHelper.swipeScreen("DOWN", 1);
        return listUniItems;
    }

    public void sortingByJava(String whatGet) {
        log.info(format("Sorting by Java by '%s'%n", whatGet));
        log.info("Getting items list from the page...");
        javaSortingItems = new ArrayList<>(gettingAllItemsFromThePage(whatGet));
        withoutCurrency = new ArrayList<>();
        sortedWithCurrency = new ArrayList<>();
        switch (whatGet) {
            case ("Price"):
                for (String eachPrice : javaSortingItems) {
                    String onlyPrice = eachPrice.substring(1);
                    double price = Double.parseDouble(onlyPrice);
                    withoutCurrency.add(price);
                }
                Collections.sort(withoutCurrency);
                for (Double eachPrice : withoutCurrency) {
                    sortedWithCurrency.add("$" + eachPrice);
                }
                break;
            case ("Name"):
                Collections.reverse(javaSortingItems);
        }
        EmulatorHelper.swipeScreen("DOWN", 1);
        EmulatorHelper.swipeScreen("DOWN", 1);
        log.info("Sorting the items by Java is completed");
    }

    public void sortingByAppsEqualsJava(String whatEqual) {
        switch (whatEqual) {
            case ("Price"):
                boolean arePricesEqual = sortedWithCurrency.equals(listUniItems);
                assertTrue(arePricesEqual, "The price sorting doesn't equal between App and Java");
                log.info("The sorting lists are equal by java and app");
                break;
            case ("Name"):
                boolean areNamesEqual = javaSortingItems.equals(listUniItems);
                assertTrue(areNamesEqual, "The name sorting doesn't equal between App and Java");
                log.info("The sorting lists are equal by java and app");
                break;
        }
    }
}