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

    public void fillLoginFormWithUser(User user) {
        type(By.cssSelector("input[name='email']"), user.getEmail());
        type(By.cssSelector("input[name='password']"), user.getPassword());
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

    public void logIn(User user) {
        openLoginRegistrationForm();
        fillLoginFormWithUser(user);
        submitLogin();
    }
}
