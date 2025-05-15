package ge.tbc.testautomation;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.options.AriaRole;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static ge.tbc.testautomation.data.Constants.*;

public class MagentoTests extends BaseTest {
    private String testPassword;
    private String testEmail;
    private String testFirstName;
    private String testLastName;


    @Test(priority = 1)
    public void colorChangeTest() {
        page.navigate(MAGENTO_URL);

        Locator allOffers = page.locator(".product-item");
        List<Locator> offersWithColors = IntStream.range(0, allOffers.count())
                .mapToObj(allOffers::nth)
                .filter(offer -> offer.locator(".swatch-option.color").count() > 1)
                .collect(Collectors.toList());

        int offerCount = offersWithColors.size();
        List<Locator> randomOffers = IntStream.generate(() -> random.nextInt(offerCount))
                .distinct()
                .limit(3)
                .mapToObj(offersWithColors::get)
                .collect(Collectors.toList());


        for (Locator offer : randomOffers) {
            offer.scrollIntoViewIfNeeded();
            Locator colorOptions = offer.locator(".swatch-option.color");
            int colorCount = colorOptions.count();
            String initialImageSrc = offer.locator("img.product-image-photo").getAttribute("src");
            int randomColorIndex = random.nextInt(colorCount);
            Locator randomColor = colorOptions.nth(randomColorIndex);
            String selectedColor = randomColor.getAttribute("option-label").toLowerCase();
            randomColor.click();
            Locator productImage = offer.locator("img.product-image-photo");
            PlaywrightAssertions.assertThat(productImage).not().hasAttribute("src", initialImageSrc);
            Pattern colorPattern = Pattern.compile(selectedColor, Pattern.CASE_INSENSITIVE);
            PlaywrightAssertions.assertThat(productImage).hasAttribute("src", colorPattern);

        }
    }

    @Test(priority = 2, groups = "cartOperations")
    public void addToCartTest() {
        page.navigate(MAGENTO_URL);

        Locator searchBarComboBox = page.getByRole(AriaRole.COMBOBOX);
        searchBarComboBox.fill(TEE);
        page.keyboard().press(ENTER);
        Locator searchResultImages = page.locator(".product-image-container");
        Locator firstSearchResult = searchResultImages.first();
        firstSearchResult.waitFor();
        firstSearchResult.click();
        Locator stockStatusLabel = page.getByText("In stock");
        stockStatusLabel.waitFor();
        Locator productTitleHeader = page.locator(".page-title");
        String productTitleText = productTitleHeader.textContent().replaceAll("[^A-Za-z]", "");
        Locator productPriceOnDetailsPage = page.locator("#product-price-1444");
        double productPriceValue = Double.parseDouble(productPriceOnDetailsPage.textContent().replaceAll("[^0-9.]", "").trim());
        Locator sizeSelectorXS = page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName("XS"));
        sizeSelectorXS.click();
        Locator colorSelectorGreen = page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName("Green"));
        colorSelectorGreen.click();
        Locator addToCartBtn = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Add to Cart"));
        addToCartBtn.click();
        Locator cartSuccessMsg = page.locator(".message-success.success.message");
        cartSuccessMsg.waitFor();
        Locator myCartLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("My Cart"));
        myCartLink.waitFor();
        myCartLink.click();
        Locator cartProductItem = page.locator(".item.product.product-item.odd.last");
        cartProductItem.waitFor();
        Locator cartProductName = page.locator(".product-item-name").first();
        String cartProductNameText = cartProductName.textContent().replaceAll("[^A-Za-z]", "");
        Locator cartPriceContainer = page.locator(".price-container");
        Locator cartPriceInfo = cartPriceContainer.first();
        String cartPriceRawText = cartPriceInfo.textContent().replaceAll("[^0-9.]", "").trim();
        double cartPriceValue = Double.parseDouble(cartPriceRawText);
        softAssert.assertEquals(productPriceValue, cartPriceValue);
        softAssert.assertEquals(productTitleText, cartProductNameText);

    }

    @Test(dependsOnMethods = {"addToCartTest"}, groups = "cartOperations", priority = 3)
    public void deleteFromCart() {
        page.navigate(MAGENTO_URL);

        Locator myCartLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("My Cart"));
        myCartLink.waitFor();
        myCartLink.click();
        Locator removeItemLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Remove"));
        removeItemLink.waitFor();
        removeItemLink.click();
        Locator confirmRemovalButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("OK"));
        confirmRemovalButton.waitFor();
        confirmRemovalButton.click();
        myCartLink.waitFor();
        myCartLink.click();
        Locator emptyCartMessage = page.locator(".subtitle.empty");
        PlaywrightAssertions.assertThat(emptyCartMessage).containsText(NO_ITEMS_IN_SHOPPING_CART);

    }

    @Test(priority = 4)
    public void outOfStockOfferTest() {
        page.navigate(MAGENTO_URL);

        Locator hotSellerFirstProductImage = page.locator(".product-image-container").first();
        hotSellerFirstProductImage.waitFor();
        hotSellerFirstProductImage.click();
        Locator sizeSelectorXS = page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName("XS"));
        sizeSelectorXS.click();
        Locator colorSelectorBlue = page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName("Blue"));
        colorSelectorBlue.click();
        Locator addToCartBtn = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Add to Cart"));
        addToCartBtn.click();
        Locator quantityErrorMsg = page.locator(".message-error.error.message");
        quantityErrorMsg.waitFor();
        PlaywrightAssertions.assertThat(quantityErrorMsg).containsText(REQUESTED_QTY_IS_NOT_AVAILABLE);


    }

    @Test(priority = 5)
    public void reviewNumberTest() {
        page.navigate(MAGENTO_URL);

        Locator searchBarComboBox = page.getByRole(AriaRole.COMBOBOX);
        searchBarComboBox.fill(TEE);
        page.keyboard().press(ENTER);
        Locator searchResultImages = page.locator(".product-image-container");
        Locator firstSearchResult = searchResultImages.first();
        Locator reviewCountLink = page.locator(".reviews-actions").first();
        int expectedReviewCount = Integer.parseInt(reviewCountLink.textContent().replaceAll("[^0-9.]", ""));
        firstSearchResult.click();
        Locator reviewsSummaryLink = page.getByText("2 Reviews").first();
        reviewsSummaryLink.click();
        Locator reviewItems = page.locator(".item.review-item");
        reviewItems.first().waitFor();
        int actualReviewCount = reviewItems.count();
        Assert.assertEquals(expectedReviewCount, actualReviewCount);


    }

    @Test(priority = 7)
    public void mobileNavigationTest() {
        page.navigate(MAGENTO_URL);

        Locator topNavigationBar = page.locator("#ui-id-2");
        topNavigationBar.waitFor();
        PlaywrightAssertions.assertThat(topNavigationBar).isVisible();
        Locator signInNavLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Sign In"));
        signInNavLink.waitFor();
        PlaywrightAssertions.assertThat(signInNavLink).isVisible();
        Locator createAccountNavLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Create an Account"));
        PlaywrightAssertions.assertThat(createAccountNavLink).isVisible();
        page.setViewportSize(WIDTH_FOR_MOBILE, HEIGHT_FOR_MOBILE);
        Locator navToggleButton = page.locator(".action.nav-toggle");
        navToggleButton.click();
        Locator mobileMenuLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Menu"));
        mobileMenuLink.waitFor();
        mobileMenuLink.click();
        PlaywrightAssertions.assertThat(topNavigationBar).isVisible();
        Locator accountMenuLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Account"));
        accountMenuLink.waitFor();
        accountMenuLink.click();
        PlaywrightAssertions.assertThat(createAccountNavLink).isVisible();
        PlaywrightAssertions.assertThat(signInNavLink).isVisible();

    }

    @Test(priority = 6)
    public void saveToFavoritesWhileUnauthorizedTest() {
        page.navigate(MAGENTO_URL);

        Locator searchBarComboBox = page.getByRole(AriaRole.COMBOBOX);
        searchBarComboBox.fill(TEE);
        page.keyboard().press(ENTER);
        Locator searchResultImages = page.locator(".product-image-container");
        Locator firstSearchResult = searchResultImages.first();
        firstSearchResult.waitFor();
        firstSearchResult.click();
        Locator productTitleElement = page.locator(".page-title-wrapper.product");
        productTitleElement.waitFor();
        String productTitleText = productTitleElement.textContent().trim();
        Locator addToWishlistButton = page.locator(".action.towishlist");
        addToWishlistButton.waitFor();
        addToWishlistButton.click();
        Locator wishlistAlertMessage = page.locator(".messages").first();
        wishlistAlertMessage.waitFor();
        PlaywrightAssertions.assertThat(wishlistAlertMessage).isVisible();
        Locator createAccountButton = page.locator(".action.create.primary");
        createAccountButton.waitFor();
        createAccountButton.click();
        testFirstName = faker.name().firstName();
        testLastName = faker.name().lastName();
        testEmail = faker.internet().emailAddress();
        String upper = faker.letterify("?").toUpperCase();
        String lower = faker.letterify("?").toLowerCase();
        String number = faker.numerify("#");
        String special = "@#$%&!".charAt(faker.random().nextInt(6)) + "";
        String rest = faker.lorem().characters(4, true);
        testPassword = new StringBuilder(upper + lower + number + special + rest).reverse().toString();
        page.getByLabel("First Name").fill(testFirstName);
        page.getByLabel("Last Name").fill(testLastName);
        page.getByLabel("Email").first().fill(testEmail);
        page.getByLabel("Password").first().fill(testPassword);
        Locator confirmPasswordField = page.locator("#password-confirmation");
        confirmPasswordField.waitFor();
        confirmPasswordField.fill(testPassword);
        page.locator(".action.submit.primary").click();
        Locator accountPageTitle = page.locator(".page-title-wrapper");
        accountPageTitle.waitFor();
        Locator wishlistProductTitle = page.locator(".product-item-name").last();
        wishlistProductTitle.waitFor();
        String wishlistProductName = wishlistProductTitle.textContent().trim();
        softAssert.assertEquals(productTitleText, wishlistProductName);
        Locator wishlistSuccessAlert = page.getByRole(AriaRole.ALERT).first();
        wishlistSuccessAlert.waitFor();
        String wishlistSuccessText = wishlistSuccessAlert.textContent().replaceAll("\\s+", " ").trim();
        softAssert.assertTrue(
                wishlistSuccessText.contains(ADDED_TO_YOUR_WISHLIST),
                EXPECTED_MESSAGE_ABOUT_CONFIRMATION
        );

        Locator welcomeBanner = page.locator(".logged-in").first();
        welcomeBanner.waitFor();
        String welcomeText = welcomeBanner.textContent().replaceAll("\\s+", " ").trim();
        softAssert.assertTrue(welcomeText.contains("Welcome, " + testFirstName + " " + testLastName + "!"));

    }

}

