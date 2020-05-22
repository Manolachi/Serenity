package net.serenity.bdd.steps.serenity;

import net.serenity.bdd.pages.UBBPage;
import net.thucydides.core.annotations.Step;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class EndUserSteps {

    private UBBPage ubbPage;

    @Step
    private void enters(String keyword) {
        ubbPage.enter_keywords(keyword);
    }

    @Step
    public void should_see_date_for_period_in_table(String date, String period) {
        assertThat(ubbPage.getTextFromTable(period), equalTo(date));
    }

    @Step
    public void is_the_home_page() {
        ubbPage.open();
    }

    @Step
    public void looks_for(String term) {
        enters(term);
    }

    public void presses_on(String link) {
        ubbPage.clickOnLink(link);
    }

    public void should_see_text_in_tag(String text, String tagName) {
        assertThat(ubbPage.getFullTextByTagName(text, tagName), containsString(text));
    }

    public void should_see_url(String actualURL, String expectedURL) {
        assertThat(actualURL, containsString(expectedURL));
    }

    public void entersName(String myName) {
        ubbPage.enterName(myName);
    }

    public void should_contains_error_message() {
        assertThat(ubbPage.tryFindElement(), equalTo(false));
    }

    public void should_contains_no_error_message() {
        assertThat(ubbPage.tryFindElement(), equalTo(false));
    }
}