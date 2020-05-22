package net.serenity.bdd.features.function_testing;

import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import net.serenity.bdd.steps.serenity.EndUserSteps;
import net.thucydides.junit.annotations.UseTestDataFrom;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import java.util.ArrayList;

//The Serenity test runner sets up the test and records the test results
@RunWith(SerenityParameterizedRunner.class)
@UseTestDataFrom(value = "src/test/resources/features/FillInTheFormData.csv")
public class FillInTheForm {

    //This is a web test, and Serenity will manage the WebDriver driver for us
    @Managed(uniqueSession = true)
    private WebDriver webdriver;

    //We hide implementation details about how the test will be executed in a "step library"
    @Steps
    private EndUserSteps ubbcluj;

    private String articleName;
    private String formName;
    private String name;

    @Test
    public void void_input_form_returns_error_message() {
        ubbcluj.is_the_home_page();
        int currentIndexTab = new ArrayList<>(webdriver.getWindowHandles()).indexOf(webdriver.getWindowHandle());

        ubbcluj.presses_on(articleName);
        webdriver.close();
        webdriver.switchTo().window(new ArrayList<>(webdriver.getWindowHandles()).get(currentIndexTab));

        ubbcluj.presses_on(formName);
        webdriver.close();
        webdriver.switchTo().window(new ArrayList<>(webdriver.getWindowHandles()).get(currentIndexTab));

        ubbcluj.entersName("\n");
        ubbcluj.should_contains_error_message();
        webdriver.close();
    }

    @Test
    public void valid_input_form_returns_no_error_message() {
        ubbcluj.is_the_home_page();
        int currentIndexTab = new ArrayList<>(webdriver.getWindowHandles()).indexOf(webdriver.getWindowHandle());

        ubbcluj.presses_on(articleName);
        webdriver.close();
        webdriver.switchTo().window(new ArrayList<>(webdriver.getWindowHandles()).get(currentIndexTab));

        ubbcluj.presses_on(formName);
        webdriver.close();
        webdriver.switchTo().window(new ArrayList<>(webdriver.getWindowHandles()).get(currentIndexTab));

        ubbcluj.entersName(name + "\n");
        ubbcluj.should_contains_no_error_message();
        webdriver.close();
    }
}