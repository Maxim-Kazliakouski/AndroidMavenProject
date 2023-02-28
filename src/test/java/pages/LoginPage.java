package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import driver.EmulatorHelper;
import io.appium.java_client.MobileBy;

import static com.codeborne.selenide.Selenide.*;
import static java.lang.String.format;
import static org.openqa.selenium.By.xpath;

public class LoginPage {

    public void isOpened() {
        SelenideElement buttonLogin = $(MobileBy.AccessibilityId("test-LOGIN"));
        buttonLogin.shouldBe(Condition.visible);
    }

    public void login() {
        SelenideElement buttonLogin = $(MobileBy.AccessibilityId("test-LOGIN"));
        $(MobileBy.AccessibilityId("test-LOGIN")).shouldBe(Condition.visible);
        $(MobileBy.AccessibilityId("test-Username")).sendKeys("standard_user");
        $(MobileBy.AccessibilityId("test-Password")).sendKeys("secret_sauce");
        buttonLogin.click();
        $(xpath("//android.view.ViewGroup[@content-desc='test-Cart']"))
                .shouldBe(Condition.visible);
    }

    public void isUserLogged(String text) {
        $(xpath(format("//android.widget.TextView[@text='%s']", text)))
                .shouldBe(Condition.visible);
    }

    public void loginWithDifferCreds(String username) {
        EmulatorHelper.androidScrollToAnElementByText(username);
        $(xpath(format("//android.widget.TextView[@text='%s']", username))).click();
        SelenideElement buttonLogin = $(MobileBy.AccessibilityId("test-LOGIN"));
        buttonLogin.click();
    }
}
