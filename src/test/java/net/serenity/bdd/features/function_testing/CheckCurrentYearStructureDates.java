package net.serenity.bdd.features.function_testing;

import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import net.serenity.bdd.steps.serenity.EndUserSteps;
import net.thucydides.junit.annotations.UseTestDataFrom;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

//The Serenity test runner sets up the test and records the test results
@RunWith(SerenityParameterizedRunner.class)
@UseTestDataFrom(value = "src/test/resources/features/CheckCurrentYearStructureDatesData.csv")
public class CheckCurrentYearStructureDates {

    //This is a web test, and Serenity will manage the WebDriver driver for us
    @Managed(uniqueSession = true)
    private WebDriver webdriver;

    //We hide implementation details about how the test will be executed in a "step library"
    @Steps
    private EndUserSteps ubbcluj;

    private String keyword;
    private String articleName;
    private String date;
    private String period;

    @Test
    public void current_year_structure_contains_correct_date() {
        ubbcluj.is_the_home_page();
        ubbcluj.looks_for(keyword + "\n");
        ubbcluj.presses_on(articleName);
        ubbcluj.should_see_date_for_period_in_table(date, period);
        webdriver.close();
    }
}