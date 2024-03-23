package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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

    @Test
    public void loginSuccess() {
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(email, password);
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
