package ge.tbc.testautomation.steps.toowebsite;

import com.github.javafaker.Faker;
import com.microsoft.playwright.Page;
import ge.tbc.testautomation.pages.toolwebsite.SignInPage;
import ge.tbc.testautomation.pages.toolwebsite.SignUpPage;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SignUpSteps {
    private String testFirstName;
    private String testLastName;
    public String testEmail;
    public String testPassword;
    Faker faker = new Faker();
    SignUpPage signUpPage;
    HomeSteps homeSteps;
    SignInPage signInPage;
    Page page;

    public SignUpSteps(Page page)
    {
        this.page = page;
        this.signUpPage = new SignUpPage(page);
        this.homeSteps = new HomeSteps(page);
        this.signInPage = new SignInPage(page);
    }

    public SignUpSteps enterFirstName()
    {
        testFirstName = faker.name().firstName();
        signUpPage.firstNameInput.fill(testFirstName);
        return this;
    }

    public SignUpSteps enterLastName()
    {
        testLastName = faker.name().lastName();
        signUpPage.lastNameInput.fill(testLastName);
        return this;
    }

    public SignUpSteps enterDateOfBirth() {
        Date dob = faker.date().birthday();
        String formattedDob = new SimpleDateFormat("yyyy-MM-dd").format(dob);
        signUpPage.dateOfBirthInput.fill(formattedDob);
        return this;
    }

    public SignUpSteps enterStreetName()
    {
        String street = faker.address().streetAddress();
        signUpPage.streetInput.fill(street);
        return this;
    }

    public SignUpSteps EnterPostalCode()
    {
        String postcode = faker.address().zipCode();
        signUpPage.postcodeInput.fill(postcode);
        return this;
    }

    public SignUpSteps enterCityName()
    {
        String city = faker.address().city();
        signUpPage.cityInput.fill(city);
        return this;
    }

    public SignUpSteps enterStateName()
    {
         String state = faker.address().state();
         signUpPage.stateInput.fill(state);
         return this;
    }

    public SignUpSteps chooseCountry()
    {
        signUpPage.countrySelect.selectOption("Italy");
        return this;
    }


    public SignUpSteps enterPhoneNumber()
    {
        String phone = faker.phoneNumber().subscriberNumber(9);
        signUpPage.phoneInput.fill(phone);
        return this;
    }

    public SignUpSteps enterEmailAddress()
    {
        testEmail = faker.internet().emailAddress();
        signUpPage.emailInput.fill(testEmail);
        return this;
    }

    public SignUpSteps enterPassword()
    {
        String upper = faker.letterify("?").toUpperCase();
        String lower = faker.letterify("?").toLowerCase();
        String number = faker.numerify("#");
        String special = "@#$%&!".charAt(faker.random().nextInt(6)) + "";
        String rest = faker.lorem().characters(4, true);
        testPassword = new StringBuilder(upper + lower + number + special + rest).reverse().toString();
        signUpPage.passwordInput.fill(testPassword);
        return this;
    }

    public SignUpSteps clickRegisterButton()
    {
        signUpPage.registerButton.click();
        return this;
    }

    public String getTestEmail() {
        return testEmail;
    }

    public String getTestPassword() {
        return testPassword;
    }

}
