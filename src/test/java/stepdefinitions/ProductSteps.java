package stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.ProductsPage;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ProductSteps {

    private final ProductsPage productsPage = new ProductsPage();
    private List<String> productNamesBeforeSort;

    @Then("Urun listesi goruntulenmelidir")
    public void urunListesiGoruntulenmelidir() {
        Assert.assertTrue(productsPage.getProductCount() > 0);
    }

    @Then("Urun listesinde en az {int} urun bulunmalidir")
    public void urunListesindeEnAzUrunBulunmalidir(int minimumCount) {
        Assert.assertTrue(productsPage.getProductCount() >= minimumCount);
    }

    @When("Kullanici urunleri {string} secenegine gore siralar")
    public void kullaniciUrunleriSecenegineGoreSiralar(String sortOption) {
        productNamesBeforeSort = productsPage.getProductNames();
        productsPage.sortBy(sortOption);
    }

    @Then("Urun fiyatlari dusukten yuksege dogru siralanmalidir")
    public void urunFiyatlariDusuktenYuksegeDogruSiralanmalidir() {
        List<Double> actualPrices = productsPage.getPrices();
        Assert.assertTrue(actualPrices.size() >= 2, "Sorting requires at least two visible products.");
        assertProductsPreservedAfterSort();
        List<Double> expectedPrices = new ArrayList<>(actualPrices);
        expectedPrices.sort(Comparator.naturalOrder());
        Assert.assertEquals(actualPrices, expectedPrices);
    }

    @Then("Urun fiyatlari yuksekten dusuge dogru siralanmalidir")
    public void urunFiyatlariYuksektenDusugeDogruSiralanmalidir() {
        List<Double> actualPrices = productsPage.getPrices();
        Assert.assertTrue(actualPrices.size() >= 2, "Sorting requires at least two visible products.");
        assertProductsPreservedAfterSort();
        List<Double> expectedPrices = new ArrayList<>(actualPrices);
        expectedPrices.sort(Comparator.reverseOrder());
        Assert.assertEquals(actualPrices, expectedPrices);
    }

    @Then("Urun isimleri A'dan Z'ye dogru siralanmalidir")
    public void urunIsimleriADanZYeDogruSiralanmalidir() {
        List<String> actualNames = productsPage.getProductNames();
        Assert.assertTrue(actualNames.size() >= 2, "Sorting requires at least two visible products.");
        assertProductsPreservedAfterSort();
        List<String> expectedNames = new ArrayList<>(actualNames);
        expectedNames.sort(Comparator.naturalOrder());
        Assert.assertEquals(actualNames, expectedNames);
    }

    private void assertProductsPreservedAfterSort() {
        Assert.assertNotNull(productNamesBeforeSort, "Product inventory must be captured before sorting.");
        List<String> productsBeforeSort = new ArrayList<>(productNamesBeforeSort);
        List<String> productsAfterSort = new ArrayList<>(productsPage.getProductNames());
        productsBeforeSort.sort(Comparator.naturalOrder());
        productsAfterSort.sort(Comparator.naturalOrder());
        Assert.assertEquals(productsAfterSort, productsBeforeSort,
                "Sorting must preserve every product and the product count.");
    }
}
