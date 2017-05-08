import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class Logout {
    private String settingBtnsCSS = ".settings-icon";
    private String mailtoBtnCSS = ".nav-item-text";

    @Test
    public void logout(Boolean isOutput) {
        $$(settingBtnsCSS).get(0).shouldBe(Condition.visible);
        $$(settingBtnsCSS).get(0).click();
//        new WebDriverWait(WebDriverRunner.getWebDriver(), 5).until(ExpectedConditions.visibilityOf($(mailtoBtnCSS)))
        if (isOutput) {
            if ($$(mailtoBtnCSS).get(1).getText().contains("Contact Hub")) {
                System.out.println("Logout ok");
            } else System.out.println("Logout fail");
        }
    }
}
