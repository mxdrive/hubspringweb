import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.util.ArrayList;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class HeaderFooter {

    public void headerFooter() {

        //header
        if ($(By.className("logo")).getAttribute("href").equals("http://hubspringhealth.com/")) {
            System.out.println("Logo link ok");
        } else {
            System.out.println("Logo link fail");
            System.out.println($(By.className("logo")).getAttribute("href"));
        }

        if ($(By.className("nav-item-text")).closest("a").getAttribute("href").equals("http://hubspringhealth.com/")) {
            System.out.println("Site logo link ok");
        } else {
            System.out.println($(By.className("nav-item-text")).closest("a").getAttribute("href"));
            System.out.println("Site logo link fail");
        }

        if ($$(".nav-item-text").last().closest("a").getAttribute("href").equals("mailto:support@hubspring.com")) {
            System.out.println("Mailto button ok");
        } else {
            System.out.println("Mailto button fail");
            System.out.println($$(".nav-item-text").last().closest("a").getAttribute("href"));
        }

        //footer
        String chord = Keys.chord(Keys.COMMAND, Keys.ENTER);
        $(By.linkText("Terms of Use")).sendKeys(chord);
        ArrayList<String> tabs = new ArrayList<> (getWebDriver().getWindowHandles());
        getWebDriver().switchTo().window(tabs.get(1));
        if (WebDriverRunner.source().contains("Please read this End User Terms of Use")) {
            System.out.println("Terms of Use link ok");
            getWebDriver().close();
            getWebDriver().switchTo().window(tabs.get(0));
        } else {
            System.out.println("Terms of Use link fail");
            open("http://dev.elhubio.com");
        }

        $(By.linkText("Privacy Policy")).sendKeys(chord);
        tabs = new ArrayList<> (getWebDriver().getWindowHandles());
        getWebDriver().switchTo().window(tabs.get(1));
        if (WebDriverRunner.source().contains("Hubspring (“Hubspring,” “we” or “us”) is committed to protecting the privacy and security of your personally identifying information")) {
            System.out.println("Privacy Policy link ok");
            getWebDriver().close();
            getWebDriver().switchTo().window(tabs.get(0));
        } else {
            System.out.println("Privacy Policy link fail");
            open("http://dev.elhubio.com");
        }
    }
}
