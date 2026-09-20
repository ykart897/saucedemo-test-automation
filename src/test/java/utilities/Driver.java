package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public final class Driver {

    private static WebDriver driver;

    private Driver() {
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            String browser = getBrowserName();

            if ("firefox".equals(browser)) {
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions options = new FirefoxOptions();
                String firefoxBinary = System.getProperty("firefox.binary", "");
                if (!firefoxBinary.isBlank()) {
                    options.setBinary(firefoxBinary);
                }
                if (isHeadlessRun()) {
                    options.addArguments("--headless");
                    options.addArguments("--width=1920");
                    options.addArguments("--height=1080");
                }
                driver = new FirefoxDriver(options);
            } else if ("chrome".equals(browser)) {
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                if (isHeadlessRun()) {
                    options.addArguments("--headless=new");
                    options.addArguments("--no-sandbox");
                    options.addArguments("--disable-dev-shm-usage");
                    options.addArguments("--window-size=1920,1080");
                }
                driver = new ChromeDriver(options);
            } else {
                throw new IllegalArgumentException("Unsupported browser: " + browser);
            }

            if (!isHeadlessRun()) {
                driver.manage().window().maximize();
            }
        }
        return driver;
    }

    public static void closeDriver() {
        WebDriver activeDriver = driver;
        driver = null;
        if (activeDriver != null) {
            activeDriver.quit();
        }
    }

    public static boolean hasDriver() {
        return driver != null;
    }

    private static boolean isHeadlessRun() {
        return Boolean.parseBoolean(System.getProperty("headless", "false"))
                || System.getenv("CI") != null;
    }

    private static String getBrowserName() {
        return System.getProperty("browser",
                ConfigReader.getProperty("browser") == null ? "chrome" : ConfigReader.getRequiredProperty("browser"))
                .trim()
                .toLowerCase();
    }
}