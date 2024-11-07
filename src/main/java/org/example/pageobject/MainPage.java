package org.example.pageobject;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;



public class MainPage {

    protected WebDriver driver;

    private By orderTopButton = By.xpath(".//div[@class='Header_Nav__AGCXC']//button[text()='Заказать']"); //верхняя кнопка Заказать
    private By orderMiddleButton = By.xpath(".//button[contains(@class, 'Button_Middle__1CSJM')]"); // нижняя кнопка Заказать
    private By closeCookieBannerButton = By.xpath(".//button[contains(@id, 'rcc-confirm-button')]"); // кнопка закрыть Cookie
    private static final String FAQ_QUESTION_PATTERN = ".//div[contains(@id, 'accordion__heading') and contains(text(), '%s')]";
    private static final String FAQ_ANSWER_PATTERN = ".//div[contains(@class, 'accordion__panel')]/p[contains(text(), '%s')]";
    public static final String HOW_MUCH_COSTS = "Сколько это стоит? И как оплатить?";
    public static final String SEVERAL_SCOOTERS = "Хочу сразу несколько самокатов! Так можно?";
    public static final String RENTAL_TIME = "Как рассчитывается время аренды?";
    public static final String ORDER_TODAY = "Можно ли заказать самокат прямо на сегодня?";
    public static final String CHANGE_RENTAL_TIME = "Можно ли продлить заказ или вернуть самокат раньше?";
    public static final String CHARGER = "Вы привозите зарядку вместе с самокатом?";
    public static final String ORDER_CANCELLATION = "Можно ли отменить заказ?";
    public static final String DELIVERY_MKAD = "Я жизу за МКАДом, привезёте?";


    public static final String HOW_MUCH_COSTS_ANSWER = "Сутки — 400 рублей. Оплата курьеру — наличными или картой.";
    public static final String SEVERAL_SCOOTERS_ANSWER = "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.";
    public static final String RENTAL_TIME_ANSWER = "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.";
    public static final String ORDER_TODAY_ANSWER = "Только начиная с завтрашнего дня. Но скоро станем расторопнее.";
    public static final String CHANGE_RENTAL_TIME_ANSWER = "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.";
    public static final String CHARGER_ANSWER = "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.";
    public static final String ORDER_CANCELLATION_ANSWER = "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.";
    public static final String DELIVERY_MKAD_ANSWER = "Да, обязательно. Всем самокатов! И Москве, и Московской области.";



    public static final String MAIN_PAGE_URL = "https://qa-scooter.praktikum-services.ru/";

    public MainPage(WebDriver driver){

        this.driver = driver;
    }

    public MainPage openMainPage(){
        driver.get(MAIN_PAGE_URL);
        return this;
    }
    public void orderTopButtonClick(){
        WebDriverWait wait = new WebDriverWait(driver, 5, TimeUnit.SECONDS.ordinal());
        wait.until(ExpectedConditions.elementToBeClickable(orderTopButton)).click();

    }

    public void clickFAQQuestion(String questionMessage) {
        String questionLocator = String.format(FAQ_QUESTION_PATTERN, questionMessage);
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(questionLocator)));

        WebElement questionElement = driver.findElement(By.xpath(questionLocator));
        scrollToElement(questionElement);
        questionElement.click();
    }

    public void scrollToElement(WebElement element){
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    public String getFAQAnswer(String answerMessage) {
        String answerLocator = String.format(FAQ_ANSWER_PATTERN, answerMessage);
        WebDriverWait wait = new WebDriverWait(driver, 30);
        WebElement answerElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(answerLocator)));
        return answerElement.getText();
    }


    public void closeCookieBannerButton() {
        WebElement cookieBanner = driver.findElement(closeCookieBannerButton);
        if (cookieBanner.isDisplayed()) {
            cookieBanner.click();
        }
    }

    public void orderMiddleButtonClick(){
        WebElement middleButtonElement = driver.findElement(orderMiddleButton);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", middleButtonElement);
        WebDriverWait wait = new WebDriverWait(driver, 10, TimeUnit.SECONDS.ordinal());
        wait.until(ExpectedConditions.elementToBeClickable(orderMiddleButton)).click();
   }
}
