package net.serenity.bdd.features.search;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Issue;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import net.serenity.bdd.steps.serenity.EndUserSteps;

import java.util.ArrayList;

@RunWith(SerenityRunner.class)
public class SearchByKeywordStory {

    @Managed(uniqueSession = true)
    public WebDriver webdriver;

    @Steps
    public EndUserSteps ubbcluj;

    @Issue("#WIKI-1")
    @Test
    public void curent_year_structure_contains_correct_date() {
        ubbcluj.is_the_home_page();
        ubbcluj.looks_for("structura\n");
        ubbcluj.presses_on("Structura anului universitar 2019-2020");
        ubbcluj.should_see_text("sesiune de examene", "20.01.2020 – 09.02.2020");
    }

    @Test
    public void searching_by_keyword_orar_should_display_the_corresponding_article() {
        ubbcluj.is_the_home_page();
        ubbcluj.looks_for("Orar semestrul II\n");
        ubbcluj.should_see_link("Orar semestrul II, an universitar 2019-2020");
    }

    @Test
    public void void_input_form_returns_error_message() {
        ubbcluj.is_the_home_page();
        ubbcluj.presses_on("Îmbunătăţirea potenţialului de absorbţie pe piaţa muncii prin stagii de practică de calitate");

        ArrayList<String> tabs1 = new ArrayList<>(webdriver.getWindowHandles());
        webdriver.switchTo().window(tabs1.get(1));

        ubbcluj.presses_on("Formular de inscriere la concursul pe teme profesionale");

        ArrayList<String> tabs2 = new ArrayList<>(webdriver.getWindowHandles());
        webdriver.switchTo().window(tabs2.get(2));

        ubbcluj.entersName("\n");

        ubbcluj.containsNoErrorMessage();
    }

    @Test
    public void valid_input_form_returns_no_error_message() {
        ubbcluj.is_the_home_page();
        ubbcluj.presses_on("Îmbunătăţirea potenţialului de absorbţie pe piaţa muncii prin stagii de practică de calitate");

        ArrayList<String> tabs1 = new ArrayList<>(webdriver.getWindowHandles());
        webdriver.switchTo().window(tabs1.get(1));

        ubbcluj.presses_on("Formular de inscriere la concursul pe teme profesionale");

        ArrayList<String> tabs2 = new ArrayList<>(webdriver.getWindowHandles());
        webdriver.switchTo().window(tabs2.get(2));

        ubbcluj.entersName("Panda\n");
        ubbcluj.containsNoErrorMessage();
    }

    @Test
    public void link_opens_another_tab_correctly() {
        ubbcluj.is_the_home_page();
        ubbcluj.presses_on("Îmbunătăţirea potenţialului de absorbţie pe piaţa muncii prin stagii de practică de calitate");

        ArrayList<String> tabs1 = new ArrayList<>(webdriver.getWindowHandles());
        webdriver.switchTo().window(tabs1.get(1));

        ubbcluj.presses_on("Formular de inscriere la concursul pe teme profesionale");

        ArrayList<String> tabs2 = new ArrayList<>(webdriver.getWindowHandles());
        webdriver.switchTo().window(tabs2.get(2));

        ubbcluj.should_see_text("Formular de inscriere la concursul pe teme profesionale");
    }
}