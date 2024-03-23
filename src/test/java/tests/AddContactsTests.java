package tests;

import models.Contact;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddContactsTests extends TestBase {
    @BeforeMethod
    public void preConditions() {
        if(!app.getHelperUser().isLogged()) {
            new LoginTests().loginSuccess();
            app.getHelperUser().clickAdd();
        }
    }
    @Test
    public void addNewContact() {
        Contact contact = Contact.builder()
                .name("Piter")
                .lastName("Parker")
                .phone("0556355533")
                .email("piter2@gmail.com")
                .address("Haifa, Israel")
                .description("coworker")
                .build();

        app.getHelperUser().fillContactForm(contact);
        app.getHelperUser().clickSave();
    }
}
