Feature: Kullanici Girisi

  Background:
    Given Kullanici SauceDemo giris sayfasindadir

  @smoke @login
  Scenario: Gecerli bilgilerle basarili giris
    When Kullanici kullanici adi "standard_user" ve sifre "secret_sauce" girer
    And Kullanici login butonuna tiklar
    Then Kullanici urunler sayfasina yonlendirilmelidir
    And Sayfa basligi "Products" olarak goruntulenmelidir

  @negative @login
  Scenario: Hatali sifre ile giris yapilamamasi
    When Kullanici kullanici adi "standard_user" ve sifre "wrong_password" girer
    And Kullanici login butonuna tiklar
    Then Kullanici hata mesaji gormelidir
    And Hata mesaji "Username and password do not match" bilgisini icermelidir

  @negative @login
  Scenario: Bos kullanici adi ile giris yapilamamasi
    When Kullanici kullanici adi "" ve sifre "secret_sauce" girer
    And Kullanici login butonuna tiklar
    Then Kullanici hata mesaji gormelidir
    And Hata mesaji "Username is required" bilgisini icermelidir
