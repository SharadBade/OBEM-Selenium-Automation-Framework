# OBEM Framework - Interview Answers

## Why Page Object Model?
It separates test/business flow from UI locators and page actions, improving maintainability and reuse.

## Why ThreadLocal WebDriver?
Parallel tests should not share the same browser session. ThreadLocal provides an isolated driver per execution thread.

## Why DataProvider?
It separates test data from test logic and lets one test execute with multiple datasets.

## Why RetryAnalyzer?
It can handle transient UI/infrastructure failures. It should not be used to hide real application defects.

## Why ExtentReports?
It provides an HTML execution report containing pass/fail status, exceptions and screenshots.

## Why Log4j2?
It provides runtime diagnostics that help identify exactly where an automation flow failed.

## Why Jenkins?
It executes the suite automatically in CI/CD, archives results and provides feedback to the team.

## Multiple environments
Use Maven/Jenkins parameters such as `baseUrl`, environment-specific configuration, and Jenkins Credentials for secrets.

## Production improvements
Add API/database validation, environment config, secret management, custom waits, test tagging, Docker/Grid, notifications and richer dashboards.
