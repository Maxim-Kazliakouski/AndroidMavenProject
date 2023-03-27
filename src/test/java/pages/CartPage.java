package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.appium.java_client.MobileBy;

import static com.codeborne.selenide.Selenide.$;
import static java.lang.String.format;

public class CartPage {

    public void isAddedItemsMatchWithItemsInCart(String... itemNames) {
        SelenideElement cartIcon = $(MobileBy.xpath("//android.view.ViewGroup[@content-desc='test-Cart']/android.view.ViewGroup/android.widget.ImageView"));
        cartIcon.click();
        for (String eachItemTitle : itemNames) {
            $(MobileBy.xpath(format("//android.widget.TextView[@text='%s']", eachItemTitle))).shouldBe(Condition.visible);
        }
    }
}
