package com.mobioffice.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.testng.Assert;

public class AccountScreen extends BaseScreen {

    private final By signInBtn = AppiumBy.xpath("//android.view.View[@resource-id=\"account_sign_in\"]/android.widget.Button");
    private final By userUsername = AppiumBy.xpath("//android.widget.TextView[@resource-id=\"account_username\"]");
    private final By signOutBtn = AppiumBy.xpath("//android.widget.TextView[@text=\"Sign out\"]");
    private final By signOutOkBtn = AppiumBy.xpath("//android.widget.Button[@resource-id=\"android:id/button1\"]");

    public AccountScreen(AndroidDriver driver) {
        super(driver);
    }

    public LoginScreen clickSignIn() {
        click(signInBtn);
        return new LoginScreen(driver);
    }

    public HomeScreen goToHome() {
        click(homeTab);
        return new HomeScreen(driver);
    }

    public AccountScreen signOut() {
        click(signOutBtn);
        click(signOutOkBtn);
        return this;
    }

    public AccountScreen logOutIfLoggedIn() {
        if (isElementDisplayed(signOutBtn)) {
            signOut();
        } else {
            LOGGER.info("Sign out button is not displayed");
        }
        return this;
    }

    public AccountScreen verifyUsername(String expectedName) {
        String actualName = getUsername();
        Assert.assertEquals(actualName, expectedName,
                "Expected username " + expectedName + " but got " + actualName + " instead!");
        return this;
    }

    public AccountScreen verifyUserLoggedOut() {
        Assert.assertFalse(isUserLoggedIn(), "User is still logged in!");
        return this;
    }

    public String getUsername() {
        return getText(userUsername);
    }

    public boolean isUserLoggedIn() {
        return isElementDisplayed(userUsername);
    }
}
