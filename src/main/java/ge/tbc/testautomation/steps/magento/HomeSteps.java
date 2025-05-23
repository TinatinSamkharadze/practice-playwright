package ge.tbc.testautomation.steps.magento;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import ge.tbc.testautomation.pages.magento.HomePage;
import io.qameta.allure.Step;
import org.testng.SkipException;

import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static ge.tbc.testautomation.data.Constants.*;

public class HomeSteps {
    Page page;
    HomePage homePage;
    public List<Locator> offersWithColors;
    Random random = new Random();
    public List<Locator> randomOffers;

    public HomeSteps(Page page) {
        this.page = page;
        this.homePage = new HomePage(page);
    }

    @Step("Wait for offers to load")
    public HomeSteps waitForOffersToLoad() {
        homePage.allOffersInHotSeller.first().waitFor();
        return this;
    }

    @Step("Locate all offers which colors can be changed")
    public HomeSteps locateAllOffersWhichColorsCanBeChanged() {
        Locator allOffers = homePage.allOffersInHotSeller;
        offersWithColors = IntStream.range(0, allOffers.count())
                .mapToObj(allOffers::nth)
                .filter(offer -> homePage.getColorOptions(offer).count() > 1)
                .collect(Collectors.toList());
        return this;
    }

    @Step("Choose random three offers")
    public HomeSteps chooseRandomThreeOffers() {
        int offerCount = offersWithColors.size();
        randomOffers = IntStream.generate(() -> random.nextInt(offerCount))
                .distinct()
                .limit(3)
                .mapToObj(offersWithColors::get)
                .collect(Collectors.toList());
        return this;
    }

    public HomeSteps scrollToOffer(Locator offer) {
        offer.scrollIntoViewIfNeeded();
        return this;
    }

    public String getInitialImageSrc(Locator offer) {
        return homePage.getProductImage(offer).getAttribute("src");
    }

    public Locator chooseDifferentColor(Locator offer, String initialImageSrc) {
        Locator colorOptions = homePage.getColorOptions(offer);
        int colorCount = colorOptions.count();
        int attempts = 0;
        String selectedColor;
        Locator randomColor;

        do {
            int randomIndex = random.nextInt(colorCount);
            randomColor = colorOptions.nth(randomIndex);
            selectedColor = randomColor.getAttribute("option-label").toLowerCase();
            attempts++;
            if (attempts >= colorCount) break;
        } while (initialImageSrc.toLowerCase().contains(selectedColor) && colorCount > 1);

        randomColor.click();
        return randomColor;
    }

    public HomeSteps assertImageChanged(Locator offer, String initialImageSrc, String selectedColor) {
        Locator productImage = homePage.getProductImage(offer);
        if (!initialImageSrc.toLowerCase().contains(selectedColor)) {
            PlaywrightAssertions.assertThat(productImage).not().hasAttribute("src", initialImageSrc);
        }
        return this;
    }

    public HomeSteps assertColorAppliedToImage(Locator offer, String selectedColor) {
        Locator productImage = homePage.getProductImage(offer);
        Pattern colorPattern = Pattern.compile(selectedColor, Pattern.CASE_INSENSITIVE);
        PlaywrightAssertions.assertThat(productImage).hasAttribute("src", colorPattern);
        return this;
    }
    @Step("Verify offers exist")
    public HomeSteps verifyOffersExist() {
        if (offersWithColors.isEmpty()) {
            throw new SkipException(NO_OFFERS_WITH_THIS_COLOR);
        }
        return this;
    }

    @Step("Verify color changes reflect on image")
    public HomeSteps verifyColorChangesReflectOnImage() {
        for (Locator offer : randomOffers) {
            scrollToOffer(offer);
            String initialSrc = getInitialImageSrc(offer);
            Locator colorClicked = chooseDifferentColor(offer, initialSrc);
            String selectedColor = colorClicked.getAttribute("option-label").toLowerCase();

            assertImageChanged(offer, initialSrc, selectedColor)
                    .assertColorAppliedToImage(offer, selectedColor);
        }
        return this;
    }


    public HomeSteps searchForItem(String item) {
        homePage.searchBarComboBox.fill(item);
        page.keyboard().press(ENTER);
        return this;
    }

    @Step("Click on the first item in hot sellers")
    public HomeSteps clickHotSellersFirstItem() {
        homePage.hotSellerFirstProductImage.click();
        return this;
    }

    public HomeSteps validateTopNavBarIsVisible() {
        PlaywrightAssertions.assertThat(homePage.topNavigationBar).isVisible();
        return this;
    }

    public HomeSteps validateSignNavLinkIsVisible() {
        PlaywrightAssertions.assertThat(homePage.signInNavLink).isVisible();
        return this;
    }

    public HomeSteps validateCreateAnAccountLinkIsVisible() {
        PlaywrightAssertions.assertThat(homePage.createAccountNavLink).isVisible();
        return this;
    }

    public HomeSteps changeViewPortSize() {
        page.setViewportSize(WIDTH_FOR_MOBILE, HEIGHT_FOR_MOBILE);
        return this;
    }

    public HomeSteps clickNavToggleBtn() {
        homePage.navToggleButton.click();
        return this;
    }

    public HomeSteps clickOnMenu() {
        homePage.mobileMenuLink.waitFor();
        homePage.mobileMenuLink.click();
        return this;
    }

    public HomeSteps validateNavBarLinksAreVisible() {
        PlaywrightAssertions.assertThat(homePage.topNavigationBar).isVisible();
        return this;
    }

    public HomeSteps clickOnAccountLink() {
        homePage.accountMenuLink.click();
        return this;
    }

    public HomeSteps clickSignInLink() {
        homePage.signInNavLink.click();
        return this;
    }

    public HomeSteps setViewPortSizeForDesktop()
    {
        page.setViewportSize(WIDTH_FOR_DESKTOP, HEIGHT_FOR_DESKTOP);
        return this;
    }
}
