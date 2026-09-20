Feature: Urun Listeleme ve Siralama

  Background:
    Given Kullanici SauceDemo giris sayfasindadir
    When Kullanici kullanici adi "standard_user" ve sifre "secret_sauce" girer
    And Kullanici login butonuna tiklar
    Then Kullanici urunler sayfasina yonlendirilmelidir

  @products
  Scenario: Urun listesinin goruntulenmesi
    Then Urun listesi goruntulenmelidir
    And Urun listesinde en az 1 urun bulunmalidir

  @products @sorting
  Scenario: Urunlerin fiyata gore dusukten yuksege siralanmasi
    When Kullanici urunleri "Price (low to high)" secenegine gore siralar
    Then Urun fiyatlari dusukten yuksege dogru siralanmalidir

  @products @sorting
  Scenario: Urunlerin fiyata gore yuksekten dusuge siralanmasi
    When Kullanici urunleri "Price (high to low)" secenegine gore siralar
    Then Urun fiyatlari yuksekten dusuge dogru siralanmalidir

  @products @sorting
  Scenario: Urunlerin isme gore A'dan Z'ye siralanmasi
    When Kullanici urunleri "Name (A to Z)" secenegine gore siralar
    Then Urun isimleri A'dan Z'ye dogru siralanmalidir
