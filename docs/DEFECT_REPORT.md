# Defect Report

Project: SauceDemo Test Automation

## Defect summary

| ID | Finding | Severity | Status | Resolution |
|---|---|---:|---|---|
| DEF-001 | Browser cleanup could be skipped when failure screenshot capture threw an exception | High | Closed | Screenshot capture is isolated and driver cleanup always runs in `finally` |
| DEF-002 | Implicit and explicit waits were mixed, producing unpredictable wait times | Medium | Closed | The implicit wait was removed and page synchronization now uses explicit waits |
| DEF-003 | Sorting checks could pass with fewer than two products | Medium | Closed | Sorting assertions now require at least two visible products |
| DEF-004 | Configuration loading depended on the Maven working directory | Medium | Closed | `config.properties` is loaded from the test classpath and required values are validated |
| DEF-005 | Native WebDriver clicks were intermittent in headless Chrome, especially on Linux CI | High | Closed | Clickable waits are followed by a centered scroll and DOM click; checkout completion also verifies the resulting URL |
| DEF-006 | Maven emitted an invalid encoding configuration warning and had no SLF4J provider | Low | Closed | UTF-8 is passed through Surefire `argLine` and a compatible test logger is configured |
| DEF-007 | Local Firefox execution is unavailable because Firefox is not installed | Low | Closed | The full Firefox suite passed in GitHub Actions |
| DEF-008 | Selenium 4.18.1 reported a CDP-version warning with local Chrome 153 | Low | Closed | Selenium was upgraded to 4.49.0; the warning no longer appears |

## Verification evidence

- Review and remediation completed on 2026-09-20.
- Focused Chrome checkout run: 3 passed, 0 failed, 0 skipped.
- Clean Chrome regression run: 13 passed, 0 failed, 0 skipped.
- Immediate repeat Chrome regression run: 13 passed, 0 failed, 0 skipped.
- Failure-path execution confirmed screenshot attachment and browser cleanup behavior.

## CI verification

The published GitHub Actions matrix passed on Chrome and Firefox. Both jobs uploaded their Cucumber and Surefire report artifacts.