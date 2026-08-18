package com.obem.tests;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.obem.base.BaseTest;
import com.obem.pages.AlarmFddPage;
import com.obem.pages.LoginPage;
import com.obem.utils.ConfigReader;

public class AlarmFddTest extends BaseTest {

    @Test(
        description = "Verify filtering alarms by High severity",
        groups = {"regression"}
    )
    public void verifyHighSeverityAlarmFilter() {

        requireCredentials();

        // Login and open Alarm/FDD module
        AlarmFddPage alarmFdd = new LoginPage(driver)
                .login(
                    ConfigReader.get("username"),
                    ConfigReader.get("password")
                )
                .openAlarmFddModule();

        // Step 1: Verify Alarm/FDD page is displayed
        Assert.assertTrue(
                alarmFdd.isDisplayed(),
                "Alarm/FDD page should be displayed."
        );

        // Step 2: Select High severity
        alarmFdd.selectSeverity("High");

        // Step 3: Verify High severity is selected
        Assert.assertTrue(
                alarmFdd.isSeveritySelected("High"),
                "High severity should be selected."
        );

        // Step 4: Verify alarm results are displayed
        Assert.assertTrue(
                alarmFdd.areAlarmResultsDisplayed(),
                "High severity alarm results should be displayed."
        );
    }

    private void requireCredentials() {

        String username = ConfigReader.get("username");
        String password = ConfigReader.get("password");

        if (username == null
                || username.isBlank()
                || password == null
                || password.isBlank()) {

            throw new SkipException(
                    "OBEM credentials are not configured."
            );
        }
    }
}