package com.mobioffice.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class LoginScreen extends BaseScreen {

    private final By emailField = AppiumBy.xpath("//android.widget.EditText[@resource-id=\"com.mobisystems.office:id/username\"]");
    private final By passwordField = AppiumBy.xpath("//android.widget.EditText[@resource-id=\"com.mobisystems.office:id/password\"]");
    private final By signInBtn = AppiumBy.xpath("//android.widget.Button[@resource-id=\"com.mobisystems.office:id/sign_in\"]");
    private final By emailOrPhoneBtn = AppiumBy.id("com.mobisystems.office:id/signin_email_phone");
    private final By googleSavePassNo = AppiumBy.id("android:id/autofill_save_no");

    public LoginScreen(AndroidDriver driver) {
        super(driver);
    }

    public AccountScreen loginWithEmail(String email, String password) {
        click(emailOrPhoneBtn);
        click(emailField);
        fillInput(emailField, email);
        click(passwordField);
        fillInput(passwordField, password);
        click(signInBtn);
        handleGoogleSmartLock();

        return new AccountScreen(driver);
    }

    private void handleGoogleSmartLock() {
        try {

            if (isElementDisplayed(googleSavePassNo)) {
                click(googleSavePassNo);
            }
        } catch (Exception e) {
            LOGGER.info("Google Smart Lock is not displayed");
        }
    }
}
