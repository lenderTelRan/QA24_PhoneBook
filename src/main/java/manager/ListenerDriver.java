package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class ListenerDriver extends AbstractWebDriverEventListener {
    Logger logger = LoggerFactory.getLogger(ListenerDriver.class);
    public ListenerDriver(Logger logger) {
        this.logger = logger;
    }

    public ListenerDriver() {
        super();
    }

    @Override
    public void beforeGetText(WebElement element, WebDriver driver) {
        super.beforeGetText(element, driver);
        logger.info("Get text from element: " + element.getText());
    }

    @Override
    public void afterGetText(WebElement element, WebDriver driver, String text) {
        super.afterGetText(element, driver, text);
        logger.info("Got the text successful: " + element.getText());
    }

    @Override
    public void beforeFindBy(By by, WebElement element, WebDriver driver) {
        super.beforeFindBy(by, element, driver);
        logger.info("Before find element: " + by);
    }

    @Override
    public void afterFindBy(By by, WebElement element, WebDriver driver) {
        super.afterFindBy(by, element, driver);
        logger.info("After find element: " + by);
    }

    @Override
    public void onException(Throwable throwable, WebDriver driver) {
        super.onException(throwable, driver);
        logger.info("Error");
        logger.info(throwable.getMessage());
        logger.info(throwable.fillInStackTrace().toString());

        int i = new Random().nextInt(1000);
        String link = "src/test/screenshots/screen-" + i + ".png";
        HelperBase helperBase = new HelperBase(driver);
        helperBase.getScreen(link);
        logger.info("Screen with error --> " + link);
    }
}
