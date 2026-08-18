package com.obem.tests;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.obem.base.BaseTest;
import com.obem.pages.AlarmFddPage;
import com.obem.pages.LoginPage;
import com.obem.utils.ConfigReader;

public class AlarmFddTest extends BaseTest {

    @Test(description = "Verify Alarm and FDD module", groups = {"regression"})
    public void verifyAlarmFddModule() {
        requireCredentials();

        AlarmFddPage alarmFdd = new LoginPage(driver)
                .login(ConfigReader.get("username"), ConfigReader.get("password"))
                .openAlarmFddModule();

        Assert.assertTrue(alarmFdd.isDisplayed(),
                "Alarm/FDD module should be displayed.");

        Assert.assertTrue(alarmFdd.isAlarmListed(),
                "Alarm list should contain data.");
    }

    @Test(description = "Verify alarm filtering by severity and status", groups = {"regression"})
    public void verifyAlarmFilters() {
        requireCredentials();

        AlarmFddPage alarmFdd = new LoginPage(driver)
                .login(ConfigReader.get("username"), ConfigReader.get("password"))
                .openAlarmFddModule();

        alarmFdd.filterBySeverity("High")
                .filterByStatus("Open");

        Assert.assertTrue(alarmFdd.isAlarmListed(),
                "Filtered alarm list should contain matching data.");
    }

    @Test(description = "Verify FDD diagnostic panel", groups = {"regression"})
    public void verifyFddDiagnosticPanel() {
        requireCredentials();

        AlarmFddPage alarmFdd = new LoginPage(driver)
                .login(ConfigReader.get("username"), ConfigReader.get("password"))
                .openAlarmFddModule();

        Assert.assertTrue(alarmFdd.isFddPanelDisplayed(),
                "FDD diagnostic panel should be displayed.");
    }

    private void requireCredentials() {
        if (ConfigReader.get("username") == null
                || ConfigReader.get("username").isBlank()
                || ConfigReader.get("password") == null
                || ConfigReader.get("password").isBlank()) {
            throw new SkipException("OBEM credentials are not configured.");
        }
    }
}
