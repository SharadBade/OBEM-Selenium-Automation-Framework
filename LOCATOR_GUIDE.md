# OBEM Locator Guide

The Page Objects intentionally use placeholder locators because the real OBEM DOM was not provided.

Replace locators in:
- LoginPage.java
- DashboardPage.java
- BuildingPage.java
- EnergyPage.java
- AlarmFddPage.java

Preferred locator order:
1. Stable id
2. name
3. data-testid / data-* attributes
4. stable CSS
5. relative XPath

Avoid brittle absolute XPath such as `/html/body/div[2]/div[1]/div/...`.
