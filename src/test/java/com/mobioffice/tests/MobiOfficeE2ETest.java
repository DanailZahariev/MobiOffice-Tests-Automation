package com.mobioffice.tests;

import com.mobioffice.pages.AccountScreen;
import com.mobioffice.pages.HomeScreen;
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
        HomeScreen homeScreen = new HomeScreen(driver);
        AccountScreen accountScreen = homeScreen.goToAccountSection();

        accountScreen.logOutIfLoggedIn();

        LOGGER.info("Starting clean up: Logging out if needed...");

        TestUser user = TestUser.STANDARD_USER;
        LOGGER.info("Attempting login with user: {}", user.getEmail());

        accountScreen
                .clickSignIn()
                .loginWithEmail(user.getEmail(), user.getPassword());

        String username = accountScreen.getUsername();

        Assert.assertEquals(username, user.getExpectedName(),
                "Login failed or username mismatch!");
        LOGGER.info("Successfully logged in as user: {}", username);

        homeScreen.clickHome();
        homeScreen.clickShowMoreOrLess();

        List<String> allFiles = homeScreen.getAllFiles();

        LOGGER.info("Verifying cloud files: {}", allFiles);

        Assert.assertTrue(homeScreen.isCloudIconDisplayed(),
                "Cloud icons should be visible when logged in!");
        Assert.assertTrue(allFiles.contains(TestFiles.XLS.getFileName()),
                "Recent file is missing!");
        Assert.assertTrue(allFiles.contains(TestFiles.PDF.getFileName()),
                "Recent file is missing!");
        Assert.assertTrue(allFiles.contains(TestFiles.SLIDESHOW.getFileName()),
                "Recent file is missing!");
        Assert.assertTrue(allFiles.contains(TestFiles.DOCUMENT.getFileName()),
                "Recent file is missing!");

        LOGGER.info("Logging out...");

        homeScreen.goToAccountSection().signOut();

        Assert.assertFalse(accountScreen.isUserLoggedIn(), "User is still logged in!");

        homeScreen.clickHome();

        Assert.assertFalse(homeScreen.isCloudIconDisplayed(),
                "Cloud icons are still visible after logout!");

        List<String> filesAfterLogout = homeScreen.getAllFiles();
        Assert.assertTrue(filesAfterLogout.contains(TestFiles.LOCAL_DOC.getFileName()),
                "Cloud file should not be visible after logout!");

        LOGGER.info("E2E Test finished successfully.");
    }
}
