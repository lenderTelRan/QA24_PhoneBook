package tests;

import manager.DataProviderContact;
import models.Contact;
import models.User;
import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class AddContactsTests extends TestBase {
    @BeforeClass(alwaysRun = true)
    public void preConditions() {
        User user = User.builder().email("test_telran@gmail.com").password("Test@12345").build();

        if(!app.getHelperUser().isLogged()) {
            app.getHelperUser().logIn(user);
        }
    }

    int i = new Random().nextInt(1000);
    @Test(dataProvider = "contactSuccess", dataProviderClass = DataProviderContact.class, groups = {"smoke"})
    public void addNewContact(Contact contact) {
//        Contact contact = Contact.builder()
//                .name("Pit" + i)
//                .lastName("Parker")
//                .phone("0556355" + i)
//                .email("parker" + i + "2@gmail.com")
//                .address("Haifa, Israel")
//                .description("coworker")
//                .build();

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
//        app.getHelperContact().getScreen("src/test/screenshots/screen.png");
        app.getHelperContact().clickSave();

        Assert.assertTrue(app.getHelperContact().isContactAdded(contact.getPhone()));
        if(app.getHelperContact().isContactAdded(contact.getPhone()))
            app.getHelperContact().clickContact(contact.getPhone());
    }

    @Test(dataProvider = "contactFromCSV", dataProviderClass = DataProviderContact.class)
    public void addNewContactWithFile(Contact contact) {

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
//        app.getHelperContact().getScreen("src/test/screenshots/screen.png");
        app.getHelperContact().clickSave();

        Assert.assertTrue(app.getHelperContact().isContactAdded(contact.getPhone()));
        if(app.getHelperContact().isContactAdded(contact.getPhone()))
            app.getHelperContact().clickContact(contact.getPhone());
    }

    @Test(groups = {"smoke"})
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
    @Test(enabled = false, description = "bug")
    public void addNewContactEmptyLastName() {      //bug
        Contact contact = Contact.builder()
                .name("Piter" + i)
                .lastName("")
                .phone("05563556" + i)
                .email("parker" + i + "2@gmail.com")
                .address("Haifa, Israel")
                .description("coworker")
                .build();

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().getScreen("src/test/screenshots/screen" + i + ".png");
        app.getHelperContact().clickSave();

        Assert.assertFalse(app.getHelperContact().isContactAdded(contact.getPhone()));
    }
    @Test(dataProvider = "contactWrongPhone", dataProviderClass = DataProviderContact.class)
    public void addNewContactWrongPhone(Contact contact) {
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().clickSave();
//        app.getHelperContact().isAlertPresent("Phone not valid");
        app.getHelperContact().alertOk();

        Assert.assertFalse(app.getHelperContact().isContactAdded(contact.getPhone()));
    }
}
