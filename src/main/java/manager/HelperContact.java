package manager;

import models.Contact;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Random;

public class HelperContact extends HelperBase {
    public HelperContact(WebDriver wd) {
        super(wd);
    }

    public void fillContactForm(Contact contact) {
        type(By.cssSelector("input[placeholder='Name']"), contact.getName());
        type(By.cssSelector("input[placeholder='Last Name']"), contact.getLastName());
        type(By.cssSelector("input[placeholder='Phone']"), contact.getPhone());
        type(By.cssSelector("input[placeholder='email']"), contact.getEmail());
        type(By.cssSelector("input[placeholder='Address']"), contact.getAddress());
        type(By.cssSelector("input[placeholder='description']"), contact.getDescription());
    }

    public void clickSave() {
        click(By.cssSelector("div.add_form__2rsm2 > button"));
    }

    public void openContactForm() {
        click(By.xpath("//a[text()='ADD']"));
    }

    public boolean isContactAdded(String phone) {
        List<WebElement> list = wd.findElements(By.cssSelector(".contact-item_card__2SOIM > h3"));

        for(WebElement element : list) {
            if(element.getText().equals(phone))
                return true;
        }
        return false;
    }
    public void clickContact(String phone) {
        List<WebElement> list = wd.findElements(By.cssSelector(".contact-item_card__2SOIM > h3"));
        int count = 1;

        if(list.size() > 0) {
            for (WebElement element : list) {
                if (element.getText().equals(phone)) {
                    click(By.xpath("//div[@class='contact-item_card__2SOIM'][" + count + "]"));
                }
                count++;
            }
        }
        pause(3);
    }

    public String getMessage() {
        return wd.findElement(By.cssSelector(".contact-page_message__2qafk > h1")).getText();
    }

    public void alertOk() {
        pause(2);
        Alert alert = wd.switchTo().alert();
        alert.accept();
    }

    public int removeContact() {
        int before = countOfContacts();
        click(By.cssSelector(".contact-item_card__SOIM"));
        click(By.xpath("//button[text()='Remove']"));
        pause(2);
        int after = countOfContacts();
        return before - after;
    }

    private int countOfContacts() {
        return wd.findElements(By.cssSelector(".contact-item_card__SOIM")).size();
    }

    public void removeContacts() {
        while(countOfContacts() != 0) {
            removeContact();
        }
    }

    public void provideContacts() {
        if(countOfContacts() < 3) {
            for (int i = 0; i < 3; i++) {
                addOneContact();
            }
        }

    }

    private void addOneContact() {
        int i = new Random().nextInt(100) + 100;
        Contact contact = Contact.builder()
                .name("Harry" + i)
                .lastName("Potter")
                .phone("0233665" + i)
                .email("harry" + i + "@gmail.com")
                .address("Hogwards")
                .build();
        openContactForm();
        fillContactForm(contact);
        clickSave();
        pause(2);
    }
}
