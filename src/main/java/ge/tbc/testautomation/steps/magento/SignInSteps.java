package ge.tbc.testautomation.steps.magento;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import ge.tbc.testautomation.pages.magento.HomePage;
import ge.tbc.testautomation.pages.magento.SignInPage;

public class SignInSteps {
    Page page;
    SignInPage signInPage;
    HomePage homePage;
    public SignInSteps(Page page) {
        this.page = page;
        this.signInPage = new SignInPage(page);
        this.homePage = new HomePage(page);
    }


    public SignInSteps enterEmail(String email) {
        signInPage.email.fill(email);
        return this;
    }

    public SignInSteps enterPassword(String password) {
        signInPage.password.fill(password);
        return this;
    }

    public SignInSteps clickLogin() {
        signInPage.signIn.click();
        return this;
    }

    public SignInSteps validateWeAreOnSignInPage()
    {
        PlaywrightAssertions.assertThat(signInPage.pageTitle).isVisible();
        return this;
    }


}

