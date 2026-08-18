package com.obem.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.obem.base.BasePage;

public class LoginPage extends BasePage {

    // Replace placeholders with actual OBEM locators.
    private final By username = By.id("username");
    private final By password = By.id("password");
    private final By loginButton = By.id("login");
    private final By loginError = By.cssSelector("[data-testid='login-error']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage enterUsername(String value) {
        type(username, value);
        return this;
    }

    public LoginPage enterPassword(String value) {
        type(password, value);
        return this;
    }

    public DashboardPage clickLogin() {
        click(loginButton);
        return new DashboardPage(driver);
    }

    public DashboardPage login(String user, String pass) {
        return enterUsername(user)
                .enterPassword(pass)
                .clickLogin();
    }

    public boolean isLoginErrorDisplayed() {
        return displayed(loginError);
    }
}
