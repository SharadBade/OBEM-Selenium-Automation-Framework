# OBEM Selenium Automation - Interview Level Framework

## Project
OpenBlue Enterprise Manager (OBEM) - UI automation starter framework.

## Architecture

```text
                    Git / GitHub
                         |
                         v
                      Jenkins
                         |
                         v
                    Maven + TestNG
                         |
             +-----------+-----------+
             |                       |
       Parallel Classes        DataProvider
             |                       |
             v                       v
        DriverFactory          Apache POI / Excel
        ThreadLocal Driver
             |
             v
      Page Object Model
             |
    +--------+--------+--------+---------+
    |        |        |        |         |
  Login  Dashboard Building  Energy  Alarm/FDD
    |
    v
  OBEM Application

TestNG Listener
     |
     +--> Extent HTML Report
     +--> Screenshot on Failure
     +--> Log4j2 Runtime Logs

RetryAnalyzer
     |
     +--> Re-run failed tests (configured attempts)
```

## Technology Stack

- Java 17
- Selenium WebDriver 4
- TestNG
- Maven
- Page Object Model
- Apache POI
- ExtentReports
- Log4j2
- Git/GitHub
- Jenkins
- ThreadLocal WebDriver
- TestNG DataProvider
- Retry Analyzer
- Screenshot-on-failure

## Modules Included

1. Login
2. Dashboard
3. Building
4. Energy
5. Alarm / FDD

The module classes contain realistic business-oriented methods, but the locators are intentionally placeholders because the real OBEM DOM/environment was not supplied.

## Import in Eclipse

1. Extract the ZIP.
2. Eclipse -> File -> Import -> Maven -> Existing Maven Projects.
3. Select the project folder.
4. Update `src/main/resources/config.properties`.
5. Replace placeholder locators in `src/main/java/com/obem/pages`.
6. Run `testng.xml` as TestNG Suite.

## Run

```bash
mvn clean test
```

Chrome:

```bash
mvn clean test -Dbrowser=chrome
```

Headless:

```bash
mvn clean test -Dbrowser=chrome -Dheadless=true
```

Environment:

```bash
mvn clean test -DbaseUrl=https://your-obem-environment
```

Credentials:

```bash
mvn clean test -Dusername=YOUR_USER -Dpassword=YOUR_PASSWORD
```

Do not commit real credentials to Git.

## Parallel Execution

`testng.xml` uses:

```xml
parallel="classes" thread-count="4"
```

The `DriverFactory` uses `ThreadLocal<WebDriver>` so each parallel test class receives its own driver instance.

## DataProvider

`LoginTest` demonstrates TestNG DataProvider + Apache POI. The Excel file is:

```text
src/test/resources/testdata.xlsx
```

## Retry

`RetryAnalyzer` retries failed tests up to the configured number of attempts. In real projects, use retries carefully; do not use retries to hide genuine product defects.

## Failure Handling

The TestNG listener:

- captures failures
- takes screenshots
- attaches screenshots to ExtentReports
- records exception details
- flushes the HTML report

Reports:

```text
test-output/ExtentReport.html
test-output/automation.log
test-output/screenshots/
```
