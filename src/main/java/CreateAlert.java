import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.codearte.jfairy.Fairy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.util.Random;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.refresh;

public class CreateAlert {
    public String searchValue;

    @Test
    public void createAlert(String alertType) {
        Random random = new Random();
        Fairy fairy = Fairy.create();

        String user = $$(".md-checkbox-label").get(0).getText();
        if (alertType.equals("Group")) {
            $(".button-tab.right-tab").click();
            $$(".md-checkbox-label").get(0).shouldNot(Condition.exactText(user));
        }
        String alertName = fairy.textProducer().sentence(10);
        $("#message").setValue(alertName);

        SelenideElement checkbox = $$(".md-checkbox-label").get(random.nextInt($$(".md-checkbox-label").size()));
//        String checkboxText = checkbox.getText();
        checkbox.click();
        searchValue = checkbox.getText();
        $(".bt-save-send").click();

        try {
            new WebDriverWait(WebDriverRunner.getWebDriver(), 10).until(ExpectedConditions.not(ExpectedConditions.urlContains("manage/notification")));
        } catch (Exception ignored) {
        }
        refresh();
        String result = "ok";
        $$(".chat-item>div>.message").get(0).shouldBe(Condition.visible);
        if (!alertName.contains($$(".chat-item>div>.message").get(0).getText().replace("...", ""))) {
            System.out.println($$(".chat-item>div>.message").get(0).getText());
            result = "fail";
        }
        System.out.println("Create " + alertType + " Alert - " + result);
    }

    public String getSearchValue() {
        return searchValue;
    }
}
