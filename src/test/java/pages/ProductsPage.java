package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class ProductsPage extends BasePage {

    @FindBy(className = "title")
    private WebElement pageTitle;

    @FindBy(className = "shopping_cart_link")
    private WebElement cartIcon;

    @FindBy(css = "[data-test='product-sort-container']")
    private WebElement sortDropdown;

    private final By cartBadge = By.className("shopping_cart_badge");
    private final By productItems = By.className("inventory_item");
    private final By productNames = By.className("inventory_item_name");
    private final By productPrices = By.className("inventory_item_price");

    public String getPageTitle() {
        return getText(pageTitle);
    }

    public int getProductCount() {
        return waitForAllVisible(productItems).size();
    }

    public void sortBy(String visibleText) {
        Select select = new Select(wait.until(ExpectedConditions.elementToBeClickable(sortDropdown)));
        select.selectByVisibleText(visibleText);
        wait.until(driver -> visibleText.equals(new Select(sortDropdown).getFirstSelectedOption().getText()));
    }

    public List<Double> getPrices() {
        return waitForAllVisible(productPrices).stream()
                .map(WebElement::getText)
                .map(price -> price.replace("$", ""))
                .map(Double::parseDouble)
                .toList();
    }

    public List<String> getProductNames() {
        return waitForAllVisible(productNames).stream().map(WebElement::getText).toList();
    }

    public void addProductToCart(String productName) {
        click(productActionButton(productName, "Add to cart"));
    }

    public void clickCartIcon() {
        click(cartIcon);
    }

    public String getCartBadgeText() {
        return findAll(cartBadge).isEmpty() ? "0" : findAll(cartBadge).get(0).getText();
    }

    private WebElement productActionButton(String productName, String buttonText) {
        String xpath = "//div[@class='inventory_item'][.//div[text()='" + productName + "']]//button[text()='" + buttonText + "']";
        return driver.findElement(By.xpath(xpath));
    }
}