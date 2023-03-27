package driver;

import com.codeborne.selenide.SelenideElement;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;

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
    /**
     * Performs small swipe from the center of screen
     *
     * @param dir the direction of swipe
     * @version java-client: 7.3.0
     **/

    public static void swipeScreen(String dir, int swipeDivider) {
//        System.out.println("swipeScreenSmall(): dir: '" + dir + "'"); // always log your actions

        // Animation default time:
        //  - Android: 300 ms
        //  - iOS: 200 ms
        // final value depends on your app and could be greater
        final int ANIMATION_TIME = 200; // ms

        final int PRESS_TIME = 200; // ms

        PointOption pointOptionStart, pointOptionEnd;

        // init screen variables
        Dimension dims = driver.manage().window().getSize();

        // init start point = center of screen
        pointOptionStart = PointOption.point(dims.width / 2, dims.height / 2);

        // reduce swipe move into multiplier times comparing to swipeScreen move
        switch (dir) {
            case "DOWN": // center of footer
                pointOptionEnd = PointOption.point(dims.width / 2, (dims.height / 2) + (dims.height / 2) / swipeDivider);
                break;
            case "UP": // center of header
                pointOptionEnd = PointOption.point(dims.width / 2, (dims.height / 2) - (dims.height / 2) / swipeDivider);
                break;
            case "LEFT": // center of left side
                pointOptionEnd = PointOption.point((dims.width / 2) - (dims.width / 2) / swipeDivider, dims.height / 2);
                break;
            case "RIGHT": // center of right side
                pointOptionEnd = PointOption.point((dims.width / 2) + (dims.width / 2) / swipeDivider, dims.height / 2);
                break;
            default:
                throw new IllegalArgumentException("swipeScreenSmall(): dir: '" + dir.toString() + "' NOT supported");
        }

        // execute swipe using TouchAction
        try {
            new TouchAction(driver)
                    .press(pointOptionStart)
                    // a bit more reliable when we add small wait
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(PRESS_TIME)))
                    .moveTo(pointOptionEnd)
                    .release().perform();
        } catch (Exception e) {
//            System.err.println("swipeScreenSmall(): TouchAction FAILED\n" + e.getMessage());
            return;
        }

        // always allow swipe action to complete
        try {
            Thread.sleep(ANIMATION_TIME);
        } catch (InterruptedException e) {
            // ignore
        }
    }
}
