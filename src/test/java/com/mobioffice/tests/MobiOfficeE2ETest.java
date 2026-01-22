package com.mobioffice.tests;

import com.mobioffice.pages.AccountScreen;
import com.mobioffice.pages.HomeScreen;
import com.mobioffice.pages.LoginScreen;
import com.mobioffice.utils.enums.TestFiles;
import com.mobioffice.utils.enums.TestUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class MobiOfficeE2ETest extends BaseTest {

    private static final Logger LOGGER = LogManager.getLogger(MobiOfficeE2ETest.class);

    @Test(description = "E2E Scenario: Login -> Verify Cloud/Local Files -> Logout -> Verify Cloud Files Not Visible")
    public void testFullUserFlow() {
        TestUser user = TestUser.STANDARD_USER;

        LoginScreen loginScreen = new HomeScreen(driver)
                .goToAccountSection()
                .logOutIfLoggedIn()
                .clickSignIn();

        AccountScreen accountScreen = loginScreen.loginWithEmail(user.getEmail(), user.getPassword());
        String actualUsername = accountScreen.getUsername();
        String expectedUserName = user.getExpectedName();

        Assert.assertEquals(actualUsername, expectedUserName,
                "Expected username name: " + expectedUserName + ", but got: " + actualUsername);
        LOGGER.info("Logged in as: {}", user.getExpectedName());

        HomeScreen homeScreen = accountScreen.goToHome().clickShowMoreOrLess();

        homeScreen.goToHome()
                .clickShowMoreOrLess();

        Assert.assertTrue(homeScreen.isCloudIconDisplayed(), "Cloud icons missing!");

        List<String> allFiles = homeScreen.getAllFiles();
        Assert.assertTrue(allFiles.contains(TestFiles.PDF.getFileName()), "PDF file is missing!");
        Assert.assertTrue(allFiles.contains(TestFiles.XLS.getFileName()), "XLS file is missing");

        homeScreen.goToAccountSection()
                .signOut();

        Assert.assertFalse(accountScreen.isUserLoggedIn(), "User still logged in!");

        homeScreen.goToHome();

        Assert.assertFalse(homeScreen.isCloudIconDisplayed(), "Cloud icon still visible!");
        Assert.assertTrue(homeScreen.getAllFiles().contains(TestFiles.LOCAL_DOC.getFileName()), "Local document is missing!");
    }
}
