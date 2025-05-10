package ge.tbc.testautomation.steps.telerik;

import ge.tbc.testautomation.pages.telerik.ProductBundlesPage;

import static org.testng.Assert.assertFalse;

public class ProductBundlesStep {
    private final ProductBundlesPage productBundlesPage;

    public ProductBundlesStep(ProductBundlesPage productBundlesPage) {
        this.productBundlesPage = productBundlesPage;
    }

    public ProductBundlesStep validateMockingSolutionIsNotIncludedInDevCraftUI()
    { boolean isVisible = productBundlesPage.devcraftUi
            .locator("text=Mocking solution for rapid unit testing")
            .isVisible();

        assertFalse(isVisible, "'Mocking solution for rapid unit testing' should NOT be in DevCraft UI");
        return this;
    }
}
