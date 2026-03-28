package ru.yandex.practikum.tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class OrderTest extends BaseTest {

    private final String orderButtonLocation;
    private final String name;
    private final String surname;
    private final String address;
    private final String metroStation;
    private final String phone;
    private final String date;
    private final String rentalPeriod;
    private final String colour;
    private final String comment;

    public OrderTest(String orderButtonLocation, String name, String surname, String address,
                     String metroStation, String phone, String date, String rentalPeriod,
                     String colour, String comment) {
        this.orderButtonLocation = orderButtonLocation;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.date = date;
        this.rentalPeriod = rentalPeriod;
        this.colour = colour;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][]{
                {"top", "Иван", "Петров", "Москва, ул. Ленина, 1", "Сокольники",
                        "+79991234567", "15.03.2026", "сутки", "black", "Позвонить за час"},
                {"bottom", "Мария", "Иванова", "Москва, пр. Мира, 10", "Бульвар Рокоссовского",
                        "+79876543210", "22.03.2026", "двое суток", "grey", "Оставить у двери"},
        };
    }

    @Test
    public void createOrderTest() {
        if (orderButtonLocation.equals("top")) {
            mainPage.clickOrderButtonTop();
        } else {
            mainPage.scrollToOrderButtonBottom();
            mainPage.clickOrderButtonBottom();
        }

        orderPage.fillFirstOrderPage(name, surname, address, metroStation, phone);
        orderPage.fillSecondOrderPage(date, rentalPeriod, colour, comment);
        orderPage.confirmOrder();

        assertTrue("Сообщение об успешном заказе не появилось",
                orderPage.isOrderSuccessDisplayed());
    }
}