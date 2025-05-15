package ge.tbc.testautomation.steps.magento;

import com.microsoft.playwright.Page;
import ge.tbc.testautomation.pages.magento.MyAccountPage;

public class MyAccountSteps {
    Page page;
    MyAccountPage myAccountPage;

    public MyAccountSteps(Page page) {
        this.page = page;
        this.myAccountPage = new MyAccountPage(page);
    }

    public MyAccountSteps clickOnMyWishListLink() {
        myAccountPage.myWishListLink.waitFor();
        myAccountPage.myWishListLink.click();
        return this;
    }
}
