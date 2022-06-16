package website;

import dev.failsafe.internal.util.Assert;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.build.ToStringPlugin;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Teszt osztály
@Slf4j
@ExtendWith(LoggingExtension.class)
@SeleniumTest
class WebsiteTest {

    //private static final Logger log = LoggerFactory.getLogger(WebsiteTest.class);

    //Teszteset//
    @Test
    void testSearch(WebDriver driver){
        //Given
        driver.get("https://www.python.org");
        //When
        driver.findElement(By.id("id-search-field")).click();
        driver.findElement(By.id("id-search-field")).sendKeys("testing");
        driver.findElement(By.id("submit")).click();
        log.debug("Click on Go button");
        //Then
        String result=driver.findElement(By.cssSelector("h3:nth-child(2)")).getText();
        assertEquals("Results",result);
    }

    @Test
    void testPsf(WebDriver driver){
        driver.get("https://www.python.org");
        driver.findElement(By.linkText("PSF")).click();
        log.debug("Click on PSF menu item");
        assertEquals("Python Software Foundation", driver.getTitle());

    }

    @Test
    void testBootstrap(WebDriver driver){
        driver.get("http://127.0.0.1:5500/bootstrap-demo.html");
        String inputStr="Valami";
        driver.findElement(By.cssSelector("#name")).sendKeys(inputStr);
        driver.findElement(By.cssSelector("#submit_btn")).click();
        String resultValStr = driver.findElement(By.cssSelector("#message_div")).getText();
        assertEquals(String.format("Hello %s!",inputStr),resultValStr);
    }

    @Test
    @DisplayName("Fancy test")
    void testSetBorder(WebDriver driver){
        driver.get("http://127.0.0.1:5500/bootstrap-demo.html");
        var input = driver.findElement(By.id("field-to-calibrate"));
        String value= input.getText();
        if (Integer.parseInt(value)!=100){
            WebElement webElement = (WebElement) ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].style['border']= '3px solid red'", input);
            System.out.println("End");
        }
    }

    @Test
    void testScreenshot(WebDriver driver) throws IOException {
        driver.get("http://127.0.0.1:5500/bootstrap-demo.html");
        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        Files.move(file.toPath(), Path.of("./screenshotBytestScreenshot.png"), StandardCopyOption.REPLACE_EXISTING);

        file = ((TakesScreenshot)driver.findElement(By.cssSelector(".alert"))).getScreenshotAs(OutputType.FILE);
        Files.move(file.toPath(), Path.of("./screenshotBytestScreenshotOfAlert.png"), StandardCopyOption.REPLACE_EXISTING);

    }

    WebsitePageObject page;
    @BeforeEach
    void initPage(WebDriver driver){
        page= new WebsitePageObject(driver);
    }

    @Test
    void testSearchByPageObject(){
        page.go()
                .fillSearchField("testing")
                .clickOnSubmit();
        assertEquals("Results",page.getResultForSearch());

    }

    @Test
    void testnavigateToPSF(){
        page.go().
                clickPSFMenu();
        assertEquals("Python Software Foundation",page.getResultForFindPage());

    }



}
