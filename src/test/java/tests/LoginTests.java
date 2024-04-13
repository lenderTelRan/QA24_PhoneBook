package tests;

import manager.DataProviderUser;
import models.User;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LoginTests extends TestBase {

    @BeforeMethod
    public void preConditions() {
        if(app.getHelperUser().isLogged())
            app.getHelperUser().logOut();
    }

    String email = "test_telran@gmail.com";
    String password = "Test@12345";
    String wrongEmail = "test_telrangmail.com";
    String wrongPassword = "test12345";

    @Test(dataProvider = "loginData", dataProviderClass = DataProviderUser.class)
    public void loginSuccess(String emailProvider, String passwordProvider) {
//        logger.info("Start test --> ");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(emailProvider, passwordProvider);
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isLogged());
//        logger.info("End test --> ");
    }

    @Test(dataProvider = "loginModels", dataProviderClass = DataProviderUser.class)
    public void loginSuccessModels(User user) {
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginFormWithUser(user);
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isLogged());
    }

    @Test(dataProvider = "loginFile", dataProviderClass = DataProviderUser.class)  // data from file csv
    public void loginSuccessModelsFile(User user) {
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginFormWithUser(user);
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isLogged());
    }

    @Test
    public void loginWrongEmail() {
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(wrongEmail, password);
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password"));
//        Assert.assertFalse(app.getHelperUser().isLogged());
    }
    @Test
    public void loginWrongPassword() {
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(email, wrongPassword);
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password"));
//        Assert.assertFalse(app.getHelperUser().isLogged());
    }
    @Test
    public void loginUnregisteredUser() {
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("ran@gmail.com", password);
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password"));
//        Assert.assertFalse(app.getHelperUser().isLogged());
    }
}
