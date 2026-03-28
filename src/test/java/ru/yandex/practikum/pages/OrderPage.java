package ru.yandex.practikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Keys;
import java.util.concurrent.TimeUnit;

public class OrderPage {
    private final WebDriver driver;

    // Конструктор класса
    public OrderPage(WebDriver driver) {
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    //Локаторы для первого экрана заказа
    private final By nameField = By.xpath(".//input[@placeholder='* Имя']");
    private final By surnameField = By.xpath(".//input[@placeholder='* Фамилия']");
    private final By addressField = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By metroStationField = By.xpath(".//input[@placeholder='* Станция метро']");
    private final By phoneField = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final By nextButton = By.xpath(".//button[text()='Далее']");

    // Локатор станции метро
    private By metroStationOption(String stationName) {
        return By.xpath(".//div[text()='" + stationName + "']");
    }

    // Методы для первого экрана заказа
    public void fillName(String name) {
        driver.findElement(nameField).sendKeys(name);
    }

    public void fillSurname(String surname) {
        driver.findElement(surnameField).sendKeys(surname);
    }

    public void fillAddress(String address) {
        driver.findElement(addressField).sendKeys(address);
    }

    public void selectMetroStation(String stationName) {
        driver.findElement(metroStationField).click();
        driver.findElement(metroStationOption(stationName)).click();
    }

    public void fillPhone(String phone) {
        driver.findElement(phoneField).sendKeys(phone);
    }

    public void clickNextButton() {
        driver.findElement(nextButton).click();
    }
    // Сборный метод первого экрана заказа
    public void fillFirstOrderPage(String name, String surname, String address,
                                   String metroStation, String phone) {
        fillName(name);
        fillSurname(surname);
        fillAddress(address);
        selectMetroStation(metroStation);
        fillPhone(phone);
        clickNextButton();
    }

    //Локаторы для второго экрана заказа
    private final By dateField = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    private final By rentalPeriodField = By.xpath(".//div[text()='* Срок аренды']");
    private final By blackColourCheckbox = By.xpath(".//input[@id='black']");
    private final By greyColourCheckbox = By.xpath(".//input[@id='grey']");
    private final By commentField = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    private final By finalOrderButton = By.xpath(".//div[contains(@class, 'Order_Buttons')]/button[text()='Заказать']");

    private By rentalPeriodOption(String period) {
        return By.xpath(".//div[text()='" + period + "']");
    }

    // Методы для второго экрана заказа
    public void enterDate(String date) {
        driver.findElement(dateField).sendKeys(date);
        driver.findElement(dateField).sendKeys(Keys.ENTER);
    }

    public void selectRentalPeriod(String period) {
        driver.findElement(rentalPeriodField).click();
        driver.findElement(rentalPeriodOption(period)).click();
    }

    public void selectBlackColour() {
        driver.findElement(blackColourCheckbox).click();
    }

    public void selectGreyColour() {
        driver.findElement(greyColourCheckbox).click();
    }

    public void fillComment(String comment) {
        driver.findElement(commentField).sendKeys(comment);
    }

    public void clickFinalOrderButton() {
        driver.findElement(finalOrderButton).click();
    }
    // Сборный метод второго экрана заказа
    public void fillSecondOrderPage(String date, String rentalPeriod, String colour, String comment) {
        enterDate(date);
        selectRentalPeriod(rentalPeriod);

        if (colour.equals("black")) {
            selectBlackColour();
        } else if (colour.equals("grey")) {
            selectGreyColour();
        }

        fillComment(comment);
        clickFinalOrderButton();
    }

    //Локаторы всплывающего окна подтверждения
    private final By confirmOrderButton = By.xpath(".//button[text()='Да']");
    private final By successModalHeader = By.xpath(".//div[contains(@class, 'Order_ModalHeader')]");

    //Методы всплывающего окна подтверждения
    public void confirmOrder() {
        driver.findElement(confirmOrderButton).click();
    }

    public boolean isOrderSuccessDisplayed() {
        return driver.findElement(successModalHeader).getText().contains("Заказ оформлен");
    }

}