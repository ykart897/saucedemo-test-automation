package stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.CartPage;
import pages.ProductsPage;

public class CartSteps {

    private final ProductsPage productsPage = new ProductsPage();
    private final CartPage cartPage = new CartPage();

    @When("Kullanici {string} urununu sepete ekler")
    public void kullaniciUrununuSepeteEkler(String productName) {
        productsPage.addProductToCart(productName);
    }

    @Then("Sepet ikonunda urun sayisi {string} olarak goruntulenmelidir")
    public void sepetIkonundaUrunSayisiOlarakGoruntulenmelidir(String expectedCount) {
        Assert.assertEquals(productsPage.getCartBadgeText(), expectedCount);
    }

    @When("Kullanici sepet ikonuna tiklar")
    public void kullaniciSepetIkonunaTiklar() {
        productsPage.clickCartIcon();
    }

    @Then("Sepet sayfasi goruntulenmelidir")
    public void sepetSayfasiGoruntulenmelidir() {
        Assert.assertEquals(cartPage.getPageTitle(), "Your Cart");
    }

    @Then("Sepette {string} urunu goruntulenmelidir")
    public void sepetteUrunuGoruntulenmelidir(String productName) {
        Assert.assertTrue(cartPage.isProductDisplayed(productName));
    }

    @When("Kullanici {string} urununu sepetten cikarir")
    public void kullaniciUrununuSepettenCikarir(String productName) {
        cartPage.removeProduct(productName);
    }

    @Then("Sepette {string} urunu goruntulenmemelidir")
    public void sepetteUrunuGoruntulenmemelidir(String productName) {
        Assert.assertFalse(cartPage.isProductDisplayed(productName));
    }
}
