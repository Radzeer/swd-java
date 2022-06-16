package website;

import lombok.AllArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class LocationsPageObject {

    private WebDriver driver;

    private WebDriverWait wait;

    @FindBy(id = "location-form")
    private WebElement locationForm;

    @FindBy(linkText = "Create location")
    private WebElement createLocationLink;

    @FindBy(css = "p > [type='submit'].btn")
    private WebElement createLocationButton;
    @FindBy(id = "location-name")
    private WebElement nameInput;

    @FindBy(id = "location-coords")
    private WebElement coordsInput;

    @FindBy(id="message-div")
    private WebElement messageDiv;

    public LocationsPageObject go(){
        driver.get("http://localhost:8080");
        return this;
    }

    public LocationsPageObject(WebDriver driver) {
        this.driver = driver;
        this.wait= new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver,this);
    }

    public LocationsPageObject clickOnCreateLocationLink(){
        createLocationLink.click();
        return this;
    }

    public LocationsPageObject fillForm(String name, String coords){
        nameInput.sendKeys(name);
        coordsInput.sendKeys(coords);
        return this;
    }

    public LocationsPageObject clickOnCreateLocationButton(){
        createLocationButton.click();
        return this;
    }

    public String waitForMessageAndGetText(){
        var message = wait.until(ExpectedConditions.visibilityOf(messageDiv));
        return message.getText();
    }
}
