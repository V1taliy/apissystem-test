package pages;

import pages.brands.BrandsPage;
import pages.brands.popup.CreateBrand;
import pages.brands.popup.DeleteBrand;
import pages.brands.popup.EditBrand;
import pages.deskExpirationTime.DeskExpirationTime;
import pages.deskExpirationTime.popup.Edit;
import pages.users.UsersPage;
import pages.users.popup.EditDesks;
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
    public EditBrand editBrand;
    public CreateBrand createBrand;
    public DeleteBrand deleteBrand;
    public DeskExpirationTime deskExpirationTime;
    public Edit edit;
    public EditDesks editDesks;
    public ListEntity listEntity;

    public ApisSystem(WebDriverWrapper driverWrapper) {
        web = new WebElementsActions(driverWrapper);
        loginPage = new LoginPage(driverWrapper);
        mainPage = new MainPage(driverWrapper);
        usersPage = new UsersPage(driverWrapper);
        editUser = new EditUser(driverWrapper);
        brandsPage = new BrandsPage(driverWrapper);
        editBrand = new EditBrand(driverWrapper);
        createBrand = new CreateBrand(driverWrapper);
        deleteBrand = new DeleteBrand(driverWrapper);
        deskExpirationTime = new DeskExpirationTime(driverWrapper);
        edit = new Edit(driverWrapper);
        editDesks = new EditDesks(driverWrapper);
        listEntity = new ListEntity(driverWrapper);
    }

}
