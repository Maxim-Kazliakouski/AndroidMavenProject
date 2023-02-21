package tests.base;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.github.romankh3.image.comparison.ImageComparison;
import com.github.romankh3.image.comparison.ImageComparisonUtil;
import com.github.romankh3.image.comparison.model.ImageComparisonResult;
import com.github.romankh3.image.comparison.model.ImageComparisonState;
//import config.ConfigReader;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.qameta.allure.Allure;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import steps.LoginPageSteps;
import steps.MainPageSteps;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import static helper.Constants.SCREENSHOT_TO_SAVE_FOLDER;
import static helper.DeviceHelper.executeBash;
import static helper.RunHelper.runHelper;
import static io.appium.java_client.remote.AndroidMobileCapabilityType.APP_ACTIVITY;
import static io.appium.java_client.remote.AndroidMobileCapabilityType.APP_PACKAGE;
import static io.appium.java_client.remote.MobileCapabilityType.DEVICE_NAME;
import static io.appium.java_client.remote.MobileCapabilityType.PLATFORM_VERSION;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.remote.CapabilityType.BROWSER_NAME;
import static org.openqa.selenium.remote.CapabilityType.PLATFORM_NAME;
import static org.testng.Assert.assertEquals;

@Log4j2
@Listeners(TestListener.class)
public abstract class BaseTest {

    public LoginPageSteps loginPageSteps;
    public MainPageSteps mainPageSteps;
    private RemoteWebDriver driver;

    @BeforeMethod
    public void setup() throws MalformedURLException {
        //добавляем логирование действий для аллюр отчета в виде степов
//        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
//        // папка для сохранения скриншотов selenide
//        Configuration.reportsFolder = SCREENSHOT_TO_SAVE_FOLDER;
//        //инициализируем андройд драйвер
//        Configuration.browser = runHelper().getDriverClass().getName();
//        Configuration.startMaximized = false;
//        Configuration.browserSize = null;
//        Configuration.timeout = 5000;
//        disableAnimationOnEmulator();
        // the list of pages with steps
        loginPageSteps = new LoginPageSteps();
        mainPageSteps = new MainPageSteps();
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);
        capabilities.setCapability("BROWSER_NAME", "Android");
        capabilities.setCapability("VERSION", "10");
        capabilities.setCapability("deviceName", "nexus_5");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appWaitActivity", "*");
        capabilities.setCapability("appPackage", APP_PACKAGE);
        capabilities.setCapability("appActivity", APP_ACTIVITY);
        capabilities.setCapability("app", "/opt/SauceLabs.apk");


//        capabilities.setCapability("BROWSER_NAME", "Android");
//        capabilities.setCapability("VERSION", "10.0");
//        capabilities.setCapability("deviceName", "Nexus5");
//        capabilities.setCapability("platformName", "Android");


//        capabilities.setCapability("appPackage", "com.android.calculator2");
// This package name of your app (you can get it from apk info app)
//        capabilities.setCapability("appActivity","com.android.calculator2.Calculator"); // This is Launcher activity of your app (you can get it from apk info app)
//Create RemoteWebDriver instance and connect to the Appium server
        //It will launch the Calculator App in Android Device using the configurations specified in Desired Capabilities
        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
    }


/**
 * Отключение анимаций на эмуляторе чтобы не лагало
 * <p>
 * Перед каждый тестом открываем приложение
 * <p>
 * Проверка скриншота с эталоном для проверки верстки
 *
 * @param actualScreenshot актуальный скриншот
 * @param expectedFileName название файла для сравнений
 */
//    private static void disableAnimationOnEmulator() {
//        executeBash("adb -s shell settings put global transition_animation_scale 0.0");
//        executeBash("adb -s shell settings put global window_animation_scale 0.0");
//        executeBash("adb -s shell settings put global animator_duration_scale 0.0");
//    }

    /**
     * Перед каждый тестом открываем приложение
     */
//    @BeforeMethod
//    public void startDriver() {
//        step("Открыть приложение", (Allure.ThrowableRunnableVoid) Selenide::open);
//    }
//
//    /**
//     * После каждого теста закрываем AndroidDriver чтобы тест атомарным был
//     */
//    @AfterMethod
//    public void afterEach() {
//        step("Закрыть приложение", Selenide::closeWebDriver);
//    }
}

/**
 * Проверка скриншота с эталоном для проверки верстки
 *
 * @param actualScreenshot актуальный скриншот
 * @param expectedFileName название файла для сравнений
 */
//    public void assertScreenshot(File actualScreenshot, String expectedFileName) {
//        //в метод передается всегда название тестового метода, поэтому меняем скобки на файл с расширением для дальнейшего сохранения
//        expectedFileName = expectedFileName.replace("()", ".png");
//        //папка для хранения эталонных скриншотов
//        String expectedScreensDir = "src/test/resources/expectedScreenshots/";
//        //если скрншоты надо обновить
//        if (ConfigReader.testConfig.isScreenshotsNeedToUpdate()) {
//            try {
//                //перемещаем текущий скрншот в папку с эталоном и заменяем файл
//                Files.move(actualScreenshot.toPath(), new File(expectedScreensDir + expectedFileName).toPath(),
//                        StandardCopyOption.REPLACE_EXISTING);
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//            //завершаем метод
//            return;
//        }
//
//        //если скриншот надо сравнить
//        // Загружаем ожидаемое изображения для сравнения.
//        BufferedImage expectedImage = ImageComparisonUtil
//                .readImageFromResources(expectedScreensDir + expectedFileName);
//
//        // Загружаем актуальный скриншот.
//        BufferedImage actualImage = ImageComparisonUtil
//                .readImageFromResources(SCREENSHOT_TO_SAVE_FOLDER + actualScreenshot.getName());
//
//        // Где будем хранить скриншот с различиями в случае падения теста.
//        File resultDestination = new File("diff/diff_" + expectedFileName);
//
//        // Сравниваем.
//        ImageComparisonResult imageComparisonResult = new ImageComparison(expectedImage, actualImage, resultDestination)
//                .compareImages();
//
//        //если скриншоты отличаются
//        if (imageComparisonResult.getImageComparisonState().equals(ImageComparisonState.MISMATCH)) {
//            try {
//                //добавляем скриншот с отличиями к аллюр отчету в виде степа
//                byte[] diffImageBytes = Files.readAllBytes(resultDestination.toPath());
//                AllureListener.saveScreenshot(diffImageBytes);
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//
//        }
//        //сравниваем скриншоты
//        assertEquals(ImageComparisonState.MATCH, imageComparisonResult.getImageComparisonState());
//    }

