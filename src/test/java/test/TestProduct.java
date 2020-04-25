package test;

import config.SeleniumConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pom.DesktopPOM;
import pom.NavigationPOM;
import pom.ProductPOM;

@Test
public class TestProduct extends SeleniumConfig
{
    @DataProvider(name = "DesktopDataProvider")
    Object[][] getDesktopData()
    {
        return _excelhelper.readDataFromExcel(strPathToDatasheet + _hashmapAppProp.get("TestDocDataFileName"), "Sheet1");
    }
    @Test(enabled = false)
    public void selectProductLinearProgrammingTest()
    {
        try {

            WebElement anchorDesktopElement = driver.findElement(By.linkText("Desktops"));
            anchorDesktopElement.click();

            Thread.sleep(2000);

            WebElement anchorMacElement = driver.findElement(By.partialLinkText("Mac"));
            anchorMacElement.click();

            Thread.sleep(2000);

            WebElement anchorIMacElement = driver.findElement(By.linkText("iMac"));
            anchorIMacElement.click();

            Thread.sleep(2000);

            WebElement btnAddToCartElement = driver.findElement(By.id("button-cart"));
            btnAddToCartElement.click();

            Thread.sleep(2000);

            WebElement btnGoToCartElement = driver.findElement(By.cssSelector("div#cart > button"));
            btnGoToCartElement.click();

            Thread.sleep(2000);

            WebElement anchorViewCartElement = driver.findElement(By.xpath(".//p[@class='text-right']/a"));
            anchorViewCartElement.click();

            Thread.sleep(2000);

            WebElement tblAddedProductElement = driver.findElement(By.xpath(".//div[@id='content']/form/div[1]/table/tbody/tr[1]/td[2]/a"));

            Assert.assertEquals(tblAddedProductElement.getText(), "iMac");
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            Assert.fail(ex.getMessage());
        }

    }

    @Test(enabled = false)
    public void selectProductPOMTest()
    {
        try {

            ProductPOM _productPOM = new ProductPOM(driver);

            _productPOM.navigateToMac();

            _productPOM.addToCart();

            _productPOM.goToCart();

            _productPOM.viewCart();

            Assert.assertEquals(_productPOM.tblAddedProductElement.getText(), "iMac");
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            Assert.fail(ex.getMessage());
        }
    }

    @Test(enabled = false)
    public void selectProductPOMNavigationTest()
    {
        try {

            _utility.implictWait(driver, Integer.parseInt(_hashmapAppProp.get("noOfSecondToWait")));

            NavigationPOM _navigationPOM = new NavigationPOM(this);

            DesktopPOM _desktopPOM = _navigationPOM.navigateToDesktopSubmenu("Mac");

            _desktopPOM.addToCart();

            _desktopPOM.goToCart();

            _desktopPOM.viewCart();

            Assert.assertEquals(_desktopPOM.tblAddedProductElement.getText(), "iMac");
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            Assert.fail(ex.getMessage());
        }
    }

    @Test(dataProvider = "DesktopDataProvider")//25Apr
    public void selectProductPOMNavigationWithDataDrivenTest(String strDesktopType, String strMacType)
    {
        try {

            _utility.implictWait(driver, Integer.parseInt(_hashmapAppProp.get("noOfSecondToWait")));

            NavigationPOM _navigationPOM = new NavigationPOM(this);

            //Data injected from Data-Provider
            DesktopPOM _desktopPOM = _navigationPOM.navigateToDesktopSubmenu(strDesktopType);

            _desktopPOM.addToCart();

            _desktopPOM.goToCart();

            _desktopPOM.viewCart();

            //Data injected from Data-Provider
            Assert.assertEquals(_desktopPOM.tblAddedProductElement.getText(), strMacType);

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            Assert.fail(ex.getMessage());
        }
    }

    @Test //25Apr
    public void selectProductPOMNavigationWithDataDrivenTableModelTest()
    {
        try {

            _jTableXL = _excelhelper.convertExcelToDataTable(strPathToDatasheet + _hashmapAppProp.get("TestDocDataFineName"), "Sheet1");

            _utility.implictWait(driver, Integer.parseInt(_hashmapAppProp.get("noOfSecondToWait")));

            NavigationPOM _navigationPOM = new NavigationPOM(this);

            //Data injected from Data-Provider
            DesktopPOM _desktopPOM = _navigationPOM.navigateToDesktopSubmenu(_jTableXL.getValueAt(1, _jTableXL.getColumn("Desktop").getModelIndex()).toString());

            _desktopPOM.addToCart();

            _desktopPOM.goToCart();

            _desktopPOM.viewCart();

            //Data injected from Data-Provider
            Assert.assertEquals(_desktopPOM.tblAddedProductElement.getText(), _jTableXL.getValueAt(1, _jTableXL.getColumn("Mac").getModelIndex()).toString());

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            Assert.fail(ex.getMessage());
        }
    }

}