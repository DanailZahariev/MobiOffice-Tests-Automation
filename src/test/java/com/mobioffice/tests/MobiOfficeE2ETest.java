package com.mobioffice.tests;

import com.mobioffice.pages.HomeScreen;
import com.mobioffice.utils.enums.TestFiles;
import com.mobioffice.utils.enums.TestUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

public class MobiOfficeE2ETest extends BaseTest {

    private static final Logger LOGGER = LogManager.getLogger(MobiOfficeE2ETest.class);

    @Test(description = "E2E Scenario: Login -> Verify Cloud/Local Files -> Logout -> Verify Cloud Files Not Visible")
    public void testFullUserFlow() {
        TestUser user = TestUser.STANDARD_USER;

        new HomeScreen(driver)
                .goToAccountSection()
                .logOutIfLoggedIn()
                .clickSignIn()
                .loginWithEmail(user.getEmail(), user.getPassword())
                .verifyUsername(user.getExpectedName())
                .goToHome()
                .clickShowMoreOrLess()
                .verifyCloudIconVisible(true)
                .verifyFileExists(TestFiles.XLS.getFileName())
                .verifyFileExists(TestFiles.PDF.getFileName())
                .verifyFileExists(TestFiles.SLIDESHOW.getFileName())
                .verifyFileExists(TestFiles.DOCUMENT.getFileName())
                .goToAccountSection()
                .signOut()
                .verifyUserLoggedOut()
                .goToHome()
                .verifyCloudIconVisible(false)
                .verifyFileExists(TestFiles.LOCAL_DOC.getFileName());
    }
}
