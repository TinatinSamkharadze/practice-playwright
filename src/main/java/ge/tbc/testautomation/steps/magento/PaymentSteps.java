package ge.tbc.testautomation.steps.magento;

import com.github.javafaker.Faker;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import ge.tbc.testautomation.pages.magento.PaymentPage;
import org.testng.asserts.SoftAssert;

import static ge.tbc.testautomation.data.Constants.THE_COUPON_CODE_IS_NOT_VALID;

public class PaymentSteps {
    Page page;
    PaymentPage paymentPage;
    SoftAssert softAssert;
    Faker faker;
    private double cartSubtotal,
            shippingSubtotal,
            totalPrice;

    public PaymentSteps(Page page) {
        this.page = page;
        this.paymentPage = new PaymentPage(page);
        this.softAssert = new SoftAssert();
        this.faker = new Faker();
    }

    public PaymentSteps retrieveCartSubtotal() {
        String cartSubtotal = paymentPage.cartSubtotal.textContent().replaceAll("[^0-9.]", "").trim();
        this.cartSubtotal = Double.parseDouble(cartSubtotal);
        return this;
    }

    public double getCartSubtotal() {
        return this.cartSubtotal;
    }

    public double getShippingSubtotal() {
        return this.shippingSubtotal;
    }

    public double getTotalPrice() {
        return this.totalPrice;
    }

    public PaymentSteps retrieveShippingPrice() {
        String shippingSubtotal = paymentPage.shippingPrice.textContent().replaceAll("[^0-9.]", "").trim();
        this.shippingSubtotal = Double.parseDouble(shippingSubtotal);
        return this;
    }

    public PaymentSteps retrieveTotalPrice() {
        String totalPrice = paymentPage.totalPrice.textContent().replaceAll("[^0-9.]", "").trim();
        this.totalPrice = Double.parseDouble(totalPrice);
        return this;
    }

    public PaymentSteps validateShippingIsAddedCorrectly() {
        softAssert.assertEquals(getCartSubtotal() + getShippingSubtotal(), getTotalPrice());
        return this;
    }

    public PaymentSteps validateShippingContentIsDisplayedCorrectly() {
        PlaywrightAssertions.assertThat(paymentPage.shippingContent).isVisible();
        return this;
    }

    public PaymentSteps clickToApplyDiscountCode() {
        paymentPage.discountCodeLink.waitFor();
        paymentPage.discountCodeLink.click();
        return this;
    }

    public PaymentSteps enterDiscountCode() {
        String discountCode = String.valueOf(faker.number().numberBetween(100000, 999999));
        paymentPage.discountCodeField.fill(discountCode);
        return this;
    }

    public PaymentSteps clickPlaceOrder() {
        paymentPage.placeOrder.click();
        return this;
    }

    public PaymentSteps clickApplyDiscountBtn() {
        paymentPage.discountBtn.click();
        return this;
    }

    public PaymentSteps waitErrorMessageToAppear() {
        paymentPage.alert.waitFor();
        return this;
    }

    public PaymentSteps validateErrorMessage() {
        PlaywrightAssertions.assertThat(paymentPage.alert)
                .containsText(THE_COUPON_CODE_IS_NOT_VALID);
        return this;
    }
}
