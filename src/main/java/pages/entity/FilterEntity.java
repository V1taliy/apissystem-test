package pages.entity;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.Page;
import utils.PropertyLoader;
import utils.WebDriverWrapper;

import java.util.List;

public class FilterEntity extends Page {

    private static final Logger log = Logger.getLogger(FilterEntity.class);

    public FilterEntity(WebDriverWrapper driverWrapper) {
        super(driverWrapper);
    }

    /**
     * Click on button 'Search' or 'Reset' on entity 'Filter'
     *
     * @param button where
     *               true - Search
     *               false - Reset
     */
    public void clickSearchOrResetButton(boolean button) {
        List<WebElement> buttonsList = web.getElements("buttonsList");
        if (button) {
            log.info(String.format("click button < %s >", buttonsList.get(0).getText()));
            buttonsList.get(0).click();
        } else {
            log.info(String.format("click button < %s >", buttonsList.get(1).getText()));
            buttonsList.get(1).click();
        }
    }

    /**
     * Wait for processing disappear
     */
    public void waitLoadedAttributeToBeEmptyClass() {
        WebElement element = web.getElement("isLoadedElement");
        WebDriverWait wait = new WebDriverWait(driverWrapper.getOriginalDriver(),
                Long.parseLong(PropertyLoader.loadProperty("wait.timeout15sec")));
        wait.until(ExpectedConditions.attributeToBe(element, "class", ""));
    }

    /**
     * Check is processing element present on the page
     *
     * @return true if processing element present, otherwise false
     */
    public boolean isLoadedClassHaveAttributeInClass() {
        WebElement element = web.getElement("isLoadedElement");
        return element.getAttribute("class").contains("traditional");
    }

}
