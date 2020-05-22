package net.serenity.bdd.features.function_testing;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import net.serenity.bdd.steps.serenity.EndUserSteps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

//The Serenity test runner sets up the test and records the test results
@RunWith(SerenityRunner.class)
public class SearchByKeyword {

    //This is a web test, and Serenity will manage the WebDriver driver for us
    @Managed(uniqueSession = true)
    private WebDriver webdriver;

    //We hide implementation details about how the test will be executed in a "step library"
    @Steps
    private EndUserSteps ubbcluj;

    @Test
    public void searching_by_keyword_orar_should_display_the_corresponding_article() {
        ubbcluj.is_the_home_page();

        String text = "Orar semestrul II\n";
        String fullText = "Orar semestrul II, an universitar 2019-2020";
        String tagName = "a";

        ubbcluj.looks_for(text);
        ubbcluj.should_see_text_in_tag(fullText,tagName);
        webdriver.close();
    }
}