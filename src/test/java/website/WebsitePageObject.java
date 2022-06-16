package website;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WebsitePageObject {

    private WebDriver driver;

    public WebsitePageObject(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "id-search-field")
    private WebElement searchField;

    public WebsitePageObject go() {
        driver.get("https://www.python.org");
        return this;
    }

    public WebsitePageObject clickOnSubmit() {
        driver.findElement(By.id("submit")).click();
        return this;
    }

    public WebsitePageObject fillSearchField(String fieldStr) {
        searchField.click();
        driver.findElement(By.id("id-search-field")).sendKeys(fieldStr);
        return this;
    }

    public WebsitePageObject clickPSFMenu() {
        driver.findElement(By.linkText("PSF")).click();
        return this;
    }

    public String getResultForSearch() {
        return driver.findElement(By.cssSelector("h3:nth-child(2)")).getText();
    }

    public String getResultForFindPage() {
        return driver.getTitle();
    }


}
