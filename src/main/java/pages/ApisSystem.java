package pages;

import utils.WebDriverWrapper;
import utils.WebElementsActions;

public class ApisSystem {

    public WebElementsActions web;
    public LoginPage loginPage;
    public MainPage mainPage;

    public ApisSystem(WebDriverWrapper driverWrapper) {
        web = new WebElementsActions(driverWrapper);
        loginPage = new LoginPage(driverWrapper);
        mainPage = new MainPage(driverWrapper);
    }

}
