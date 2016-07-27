package pages;

import pages.brands.BrandsPage;
import pages.users.UsersPage;
import pages.users.popup.EditUser;
import utils.WebDriverWrapper;
import utils.WebElementsActions;

public class ApisSystem {

    public WebElementsActions web;
    public LoginPage loginPage;
    public MainPage mainPage;
    public UsersPage usersPage;
    public EditUser editUser;
    public BrandsPage brandsPage;

    public ApisSystem(WebDriverWrapper driverWrapper) {
        web = new WebElementsActions(driverWrapper);
        loginPage = new LoginPage(driverWrapper);
        mainPage = new MainPage(driverWrapper);
        usersPage = new UsersPage(driverWrapper);
        editUser = new EditUser(driverWrapper);
        brandsPage = new BrandsPage(driverWrapper);
    }

}
