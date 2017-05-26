import com.codeborne.selenide.WebDriverRunner;
import io.codearte.jfairy.Fairy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.util.Random;

import static com.codeborne.selenide.Selenide.*;

public class CreateStream {
    public String searchValue;

    @Test
    public void createStream() {
        Fairy fairy = Fairy.create();
        Random random = new Random();
        String streamName;
        String result = "ok";
        streamName = fairy.textProducer().sentence(10);
        $("#chat-title").setValue(streamName);
        $(".row>div>label>textarea").setValue(fairy.textProducer().sentence(10));
//        $$(".md-checkbox-label").get(random.nextInt($$(".md-checkbox-label").size())).click();
        new CreateNode().changeNodeIcon(".input-file", 0, 70);
        $(".bt-save-send").click();
        //TODO add roles choice?
        $(".btn-text").click();
        new WebDriverWait(WebDriverRunner.getWebDriver(), 10).until(ExpectedConditions.not(ExpectedConditions.urlContains("manage")));
//        refresh();
        new WebDriverWait(WebDriverRunner.getWebDriver(), 5).until(ExpectedConditions.not(ExpectedConditions.urlContains(";")));
//        open(WebDriverRunner.url().replaceAll(";", "/"));
        $$(".text-block").get(0).click();
        try {
            new WebDriverWait(WebDriverRunner.getWebDriver(), 3).until(ExpectedConditions.textToBePresentInElement($(".main-title"), streamName));
        } catch (Exception ignored) {
        }
        if (!$(".main-title").getText().contains(streamName)) {
            System.out.println(".main-title - " + $(".main-title").getText());
            System.out.println("streamName - " + streamName);
            result = "fail";
        }
        searchValue = streamName;
        if ($(".chat-icon").getAttribute("src") == null) {
            System.out.println("Set Stream Picture - ok");
        } else System.out.println("Set Stream Picture - fail");
        System.out.println("Create Stream - " + result);

        new SendMessages().sendFiles("stream");
        new SendMessages().sendTextMessage("stream");
    }

    public String getSearchValue() {
        return searchValue;
    }
}
