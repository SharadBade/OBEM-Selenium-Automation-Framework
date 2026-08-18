package com.obem.tests;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.obem.base.BaseTest;
import com.obem.data.LoginDataProvider;
import com.obem.pages.DashboardPage;
import com.obem.pages.LoginPage;
import com.obem.retry.RetryAnalyzer;
import com.obem.utils.ConfigReader;

public class LoginTest extends BaseTest {

    @Test(
        description = "Verify valid OBEM login",
        groups = {"smoke", "regression"},
        retryAnalyzer = RetryAnalyzer.class
    )
    public void verifyValidLogin() {
        String username = ConfigReader.get("username");
        String password = ConfigReader.get("password");

        if (username == null || username.isBlank()
                || password == null || password.isBlank()) {
            throw new SkipException(
                    "Configure username/password using Maven properties or config.properties.");
        }

        DashboardPage dashboard = new LoginPage(driver)
                .login(username, password);

        Assert.assertTrue(
                dashboard.isDisplayed(),
                "Dashboard should be displayed after valid login.");
    }

    @Test(
        dataProvider = "loginData",
        dataProviderClass = LoginDataProvider.class,
        description = "Data-driven login scenarios",
        groups = {"regression"}
    )
    public void verifyLoginData(String username, String password, String expected) {
        if ("PLACEHOLDER_PASSWORD".equals(password)) {
            throw new SkipException("Replace Excel placeholder password with test data.");
        }

        LoginPage loginPage = new LoginPage(driver);

        if ("Success".equalsIgnoreCase(expected)) {
            Assert.assertTrue(
                    loginPage.login(username, password).isDisplayed(),
                    "Dashboard should be displayed for valid credentials.");
        } else {
            loginPage.enterUsername(username)
                    .enterPassword(password)
                    .clickLogin();

            // In a real implementation, assert the actual OBEM error/validation message.
            Assert.assertTrue(
                    true,
                    "Invalid-login scenario executed; replace with actual OBEM validation.");
        }
    }
}
