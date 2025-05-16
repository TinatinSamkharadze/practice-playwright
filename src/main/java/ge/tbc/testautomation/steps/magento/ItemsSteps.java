package ge.tbc.testautomation.steps.magento;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import ge.tbc.testautomation.pages.magento.ItemsPage;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import static ge.tbc.testautomation.data.Constants.NO_ITEMS_IN_SHOPPING_CART;
import static ge.tbc.testautomation.data.Constants.REQUESTED_QTY_IS_NOT_AVAILABLE;

public class ItemsSteps {
    SoftAssert softAssert = new SoftAssert();
    Page page;
    ItemsPage itemsPage;
    private String productTitleText;
    private String productPriceValue;
    private String productItemName;
    private String productItemPrice;
    private int expectedReviewCount;

    public ItemsSteps(Page page) {
        this.page = page;
        this.itemsPage = new ItemsPage(page);
    }

    public ItemsSteps waitUntilStockStatusLabelBecomesVisible() {
        itemsPage.stockStatusLabel.waitFor();
        return this;
    }

    public ItemsSteps getProductsTitle() {
        this.productTitleText = itemsPage.productTitleHeader.textContent().replaceAll("[^A-Za-z]", "");
        return this;
    }

    public String getProductTitleText() {
        return this.productTitleText;
    }


    public ItemsSteps getProductPriceValue() {
        this.productPriceValue = itemsPage.productPriceOnDetailsPage.textContent().replaceAll("[^0-9.]", "").trim();
        return this;
    }

    public String getProductPrice() {
        return this.productPriceValue;
    }

    public ItemsSteps chooseSize() {
        itemsPage.sizeOptionXS.waitFor();
        itemsPage.sizeOptionXS.click();
        return this;
    }

    public ItemsSteps chooseColor() {
        itemsPage.colorOptionBlue.click();
        return this;
    }

    public ItemsSteps scrollToAddToCartBtn() {
        Locator cartBtn = itemsPage.addToCartBtn;
        cartBtn.scrollIntoViewIfNeeded();
        return this;
    }

    public ItemsSteps goToCheckout() {
        Locator checkoutBtn = itemsPage.checkout;
        checkoutBtn.waitFor();
        checkoutBtn.click();
        return this;
    }

    public ItemsSteps validateCartSuccessMessageIsVisible() {
        Locator cartSuccessMessage = itemsPage.cartSuccessMsg;
        cartSuccessMessage.waitFor();
        Assert.assertTrue(cartSuccessMessage.isVisible());
        return this;
    }

    public ItemsSteps clickOnMyCartBtn() {
        itemsPage.myCartLink.waitFor();
        itemsPage.myCartLink.click();
        return this;
    }

    public ItemsSteps getProductItemName() {
        this.productItemName = itemsPage.cartProductName.textContent().replaceAll("[^A-Za-z]", "");
        return this;
    }

    public String getProductItemNameText() {
        return this.productItemName;
    }

    public ItemsSteps getProductItemPrice() {
        itemsPage.cartPriceInfo.textContent().replaceAll("[^0-9.]", "").trim();
        return this;
    }

    public double getProductItemPriceValue() {
        double cartPriceValue = Double.parseDouble(productItemPrice);
        return cartPriceValue;
    }

    public ItemsSteps validateItemPricesAreSame() {
        softAssert.assertEquals(getProductPrice(), getProductItemPriceValue());
        return this;
    }

    public ItemsSteps validateItemNamesAreSame() {
        softAssert.assertEquals(getProductTitleText(), getProductItemNameText());
        return this;
    }

    public ItemsSteps clickRemoveItemBtn() {
        itemsPage.removeItemBtn.click();
        return this;
    }

    public ItemsSteps clickConfirmRemovalBtn() {
        itemsPage.confirmRemovalButton.click();
        return this;
    }

    public ItemsSteps validateCartIsEmpty() {
        PlaywrightAssertions.assertThat(itemsPage.emptyCartMessage).containsText(NO_ITEMS_IN_SHOPPING_CART);
        return this;
    }

    public ItemsSteps validateQuantityErrorMessage() {
        Locator quantityErrorMessage = itemsPage.quantityErrorMsg;
        quantityErrorMessage.waitFor();
        PlaywrightAssertions.assertThat(quantityErrorMessage).containsText(REQUESTED_QTY_IS_NOT_AVAILABLE);
        return this;
    }

    public ItemsSteps getNumberOfReviews() {
        itemsPage.reviewsSummaryLink.waitFor();
        this.expectedReviewCount = Integer.parseInt(itemsPage.reviewsSummaryLink.textContent().replaceAll("[^0-9.]", ""));
        return this;

    }

    public int getExpectedReviewCount() {
        return this.expectedReviewCount;
    }

    public ItemsSteps goToReviewsPage() {
        itemsPage.reviewsSummaryLink.waitFor();
        itemsPage.reviewsSummaryLink.first().click();
        return this;
    }

    public ItemsSteps clickAddToWishlistButton() {
        itemsPage.addToWishlistButton.waitFor();
        itemsPage.addToWishlistButton.click();
        return this;
    }

    public ItemsSteps validateWishlistErrorMsgIsVisible() {
        Locator wishlistErrorMsg = itemsPage.wishlistAlertMessage;
        wishlistErrorMsg.waitFor();
        PlaywrightAssertions.assertThat(wishlistErrorMsg).isVisible();
        return this;
    }

    public ItemsSteps clickCreateAnAccountBtn() {
        itemsPage.createAccountButton.waitFor();
        itemsPage.createAccountButton.click();
        return this;
    }

    public ItemsSteps validateWeAreInShoppingCart() {
        PlaywrightAssertions.assertThat(itemsPage.pageTitle).isVisible();
        return this;
    }

    public ItemsSteps continueShopping() {
        itemsPage.proceedToCheckoutBtn.waitFor();
        itemsPage.proceedToCheckoutBtn.click();
        return this;
    }
}
