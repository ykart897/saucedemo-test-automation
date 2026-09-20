Feature: Checkout ve Siparis Tamamlama

  Background:
    Given Kullanici SauceDemo giris sayfasindadir
    When Kullanici kullanici adi "standard_user" ve sifre "secret_sauce" girer
    And Kullanici login butonuna tiklar
    Then Kullanici urunler sayfasina yonlendirilmelidir

  @smoke @checkout
  Scenario: Kullanicinin siparisi basariyla tamamlamasi
    When Kullanici "Sauce Labs Backpack" urununu sepete ekler
    And Kullanici sepet ikonuna tiklar
    And Kullanici checkout butonuna tiklar
    And Kullanici checkout bilgilerini ad "Ahmet", soyad "Yilmaz", posta kodu "34000" olarak girer
    And Kullanici continue butonuna tiklar
    Then Checkout overview sayfasi goruntulenmelidir
    And Siparis ozetinde "Sauce Labs Backpack" urunu goruntulenmelidir
    When Kullanici finish butonuna tiklar
    Then Siparis basari mesaji goruntulenmelidir
    And Basari mesaji "Thank you for your order!" olmalidir

  @checkout @negative
  Scenario: Checkout bilgilerinde ad alani bos birakildiginda hata alinmasi
    When Kullanici "Sauce Labs Backpack" urununu sepete ekler
    And Kullanici sepet ikonuna tiklar
    And Kullanici checkout butonuna tiklar
    And Kullanici checkout bilgilerini ad "", soyad "Yilmaz", posta kodu "34000" olarak girer
    And Kullanici continue butonuna tiklar
    Then Checkout hata mesaji goruntulenmelidir
    And Checkout hata mesaji "First Name is required" bilgisini icermelidir

  @checkout @negative
  Scenario: Checkout bilgilerinde posta kodu bos birakildiginda hata alinmasi
    When Kullanici "Sauce Labs Backpack" urununu sepete ekler
    And Kullanici sepet ikonuna tiklar
    And Kullanici checkout butonuna tiklar
    And Kullanici checkout bilgilerini ad "Ahmet", soyad "Yilmaz", posta kodu "" olarak girer
    And Kullanici continue butonuna tiklar
    Then Checkout hata mesaji goruntulenmelidir
    And Checkout hata mesaji "Postal Code is required" bilgisini icermelidir
