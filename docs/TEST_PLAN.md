# Software Test Plan

Project: SauceDemo E-Commerce Test Automation

## 1. Introduction

Purpose: This document defines the testing strategy, scope, resources, schedule,
CI/CD approach, risks, and deliverables for the SauceDemo e-commerce demo
application. The objective is to verify the main user flows before release by
using automated UI tests.

Scope: Testing includes authentication, product catalog, product sorting, cart
operations, checkout process, and order confirmation flows. The generic course
template mentions search, payment, and order history modules; SauceDemo does not
provide all of these modules, so unsupported items are listed under "Features
Not to be Tested".

Definitions:

| Term | Definition |
|---|---|
| SUT | System Under Test |
| BDD | Behavior Driven Development |
| CI/CD | Continuous Integration / Continuous Deployment |
| POM | Page Object Model |

## 2. Test Items

Modules to be tested:

- Authentication Module
- Product Catalog Module
- Product Sorting Module
- Shopping Cart Module
- Checkout Module
- Order Confirmation Module

## 3. Features to be Tested

Functional Testing:

- Valid login with standard user
- Invalid login with wrong password
- Invalid login with empty username
- Product list visibility
- Product count validation
- Product sorting by price low to high
- Product sorting by price high to low
- Product sorting by name A to Z
- Add item to cart
- Validate cart badge count
- View cart page
- Remove item from cart
- Successful checkout and order confirmation
- Failed checkout when first name is empty
- Failed checkout when postal code is empty

Non-Functional Testing:

- Browser compatibility on Chrome and Firefox through CI matrix execution
- Basic stability validation through smoke-tagged scenarios
- Failure evidence collection through screenshots attached to Cucumber reports

## 4. Features Not to be Tested

- Product search, because SauceDemo has no search input
- Product filtering beyond the existing sorting dropdown
- Third-party payment gateway internal logic
- Order history, because SauceDemo has no order history screen
- Mobile native application
- Database internal performance tuning
- API testing
- Security penetration testing
- Real payment integration

## 5. Test Strategy

Test Levels:

- System Testing
- Regression Testing
- Smoke Testing

Test Types:

- Functional Testing
- Negative Testing
- Boundary-oriented field validation for checkout required fields

Automation Strategy:

- Selenium WebDriver for UI automation
- TestNG for test execution and assertions
- Cucumber for BDD feature files
- Page Object Model design pattern
- TestNG DataProvider through the Cucumber TestNG runner
- Browser selection through Maven system property: `-Dbrowser=chrome` or
  `-Dbrowser=firefox`
- Headless execution through Maven system property: `-Dheadless=true`

Test Data:

| Data | Value |
|---|---|
| Base URL | `https://www.saucedemo.com/` |
| Valid username | `standard_user` |
| Valid password | `secret_sauce` |
| Invalid password | `wrong_password` |
| Checkout first name | `Ahmet` |
| Checkout last name | `Yilmaz` |
| Checkout postal code | `34000` |

## 6. Test Environment

Hardware:

- Minimum 8GB RAM
- Windows 10/11 for local execution
- Ubuntu latest runner for GitHub Actions

Software:

- Java 17
- Maven
- Selenium WebDriver
- TestNG
- Cucumber
- WebDriverManager
- Chrome browser
- Firefox browser

Version Control:

- Git
- GitHub repository for source control

## 7. CI/CD and GitHub Integration

Repository Management:

- All automation code should be stored in GitHub.
- Feature branches should be used for new features.
- Pull Requests should be reviewed before merging to `main` or `master`.

CI Pipeline:

- GitHub Actions workflow file: `.github/workflows/main.yml`
- Pipeline is triggered on every push and pull request to `main` and `master`.
- Manual execution is supported with `workflow_dispatch`.

Pipeline Steps:

1. Checkout code
2. Install Java 17
3. Restore Maven dependency cache
4. Install selected browser
5. Execute Maven test suite in headless mode
6. Generate Cucumber HTML/JSON report
7. Upload Cucumber and Surefire reports as artifacts

Quality Gates:

- Build fails if any automated test fails.
- Smoke scenarios are tagged with `@smoke` for critical flow validation.
- Chrome and Firefox jobs both need to pass before merge approval.

Optional Improvements:

- Schedule nightly regression runs.
- Add GitHub Actions status badge to README after repository URL is finalized.
- Publish Cucumber HTML report with GitHub Pages or another report portal.

## 8. Entry and Exit Criteria

Entry Criteria:

- Requirements finalized or course template clarified
- SauceDemo application is reachable
- Test environment is ready
- Java 17 and Maven are installed
- Maven dependencies can be downloaded
- Feature files are reviewed

Exit Criteria:

- 95% of planned automated test cases are executed
- No open critical defects remain
- Critical login, cart, and checkout scenarios pass
- HTML and JSON Cucumber reports are generated
- GitHub Actions pipeline completes successfully
- Test summary report is reviewed

## 9. Risk Analysis

| Risk | Impact | Probability | Mitigation |
|---|---:|---:|---|
| SauceDemo site is unavailable | High | Medium | Re-run tests later and keep failure evidence |
| Browser driver incompatibility | High | Medium | Use WebDriverManager and CI browser setup actions |
| UI locator changes | Medium | Low | Keep locators inside Page Object classes |
| CI browser issue | High | Medium | Run tests in headless mode and matrix browsers separately |
| Missing modules in SauceDemo | Medium | High | Document unsupported modules as out of scope |
| Internet or dependency download issue | Medium | Medium | Use Maven cache in CI |

## 10. Roles and Responsibilities

| Role | Responsibility |
|---|---|
| Test Lead | Approve test plan, review scope, monitor progress |
| Automation Engineer | Develop and maintain automated test scripts |
| Developer | Fix defects reported by test results |
| Reviewer / Instructor | Evaluate deliverables and CI evidence |

## 11. Test Deliverables

- Software Test Plan: `docs/TEST_PLAN.md`
- Automated Test Scripts: `src/test/java`
- Cucumber Feature Files: `src/test/resources/features`
- Cucumber HTML/JSON Reports: `target/cucumber-reports`
- TestNG Surefire Reports: `target/surefire-reports`
- Defect Report: `docs/DEFECT_REPORT.md`
- Test Summary Report: `docs/TEST_SUMMARY_REPORT.md`
- Requirements Traceability Matrix: `docs/TRACEABILITY_MATRIX.md`
- CI Pipeline Configuration File: `.github/workflows/main.yml`
- GitHub Repository Link: to be added to README when repository URL is known
