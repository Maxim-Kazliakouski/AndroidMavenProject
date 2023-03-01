package tests.base;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
//import config.ConfigReader;
import io.qameta.allure.Allure;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import steps.LoginPageSteps;
import steps.MainPageSteps;

import java.io.*;

import static com.codeborne.selenide.Selenide.sleep;
import static helper.Constants.SCREENSHOT_TO_SAVE_FOLDER;
import static helper.DeviceHelper.executeBash;
import static helper.RunHelper.runHelper;
import static io.qameta.allure.Allure.step;
import static org.testng.Assert.assertEquals;

@Log4j2
@Listeners(TestListener.class)
public abstract class BaseTest {

    public LoginPageSteps loginPageSteps;
    public MainPageSteps mainPageSteps;
    private RemoteWebDriver driver;
    public Process appium;
    public Process emulatorAndroid;

    @BeforeMethod
    public void setup() throws IOException {
        //добавляем логирование действий для аллюр отчета в виде степов
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
//        // папка для сохранения скриншотов selenide
        Configuration.reportsFolder = SCREENSHOT_TO_SAVE_FOLDER;
        // запуск appium
        appium = Runtime.getRuntime().exec("C:\\ProgramData\\Jenkins\\.jenkins\\workspace\\android\\appium_launcher.bat");
        sleep(2000);
        System.out.println("Appium is lunched!");
        // запуск эмулятора
        emulatorAndroid = Runtime.getRuntime().exec("C:\\Users\\Selecty\\AppData\\Local\\Android\\Sdk\\emulator\\emulator -avd Pixel_5_API_29  ");
        sleep(2000);
        System.out.println("Emulator is launched!");
        //инициализируем андройд драйвер
        Configuration.browser = runHelper().getDriverClass().getName();
        Configuration.startMaximized = false;
        Configuration.browserSize = null;
        Configuration.timeout = 5000;

        disableAnimationOnEmulator();
        // the list of pages with steps
        loginPageSteps = new LoginPageSteps();
        mainPageSteps = new MainPageSteps();
        DesiredCapabilities capabilities = new DesiredCapabilities();
    }

    /**
     * Отключение анимаций на эмуляторе чтобы не лагало
     * <p>
     * Перед каждый тестом открываем приложение
     * <p>
     * Проверка скриншота с эталоном для проверки верстки
     * <p>
     * // * @param actualScreenshot актуальный скриншот
     * // * @param expectedFileName название файла для сравнений
     */
    private static void disableAnimationOnEmulator() {
        executeBash("adb -s shell settings put global transition_animation_scale 0.0");
        executeBash("adb -s shell settings put global window_animation_scale 0.0");
        executeBash("adb -s shell settings put global animator_duration_scale 0.0");
    }

    /**
     * Перед каждый тестом открываем приложение
     */
    @BeforeMethod
    public void startDriver() {
        step("Открыть приложение", (Allure.ThrowableRunnableVoid) Selenide::open);
    }

    /**
     * После каждого теста закрываем AndroidDriver чтобы тест атомарным был
     */
    @AfterMethod
    public void afterEach() {
        step("Закрыть приложение", Selenide::closeWebDriver);
    }

    @AfterClass
    public void killingProcess() {
        appium.destroy();
        emulatorAndroid.destroy();
    }
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

