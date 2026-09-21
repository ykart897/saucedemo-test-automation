package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.ConfigReader;
import utilities.Driver;

import java.time.Duration;
import java.util.List;

public class BasePage {

    protected final WebDriver driver;
    protected final WebDriverWait wait;

    public BasePage() {
        driver = Driver.getDriver();
        long timeout;
        try {
            timeout = Long.parseLong(ConfigReader.getRequiredProperty("timeout"));
        } catch (NumberFormatException e) {
            throw new IllegalStateException("Configuration property 'timeout' must be a whole number.", e);
        }
        wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        PageFactory.initElements(driver, this);
    }

    protected void click(WebElement element) {
        WebElement clickableElement = wait.until(ExpectedConditions.elementToBeClickable(element));
        if (Boolean.getBoolean("dom.click.fallback")) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", clickableElement);
            return;
        }
        if ("a".equalsIgnoreCase(clickableElement.getTagName())) {
            clickableElement.click();
            return;
        }
        clickableElement.sendKeys(Keys.ENTER);
    }

    protected void type(WebElement element, String text) {
        wait.until(ExpectedConditions.visibilityOf(element));
        element.clear();
        element.sendKeys(text);
    }

    protected void setInputValue(WebElement element, String text) {
        WebElement visibleElement = wait.until(ExpectedConditions.visibilityOf(element));
        ((JavascriptExecutor) driver).executeScript(
                "const element = arguments[0];"
                        + "const value = arguments[1];"
                        + "const setter = Object.getOwnPropertyDescriptor(HTMLInputElement.prototype, 'value').set;"
                        + "setter.call(element, value);"
                        + "element.dispatchEvent(new Event('input', {bubbles: true}));"
                        + "element.dispatchEvent(new Event('change', {bubbles: true}));",
                visibleElement,
                text);
        wait.until(driver -> text.equals(element.getDomProperty("value")));
    }
    protected String getText(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element)).getText();
    }

    protected List<WebElement> findAll(By locator) {
        return driver.findElements(locator);
    }

    protected List<WebElement> waitForAllVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    protected void waitUntilAbsent(By locator) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }
}
