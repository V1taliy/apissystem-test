package pages.popups.comment;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.Page;
import utils.PropertyLoader;
import utils.WebDriverWrapper;

public class ViewComment extends Page {

    private static final Logger log = Logger.getLogger(ViewComment.class);

    public ViewComment(WebDriverWrapper driverWrapper) {
        super(driverWrapper);
    }

    /**
     * Get comment from pop up for comment
     */
    public String getCommentText() {
        WebElement viewComment = web.getElement("withdrawalCommentViewText");
        log.info(String.format("comment text: < %s >", viewComment.getText()));
        return viewComment.getText();
    }

    /**
     * Check is comment displayed on a page
     */
    public boolean isCommentDisplayed() {
        WebElement comment = web.getElement("withdrawalCommentViewText");
        WebDriverWait wait = new WebDriverWait(driverWrapper.getOriginalDriver(),
                Long.parseLong(PropertyLoader.loadProperty("wait.timeout3sec")));
        wait.until(ExpectedConditions.visibilityOf(comment));
//        if (comment.isDisplayed()) {
//            return true;
//        } else {
//            return false;
//        }
        return comment.isDisplayed();
    }

}
