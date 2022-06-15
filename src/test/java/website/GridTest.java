package website;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.openqa.selenium.support.locators.RelativeLocator.with;

@Slf4j
@ExtendWith(LoggingExtension.class)
@SeleniumTest
public class GridTest {

    @Test
    void testRelativeSelection(WebDriver driver){
        driver.get("http://127.0.0.1:5500/grid/index.html");
        var cell5 = driver.findElement(By.cssSelector("tr:nth-child(2) > td:nth-child(2)"));
        System.out.println(cell5.getText());
        var cell2 = driver.findElement(with(By.tagName("td")).below(cell5));
        assertEquals("2",cell2.getText());

    }
}
