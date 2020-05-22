package net.serenity.bdd.pages;

import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import net.serenitybdd.core.pages.WebElementFacade;

import java.util.stream.Collectors;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.thucydides.core.pages.PageObject;

import java.util.List;

@DefaultUrl("http://www.cs.ubbcluj.ro/")
public class UBBPage extends PageObject {

    @FindBy(name = "s")
    private WebElementFacade searchTerms;

    @FindBy(name = "name")
    private WebElementFacade nameInput;

    public void enter_keywords(String keyword) {
        searchTerms.type(keyword);
    }

    public void clickOnLink(String linkName) {
        WebElementFacade link = find(By.tagName("a"));
        WebElement linkElement = link.findElement(By.xpath("//*[text()[contains(.,'" + linkName + "')]]"));
        linkElement.click();
    }

    public String getFullTextByTagName(String text, String tagName) {
        WebElementFacade element = find(By.tagName(tagName));
        WebElement webElement = element.findElement(By.xpath("//*[text()[contains(.,'" + text + "')]]"));
        return webElement.getText();
    }

    public String getTextFromTable(String text) {
        List<WebElement> rows_table = find(By.tagName("table")).findElements(By.tagName("tr"));
        int rows_count = rows_table.size();

        for (int row = 0; row < rows_count; row++) {
            List<WebElement> Columns_row = rows_table.get(row).findElements(By.tagName("td"));
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
        return linkElement.getText().equals("Completati numele");
    }

    public String getText(String text) {
        WebElementFacade link = find(By.tagName("h2"));
        WebElement linkElement = link.findElement(By.xpath("//*[text()[contains(.,'" + text + "')]]"));
        return linkElement.getText();
    }
}