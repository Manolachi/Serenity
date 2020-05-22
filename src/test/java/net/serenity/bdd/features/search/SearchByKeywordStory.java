package net.serenity.bdd.features.search;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import net.serenity.bdd.steps.serenity.EndUserSteps;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;

@RunWith(SerenityRunner.class)
public class SearchByKeywordStory {

    @Managed(uniqueSession = true)
    private WebDriver webdriver;

    @Steps
    private EndUserSteps ubbcluj;

    @Test
    public void curent_year_structure_contains_correct_date() {
        ubbcluj.is_the_home_page();
        ubbcluj.looks_for("structura\n");
        ubbcluj.presses_on("Structura anului universitar 2019-2020");
        ubbcluj.should_see_text("sesiune de examene", "20.01.2020 – 09.02.2020");
        webdriver.close();
    }

    @Test
    public void searching_by_keyword_orar_should_display_the_corresponding_article() {
        ubbcluj.is_the_home_page();
        ubbcluj.looks_for("Orar semestrul II\n");
        ubbcluj.should_see_link("Orar semestrul II, an universitar 2019-2020");
        webdriver.close();
    }

    @Test
    public void void_input_form_returns_error_message() {
        ubbcluj.is_the_home_page();
        int currentIndexTab = new ArrayList<>(webdriver.getWindowHandles()).indexOf(webdriver.getWindowHandle());

        ubbcluj.presses_on("Îmbunătăţirea potenţialului de absorbţie pe piaţa muncii prin stagii de practică de calitate");
        webdriver.close();
        webdriver.switchTo().window(new ArrayList<>(webdriver.getWindowHandles()).get(currentIndexTab));

        ubbcluj.presses_on("Formular de inscriere la concursul pe teme profesionale");
        webdriver.close();
        webdriver.switchTo().window(new ArrayList<>(webdriver.getWindowHandles()).get(currentIndexTab));

        ubbcluj.entersName("\n");
        ubbcluj.containsNoErrorMessage();
        webdriver.close();
    }

    @Test
    public void valid_input_form_returns_no_error_message() {
        ubbcluj.is_the_home_page();
        int currentIndexTab = new ArrayList<>(webdriver.getWindowHandles()).indexOf(webdriver.getWindowHandle());

        ubbcluj.presses_on("Îmbunătăţirea potenţialului de absorbţie pe piaţa muncii prin stagii de practică de calitate");
        webdriver.close();
        webdriver.switchTo().window(new ArrayList<>(webdriver.getWindowHandles()).get(currentIndexTab));

        ubbcluj.presses_on("Formular de inscriere la concursul pe teme profesionale");
        webdriver.close();
        webdriver.switchTo().window(new ArrayList<>(webdriver.getWindowHandles()).get(currentIndexTab));

        ubbcluj.entersName("Panda\n");
        ubbcluj.containsNoErrorMessage();
        webdriver.close();
    }

    @Test
    public void link_opens_another_tab_correctly() {
        ubbcluj.is_the_home_page();
        int currentIndexTab = new ArrayList<>(webdriver.getWindowHandles()).indexOf(webdriver.getWindowHandle());

        ubbcluj.presses_on("Îmbunătăţirea potenţialului de absorbţie pe piaţa muncii prin stagii de practică de calitate");
        webdriver.close();
        webdriver.switchTo().window(new ArrayList<>(webdriver.getWindowHandles()).get(currentIndexTab));

        ubbcluj.presses_on("Formular de inscriere la concursul pe teme profesionale");
        webdriver.close();
        webdriver.switchTo().window(new ArrayList<>(webdriver.getWindowHandles()).get(currentIndexTab));

        ubbcluj.should_see_text("Formular de inscriere la concursul pe teme profesionale");
        webdriver.close();
    }
}