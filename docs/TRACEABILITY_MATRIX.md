# Requirements Traceability Matrix

Project: SauceDemo E-Commerce Test Automation

| Requirement ID | Requirement | Feature File | Scenario / Coverage |
|---|---|---|---|
| REQ-001 | User can login with valid credentials | `login.feature` | Gecerli bilgilerle basarili giris |
| REQ-002 | User sees an error for invalid password | `login.feature` | Hatali sifre ile giris yapilamamasi |
| REQ-003 | User sees an error for empty username | `login.feature` | Bos kullanici adi ile giris yapilamamasi |
| REQ-004 | Product list is displayed | `products.feature` | Urun listesinin goruntulenmesi |
| REQ-005 | Products can be sorted by price low to high | `products.feature` | Urunlerin fiyata gore dusukten yuksege siralanmasi |
| REQ-006 | Products can be sorted by price high to low | `products.feature` | Urunlerin fiyata gore yuksekten dusuge siralanmasi |
| REQ-007 | Products can be sorted by name A to Z | `products.feature` | Urunlerin isme gore A'dan Z'ye siralanmasi |
| REQ-008 | User can add a product to cart | `cart.feature` | Tek urunun sepete eklenmesi |
| REQ-009 | User can view product in cart | `cart.feature` | Sepete eklenen urunun sepet sayfasinda goruntulenmesi |
| REQ-010 | User can remove product from cart | `cart.feature` | Sepete eklenen urunun sepetten cikarilmasi |
| REQ-011 | User can complete checkout | `checkout.feature` | Kullanicinin siparisi basariyla tamamlamasi |
| REQ-012 | Checkout requires first name | `checkout.feature` | Checkout bilgilerinde ad alani bos birakildiginda hata alinmasi |
| REQ-013 | Checkout requires postal code | `checkout.feature` | Checkout bilgilerinde posta kodu bos birakildiginda hata alinmasi |
| REQ-014 | Tests run in CI | `.github/workflows/main.yml` | Push, pull request, and manual workflow triggers |
| REQ-015 | Tests support Chrome and Firefox | `Driver.java`, `.github/workflows/main.yml` | Browser selected with `-Dbrowser` and CI matrix |

## Not Applicable Template Items

| Template Item | Reason |
|---|---|
| Product search | SauceDemo has no search input |
| Order history | SauceDemo has no order history screen |
| Payment gateway logic | SauceDemo checkout does not process real payments |
