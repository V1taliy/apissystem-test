package pages;

import org.openqa.selenium.WebElement;
import utils.WebDriverWrapper;

import java.util.List;

public class UsersPage extends Page {

    public UsersPage(WebDriverWrapper driverWrapper) {
        super(driverWrapper);
    }

    // methods for FILTER

    public void inputUserName(String userName) {
        web.clearAndInput("filterUserNameField", userName);
    }

    public void inputFirstName(String firstName) {
        web.clearAndInput("filterFirstNameField", firstName);
    }

    public void inputLastName(String lastName) {
        web.clearAndInput("filterLastNameField", lastName);
    }

    public void inputEmail(String email) {
        web.clearAndInput("filterEmailField", email);
    }

    public void clickAndSelectValueFromGroup() {
        web.clickLink("filterGroupField");
        // all values from group list
        List<WebElement> groupList = web.getElements("filterGroupDropDownList");
        // generate random value position
        int random = (int) (Math.random() * groupList.size());
        // click on this value
        groupList.get(random).click();
    }

    /**
     * Click button 'Search' or 'Reset'
     *
     * @param selectButton button, where
     *                     true - Search
     *                     false - Reset
     */
    public void clickButtonSearchOrReset(boolean selectButton) {
        if (selectButton) {
            web.clickButton("filterButtonSearch");
        } else {
            web.clickButton("filterButtonReset");
        }
    }

    // methods for LIST

    /**
     * Select and click table sort
     *
     * @param tableNumber number of table, where
     *                    1 - select all checkbox
     *                    2 - ID
     *                    3 - user name
     *                    4 - first name
     *                    5 - last name
     *                    6 - email
     *                    7 - group
     *                    8 - enable
     *                    9 - role
     */
    public void selectTableSort(int tableNumber) {
        List<WebElement> tableList = web.getElements("listDataTableMainTabs");
        tableList.get(tableNumber - 1).click();
    }

    /**
     * Click user checkbox
     *
     * @param userPosition user position in the desk table
     */
    public void clickUserCheckbox(int userPosition) {
        List<WebElement> checkboxList = web.getElements("checkboxList");
        checkboxList.get(userPosition - 1).click();
    }


}
