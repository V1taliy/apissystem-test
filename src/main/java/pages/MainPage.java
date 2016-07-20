package pages;

import utils.WebDriverWrapper;

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

}
