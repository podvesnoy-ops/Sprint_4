package ru.yandex.practikum.tests;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import ru.yandex.practikum.pages.MainPage;
import ru.yandex.practikum.pages.OrderPage;
import java.util.concurrent.TimeUnit;
import ru.yandex.practikum.config.Config;

public class BaseTest {
    protected WebDriver driver;
    protected MainPage mainPage;
    protected OrderPage orderPage;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(Config.BASE_URL);

        mainPage = new MainPage(driver);
        orderPage = new OrderPage(driver);

        mainPage.closeCookieBanner();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}