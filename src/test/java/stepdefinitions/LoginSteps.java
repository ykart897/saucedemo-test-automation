package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.LoginPage;
import pages.ProductsPage;
import utilities.ConfigReader;
import utilities.Driver;

public class LoginSteps {

    private final LoginPage loginPage = new LoginPage();
    private final ProductsPage productsPage = new ProductsPage();

    @Given("Kullanici SauceDemo giris sayfasindadir")
    public void kullaniciSauceDemoGirisSayfasindadir() {
        Driver.getDriver().get(ConfigReader.getRequiredProperty("baseUrl"));
    }

    @When("Kullanici kullanici adi {string} ve sifre {string} girer")
    public void kullaniciKullaniciAdiVeSifreGirer(String username, String password) {
        loginPage.enterCredentials(username, password);
    }

    @When("Kullanici login butonuna tiklar")
    public void kullaniciLoginButonunaTiklar() {
        loginPage.clickLogin();
    }

    @Then("Kullanici urunler sayfasina yonlendirilmelidir")
    public void kullaniciUrunlerSayfasinaYonlendirilmelidir() {
        Assert.assertEquals(productsPage.getPageTitle(), "Products");
    }

    @Then("Sayfa basligi {string} olarak goruntulenmelidir")
    public void sayfaBasligiOlarakGoruntulenmelidir(String expectedTitle) {
        Assert.assertEquals(productsPage.getPageTitle(), expectedTitle);
    }

    @Then("Kullanici hata mesaji gormelidir")
    public void kullaniciHataMesajiGormelidir() {
        Assert.assertFalse(loginPage.getErrorMessage().isBlank());
    }

    @Then("Hata mesaji {string} bilgisini icermelidir")
    public void hataMesajiBilgisiniIcermelidir(String expectedMessage) {
        Assert.assertTrue(loginPage.getErrorMessage().contains(expectedMessage));
    }
}
