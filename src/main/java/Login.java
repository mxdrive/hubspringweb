import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import static com.codeborne.selenide.Selenide.$;

public class Login extends TestSuite{

    @Test
    public void login(String pswd, Boolean isOutput) {

        if (isOutput) {
            new HeaderFooter().headerFooter();
            if ($(By.name("action")).has(Condition.attribute("ng-reflect-disabled"))) {
                System.out.println("Submit button disabled - ok");
            } else System.out.println("Submit button enabled - fail");
        }

        //uncomment if want to check forgot password page
//        $(By.linkText("Forgot password")).click();
//        new HeaderFooter().headerFooter();
//        new Forgot().forgot();

        $(By.id("email")).setValue(email).pressEnter();
        $(By.id("password")).setValue(pswd).pressEnter();
//        Thread.sleep(3000);
        Configuration.timeout = 10000;
        $(By.className("header-title")).shouldBe(Condition.visible);
        if (isOutput) {
            if ($(".profile-name").is(Condition.visible)) {
                System.out.println("Login successful");
            } else {
                System.out.println("Login failed");
            }
        }


    }
}
