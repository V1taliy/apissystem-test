package pages;

import pages.brands.BrandsPage;
import pages.entity.FilterEntity;
import pages.entity.ListEntity;
import pages.entity.buttons.ListNavigationButtons;
import pages.groups.GroupsPage;
import pages.entity.groups.PagesEntity;
import pages.entity.groups.WithdrawalEntity;
import pages.message.GreenMessage;
import pages.popups.MainPopup;
import pages.popups.brands.CreateBrand;
import pages.popups.brands.DeleteBrand;
import pages.popups.brands.EditBrand;
import pages.deskExpirationTime.DeskExpirationTime;
import pages.popups.comment.ViewComment;
import pages.popups.deskExpirationTime.Edit;
import pages.popups.groups.CreateGroup;
import pages.popups.groups.EditGroup;
import pages.popups.withdrawal.ApprovePopup;
import pages.popups.withdrawal.AssignPopup;
import pages.popups.withdrawal.DeclinePopup;
import pages.popups.withdrawal.WithdrawalMainPopup;
import pages.users.UsersPage;
import pages.popups.users.EditDesks;
import pages.popups.users.EditUser;
import pages.withdrawal.WithdrawalPage;
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
    public FilterEntity filterEntity;
    public MainPopup mainPopup;
    public CreateGroup createGroup;
    public EditGroup editGroup;
    public GreenMessage greenMessage;
    public WithdrawalPage withdrawalPage;
    public GroupsPage groupsPage;
    public PagesEntity pagesEntity;
    public WithdrawalEntity withdrawalEntity;
    public AssignPopup assignPopup;
    public ViewComment viewComment;
    public WithdrawalMainPopup withdrawalMainPopup;
    public DeclinePopup declinePopup;
    public ListNavigationButtons listNavigationButtons;
    public ApprovePopup approvePopup;

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
        filterEntity = new FilterEntity(driverWrapper);
        greenMessage = new GreenMessage(driverWrapper);
        withdrawalPage = new WithdrawalPage(driverWrapper);
        groupsPage = new GroupsPage(driverWrapper);
        pagesEntity = new PagesEntity(driverWrapper);
        withdrawalEntity = new WithdrawalEntity(driverWrapper);
        assignPopup = new AssignPopup(driverWrapper);
        viewComment = new ViewComment(driverWrapper);
        withdrawalMainPopup = new WithdrawalMainPopup(driverWrapper);
        declinePopup = new DeclinePopup(driverWrapper);
        listNavigationButtons = new ListNavigationButtons(driverWrapper);
        approvePopup = new ApprovePopup(driverWrapper);
    }

}
