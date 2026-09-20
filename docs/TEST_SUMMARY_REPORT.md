# Test Summary Report

Project: SauceDemo Test Automation

Execution date: 2026-09-20

## Result

The repaired suite passed two consecutive full headless Chrome runs.

| Metric | Result |
|---|---:|
| Scenarios | 13 |
| Passed | 13 |
| Failed | 0 |
| Skipped | 0 |
| Consecutive successful full runs | 2 |

A focused checkout run also passed all three checkout scenarios after the interaction fix.

## Coverage

| Area | Scenarios | Status |
|---|---:|---|
| Authentication | 3 | Passed |
| Product catalog and sorting | 4 | Passed |
| Shopping cart | 3 | Passed |
| Checkout and order confirmation | 3 | Passed |

## Environments

| Environment | Status | Notes |
|---|---|---|
| Windows 11, Java 17, Chrome 153, headless | Verified | Two full local runs passed |
| Local Firefox | Not executed | Firefox is not installed locally |
| GitHub Actions, Chrome and Firefox | Passed | Both matrix jobs passed and uploaded reports |

## Reports

- Cucumber HTML: `target/cucumber-reports/cucumber.html`
- Cucumber JSON: `target/cucumber-reports/cucumber.json`
- TestNG/Surefire: `target/surefire-reports/`
- Failure screenshots: embedded in the Cucumber report

## Conclusion

The current automation covers the critical flows available in SauceDemo and is stable on the verified Chrome environment. Chrome and Firefox both pass in the published CI matrix; no execution environment remains pending.