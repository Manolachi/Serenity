package net.serenity.bdd.steps.serenity;

import net.serenity.bdd.pages.DictionaryPage;
import net.thucydides.core.annotations.Step;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class EndUserSteps {

    DictionaryPage dictionaryPage;

    @Step
    public void enters(String keyword) {
        dictionaryPage.enter_keywords(keyword);
    }

    @Step
    public void starts_search() {
        dictionaryPage.lookup_terms();
    }

    @Step
    public void should_see_text(String definition, String expectedText) {
        assertThat(dictionaryPage.getTextFromTable(definition), equalTo(expectedText));
    }

    @Step
    public void is_the_home_page() {
        dictionaryPage.open();
    }

    @Step
    public void looks_for(String term) {
        enters(term);
    }

    public void presses_on(String link) {
        dictionaryPage.clickOnLink(link);
    }

    public void should_see_link(String text) {
        dictionaryPage.getLink(text);
    }

    public void entersName(String myName) {
        dictionaryPage.enterName(myName);
    }

    public void containsErrorMessage() {
        assertThat(dictionaryPage.tryFindElement(), equalTo(true));
    }

    public void containsNoErrorMessage() {
        assertThat(dictionaryPage.tryFindElement(), equalTo(false));
    }

    public void should_see_text(String text) {
        dictionaryPage.getText(text);

    }
}