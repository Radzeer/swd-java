package website;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
@SeleniumTest
class MessagesTest {

    @Test
    void messageTest(WebDriver driver){
        driver.get("http://127.0.0.1:5500/messages/index.html");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        var btnAlert = driver.findElement(By.id("liveAlertTimeoutBtn"));
        btnAlert.click();
        var appearedElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".alert-success")));

        assertTrue(appearedElement != null);


    }
}
