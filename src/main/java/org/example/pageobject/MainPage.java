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
    private static final String faqQuestionPattern = ".//div[contains(@id, 'accordion__heading') and contains(text(), '%s')]";
    private static final String faqAnswerPattern = ".//div[contains(@class, 'accordion__panel') and contains(text(), '%s')]";
    public static final String howMuchCosts = "Сколько это стоит? И как оплатить?";
    public static final String severalScooters = "Хочу сразу несколько самокатов! Так можно?";
    public static final String rentalTime = "Как рассчитывается время аренды?";
    public static final String orderToday = "Можно ли заказать самокат прямо на сегодня?";
    public static final String changeRentalTime = "Можно ли продлить заказ или вернуть самокат раньше?";
    public static final String charger = "Вы привозите зарядку вместе с самокатом?";
    public static final String orderCancellation = "Можно ли отменить заказ?";
    public static final String deliveryMkad = "Я живу за МКАДом, привезёте?";


    public static final String howMuchCostsAnswer = "Сутки — 400 рублей. Оплата курьеру — наличными или картой.";
    public static final String severalScootersAnswer = "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.";
    public static final String rentalTimeAnswer = "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.";
    public static final String orderTodayAnswer = "Только начиная с завтрашнего дня. Но скоро станем расторопнее.";
    public static final String changeRentalTimeAnswer = "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.";
    public static final String chargerAnswer = "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.";
    public static final String orderCancellationAnswer = "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.";
    public static final String deliveryMkadAnswer = "Да, обязательно. Всем самокатов! И Москве, и Московской области.";



    public static final String mainPageUrl = "https://qa-scooter.praktikum-services.ru/";

    public MainPage(WebDriver driver){

        this.driver = driver;
    }

    public MainPage openMainPage(){
        driver.get(mainPageUrl);
        return this;
    }
    public void orderTopButtonClick(){
        WebDriverWait wait = new WebDriverWait(driver, 5, TimeUnit.SECONDS.ordinal());
        wait.until(ExpectedConditions.elementToBeClickable(orderTopButton)).click();

    }

    public void clickFAQQuestion(String questionMessage) {
        String questionLocator = String.format(faqQuestionPattern, questionMessage);
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(questionLocator)));

        WebElement questionElement = driver.findElement(By.xpath(questionLocator));
        scrollToElement(questionElement);
        questionElement.click();
    }

    public void scrollToElement(WebElement element){
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    public String getFAQAnswer(String questionMessage) {
        String answerLocator = String.format(faqAnswerPattern, questionMessage);
        WebDriverWait wait = new WebDriverWait(driver, 30);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(answerLocator)));

        WebElement answerElement = driver.findElement(By.xpath(answerLocator));
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
