package ge.tbc.testautomation.pages.magento;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class ItemsPage {
    public Locator productTitleHeader,
            addToWishlistButton,
            wishlistAlertMessage,
            stockStatusLabel,
            productPriceOnDetailsPage,
            sizeOptionXS,
            colorOptionBlue,
            addToCartBtn,
            cartSuccessMsg,
            myCartLink,
            cartProductName,
            cartPriceInfo,
            removeItemBtn,
            confirmRemovalButton,
            emptyCartMessage,
            quantityErrorMsg,
            reviewCountLink,
            reviewsSummaryLink,
            createAccountButton,
            proceedToCheckoutBtn,
            checkout,
            pageTitle;


    public ItemsPage(Page page) {
        this.productTitleHeader = page.locator(".page-title");
        this.addToWishlistButton = page.locator(".action.towishlist");
        this.wishlistAlertMessage = page.locator(".messages").first();
        this.stockStatusLabel = page.getByText("In stock");
        this.productPriceOnDetailsPage = page.locator("#product-price-1444");
        this.sizeOptionXS = page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName("XS"));
        this.colorOptionBlue = page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName("Blue"));
        this.addToCartBtn = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Add to Cart")).first();
        this.cartSuccessMsg = page.locator(".message-success.success.message");
        this.myCartLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("My Cart"));
        this.cartProductName = page.locator(".product-item-name").first();
        this.cartPriceInfo = page.locator(".price-container").first();
        this.removeItemBtn = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Remove"));
        this.confirmRemovalButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("OK"));
        this.emptyCartMessage = page.locator(".subtitle.empty");
        this.quantityErrorMsg = page.locator(".message-error.error.message");
        this.reviewCountLink = page.locator(".action.view");
        this.reviewsSummaryLink = page.getByText("2 Reviews").first();
        this.createAccountButton = page.locator(".action.create.primary");
        this.productPriceOnDetailsPage = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Proceed to Checkout"));
        this.checkout = page.locator(".action.tocart.primary").last();
        this.proceedToCheckoutBtn = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Proceed to Checkout"));
        this.pageTitle = page.getByText("Shopping Cart").first();
    }
}
