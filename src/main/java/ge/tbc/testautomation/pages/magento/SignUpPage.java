package ge.tbc.testautomation.pages.magento;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class SignUpPage {

    public Locator firstName,
            lastName,
            email,
            password,
            confirmPasswordField,
            submitBtn;

    public SignUpPage(Page page) {
        this.firstName = page.getByLabel("First Name");
        this.lastName = page.locator("#lastname");
        this.email = page.locator("#email_address");
        this.password = page.locator("#password");
        this.confirmPasswordField = page.locator("#password-confirmation");
        this.submitBtn = page.locator(".action.submit.primary");


    }
}
