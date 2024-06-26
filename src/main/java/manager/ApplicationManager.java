package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {

//    WebDriver wd;
    EventFiringWebDriver wd;
    Logger logger = LoggerFactory.getLogger(ApplicationManager.class);
    HelperUser helperUser;
    HelperContact helperContact;
    String browser;

    public ApplicationManager(String browser) {
        this.browser = browser;
    }

    public void init() {

        if(browser.equals(BrowserType.CHROME)) {
//          wd = new ChromeDriver();
            wd = new EventFiringWebDriver(new ChromeDriver()); //   listener
            logger.info("All tests run in Chrome browser");
        }
        else if(browser.equals(BrowserType.FIREFOX)) {
            wd = new EventFiringWebDriver(new FirefoxDriver());
            logger.info("All tests run in FIREFOX browser");
        }
        else if(browser.equals(BrowserType.EDGE)) {
            wd = new EventFiringWebDriver(new EdgeDriver());
            logger.info("All tests run in EDGE browser");
        }


        helperUser = new HelperUser(wd);
        helperContact = new HelperContact(wd);
        wd.manage().window().maximize();
        wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wd.navigate().to("https://telranedu.web.app/");
        logger.info("Get link --> " + wd.getCurrentUrl());
        wd.register(new ListenerDriver(logger)); //     listener
    }

    public HelperUser getHelperUser() {
        return helperUser;
    }

    public HelperContact getHelperContact() {
        return helperContact;
    }

    public void stop() {
        wd.quit();
    }
}
