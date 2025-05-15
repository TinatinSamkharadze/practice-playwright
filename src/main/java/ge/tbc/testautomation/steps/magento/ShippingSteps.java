package ge.tbc.testautomation.steps.magento;

import com.github.javafaker.Faker;
import com.microsoft.playwright.Page;
import ge.tbc.testautomation.pages.magento.ShippingPage;

public class ShippingSteps {
    Page page;
    ShippingPage shippingPage;
    Faker faker;
    private String errorMsg;

    public ShippingSteps(Page page)
    {
        this.page = page;
        this.shippingPage = new ShippingPage(page);
        this.faker = new Faker();
    }

    public ShippingSteps enterStreetAddress()
    {
        String street = faker.address().streetAddress();
        shippingPage.streetAddress.fill(street);
        return this;
    }

    public ShippingSteps enterCityName()
    {
        String city = faker.address().city();
        shippingPage.city.fill(city);
        return this;
    }

    public ShippingSteps enterPostalCode()
    {
        String postalCode = faker.address().zipCode();
        shippingPage.postalCode.fill(postalCode);
        return this;
    }

    public ShippingSteps enterPhoneNumber()
    {
        String phone = faker.phoneNumber().subscriberNumber(9);
        shippingPage.phoneInput.fill(phone);
        return this;
    }

    public ShippingSteps checkShippingMethod()
    {
        shippingPage.shippingMethod.click();
        return this;
    }

    public ShippingSteps clickNextBtn()
    {
        shippingPage.nextBtn.waitFor();
        shippingPage.nextBtn.click();
        return this;
    }

    public ShippingSteps clickOnProceedToCheckoutBtn()
    {
        shippingPage.checkoutBtn.waitFor();
        shippingPage.checkoutBtn.click();
        return this;
    }

    public ShippingSteps selectState()
    {
        shippingPage.state.click();
        shippingPage.state.selectOption("Florida");
        return this;
    }



}
