# OBEM Interview-Level Framework - Flow

## Modules
Login -> Dashboard -> Building -> Energy -> Alarm/FDD

## Failure Flow
TestNG failure -> RetryAnalyzer -> final failure -> Extent listener -> screenshot + exception + HTML report

## Parallel Flow
Thread-1 -> Driver-1 -> Test Class A
Thread-2 -> Driver-2 -> Test Class B
Thread-3 -> Driver-3 -> Test Class C
Thread-4 -> Driver-4 -> Test Class D

## CI/CD
Git Push -> Jenkins -> Checkout -> Compile -> Smoke/Regression -> Archive reports/screenshots -> Publish JUnit results
