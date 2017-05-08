import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.codearte.jfairy.Fairy;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.io.File;

import static com.codeborne.selenide.Selenide.*;

public class SendMessages {
    Fairy fairy = Fairy.create();

    @Test
    public void sendTextMessage(String target) {
        String tenWordsMessage = fairy.textProducer().latinSentence(10);
        $(".input-msg").setValue(tenWordsMessage);
        $(".input-msg").sendKeys(Keys.ENTER);
        String result = "ok";
        if (!$$(".message-parsed").get($$(".message-parsed").size() - 1).getText().equals(tenWordsMessage)) {
            result = "fail";
        }
        System.out.println("Send message to " + target + " - " + result);
    }

    @Test
    public void sendFiles(String target) {
        final File folder = new File("./FilesToUpload");
        listFilesForFolder(folder, target);
    }

    public void listFilesForFolder(final File folder, String target) {
        String result = "ok";
        String viewAttach = "ok";
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry, target);
            } else {
//                System.out.println(fileEntry.getName());
                try {
                    new WebDriverWait(WebDriverRunner.getWebDriver(), 1).until(ExpectedConditions.visibilityOf($$(".send-btn").get($$(".send-btn").size() - 1)));
                } catch (Exception ignored) {
                }
                try {
                    new WebDriverWait(WebDriverRunner.getWebDriver(), 2).until(ExpectedConditions.invisibilityOf($$(".send-btn").get($$(".send-btn").size() - 1)));
                } catch (Exception ignored) {
                }
                if (isClickable($$(".send-btn").get($$(".send-btn").size() - 1)) && $$(".send-btn").get($$(".send-btn").size() - 1).isDisplayed()) {
                    try {
                        if ($$(".send-btn").size() > 0) {
                            $$(".send-btn").get($$(".send-btn").size() - 1).click();
                        }
                    } catch (Exception ignored) {
                    }
                    screenshot("sendbtn.jpg");
                } else  {
                    if (isClickable($(".dialog__close-btn"))) {
                        try {
                            $(".dialog__close-btn").click();
                        } catch (Exception ignored) {
                        }
                        System.out.println("File " + fileEntry.getName() + " uploading problem");
                    }
                }
                $(".attachment-icon").click();
                $(".uploader>input").uploadFile(fileEntry);
                if (isAlertPresent()) System.out.println(fileEntry.getName());
                new WebDriverWait(WebDriverRunner.getWebDriver(), 5).until(ExpectedConditions.elementToBeClickable($$(".send-btn").get($$(".send-btn").size() - 1)));
                new WebDriverWait(WebDriverRunner.getWebDriver(), 5).until(ExpectedConditions.visibilityOf($$(".send-btn").get(2)));
                $$(".send-btn").get(2).click();
                try {
                    new WebDriverWait(WebDriverRunner.getWebDriver(), 5).until(ExpectedConditions.visibilityOf($$(".attachment-container").get($$(".attachment-container").size() - 1).$(By.xpath("./md-progress-bar"))));
                } catch (Exception ignored) {}
                try {
                    new WebDriverWait(WebDriverRunner.getWebDriver(), 150).until(ExpectedConditions.invisibilityOf($$(".attachment-container").get($$(".attachment-container").size() - 1).$(By.xpath("./md-progress-bar"))));
                } catch (Exception ignored) {
//                    System.out.println("File " + fileEntry.getName() + " hasn't been uploaded");
                    result = "fail";
//                    System.out.println("$(\".file-name\").getText() - " + $$(".file-name").get($$(".file-name").size() - 1).getText());
                    System.out.println("File " + fileEntry.getName() + " hasn't been uploaded");
                    screenshot(fileEntry.getName() + ".jpg");
                }

                if (!$$(".file-name").get($$(".file-name").size() - 1).exists() || !fileEntry.getName().contains($$(".file-name").get($$(".file-name").size() - 1).getText().replace("...", "@#@").split("@#@")[0])) {
                    result = "fail";
//                    System.out.println("$(\".file-name\").getText() - " + $$(".file-name").get($$(".file-name").size() - 1).getText());
                    System.out.println("File " + fileEntry.getName() + " hasn't been uploaded");
                }
                else {
                    if ($$(".file-name").get($$(".file-name").size() - 1).$(By.xpath("following::div[1]")).$(By.xpath("./img")).exists()) {
                        $$(".file-name").get($$(".file-name").size() - 1).$(By.xpath("following::div[1]")).$(By.xpath("./img")).click();
                    } else if ($$(".file-name").get($$(".file-name").size() - 1).$(By.xpath("following::div")).$(By.xpath("./img")).exists()) {
                        $$(".file-name").get($$(".file-name").size() - 1).$(By.xpath("following::div")).$(By.xpath("./img")).click();
                    }
                        else $$(".file-name").get($$(".file-name").size() - 1).click();
                    try {
                        new WebDriverWait(WebDriverRunner.getWebDriver(), 1).until(ExpectedConditions.invisibilityOf($$(".file-name").get($$(".file-name").size() - 1)));
                    } catch (Exception ignored) {
                    }
                    if (fileEntry.getName().toLowerCase().contains(".jpg") || fileEntry.getName().toLowerCase().contains(".jpeg") || fileEntry.getName().toLowerCase().contains(".png") || fileEntry.getName().toLowerCase().contains(".gif") || fileEntry.getName().toLowerCase().contains(".svg") || fileEntry.getName().toLowerCase().contains(".mp4") || fileEntry.getName().toLowerCase().contains(".ogg") || fileEntry.getName().toLowerCase().contains(".mpg") || fileEntry.getName().toLowerCase().contains(".3gp") || fileEntry.getName().toLowerCase().contains(".avi") || fileEntry.getName().toLowerCase().contains(".mov")) {
                        if (!imageVideoAttach(fileEntry.getName())) {
                            viewAttach = "fail";
                        }
                    }
                    try {
                        new WebDriverWait(WebDriverRunner.getWebDriver(), 3).until(ExpectedConditions.elementToBeClickable($(".close")));
                    } catch (Exception ignored) {
                    }
                    if ($$(".close").get(1).isDisplayed()) {
                        $$(".close").get(1).click();
                    } else if ($$(".close").get(0).isDisplayed()){
                        $$(".close").get(0).click();
                    }
//                    $$(".file-name").get($$(".file-name").size()-1).click();
//                    try {
//                        new WebDriverWait(WebDriverRunner.getWebDriver(), 3).until(ExpectedConditions.visibilityOf($(By.id(".iframe"))));
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
////                    switchTo().innerFrame();
//                    switchTo().innerFrame("parentFrame", "childFrame_1");
//                    try {
//                        new WebDriverWait(WebDriverRunner.getWebDriver(), 5).until(ExpectedConditions.visibilityOf($(By.xpath("html/body/div[1]/div[2]/div[4]/div[3]/div[1]"))));
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                    if ($(By.xpath("//div[4]/div[3]/div[1]")).isDisplayed()) {
//                        System.out.println("vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv");
//                    } else System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaa");
                }
            }
        }
        System.out.println("Test Files Upload to " + target + " - " + result);
        System.out.println("Test Files Preview - " + viewAttach);
    }

    public boolean isAlertPresent()
    {
        try
        {
            WebDriverRunner.getWebDriver().switchTo().alert();
            return true;
        }   // try
        catch (NoAlertPresentException Ex)
        {
            return false;
        }   // catch
    }

    private Boolean isClickable(SelenideElement element) {
        try {
            new WebDriverWait(WebDriverRunner.getWebDriver(), 1).until(ExpectedConditions.elementToBeClickable(element));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private Boolean imageVideoAttach(String filename) {
        Boolean isDisplayed = false;
        if (filename.toLowerCase().contains(".jpg") || filename.toLowerCase().contains(".jpeg") || filename.toLowerCase().contains(".png") || filename.toLowerCase().contains(".gif") || filename.toLowerCase().contains(".svg")) {
            try {
                new WebDriverWait(WebDriverRunner.getWebDriver(), 5).until(ExpectedConditions.visibilityOf($(".content>img")));
            } catch (Exception ignored) {
            }
            if ($(".content>img").isDisplayed() && $(".content>img").getAttribute("src").contains("amazonaws")) {
                isDisplayed = true;
            } else if ($(".content>img").isDisplayed()) {
                System.out.println("$(\".content>img\").getAttribute(\"src\") " + $(".content>img").getAttribute("src"));
            } else {
                System.out.println("Problem with preview of file " + filename);
                screenshot("Problem with preview of file " + filename);
            }
        } else if (filename.toLowerCase().contains(".mp4")) {
            if ($(".content>video").isDisplayed() && $(".content>video").getAttribute("src").contains("amazonaws")) {
                isDisplayed = true;
            } else if ($(".content>video").isDisplayed()) {
                System.out.println("$(\".content>video\").getAttribute(\"src\") " + $(".content>video").getAttribute("src"));
            } else {
                System.out.println("Problem with preview of file " + filename);
                screenshot("Problem with preview of file " + filename);
            }
        } else if (filename.toLowerCase().contains(".ogg") || filename.toLowerCase().contains(".mpg") || filename.toLowerCase().contains(".3gp") || filename.toLowerCase().contains(".avi") || filename.toLowerCase().contains(".mov")) {
            try {
                new WebDriverWait(WebDriverRunner.getWebDriver(), 2).until(ExpectedConditions.visibilityOf($(".download-btn")));
            } catch (Exception ignored) {
            }
            if ($(".download-btn").isDisplayed() && $(".download-btn").getAttribute("href").contains("amazonaws")) {
                isDisplayed = true;
            } else if ($(".download-btn").isDisplayed()) {
                System.out.println("$(\".download-btn\").getAttribute(\"href\") " + $(".download-btn").getAttribute("href"));
            } else {
                System.out.println("Problem with preview of file " + filename);
                screenshot("Problem with preview of file " + filename);
            }
        }
        return isDisplayed;
    }
}
