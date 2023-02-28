package pages;

import com.codeborne.selenide.SelenideElement;
import driver.EmulatorHelper;
import io.appium.java_client.MobileBy;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Selenide.*;
import static java.lang.String.format;
@Log4j2
public class MainPage {
    public void isOpened(){

    }

    public void addingGoodInCart(String goodName){
        EmulatorHelper.androidScrollToAnElementByText(goodName);
        SelenideElement addToCart = $(MobileBy.xpath(format("//android.widget.TextView[@text='%s']//..//android.view.ViewGroup[@content-desc='test-ADD TO CART']", goodName)));
        addToCart.click();
        log.info(format("The good --> '%s' has been added", goodName));
    }
}
