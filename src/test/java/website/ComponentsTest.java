package website;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@Slf4j
@SeleniumTest
@ExtendWith(LoggingExtension.class)
public class ComponentsTest {

    @Test
    void testListTexts(WebDriver driver){
        driver.get("http://127.0.0.1:5500/components/index.html");
        var elements = driver.findElements(By.tagName("li"));
        /*var texts = new ArrayList<>();

        for(var item:elements){
            texts.add(item.getText());
        }
        log.debug("List items: " + texts);

        assertEquals(List.of("One","Two","Three","Four"),texts);
        */

        // assertEquals(List.of("One","Two","Three","Four"),elements.stream().map(WebElement::getText).toList());

        assertThat(elements)
                .extracting(WebElement::getText)
                .hasSize(4)
                .contains("One","Three");
    }

    @Test
    void testTableTexts(WebDriver driver){
        driver.get("http://127.0.0.1:5500/components/index.html");
        var elements = driver.findElements(By.cssSelector("td:nth-child(2)"));

        assertThat(elements)
                .extracting(WebElement::getText)
                .hasSize(3)
                .contains("Joe","Jane","Jack");
    }

    @Test
    void testInputField(WebDriver driver) throws InterruptedException {
        driver.get("http://127.0.0.1:5500/components/index.html");
        var inputField = driver.findElement(By.name("text"));
        inputField.sendKeys("1234");
        log.debug(inputField.getDomProperty("value"));
    }

    @Test
    void testCheckDisabled(WebDriver driver) throws InterruptedException {
        driver.get("http://127.0.0.1:5500/components/index.html");
        var checkBox = driver.findElement(By.name("disabled-checkbox"));
        assertFalse(checkBox.isEnabled());
    }

    @Test
    void testSelect(WebDriver driver) throws InterruptedException {
        driver.get("http://127.0.0.1:5500/components/index.html");
        var select = new Select(driver.findElement(By.id("dropdown")));
        select.getOptions().forEach(e -> e.getText());
        select.selectByIndex(2);

        assertEquals("Option 3", select.getFirstSelectedOption().getText());
    }

}
