package pages.popups.users;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.popups.MainPopup;
import utils.PropertyLoader;
import utils.WebDriverWrapper;

import java.util.ArrayList;
import java.util.List;

public class EditUser extends MainPopup {

    private static final Logger log = Logger.getLogger(EditUser.class);

    public EditUser(WebDriverWrapper driverWrapper) {
        super(driverWrapper);
    }

    /**
     * Clear and (no) input data in first name field from pop up 'Edit User'
     *
     * @param status where
     *               true - clear field and input data
     *               false - only clear field
     * @param data   data to first name field
     */
    public void inputFistName(boolean status, String data) {
        if (status) {
            web.clearAndInput("editUserFirstNameField", data);
        } else {
            web.clear("editUserFirstNameField");
        }
    }

    /**
     * Clear and (no) input data in last name field from pop up 'Edit User'
     *
     * @param status where
     *               true - clear field and input data
     *               false - only clear field
     * @param data   data to last name field
     */
    public void inputLastName(boolean status, String data) {
        if (status) {
            web.clearAndInput("editUserLastNameField", data);
        } else {
            web.clear("editUserLastNameField");
        }
    }

    public String isFirstNameEmpty() {
        return web.getElement("editUserFirstNameField").getAttribute("value");
    }

    public String isLastNameEmpty() {
        return web.getElement("editUserLastNameField").getAttribute("value");
    }

    /**
     * Click on Brands field
     */
    public void clickOnBrandsField() {
        web.clickLink("editUserBrandsField");
    }

    /**
     * Click on select brand from drop down list
     *
     * @param brandPosition brand position on drop down list
     */
    public String clickOnSelectBrand(int brandPosition) {
        List<WebElement> brandsList = web.getElements("editUserBrandsList");
        String brandName = brandsList.get(brandPosition).getText();
        log.info(String.format("select brand < %s >", brandName));
        brandsList.get(brandPosition).click();
        return brandName;
    }

    public void deleteSelectBrand(int brandPosition) {
        List<WebElement> brandsList = web.getElements("editUserDeleteBrandsList");
        log.info(String.format("delete brand < %s >", brandsList.get(brandPosition - 1).getText()));
        brandsList.get(brandPosition - 1).click();
    }

    public String getAddBrandName(int brandPosition) {
        List<WebElement> addBrandsNames = web.getElements("editUserAddBrandsList");
        log.info(String.format("brand name < %s >", addBrandsNames.get(brandPosition - 1).getText()));
        return addBrandsNames.get(brandPosition - 1).getText();
    }

    /**
     * Select and click random position from group list
     */
    public void clickAndSelectGroup() {
        web.clickLink("editUserGroupLink");
        List<WebElement> groupList = web.getElements("editUserGroupList");
        int random = (int) (Math.random() * groupList.size());
        if (groupList.get(random).getAttribute("value").contains("undefined:undefined")) {
            groupList.get(++random).click();
        } else {
            groupList.get(random).click();
        }
    }

    /**
     * Select group by index and click
     *
     * @param groupPosition group position from drop down list
     */
    public void clickAndSelectGroup(int groupPosition) {
        web.clickLink("editUserGroupLink");
        List<WebElement> groupList = web.getElements("editUserGroupList");
        log.info(String.format("group select < %s >",
                groupList.get(groupPosition - 1).getText()));
        groupList.get(groupPosition - 1).click();
    }

    /**
     * Select group by group name and click
     *
     * @param groupName group name from drop down list
     */
    public void clickAndSelectGroup(String groupName) {
        web.clickLink("editUserGroupLink");
        List<WebElement> groupList = web.getElements("editUserGroupList");
        for (WebElement element : groupList) {
            log.info(String.format("group: < %s >", element.getText()));
            if (element.getText().equals(groupName)) {
                log.info(String.format("group select < %s >", element.getText()));
                element.click();
                return;
            }
        }
    }

    /**
     * Select value 'Select user group'
     * from group drop down list on 'Edit user' popup
     */
    public void makeUserGroupEmpty() {
        web.clickLink("editUserGroupLink");
        web.clickLink("editUserGroupSelectUserGroup");
    }

    /**
     * Select and click random position from role list
     */
    public void clickAndSelectRole() {
        web.clickLink("editUserRoleLink");
        List<WebElement> roleList = web.getElements("editUserRoleList");
        int random = (int) (Math.random() * roleList.size());
        roleList.get(random).click();
    }

    /**
     * Wait for edit user loading change on invisibility
     */
    public void waitInvisibilityLoading() {
        web.waitDisappearElement("editUserLoading");
    }

    public void waitInvisibilityToaster() {
        WebElement element = web.getElement("editUserToaster");
        WebDriverWait wait = new WebDriverWait(driverWrapper.getOriginalDriver(),
                Long.parseLong(PropertyLoader.loadProperty("wait.timeout3sec")));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
//        web.waitDisappearElement("editUserToaster");
    }

    public boolean isErrorMessagePresent() {
        return web.isElementPresent("errorMessage");
    }

    /**
     * Click on Enabled from popup 'Edit user'
     */
    public void clickOnEnabled() {
        web.selectCheckBox("editUserEnabled");
    }

}
