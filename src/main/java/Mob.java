import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.io.File;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.codeborne.selenide.WebDriverRunner.setWebDriver;
import static com.codeborne.selenide.WebDriverRunner.source;

public class Mob {

    @Test
    public void mob() throws InterruptedException {

        String emailName = "email";
        String nextBtn = ".a0-primary";
        String pass = ".password-input";
        String settings = ".ion-gear-b";
        String deleteNodeBtns = ".ion-trash-b"; //get(0)
        String del = ".ion-close";
        String btns = ".button-default";

        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        setWebDriver(new CustomWebDriverProvider().createDriver(capabilities));
        getWebDriver().manage().window().maximize();

        Selenide.open("http://localhost:9000");
        $(By.name(emailName)).setValue("achernenko@s-pro.io");
        $(nextBtn).click();
        $(pass).setValue("Password1!");
        $(".button-block").click();
        Thread.sleep(5000);
        $(settings).click();
//        new WebDriverWait(WebDriverRunner.getWebDriver(), 5).until(ExpectedConditions.visibilityOf($(By.id("#Group"))));
//        new WebDriverWait(WebDriverRunner.getWebDriver(), 5).until(ExpectedConditions.invisibilityOf($(By.id("#Group"))));

        try {
            new WebDriverWait(WebDriverRunner.getWebDriver(), 5).until(ExpectedConditions.elementToBeClickable($$(deleteNodeBtns).get(0)));
        } catch (Exception ignored) {
        }
        $$(deleteNodeBtns).get(0).click();
        List<SelenideElement> elements = $$(".title-text");
        System.out.println(elements.size());
        for (int i = 0; i <= elements.size(); i++) {
            System.out.println("elements.size() " + elements.size());
            System.out.println(i);
            SelenideElement e = elements.get(i);
            Thread.sleep(1000);
            new WebDriverWait(WebDriverRunner.getWebDriver(), 5).until(ExpectedConditions.elementToBeClickable(e));
            e.click();
            $(".button-positive").click();
            Thread.sleep(500);
//            new WebDriverWait(WebDriverRunner.getWebDriver(), 2).until(ExpectedConditions.invisibilityOf($(".button-positive")));
            try {
                new WebDriverWait(WebDriverRunner.getWebDriver(), 10).until(ExpectedConditions.visibilityOf($(".button-positive")));
                $(".button-positive").click();
            } catch (Exception ignored) {
            }
            new WebDriverWait(WebDriverRunner.getWebDriver(), 2).until(ExpectedConditions.invisibilityOf($$(btns).get(0)));
            elements = $$(".title-text");
        }
    }

    @Test
    void textNode() {
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        setWebDriver(new CustomWebDriverProvider().createDriver(capabilities));
        getWebDriver().manage().window().maximize();

        open("https://web.hubspringhealth.com/");
        $(By.id("email")).clear();
        $(By.id("email")).sendKeys("achernenko@s-pro.io");
        $(By.id("password")).clear();
        $(By.id("password")).sendKeys("Password1!");


        for (int i = 0; i < 71; i++) {
            $$(".material-icons.settings-icon").get(1).click();
            new WebDriverWait(WebDriverRunner.getWebDriver(), 5).until(ExpectedConditions.elementToBeClickable($(By.xpath("//i[2]"))));
            $$(".md-menu-content>button").get(0).click();
            $$(By.cssSelector("div.md-menu-content > button")).get(0).click();
            $$(".btn-name").get(0).click();

            //filenode
            $(".choose-btn.input-file>input").uploadFile(new File("./FilesToUpload/testfile_ms_xlsx.xlsx"));


            $(By.cssSelector("div.choose-icon > span")).click();
            //png icon
            $$((".icon-img")).get(i).click();

            //svg icon
//            $$(".icon-container>.node-icon").get(i).click();
//            $$(".circle").get(4).click();

            $(".save-btn").click();

            //text or generic
            $(".md-input-element").clear();
            $(".md-input-element").sendKeys("Lorem");

            //link
//            $$(".md-input-element").get(0).clear();
//            $$(".md-input-element").get(0).sendKeys("Test");
//            $$(".md-input-element").get(1).clear();
//            $$(".md-input-element").get(1).sendKeys("test.com");

            $(By.cssSelector("button.bt-save-send")).click();
            $(".btn-text").click();
        }
    }
}
