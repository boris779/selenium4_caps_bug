package at.co.boris.pageobjects;

import at.co.boris.webdriversession.WebDriverSession;
import org.openqa.selenium.By;

public class WikipediaContentPage extends MainPage{

    public WikipediaContentPage(WebDriverSession session) {
        super(session);
    }

    public String getHeader() {
        return getWebDriver().findElement(By.id("firstHeading")).getText();
    }
}
