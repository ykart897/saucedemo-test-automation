package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utilities.Driver;

public class Hooks {

    @After
    public void tearDown(Scenario scenario) {
        try {
            if (scenario.isFailed() && Driver.hasDriver()) {
                try {
                    byte[] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
                    scenario.attach(screenshot, "image/png", scenario.getName());
                } catch (RuntimeException screenshotError) {
                    scenario.log("Screenshot could not be captured: " + screenshotError.getMessage());
                }
            }
        } finally {
            Driver.closeDriver();
        }
    }
}