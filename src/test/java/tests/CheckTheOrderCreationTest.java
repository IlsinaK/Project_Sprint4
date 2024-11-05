package tests;

import org.example.pageobject.MainPage;
import org.example.pageobject.OrderPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


import java.util.Arrays;
import java.util.Collection;



@RunWith(Parameterized.class)
public class CheckTheOrderCreationTest extends BaseUITest {

    private String firstName;
    private String lastName;
    private String address;
    private String stationName;
    private String phone;
    private String deliveryData;
    private String rentalPeriod;
    private String color;
    private String comment;

    public CheckTheOrderCreationTest(String firstName, String lastName, String address, String stationName, String phone, String deliveryData, String rentalPeriod, String color, String comment) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.stationName = stationName;
        this.phone = phone;
        this.deliveryData = deliveryData;
        this.rentalPeriod = rentalPeriod;
        this.color = color;
        this.comment = comment;

    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"Иван", "Иванов", "Москва", "Бульвар Рокоссовского", "79123456789", "07.11.2024",  "трое суток", "чёрный жемчуг", "Спасибо!"},
                {"Мария", "Петрова", "Санкт-Петербург", "Сокольники", "79211234567", "25.11.2024", "двое суток", "серая безысходность", "Хорошо!"}
        });
    }

    @Test
    public void userDataTest() {
        MainPage mainPage = new MainPage(driver);

        mainPage.orderTopButtonClick();
        OrderPage orderPage = new OrderPage(driver);

        orderPage.setStringNameInput(firstName);
        orderPage.setStringLastNameInput(lastName);
        orderPage.setAdressLineInput(address);
        orderPage.selectMetroStation(stationName);
        orderPage.setPhoneLineInput(phone);
        mainPage.closeCookieBannerButton();
        orderPage.nextButtonClick();


        //String deliveryData = LocalDate.now().plusDays(1).toString();
        System.out.println("delyveryData = " + deliveryData);
        orderPage.selectDeliveryDate(deliveryData);
        orderPage.setCommentInput(comment);
        orderPage.rentalPeriod(rentalPeriod); // Передаем срок аренды
        orderPage.selectColor(color); // Передаем цвет самоката
        orderPage.orderMiddleButtonClick();
        orderPage.yesButtonClick();

        boolean isSavedOrder = orderPage.getIsSavedOrder();
        Assert.assertTrue(isSavedOrder);
    }



    @Test
    public void orderMiddleButtonTest(){
        MainPage mainPage = new MainPage(driver);

        OrderPage orderPage = new OrderPage(driver);

        mainPage.orderMiddleButtonClick();
        boolean isPageForWhom = orderPage.getIsPageForWhom();
        Assert.assertTrue(isPageForWhom);
    }


}

