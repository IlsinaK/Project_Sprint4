package tests;

import org.example.pageobject.MainPage;
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
                {HOW_MUCH_COSTS, HOW_MUCH_COSTS_ANSWER},
                {SEVERAL_SCOOTERS, SEVERAL_SCOOTERS_ANSWER},
                {RENTAL_TIME, RENTAL_TIME_ANSWER},
                {ORDER_TODAY, ORDER_TODAY_ANSWER},
                {CHANGE_RENTAL_TIME, CHANGE_RENTAL_TIME_ANSWER},
                {CHARGER, CHARGER_ANSWER},
                {ORDER_CANCELLATION, ORDER_CANCELLATION_ANSWER},
                {DELIVERY_MKAD, DELIVERY_MKAD_ANSWER}
        });
    }

    @Test
    public void testFAQQuestions() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage();

        mainPage.closeCookieBannerButton();
        mainPage.clickFAQQuestion(questionMessage);

        String actualAnswer = mainPage.getFAQAnswer(expectedAnswer);
        Assert.assertEquals(expectedAnswer, actualAnswer);
    }
}

