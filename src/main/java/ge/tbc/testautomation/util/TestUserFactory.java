package ge.tbc.testautomation.util;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import ge.tbc.testautomation.steps.toowebsite.SignUpSteps;

import static ge.tbc.testautomation.data.Constants.PRACTICE_SOFTWARE_TESTING_LOGIN_URL;
import static ge.tbc.testautomation.data.Constants.PRACTICE_SOFTWARE_TESTING_REGISTER;

public class TestUserFactory {

    public static TestUser registerNewUser(BrowserContext context) {
        Page page = context.newPage();
        SignUpSteps signUpSteps = new SignUpSteps(page);
        page.navigate(PRACTICE_SOFTWARE_TESTING_REGISTER);
        signUpSteps
                .enterFirstName()
                .enterLastName()
                .enterDateOfBirth()
                .enterStreetName()
                .EnterPostalCode()
                .enterCityName()
                .enterStateName()
                .chooseCountry()
                .enterPhoneNumber()
                .enterEmailAddress()
                .enterPassword()
                .clickRegisterButton();
        page.waitForURL(PRACTICE_SOFTWARE_TESTING_LOGIN_URL);
        TestUser user = new TestUser(signUpSteps.getTestEmail(), signUpSteps.getTestPassword());
        page.close();
        return user;
    }


}
