import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class Forgot extends TestSuite {

    @Test
    public void forgot() {
        new HeaderFooter().headerFooter();

        $(By.id("email")).sendKeys(email);
        $(By.className("btn")).click();

        new HeaderFooter().headerFooter();

        String chord = Keys.chord(Keys.LEFT_CONTROL, Keys.ENTER);
        $(By.className("nav-item-text")).closest("a").sendKeys(chord);
        ArrayList<String> tabs = new ArrayList<>(getWebDriver().getWindowHandles());
        getWebDriver().switchTo().window(tabs.get(1));

        open("https://google.com/gmail/about/");
        $(By.className("gmail-nav__nav-link__sign-in")).click();
        $(By.id("Email")).clear();
        $(By.id("Email")).sendKeys("achernenko@s-pro.io");
        $(By.id("next")).click();
        $(By.id("Passwd")).clear();
        $(By.id("Passwd")).sendKeys("El413423");
        $(By.id("Passwd")).sendKeys(Keys.ENTER);
        $$(By.cssSelector(".yf.xY")).get(0).click();
        if ($(By.linkText("change password")).isDisplayed()) {
            System.out.println("Change password link ok");
            getWebDriver().close();
            getWebDriver().switchTo().window(tabs.get(0));
        } else {
            System.out.println("Change password link fail");
            getWebDriver().close();
            getWebDriver().switchTo().window(tabs.get(0));
        }

        $(By.linkText("Ok, thanks!")).click();
    }
}
