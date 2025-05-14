package ge.tbc.testautomation.steps.toowebsite;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.options.LoadState;
import ge.tbc.testautomation.pages.toolwebsite.SignInPage;

import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class SignInSteps {
    private final Page page;
    private final SignInPage signInPage;
    private String email;
    private String password;

    public SignInSteps(Page page) {
        this.page = page;
        this.signInPage = new SignInPage(page);
    }

    public SignInSteps validateWeAreOnCorrectPage()
    {
        assertThat(page).hasURL(Pattern.compile(".*/auth/login"));
        return this;
    }

    public SignInSteps validateWeHaveCorrectCredentials(String email, String password) {
        this.email = email;
        this.password = password;
        return this;
    }


    public SignInSteps enterEmail() {
        signInPage.loginEmailInput.fill(email);
        signInPage.loginEmailInput.press("Enter");
        return this;
    }

    public SignInSteps enterPassword() {
        signInPage.loginPasswordInput.fill(password);
        signInPage.loginPasswordInput.press("Enter");
        return this;
    }

    public SignInSteps validateLoginButtonIsEnabled() {
        PlaywrightAssertions.assertThat(signInPage.loginButton).isEnabled();
        return this;
    }

    public SignInSteps clickLoginButton() {
        page.waitForNavigation(() -> {
            signInPage.loginButton.press("Enter");
        });
        return this;
    }


    public SignInSteps validateLoginBtnIsVisible()
  {
      Locator loginBtn = signInPage.loginButton;
      assertThat(loginBtn).isVisible();
      return this;
  }

    public SignInSteps waitForDomReady() {
        page.waitForLoadState(LoadState.LOAD);
        return this;
    }



}
