package com.mobioffice.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

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

    public void signOut() {
        click(signOutBtn);
        click(signOutOkBtn);
    }

    public void logOutIfLoggedIn() {
        try {
            if (isElementDisplayed(signOutBtn)) {
                signOut();
            }
        } catch (Exception e) {
            LOGGER.info("User is not logged in");
        }
    }

    public String getUsername() {
        return getText(userUsername);
    }

    public boolean isUserLoggedIn() {
        try {
            return isElementDisplayed(userUsername);
        } catch (Exception e) {
            return false;
        }
    }
}
