package net.serenity.bdd.pages;

import net.thucydides.core.annotations.DefaultUrl;
import org.jsoup.nodes.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import net.serenitybdd.core.pages.WebElementFacade;

import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import org.openqa.selenium.Keys;

import net.serenitybdd.core.annotations.findby.FindBy;

import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

//@DefaultUrl("http://www.cs.ubbcluj.ro/invatamant/structura-anului-universitar/")
@DefaultUrl("http://www.cs.ubbcluj.ro/")
public class DictionaryPage extends PageObject {

    @FindBy(name = "s")
    private WebElementFacade searchTerms;

    @FindBy(name = "name")
    private WebElementFacade nameInput;

    public void enter_keywords(String keyword) {
        searchTerms.type(keyword);
    }

    public void lookup_terms() {
//        WebElement.sendKeys(Keys.ENTER);
    }

    public List<String> getDefinitions() {
        WebElementFacade definitionList = find(By.tagName("ol"));
        return definitionList.findElements(By.tagName("li")).stream()
                .map(element -> element.getText())
                .collect(Collectors.toList());
    }

    public void clickOnLink(String linkName) {
        WebElementFacade link = find(By.tagName("a"));
        WebElement linkElement = link.findElement(By.xpath("//*[text()[contains(.,'" + linkName + "')]]"));
        linkElement.click();
    }

    public String getLink(String text) {
        WebElementFacade link = find(By.tagName("a"));
        WebElement linkElement = link.findElement(By.xpath("//*[text()[contains(.,'" + text + "')]]"));
        return linkElement.getText();
    }

    public String getTextFromTable(String text) {
//        WebElementFacade element = find(By.tagName("table")).find(By.tagName("tr"));
//        WebElement a = element.findElement(By.xpath("//*[text()[contains(.,'" + text + "')]]"));
//        System.out.println(a.getText());


        //To locate rows of table.
        List<WebElement> rows_table = find(By.tagName("table")).findElements(By.tagName("tr"));

        //To calculate no of rows In table.
        int rows_count = rows_table.size();


        //Loop will execute for all the rows of the table
        for (int row = 0; row < rows_count; row++) {

            //To locate columns(cells) of that specific row.
            List<WebElement> Columns_row = rows_table.get(row).findElements(By.tagName("td"));

            //To calculate no of columns(cells) In that specific row.
            int columns_count = Columns_row.size();
            System.out.println("Number of cells In Row " + row + " are " + columns_count);

            //Loop will execute till the last cell of that specific row.
            for (int column = 0; column < columns_count; column++) {
                //To retrieve text from the cells.
                String celltext = Columns_row.get(column).getText();
                System.out.println("Cell Value Of row number " + row + " and column number " + column + " Is " + celltext);
                if (celltext.equals(text))
                    return Columns_row.get(column - 1).getText();
            }
        }
        return "";
    }

    public void enterName(String myName) {
        nameInput.type(myName);
    }

    public boolean tryFindElement(){
        WebElement linkElement = find(By.tagName("p")).findElement(By.xpath("//*[text()[contains(.,'numele')]]"));
        System.out.println("hey" + linkElement.getText() + "adewfefi");
        return linkElement.getText().equals("Completati numele");
    }

    public String getText(String text) {
        WebElementFacade link = find(By.tagName("h2"));
        WebElement linkElement = link.findElement(By.xpath("//*[text()[contains(.,'" + text + "')]]"));
        return linkElement.getText();
    }
}