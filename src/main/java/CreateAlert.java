import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.codearte.jfairy.Fairy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.io.File;
import java.util.Random;

import static com.codeborne.selenide.Selenide.*;

public class CreateAlert {
    public String searchValue;

    @Test
    public void createAlert(String alertType, Boolean ifAttach) {
        String result = null;
        if (ifAttach) {
            for (final File fileEntry : SendMessages.folder.listFiles()) {
                    Random random = new Random();
                    Fairy fairy = Fairy.create();

                $$(".tab-icon").get(2).click();
                new LeftSidebar().createChatIcon(".create-chat-icon", "manage/notification", "alert", false);
                String user = $$(".md-checkbox-label").get(0).getText();
                if (alertType.equals("Group")) {
                    $(".button-tab.right-tab").click();
                    $$(".md-checkbox-label").get(0).shouldNot(Condition.exactText(user));
                }
                String alertName = fairy.textProducer().sentence(10);
                $("#message").setValue(alertName);
                $(".attachment-icon").click();
                $(".uploader>input").uploadFile(fileEntry);
                $$(".send-btn.modal-btn").get(2).click();

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
                result = "ok";
                $$(".chat-item>div>.message").get(0).shouldBe(Condition.visible);
                if (!alertName.contains($$(".chat-item>div>.message").get(0).getText().replace("...", ""))) {
                    System.out.println($$(".chat-item>div>.message").get(0).getText());
                    result = "fail";
                }
            }
        } else {
            Random random = new Random();
            Fairy fairy = Fairy.create();

            $$(".tab-icon").get(2).click();
            try {
                new WebDriverWait(WebDriverRunner.getWebDriver(), 3).until(ExpectedConditions.visibilityOf($$(".md-checkbox-label").get(0)));
            } catch (Exception ignored) {
            }
            new LeftSidebar().createChatIcon(".create-chat-icon", "manage/notification", "alert", false);
//            $(".create-chat-icon").click();
//            $(".create-chat-icon").click();
            if (new TestSuite().isDisplayed($$(".md-checkbox-label").get(0))) {
                try {
                    new WebDriverWait(WebDriverRunner.getWebDriver(), 10).until(ExpectedConditions.visibilityOf($$(".md-checkbox-label").get(0)));
                } catch (Exception ignored) {
                    screenshot("noCheckboxes.png");
                }
            } else $(".create-chat-icon").click();;
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
            result = "ok";
            $$(".chat-item>div>.message").get(0).shouldBe(Condition.visible);
            if (!alertName.contains($$(".chat-item>div>.message").get(0).getText().replace("...", ""))) {
                System.out.println($$(".chat-item>div>.message").get(0).getText());
                result = "fail";
            }
        }
        System.out.println("Create " + alertType + " Alert - " + result);
    }

    public String getSearchValue() {
        return searchValue;
    }
}
