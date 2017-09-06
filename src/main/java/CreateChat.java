import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.codearte.jfairy.Fairy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.util.Random;

import static com.codeborne.selenide.Selenide.*;

public class CreateChat {
    String searchValue;

    @Test
    public void createChat(String chatType) {
        String chatname;
        Fairy fairy = Fairy.create();
        Random random = new Random();
        if (chatType.equals("Group") || chatType.equals("Direct")) {
            Configuration.timeout = 10000;
            $$(".md-checkbox-label").get(0).shouldBe(Condition.visible);
            SelenideElement element = $$(".md-checkbox-label").get(random.nextInt($$(".md-checkbox-label").size()));
            chatname = element.getText();
            element.click();
            if (chatType.equals("Group")) {
                $$(".md-accent.ng-untouched.ng-pristine.ng-valid").get(random.nextInt($$(".md-accent.ng-untouched.ng-pristine.ng-valid").size())).click();
            }
        } else {
            $(".button-tab.right-tab").click();
            try {
                new WebDriverWait(WebDriverRunner.getWebDriver(), 8).until(ExpectedConditions.visibilityOf($$(".chat-name").get($$(".chat-name").size() -1)));
                new WebDriverWait(WebDriverRunner.getWebDriver(), 8).until(ExpectedConditions.elementToBeClickable($$(".chat-name").get($$(".chat-name").size() -1)));
            } catch (Exception ignored) {
            }
            //TODO temp
            SelenideElement radio = null;
            if ($$(".chat-name").size() > 1) {
                radio = $$(".chat-name").get(random.nextInt($$(".chat-name").size() - 1));
            } else radio = $$(".chat-name").get(0);
//            SelenideElement radio = $$(".chat-name").get(0);
//            System.out.println($$(".chat-name").get(random.nextInt($$(".chat-name").size())).getText());
//            SelenideElement radio = $$(".chat-name").get(0);
            chatname = radio.getText();
            radio.click();
            if (!radio.isSelected()) {
                radio.click();
            }
        }
        $("#chat-message").sendKeys(fairy.textProducer().sentence(10));
        if (chatType.equals("Group")) {
            chatname = fairy.textProducer().sentence(4);
            $(".form-container>div>div>input").sendKeys(chatname);
            new ProfileEdit().uploadImage(ProfileEdit.changePhotoBtnCSS, ProfileEdit.chooseFileCSS, ProfileEdit.saveFileCSS);
//            $(".input-file>input").setValue("./FilesToUpload/1.99MB.jpg");
//            WebDriverRunner.getWebDriver().findElement(By.cssSelector(".send-btn.modal-btn")).click();
//            $(".send-btn.modal-btn").click();
        }
        $(".bt-save-send").click();
        try {
            new WebDriverWait(WebDriverRunner.getWebDriver(), 4).until(ExpectedConditions.not(ExpectedConditions.urlContains("manage/conversation")));
        } catch (Exception ignored) {
        }
//        refresh();
        String result = "ok";
        if (chatType.equals("Direct")) {
//            System.out.println("$$(\".chat-item>div>.title\").get(0).getText() " + $$(".chat-item>div>.title").get(0).getText());
//            System.out.println("chatname.split(\" \")[0] " + chatname.split(" ")[0]);
//            System.out.println("chatname.split(\" \")[1] " + chatname.split(" ")[1]);
            if (!chatname.split(" ")[1].equals("") && !chatname.split(" ")[0].equals("")) {
                if (!$$(".chat-item>div>.title").get(0).getText().contains(chatname.split(" ")[0]) || !$$(".chat-item>div>.title").get(0).getText().contains(chatname.split(" ")[1])) {
                    result = "fail";
                }
            } else if (chatname.split(" ")[1].equals("")){
                if (!$$(".chat-item>div>.title").get(0).getText().contains(chatname.split(" ")[0])) {
                    result = "fail";
                }
            } else if (chatname.split(" ")[0].equals("")) {
                if (!$$(".chat-item>div>.title").get(0).getText().contains(chatname.split(" ")[1])) {
                    result = "fail";
                }
            }
        } else {
            try {
                new WebDriverWait(WebDriverRunner.getWebDriver(), 5).until(ExpectedConditions.textToBePresentInElement($$(".chat-item>div>.title").get(0), chatname));
            } catch (Exception ignored) {
            }
            if (!$$(".chat-item>div>.title").get(0).getText().contains(chatname) && !chatname.contains($$(".chat-item>div>.title").get(0).getText())) {
                System.out.println($$(".chat-item>div>.title").get(0).getText());
                System.out.println(chatname);
                screenshot("chatCreate_fail.jpg");
                result = "fail";
            }
        }

        if (chatType.equals("Group")) {
            if (!$(".chat-icon").getAttribute("src").contains("hub_icon")) {
                System.out.println("Set Group Chat Picture - ok");
            } else System.out.println("Set Group Chat Picture - fail");
        }

        if (chatType.equals("Direct")) {
            searchValue = chatname.split(" ")[1] + " " + chatname.split(" ")[0];
        } else searchValue = chatname;
        System.out.println("Create " + chatType + " Chat - " + result);

        new SendMessages().sendFiles(chatType + " chat");
        new SendMessages().sendTextMessage(chatType + " chat");
    }

    String getSearchValue(){
        return searchValue;
    }
}
