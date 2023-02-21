package driver;

import com.codeborne.selenide.SelenideElement;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static java.lang.String.format;

/**
 * Класс помощник для Page страниц
 */
public class EmulatorHelper extends EmulatorDriver{

    /**
     * Нажимает кнопку назад
     */
    public static void goBack(){
        driver.navigate().back();
    }

    /**
     * Листает к элементу по его тексту
     * @param text текст на элементе
     */
    public static void androidScrollToAnElementByText(String text){
        SelenideElement element = $(MobileBy.xpath(format("//android.widget.TextView[@text='%s']", text)));
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)" +
                        ".instance(0)).scrollIntoView(new UiSelector().textContains(\""+text+"\").instance(0))");
//                .click();
        org.openqa.selenium.Dimension size = driver.manage().window().getSize();
        TouchAction action = new TouchAction(driver);
        int startX = element.getLocation().getX();
        int startY = element.getLocation().getY();
        int endY = (int) (size.width*0.99);
//        swipe back
//        int endX = (int) (size.width*0.5);
        action.press(PointOption.point(startX, startY)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                .moveTo(PointOption.point(startX, endY)).release().perform();
    }

    /**
     * Закрывает клавиатуру если она есть
     */
    public static void closeKeyBoard(){
        if(driver.isKeyboardShown()){
            driver.hideKeyboard();
        }
    }

    /**
     * Вводит текст и нажимает Enter
     * @param element поле для ввода
     * @param text текст
     */
    public static void sendKeysAndFind(SelenideElement element, String text){
        element.sendKeys(text);
        driver.pressKey(new KeyEvent(AndroidKey.ENTER));
    }
}
