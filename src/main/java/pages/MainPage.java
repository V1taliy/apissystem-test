package pages;

import org.openqa.selenium.WebElement;
import utils.WebDriverWrapper;

import java.util.List;

public class MainPage extends Page {

    public MainPage(WebDriverWrapper driverWrapper) {
        super(driverWrapper);
    }

    public boolean isWelcomeToApisSystemPresent() {
        return web.isElementPresent("welcomeToApisSystem");
    }

    public void clickLogoutButton() {
        web.clickButton("logoutButton");
    }

    /**
     * Click on item from navigation panel
     *
     * @param navItemNumber navigation item number, where
     *                      1 - Withdrawal
     *                      2 - Brands
     *                      3 - Users
     *                      4 - Groups
     *                      5 - Desk exp.time
     *                      6 - Admin Menu
     *                      7 - Logs
     *                      8 - Email (must be clicked Logs)
     *                      9 - Client (must be clicked Logs)
     */
    public void clickOnNavigationItem(int navItemNumber) {
        List<WebElement> navItemList = web.getElements("navigationItemList");
        // must opened 'Logs' items
        if (navItemNumber == 8 || navItemNumber == 9) {
            // clicked on 'Logs' item
            navItemList.get(6).click();
            web.waitElementToBeClickable(navItemList.get(navItemNumber - 1));
        } else {
            navItemList.get(navItemNumber - 1).click();
        }
    }

    public void scrollDown() {
        web.scrollToElementBy("footerApissystem");
    }

}
