package pages;

import pages.brands.BrandsPage;
import pages.popups.MainPopup;
import pages.popups.brands.CreateBrand;
import pages.popups.brands.DeleteBrand;
import pages.popups.brands.EditBrand;
import pages.deskExpirationTime.DeskExpirationTime;
import pages.popups.deskExpirationTime.Edit;
import pages.popups.groups.CreateGroup;
import pages.popups.groups.EditGroup;
import pages.users.UsersPage;
import pages.popups.users.EditDesks;
import pages.popups.users.EditUser;
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
    public MainPopup mainPopup;
    public CreateGroup createGroup;
    public EditGroup editGroup;

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
        mainPopup = new MainPopup(driverWrapper);
        createGroup = new CreateGroup(driverWrapper);
        editGroup = new EditGroup(driverWrapper);
    }

}
