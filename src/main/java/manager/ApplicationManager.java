package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
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

    public void init() {
//        wd = new ChromeDriver();
        wd = new EventFiringWebDriver(new ChromeDriver()); //   listener
        logger.info("All tests run in Chrome browser");
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
