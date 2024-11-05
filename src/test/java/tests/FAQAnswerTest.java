package tests;

import org.example.pageobject.MainPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


import java.util.Arrays;
import java.util.Collection;

import static org.example.pageobject.MainPage.*;

@RunWith(Parameterized.class)
public class FAQAnswerTest extends BaseUITest {

    private final String questionMessage;
    private final String expectedAnswer;

    public FAQAnswerTest(String questionMessage, String expectedAnswer) {
        this.questionMessage = questionMessage;
        this.expectedAnswer = expectedAnswer;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> faqData() {
        return Arrays.asList(new Object[][]{
                {howMuchCosts, howMuchCostsAnswer},
                {severalScooters, severalScootersAnswer},
                {rentalTime, rentalTimeAnswer},
                {orderToday, orderTodayAnswer},
                {changeRentalTime, changeRentalTimeAnswer},
                {charger, chargerAnswer},
                {orderCancellation, orderCancellationAnswer},
                {deliveryMkad, deliveryMkadAnswer}
        });
    }

    @Test
    public void testFAQQuestions() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage();

        mainPage.closeCookieBannerButton();
        mainPage.clickFAQQuestion(questionMessage);

        String actualAnswer = mainPage.getFAQAnswer(questionMessage);
        Assert.assertEquals(expectedAnswer, actualAnswer);
    }
}

