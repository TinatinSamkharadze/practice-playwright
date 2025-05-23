package ge.tbc.testautomation.pages.toolwebsite;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class SignUpPage {
    public Locator firstNameInput,
            lastNameInput,
            dateOfBirthInput,
            streetInput,
            postcodeInput,
            cityInput,
            stateInput,
            countrySelect,
            phoneInput,
            emailInput,
            passwordInput,
            registerButton;


    public SignUpPage(Page page) {
        this.firstNameInput = page.getByPlaceholder("First name *");
        this.lastNameInput = page.getByPlaceholder("Your last name *");
        this.dateOfBirthInput = page.getByLabel("Date of Birth *");
        this.streetInput = page.getByPlaceholder("Your Street *");
        this.postcodeInput = page.getByPlaceholder("Your Postcode *");
        this.cityInput = page.getByPlaceholder("Your City *");
        this.stateInput = page.getByPlaceholder("Your State *");
        this.countrySelect = page.locator("#country");
        this.phoneInput = page.getByPlaceholder("Your phone *");
        this.emailInput = page.getByPlaceholder("Your email *");
        this.passwordInput = page.getByPlaceholder("Your password");
        this.registerButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Register"));

    }
}
