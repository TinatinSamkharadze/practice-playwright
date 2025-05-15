package ge.tbc.testautomation.util;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import ge.tbc.testautomation.steps.toowebsite.SignUpSteps;

public class TestUserFactory {

    public static TestUser registerNewUser(BrowserContext context) {
        Page page = context.newPage();
        SignUpSteps signUpSteps = new SignUpSteps(page);
        page.navigate("https://practicesoftwaretesting.com/auth/register");
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
        page.waitForURL("https://practicesoftwaretesting.com/auth/login");
        TestUser user = new TestUser(signUpSteps.getTestEmail(), signUpSteps.getTestPassword());
        page.close();
        return user;
    }


}
