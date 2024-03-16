package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {
    String email = "test_telran@gmail.com";
    String password = "Test@12345";

    @Test
    public void loginSuccess() {
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(email, password);
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isLogged());
    }
}
