package org.example.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class OrderPage {
    protected WebDriver driver;
    private WebDriverWait wait;

    private By stringNameInput = By.xpath(".//input[@placeholder ='* Имя']"); // строка ввода Имя
    private By stringLastNameInput = By.xpath(".//input[@placeholder ='* Фамилия']"); // строка Фамилия
    private By adressLineInput = By.xpath(".//input[@placeholder ='* Адрес: куда привезти заказ']"); //строка Адрес
    private By metroSelectInput = By.xpath(".//input[@placeholder='* Станция метро']");// строка станция метро
    private By phoneLineInput = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']"); // строка телефон
    private By nextButton = By.xpath(".//button[contains(@class, 'Button_Middle__1CSJM')]"); // кнопка Далее
    private By deliveryDateInput = By.xpath(".//input[@placeholder= '* Когда привезти самокат']"); //календарь
    private By getDeliveryDateDataPicker = By.xpath(".//div[@tabindex and contains(@class, 'react-datepicker__day')]"); //выбор даты
    private By isPageForWhom = By.xpath(".//div[@id ='root']"); // страница Для кого самокат
    public static final String orderPageUrl = "https://qa-scooter.praktikum-services.ru/order"; //url страницы ввода данных для заказа
    private By rentalPeriod   = By.xpath (".//div[@class = 'Dropdown-root']") ; // срок аренды
    private By  colorBlackInput  = By.xpath (".//input[@id = 'black']"); //  цвет самоката черный жемчуг
    private By  commentInput = By.xpath (".//input[@placeholder = 'Комментарий для курьера']"); //поле 'Комментарий для курьера'
    private By  orderMiddleButton = By.xpath (".//button[contains(@class, 'Middle__1CSJM') and contains(text(), 'Заказать')]"); // нижняя кнопка «Заказать»
    private By rentalDays   = By.xpath (".//div[contains(@class, 'Dropdown-menu')]"); //выбор дней аренды
    private By yesButton  = By.xpath(".//button[contains(@class, 'Middle__1CSJM') and text()='Да']"); // кнопка "Да" для подтверждения заказа
    private By  isSavedOrder= By.xpath(".//div[contains(@class, 'App_App__15LM-')]"); //окошко заказ оформлен
    private By colorGrayInput = By.xpath (".//input[@id = 'grey']");  // цвет самоката серая безысходность


    public OrderPage(WebDriver driver) {

        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10, TimeUnit.SECONDS.ordinal());
    }

    public OrderPage openOrderPage(){
    driver.get(orderPageUrl);
      return this;
    }


    public void setStringNameInput(String stringNameValue) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(stringNameInput));
        WebElement stringNameWebElement = driver.findElement(stringNameInput);
        stringNameWebElement.clear();
        driver.findElement(stringNameInput).sendKeys(stringNameValue);

    }

    public void setStringLastNameInput(String stringLastNameValue) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(stringLastNameInput));
        WebElement stringLastNameWebElement = driver.findElement(stringLastNameInput);
        stringLastNameWebElement.clear();
        driver.findElement(stringLastNameInput).sendKeys(stringLastNameValue);
    }

    public void setAdressLineInput(String adressLineValue) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(adressLineInput));
        WebElement adressLineWebElement = driver.findElement(adressLineInput);
        adressLineWebElement.clear();
        driver.findElement(adressLineInput).sendKeys(adressLineValue);
    }


    public void selectMetroStation(String stationNameValue) {
        WebElement stationInput = driver.findElement(metroSelectInput);
        stationInput.click();
        wait = new WebDriverWait(driver, 10, TimeUnit.SECONDS.ordinal());
         stationInput.sendKeys(stationNameValue);



        WebElement stationOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class, 'select-search')]//div[text()='" + stationNameValue + "']")));
        stationOption.click();

    }

    public void setPhoneLineInput(String phoneLineValue) {

        WebElement phoneLineWebElement = wait.until(ExpectedConditions.visibilityOfElementLocated(phoneLineInput));
        phoneLineWebElement.clear();
        phoneLineWebElement.sendKeys(phoneLineValue);
    }



    public void nextButtonClick() {

        wait.until(ExpectedConditions.elementToBeClickable(nextButton));
        driver.findElement(nextButton).click();
    }


    public boolean getIsPageForWhom(){

        wait.until(ExpectedConditions.visibilityOfElementLocated(isPageForWhom));
        return driver.findElement(isPageForWhom).isDisplayed();
    }


    public void selectDeliveryDate(String date) {
        wait = new WebDriverWait(driver, 30, TimeUnit.SECONDS.ordinal());
        wait.until(ExpectedConditions.visibilityOfElementLocated(deliveryDateInput));
        driver.findElement(deliveryDateInput).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(getDeliveryDateDataPicker));
        driver.findElement(getDeliveryDateDataPicker).click();
    }


    public void  rentalPeriod(String rentalPeriod){
        WebElement rentalPeriodWebElement = driver.findElement(this.rentalPeriod);
        rentalPeriodWebElement.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(rentalDays));

        List<WebElement> daysOptions = driver.findElements(rentalDays);


        WebElement firstDay = daysOptions.get(0);
        firstDay.click();


    }


    public void selectColor(String color) {
        String colorLowerCase = color.toLowerCase();

        if (colorLowerCase.equals("чёрный жемчуг")) {
            wait.until(ExpectedConditions.elementToBeClickable(colorBlackInput)).click();
        } else if (colorLowerCase.equals("серая безысходность")) {
            wait.until(ExpectedConditions.elementToBeClickable(colorGrayInput)).click();
        }
    }

    public void setCommentInput(String commentValue){

        wait.until(ExpectedConditions.visibilityOfElementLocated(commentInput));

        WebElement commentWebElement = driver.findElement(commentInput);
        commentWebElement.clear();
        driver.findElement(commentInput).sendKeys(commentValue);
    }


    public void orderMiddleButtonClick(){

        wait.until(ExpectedConditions.elementToBeClickable(orderMiddleButton)).click();
    }

    public void yesButtonClick() {

        wait.until(ExpectedConditions.elementToBeClickable(yesButton));
        driver.findElement(yesButton).click();
    }

    public boolean getIsSavedOrder(){

        wait.until(ExpectedConditions.visibilityOfElementLocated(isSavedOrder));
        return driver.findElement(isSavedOrder).isDisplayed();
    }

}




