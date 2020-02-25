package pom;

import config.SeleniumConfig;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.*;
import java.util.List;

public class NavigationPOM {

    //Object/Variables

    SeleniumConfig _selConfig;

    //end region

    public NavigationPOM(SeleniumConfig _seleniumConfig)
    {
        PageFactory.initElements(_seleniumConfig.driver, this);
        _selConfig = _seleniumConfig;
    }

    //Elements

    @FindBy(how = How.LINK_TEXT, using = "Desktops")
    private WebElement anchorDesktopElement;

    @FindBy(how = How.PARTIAL_LINK_TEXT, using = "Mac")
    private WebElement anchorMacElement;

    @FindBy(how = How.XPATH, using = ".//ul[@class='nav navbar-nav']/li[1]/div/div/ul/li/a")
    private List<WebElement> anchorListOfDesktopSubmenu;

    //end region

    //Action Methods

    private void clickOnDesktopMenu() throws InterruptedException
    {
        anchorDesktopElement.click();

        Thread.sleep(2000);
    }

    public DesktopPOM navigateToDesktopSubmenu(String strDesktopSubMenuOption)  throws InterruptedException
    {
        clickOnDesktopMenu();

        for (WebElement _element: anchorListOfDesktopSubmenu) {

            if(_element.getText().substring(0, _element.getText().indexOf(' ')).equalsIgnoreCase(strDesktopSubMenuOption))
            {
                _element.click();
                break;
            }

        }

        Thread.sleep(2000);

        return new DesktopPOM(_selConfig);
    }

    //end region
}
