package ge.tbc.testautomation.steps;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import ge.tbc.testautomation.pages.HomePage;

import static ge.tbc.testautomation.data.Constants.*;

public class HomeSteps {
     Page page;
     HomePage homePage;

     public HomeSteps(Page page)
     {
         this.page = page;
         this.homePage = new HomePage(page);
     }

     public HomeSteps searchForItem(String item)
     {
         homePage.searchBarComboBox.fill(item);
         page.keyboard().press(ENTER);
         return this;
     }

     public HomeSteps clickHotSellersFirstItem()
     {
         homePage.hotSellerFirstProductImage.click();
         return this;
     }

     public HomeSteps validateTopNavBarIsVisible()
     {
         PlaywrightAssertions.assertThat(homePage.topNavigationBar).isVisible();
        return this;
     }

     public HomeSteps validateSignNavLinkIsVisible()
     {
         PlaywrightAssertions.assertThat(homePage.signInNavLink).isVisible();
         return this;
     }

     public HomeSteps validateCreateAnAccountLinkIsVisible()
     {
         PlaywrightAssertions.assertThat(homePage.createAccountNavLink).isVisible();
         return this;
     }

     public HomeSteps changeViewPortSize()
     {
         page.setViewportSize(WIDTH_FOR_MOBILE, HEIGHT_FOR_MOBILE);
         return this;
     }

     public HomeSteps clickNavToggleBtn()
     {
         homePage.navToggleButton.click();
         return this;
     }

     public HomeSteps clickOnMenu()
     {
         homePage.mobileMenuLink.waitFor();
         homePage.mobileMenuLink.click();
         return this;
     }

     public HomeSteps validateNavBarLinksAreVisible()
     {
         PlaywrightAssertions.assertThat(homePage.topNavigationBar).isVisible();
         return this;
     }

     public HomeSteps clickOnAccountLink()
     {
         homePage.accountMenuLink.click();
         return this;
     }
}
