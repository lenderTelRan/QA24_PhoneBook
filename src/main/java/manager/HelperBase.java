package manager;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
}
