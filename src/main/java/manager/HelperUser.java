package manager;

import models.Contact;
import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HelperUser extends HelperBase {

    public HelperUser(WebDriver wd) {
        super(wd);
    }
    public void openLoginRegistrationForm() {
//        WebElement loginButton = wd.findElement(By.xpath("//a[text()='LOGIN']"));
//        loginButton.click();
        click(By.xpath("//a[text()='LOGIN']"));
    }
    public void fillLoginRegistrationForm(String email, String password) {
//        WebElement emailInput = wd.findElement(By.cssSelector("input[name='email']"));
//        WebElement passwordInput = wd.findElement(By.cssSelector("input[name='password']"));

        type(By.cssSelector("input[name='email']"), email);
        type(By.cssSelector("input[name='password']"), password);
    }
    public void pause(int sec) {
        try {
            Thread.sleep(1000L * sec);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void submitLogin() {
        click(By.name("login"));
    }

    public boolean isLogged() {
        return isElementPresent(By.xpath("//button[text()='Sign Out']"));
    }

    public void logOut() {
        click(By.cssSelector("div>button"));
    }


    //------registration------

    public void fillRegistrationForm(User user) {
        type(By.name("email"), user.getEmail());
        type(By.name("password"), user.getPassword());
    }
    public void submitRegistration() {
        click(By.name("registration"));
    }

    //-------add contact--------
    public void clickAdd() {
        click(By.xpath("//a[text()='ADD']"));
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
}
