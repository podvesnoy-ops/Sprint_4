package ru.yandex.practikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import java.util.concurrent.TimeUnit;

public class MainPage {
    private final WebDriver driver;

    // Конструктор класса
    public MainPage(WebDriver driver) {
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    //Локаторы
    private final By orderButtonTop = By.xpath(".//button[contains(@class, 'Button_Button') and text()='Заказать']");
    private final By orderButtonBottom = By.xpath(".//div[contains(@class, 'FinishButton')]/button[text()='Заказать']");
    private final By faqBlock = By.xpath(".//div[contains(@class, 'Home_FAQ')]");
    private final By cookieBanner = By.id("rcc-confirm-button");

    // Локаторы с параметрами
    private By question(int index) {
        return By.id("accordion__heading-" + index);
    }

    private By answer(int index) {
        return By.id("accordion__panel-" + index);
    }

    //Методы
    public void clickOrderButtonTop() {
        driver.findElement(orderButtonTop).click();
    }

    public void clickOrderButtonBottom() {
        driver.findElement(orderButtonBottom).click();
    }

    public void scrollToOrderButtonBottom() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(orderButtonBottom));
    }

    public void scrollToFaqBlock() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(faqBlock));
    }

    public void closeCookieBanner() {
        if (!driver.findElements(cookieBanner).isEmpty()) {
            driver.findElement(cookieBanner).click();
        }
    }

    public void clickQuestion(int index) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});",
                driver.findElement(question(index)));
        driver.findElement(question(index)).click();
    }

    public String getQuestionText(int index) {
        return driver.findElement(question(index)).getText();
    }

    public String getAnswerText(int index) {
        return driver.findElement(answer(index)).getText();
    }
}