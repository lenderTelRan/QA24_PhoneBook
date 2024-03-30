package manager;

import models.Contact;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.nio.charset.Charset;
import java.util.List;

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

        for(WebElement element : list) {
            if(element.getText().equals(phone))
                click(By.cssSelector("h3"));
        }
        pause(3);
    }

    public void alertOk() {
        pause(2);
        Alert alert = wd.switchTo().alert();
        alert.accept();
    }
}
