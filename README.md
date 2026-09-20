# SauceDemo Test Automation

[![CI](https://github.com/ykart897/saucedemo-test-automation/actions/workflows/main.yml/badge.svg)](https://github.com/ykart897/saucedemo-test-automation/actions/workflows/main.yml)

BDD-style UI test automation for the [SauceDemo](https://www.saucedemo.com/) e-commerce demo. The project exercises its critical customer journeys with Java 17, Selenium WebDriver, Cucumber, TestNG, Maven, and the Page Object Model.

## Test coverage

The suite contains 13 scenarios covering:

- valid and invalid login flows;
- product list visibility and sorting;
- adding and removing products from the cart;
- successful checkout and required-field validation;
- order confirmation.

SauceDemo does not provide product search, order history, or real payment processing, so those areas are outside this project's scope.

## Requirements

- Java 17
- Maven 3.9+
- Google Chrome or Mozilla Firefox
- Internet access to SauceDemo and Maven Central

The local default browser is Chrome. WebDriverManager resolves the matching browser driver automatically.

## Run locally

```bash
mvn clean test -Dheadless=true -Dbrowser=chrome
```

Run the Firefox suite with:

```bash
mvn clean test -Dheadless=true -Dbrowser=firefox
```

To watch the browser, omit `-Dheadless=true`. If Firefox is installed in a non-standard location, add `-Dfirefox.binary="C:\path\to\firefox.exe"`.

## Reports

Each run creates:

- `target/cucumber-reports/cucumber.html`
- `target/cucumber-reports/cucumber.json`
- `target/surefire-reports/`

Failed scenarios include a screenshot in the Cucumber report when the browser supports capture. GitHub Actions runs the full suite on Chrome and Firefox and retains both report sets as workflow artifacts for 14 days.

## Project structure

```text
src/test/java
├── hooks
├── pages
├── runners
├── stepdefinitions
└── utilities
src/test/resources
├── features
└── config.properties
docs
└── test documentation
```

The Gherkin scenarios are written in Turkish to match the original course deliverable. Project documentation and developer instructions are in English.

## Test documentation

- [Test plan](docs/TEST_PLAN.md)
- [Defect report](docs/DEFECT_REPORT.md)
- [Test summary](docs/TEST_SUMMARY_REPORT.md)
- [Requirements traceability matrix](docs/TRACEABILITY_MATRIX.md)

## Known environment limitation

Local Firefox execution requires Firefox to be installed. Cross-browser compatibility is verified by the GitHub Actions browser matrix.