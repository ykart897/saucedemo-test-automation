Feature: Sepet Islemleri

  Background:
    Given Kullanici SauceDemo giris sayfasindadir
    When Kullanici kullanici adi "standard_user" ve sifre "secret_sauce" girer
    And Kullanici login butonuna tiklar
    Then Kullanici urunler sayfasina yonlendirilmelidir

  @smoke @cart
  Scenario: Tek urunun sepete eklenmesi
    When Kullanici "Sauce Labs Backpack" urununu sepete ekler
    Then Sepet ikonunda urun sayisi "1" olarak goruntulenmelidir

  @cart
  Scenario: Sepete eklenen urunun sepet sayfasinda goruntulenmesi
    When Kullanici "Sauce Labs Backpack" urununu sepete ekler
    And Kullanici sepet ikonuna tiklar
    Then Sepet sayfasi goruntulenmelidir
    And Sepette "Sauce Labs Backpack" urunu goruntulenmelidir

  @cart
  Scenario: Sepete eklenen urunun sepetten cikarilmasi
    When Kullanici "Sauce Labs Backpack" urununu sepete ekler
    And Kullanici sepet ikonuna tiklar
    When Kullanici "Sauce Labs Backpack" urununu sepetten cikarir
    Then Sepette "Sauce Labs Backpack" urunu goruntulenmemelidir
