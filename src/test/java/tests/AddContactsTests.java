package tests;

import models.Contact;
import models.User;
import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class AddContactsTests extends TestBase {
    @BeforeClass
    public void preConditions() {
        User user = User.builder().email("test_telran@gmail.com").password("Test@12345").build();

        if(!app.getHelperUser().isLogged()) {
            app.getHelperUser().logIn(user);
        }
    }

    int i = new Random().nextInt(1000);
    @Test
    public void addNewContact() {
        Contact contact = Contact.builder()
                .name("Piter" + i)
                .lastName("Parker")
                .phone("0556355" + i)
                .email("parker" + i + "2@gmail.com")
                .address("Haifa, Israel")
                .description("coworker")
                .build();

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().clickSave();

        Assert.assertTrue(app.getHelperContact().isContactAdded(contact.getPhone()));
        if(app.getHelperContact().isContactAdded(contact.getPhone()))
            app.getHelperContact().clickContact(contact.getPhone());
    }

    @Test
    public void addNewContactEmptyName() {
        Contact contact = Contact.builder()
                .name("")
                .lastName("Parker")
                .phone("0556355" + i)
                .email("parker" + i + "2@gmail.com")
                .address("Haifa, Israel")
                .description("coworker")
                .build();

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().clickSave();

        Assert.assertFalse(app.getHelperContact().isContactAdded(contact.getPhone()));
    }

    @Test
    public void addNewContactEmptyAddress() {
        Contact contact = Contact.builder()
                .name("Piter")
                .lastName("Parker")
                .phone("0556355" + i)
                .email("parker" + i + "2@gmail.com")
                .address("")
                .description("coworker")
                .build();

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().clickSave();

        Assert.assertFalse(app.getHelperContact().isContactAdded(contact.getPhone()));
    }
    @Test
    public void addNewContactEmptyLastName() {
        Contact contact = Contact.builder()
                .name("Piter")
                .lastName("")
                .phone("0556355" + i)
                .email("parker" + i + "2@gmail.com")
                .address("Haifa, Israel")
                .description("coworker")
                .build();

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().clickSave();

        Assert.assertFalse(app.getHelperContact().isContactAdded(contact.getPhone()));
    }
    @Test
    public void addNewContactWrongPhone() {
        Contact contact = Contact.builder()
                .name("Piter")
                .lastName("Parker")
                .phone("05365421")
                .email("parker" + i + "2@gmail.com")
                .address("")
                .description("coworker")
                .build();

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().clickSave();
//        app.getHelperContact().isAlertPresent("Phone not valid");
        app.getHelperContact().alertOk();

        Assert.assertFalse(app.getHelperContact().isContactAdded(contact.getPhone()));
    }
}
