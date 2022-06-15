package website;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
@SeleniumTest
class TrainingTest {

    @RepeatedTest(15)
    void testTraining(WebDriver driver){
        driver.get("https://www.training360.com/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        var modalDialogDiv = driver.findElement(By.id("NewsletterModal"));
        wait.until(ExpectedConditions.visibilityOf(modalDialogDiv));
        log.debug("Modal has appeared.");
        assertTrue(modalDialogDiv.isDisplayed());

        var xButton = modalDialogDiv.findElement(By.id("NewsletterModalCloseButton"));

        xButton.click();

        wait.until(ExpectedConditions.invisibilityOf(modalDialogDiv));
        assertFalse(modalDialogDiv.isDisplayed());

    }
}
