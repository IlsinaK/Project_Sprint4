package tests;

import org.example.pageobject.MainPage;
import org.example.pageobject.OrderPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;



@RunWith(Parameterized.class)
public class CheckTheOrderCreationTest extends BaseUITest {

    private String firstName;
    private String lastName;
    private String address;
    private String phone;

    public CheckTheOrderCreationTest(String firstName, String lastName, String address, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"Иван", "Иванов", "Москва", "Бульвар Рокоссовского", "79123456789"},
                {"Мария", "Петрова", "Санкт-Петербург", "Невский проспект", "79211234567"}
        });
    }

    @Test
    public void userDataTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage();
        mainPage.orderTopButtonClick();
        OrderPage orderPage = new OrderPage(driver);

        orderPage.setStringNameInput(firstName);
        orderPage.setStringLastNameInput(lastName);
        orderPage.setAdressLineInput(address);
        orderPage.selectMetroStation();
        orderPage.setPhoneLineInput(phone);
        orderPage.closeCookieBannerButton();
        orderPage.nextButtonClick();
    }

    @Test
    public void dataPickerTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage();
        OrderPage orderPage = new OrderPage(driver);

        String deliveryData = LocalDate.now().plusDays(1).toString();
        System.out.println("delyveryData = " + deliveryData);
        orderPage.selectDeliveryDate(deliveryData);

        orderPage.rentalPeriod();
        orderPage.colorBlackInputClick();
        orderPage.setCommentInput("Спасибо!");
        orderPage.orderMiddleButtonClick();
        orderPage.yesButtonClick();

        boolean isSavedOrder = orderPage.getIsSavedOrder();
        Assert.assertTrue(isSavedOrder);
    }


    @Test
    public void orderMiddleButtonTest(){
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage();
        OrderPage orderPage = new OrderPage(driver);

        mainPage.orderMiddleButtonClick();
        boolean isPageForWhom = orderPage.getIsPageForWhom();
        Assert.assertTrue(isPageForWhom);
    }

    @After
    public void tearDown() {
            driver.quit();
            }
}

