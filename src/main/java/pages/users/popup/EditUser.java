package pages.users.popup;

import org.openqa.selenium.WebElement;
import pages.Page;
import utils.WebDriverWrapper;

import java.util.ArrayList;
import java.util.List;

public class EditUser extends Page {

    public EditUser(WebDriverWrapper driverWrapper) {
        super(driverWrapper);
    }

    /**
     * Check is pop up window displayed on a page
     */
    public boolean isPopupPresent() {
        return web.isElementPresent("editUser");
    }

    /**
     * Check is pop up window 'Edit user' loaded
     *
     * @return true if pop up window down buttons loaded, otherwise false
     */
    public boolean waitPopupLoaded() {
        return web.waitElementToBeVisibility("editUserDown");
    }

    /**
     * Click on button 'Save' or 'Cancel'
     *
     * @param button where
     *               true - button 'Save'
     *               false - button 'Cancel'
     */
    public void clickButtonSaveOrCancel(boolean button) {
        List<WebElement> buttonsList = web.getElements("editUserButtons");
        if (button) {
            buttonsList.get(0).click();
        } else {
            buttonsList.get(1).click();
        }
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
     * Select and click random position from group list
     */
    public void clickAndSelectGroup() {
        web.clickLink("editUserGroupLink");
        List<WebElement> groupList = web.getElements("editUserGroupList");
        List<WebElement> newGroupList = new ArrayList<>();
        for (WebElement group : groupList) {
            if (!group.getAttribute("value").contains("undefined:undefined")) {
                newGroupList.add(group);
            }
        }
        int random = (int) (Math.random() * newGroupList.size());
        newGroupList.get(random).click();
    }

    /**
     * Select and click random position from role list
     */
    public void clickAndSelectRole() {
        web.clickLink("editUserRoleLink");
        List<WebElement> roleList = web.getElements("editUserRoleList");
        List<WebElement> newRoleList = new ArrayList<>();
        for (WebElement role : roleList) {
            if (!role.getAttribute("value").contains("undefined:undefined")) {
                newRoleList.add(role);
            }
        }
        int random = (int) (Math.random() * newRoleList.size());
        newRoleList.get(random).click();
    }

    /**
     * Wait for edit user loading change on invisibility
     */
    public void waitInvisibilityLoading() {
        web.waitDisappearElement("editUserLoading");
    }

    public boolean isErrorMessagePresent() {
        return web.isElementPresent("errorMessage");
    }

    /**
     * Click on Enabled from popup 'Edit user'
     */
    public void clickOnEnabled() {
        web.clickLink("editUserEnabled");
    }

    public boolean waitInvisibilityPopup() {
        return web.waitDisappearElement("editUserPopup");
    }

    public void closeEditUserPopup() {
        web.clickLink("editUserClose");
    }

}
