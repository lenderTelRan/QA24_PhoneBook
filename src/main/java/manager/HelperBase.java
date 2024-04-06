package manager;

import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.io.File;
import java.io.IOException;
import java.util.List;

public class HelperBase {
    WebDriver wd;

    public HelperBase(WebDriver wd) {
        this.wd = wd;
    }
    public void type(By locator, String text) {
        WebElement element = wd.findElement(locator);
            element.click();
            element.clear();

        if (text != null) {
            element.sendKeys(text);
        }
    }
    public void click(By locator) {
        WebElement element = wd.findElement(locator);
        element.click();
    }

    public void pause(int sec) {
        try {
            Thread.sleep(1000L * sec);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isElementPresent(By locator) {
        List<WebElement> list = wd.findElements(locator);
        return list.size() > 0;
    }
    public boolean isAlertPresent(String text) {
        Alert alert = new WebDriverWait(wd, 10)
                .until(ExpectedConditions.alertIsPresent());

        if(alert != null && alert.getText().contains(text)) {
            alert.accept();
            return true;
        }
        return false;
    }

    /*
    screenshot method
     */
    public void getScreen(String path) {
        TakesScreenshot screen = (TakesScreenshot) wd;
        File temp = screen.getScreenshotAs(OutputType.FILE);
        try {
            Files.copy(temp, new File(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
