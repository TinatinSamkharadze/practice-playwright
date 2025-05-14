package ge.tbc.testautomation.pages.magento;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class ItemsPage {
   public Locator productTitleHeader;
   public Locator addToWishlistButton;
   public Locator wishlistAlertMessage;
   public Locator stockStatusLabel;
   public Locator productPriceOnDetailsPage;
   public Locator sizeOptionXS;
   public Locator colorOptionBlue;
   public Locator addToCartBtn;
   public Locator cartSuccessMsg;
   public Locator myCartLink;
   public  Locator cartProductName;
   public Locator cartPriceInfo;
   public Locator removeItemBtn;
   public Locator confirmRemovalButton;
    public Locator emptyCartMessage;
    public Locator quantityErrorMsg;
   public  Locator reviewCountLink;
   public Locator reviewsSummaryLink;
   public Locator createAccountButton;









    public ItemsPage(Page page)
    {
         this.productTitleHeader = page.locator(".page-title");
        this.addToWishlistButton = page.locator(".action.towishlist");
        this.wishlistAlertMessage  = page.locator(".messages").first();
        this.stockStatusLabel = page.getByText("In stock");
        this.productPriceOnDetailsPage  = page.locator("#product-price-1444");
        this.sizeOptionXS = page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName("XS"));
       this.colorOptionBlue = page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName("Blue"));
       this.addToCartBtn = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Add to Cart"));
       this.cartSuccessMsg  = page.locator(".message-success.success.message");
       this.myCartLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("My Cart"));
       this.cartProductName  = page.locator(".product-item-name").first();
       this.cartPriceInfo  = page.locator(".price-container").first();
       this.removeItemBtn  = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Remove"));
       this.confirmRemovalButton  = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("OK"));
      this.emptyCartMessage  = page.locator(".subtitle.empty");
      this.quantityErrorMsg = page.locator(".message-error.error.message");
      this.reviewCountLink  = page.locator(".action.view");
      this.reviewsSummaryLink  = page.getByText("2 Reviews").first();;
      this.createAccountButton  = page.locator(".action.create.primary");
    }
}
