package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class RemoveContactTest extends TestBase {
    @BeforeMethod
    public void preConditions() {
        User user = User.builder().email("test_telran@gmail.com").password("Test@12345").build();

        if(!app.getHelperUser().isLogged()) {
            app.getHelperUser().logIn(user);
        }
        app.getHelperContact().provideContacts(); //add contacts
    }
    int i = new Random().nextInt(1000);

    @Test(priority = 1)
    public void removeOneContact() {
        Assert.assertEquals(app.getHelperContact().removeContact(), 1);

    }
    @Test(priority = 2)
    public void removeAllContacts() {
        app.getHelperContact().removeContacts();
        Assert.assertEquals(app.getHelperContact().getMessage(), "No contacts here!");
    }
}
