package net.serenity.bdd.features.function_testing;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import net.serenity.bdd.steps.serenity.EndUserSteps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import java.util.ArrayList;

//The Serenity test runner sets up the test and records the test results
@RunWith(SerenityRunner.class)
public class OpenNewTab {

    //This is a web test, and Serenity will manage the WebDriver driver for us
    @Managed(uniqueSession = true)
    private WebDriver webdriver;

    //We hide implementation details about how the test will be executed in a "step library"
    @Steps
    private EndUserSteps ubbcluj;

    @Test
    public void link_opens_another_tab_correctly() {
        ubbcluj.is_the_home_page();
        int currentIndexTab = new ArrayList<>(webdriver.getWindowHandles()).indexOf(webdriver.getWindowHandle());

        String articleName = "Îmbunătăţirea potenţialului de absorbţie pe piaţa muncii prin stagii de practică de calitate";
        String formName = "Formular de inscriere la concursul pe teme profesionale";
        String firstLinkName = "http://www.cs.ubbcluj.ro/POCU109172/";
        String secondLinkName = "http://www.cs.ubbcluj.ro/POCU109172/contest/PreRegister.php";

        ubbcluj.presses_on(articleName);
        webdriver.close();
        webdriver.switchTo().window(new ArrayList<>(webdriver.getWindowHandles()).get(currentIndexTab));
        String currentURL = webdriver.getCurrentUrl();
        ubbcluj.should_see_url(currentURL, firstLinkName);

        ubbcluj.presses_on(formName);
        webdriver.close();
        webdriver.switchTo().window(new ArrayList<>(webdriver.getWindowHandles()).get(currentIndexTab));
        currentURL = webdriver.getCurrentUrl();
        ubbcluj.should_see_url(currentURL, secondLinkName);

        webdriver.close();
    }
}