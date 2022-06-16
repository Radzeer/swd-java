package website;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@SeleniumTest
class LocationTest {
    String url="http://localhost:8080/";

    @Test
    void testCreate(WebDriver driver){
        driver.get(url);
        String addedName = UUID.randomUUID().toString();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement locationForm =driver.findElement(By.id("location-form"));
        driver.findElement(By.id("create-location-link")).click();
        locationForm.findElement(By.id("location-name")).sendKeys(addedName);
        locationForm.findElement(By.id("location-coords")).sendKeys("0,0");
        locationForm.findElement(By.cssSelector("p > [type='submit'].btn")).click();

        var message = driver.findElement(By.id("message-div"));
        wait.until(ExpectedConditions.visibilityOf(message));
        assertEquals("Location has been created",message.getText());

        var rows = driver.findElements(By.cssSelector("#locations-table > tbody > tr"));

        assertEquals(1, rows
                .stream()
                .filter(r -> r.findElement(By.cssSelector("td:nth-child(2)")).getText().equals(addedName)).count());
    }
    LocationsPageObject page;

    @BeforeEach
    void initPage(WebDriver driver){
        page= new LocationsPageObject(driver);
    }
    @Test
    void testCreateWithPageObject(){
        var name =UUID.randomUUID().toString();
        page
                .go()
                .clickOnCreateLocationLink()
                .fillForm(name,"0,0")
                .clickOnCreateLocationButton();
        assertEquals("Location has been created", page.waitForMessageAndGetText());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/MOCK_DATA.csv",numLinesToSkip = 1)
    void testCreateLocationDDt(String name, String lat, String lon){
        log.debug("Location:{} ({},{})", name, lat, lon);
        page
                .go()
                .clickOnCreateLocationLink()
                .fillForm(name, lat + "," + lon)
                .clickOnCreateLocationButton();
    }

}
