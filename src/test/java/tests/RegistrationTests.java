package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import java.util.Random;

public class RegistrationTests extends TestBase {
    int randomNum = new Random().nextInt(1000);

    @BeforeMethod
    public void preConditions() {
        if (app.getHelperUser().isLogged())
            app.getHelperUser().logOut();
    }

    @Test
    public void registrationPositive() {
        User user = User.builder()
                .email("piterTest" + randomNum + "@gmail.com")
                .password("Piter12345678@")
                .build();

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().submitRegistration();

        app.getHelperUser().pause(3);
        Assert.assertTrue(app.getHelperUser().isLogged());
    }

    @Test
    public void registrationWrongEmail() {
        User user = User.builder()
                .email("piterTest" + randomNum + "gmail.com")
                .password("Piter12345678@")
                .build();

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().submitRegistration();


        app.getHelperUser().pause(3);
        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password"));
    }

    @Test
    public void registrationWrongPassword() {
        User user = User.builder()
                .email("piterTest" + randomNum + "@gmail.com")
                .password("12345678")
                .build();

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().submitRegistration();

        app.getHelperUser().pause(3);
        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password"));
    }

    @Test
    public void registrationExistsUser() {
        User user = User.builder()
                .email("margo@gmail.com")
                .password("Mmar123456$")
                .build();

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().submitRegistration();

        app.getHelperUser().pause(3);
        Assert.assertTrue(app.getHelperUser().isAlertPresent("User already exist"));
    }
}
