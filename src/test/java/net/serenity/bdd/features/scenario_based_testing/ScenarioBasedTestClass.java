package net.serenity.bdd.features.scenario_based_testing;

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
@UseTestDataFrom(value = "src/test/resources/features/ScenarioBasedTestClassData.csv")
public class ScenarioBasedTestClass {

    //This is a web test, and Serenity will manage the WebDriver driver for us
    @Managed(uniqueSession = true)
    private WebDriver webdriver;

    //We hide implementation details about how the test will be executed in a "step library"
    @Steps
    private EndUserSteps ubbcluj;

    private String keyword;
    private String articleName;
    private String linkName;
    private String formName;
    private String formLink;
    private String tagName;
    private String name;

    @Test
    public void scenario_based_test_search_pocu_form_and_fill_the_form() {
        ubbcluj.is_the_home_page();
        int currentIndexTab = new ArrayList<>(webdriver.getWindowHandles()).indexOf(webdriver.getWindowHandle());

        //SearchByKeyword
        ubbcluj.looks_for(keyword + "\n");
        ubbcluj.should_see_text_in_tag(articleName, tagName);
        ubbcluj.presses_on(articleName);    //OpenArticle

        //OpenNewTab
        ubbcluj.presses_on(linkName);
        webdriver.close();
        webdriver.switchTo().window(new ArrayList<>(webdriver.getWindowHandles()).get(currentIndexTab));
        String currentURL = webdriver.getCurrentUrl();
        ubbcluj.should_see_url(currentURL, linkName);

        //OpenNewTab
        ubbcluj.presses_on(formName);
        webdriver.close();
        webdriver.switchTo().window(new ArrayList<>(webdriver.getWindowHandles()).get(currentIndexTab));
        currentURL = webdriver.getCurrentUrl();
        ubbcluj.should_see_url(currentURL, formLink);

        //FillInTheForm
        ubbcluj.entersName(name + "\n");
        ubbcluj.should_contains_no_error_message();

        webdriver.close();
    }
}