import com.codeborne.selenide.Selenide;
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

public class SendMessages extends TestSuite{
    Fairy fairy = Fairy.create();
    private String loginCSS = "[name=login_email]";
    private String passwordCSS = "[name=login_password]";
    private String filesFolderCSS = ".dropbox-personal";
    private String filesCSS = ".file-name";
    private String selectFileBtnCSS = "#select-btn";
    private String loginGoogleCSS = "#identifierId";
    private String passGoogleCSS = "#password>div>div>div>input";
    private String searchGoogleCSS = "div>div>div>div>div>input";
    private String filesGoogleXpath = ".//*/div/div/div/div[3]/span";

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
//        listFilesForFolder(folder, target);
//        dropbox(target);
        googleDrive(target);
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
                    if (!imageVideoAttach(fileEntry.getName())) {
                        viewAttach = "fail";
                        System.out.println("fileview fail, file " + fileEntry.getName());
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
        } else {
            new WebDriverWait(WebDriverRunner.getWebDriver(), 5).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));

            try {
                switchTo().frame(0);
//                System.out.println("url - " + WebDriverRunner.currentFrameUrl() + " file - " + filename + " frame 0");
            } catch (Exception ignored) {

            }

            if (!WebDriverRunner.source().contains("error") || !WebDriverRunner.source().contains("problem")) {
                isDisplayed = true;
            } else {
                screenshot("WebDriverRunner.source() " + filename);
                System.out.println(filename + " made screenshot");
            }
            switchTo().parentFrame();
            switchTo().parentFrame();
        }
        return isDisplayed;
    }

    public void dropbox(String target) {

        int size = 100;
        String result = "ok";
        String filename = null;
        String viewAttach = "ok";
        for (int i = 0; i < size; i++) {
            $(".attachment-icon").click();
            $$(".send-btn").get(1).click();
            Selenide.switchTo().window(1);
            if ($$(loginCSS).get(1).isDisplayed()) {
                $$(loginCSS).get(1).setValue(email);
                $$(passwordCSS).get(1).setValue("El413423");
                $(".login-button>.sign-in-text").click();
            }
            $(filesFolderCSS).click();
            new WebDriverWait(WebDriverRunner.getWebDriver(), 5).until(ExpectedConditions.visibilityOf($$(filesCSS).get(0)));
            for (SelenideElement e : $$(filesCSS)) {
                if (e.getText().equals("testfiles")) e.click();
            }
            new WebDriverWait(WebDriverRunner.getWebDriver(), 5).until(ExpectedConditions.visibilityOf($$(filesCSS).get(0)));
            size = $$(filesCSS).size();
            filename = $$(filesCSS).get(i).getText();
            $$(filesCSS).get(i).click();
            $(selectFileBtnCSS).click();
            new WebDriverWait(WebDriverRunner.getWebDriver(), 5).until(ExpectedConditions.numberOfWindowsToBe(1));
            Selenide.switchTo().window("The Hub");

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
                System.out.println("File " + filename + " hasn't been uploaded");
                screenshot(filename + ".jpg");
            }

            if (!$$(".file-name").get($$(".file-name").size() - 1).exists() || !filename.contains($$(".file-name").get($$(".file-name").size() - 1).getText().replace("...", "@#@").split("@#@")[0])) {
                result = "fail";
//                    System.out.println("$(\".file-name\").getText() - " + $$(".file-name").get($$(".file-name").size() - 1).getText());
                System.out.println("File " + filename + " hasn't been uploaded");
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
                if (!imageVideoAttach(filename)) {
                    viewAttach = "fail";
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
            }
        }
        System.out.println("Dropbox Files Upload to " + target + " - " + result);
        System.out.println("Dropbox Files Preview - " + viewAttach);
    }

    //TODO fix this
    public void googleDrive(String target) {
        int size = 100;
        String result = "ok";
        String filename;
        String viewAttach = "ok";
        for (int i = 0; i < size; i++) {
            $(".attachment-icon").click();
            $$(".send-btn").get(0).click();
            Selenide.switchTo().window(1);
            if ($(loginGoogleCSS).isDisplayed()) {
                $(loginGoogleCSS).setValue(email);
                $(loginGoogleCSS).sendKeys(Keys.ENTER);
                $(passGoogleCSS).setValue("El413423");
                $(passGoogleCSS).sendKeys(Keys.ENTER);
                new WebDriverWait(WebDriverRunner.getWebDriver(), 5).until(ExpectedConditions.numberOfWindowsToBe(1));
                Selenide.switchTo().window("The Hub");
//                System.out.println(WebDriverRunner.currentFrameUrl());
            }
            switchTo().frame(2);
//            System.out.println(WebDriverRunner.currentFrameUrl());
            new WebDriverWait(WebDriverRunner.getWebDriver(), 5).until(ExpectedConditions.visibilityOf($(searchGoogleCSS)));
            $(searchGoogleCSS).setValue("testfile");
            $(searchGoogleCSS).sendKeys(Keys.ENTER);
            new WebDriverWait(WebDriverRunner.getWebDriver(), 5).until(ExpectedConditions.visibilityOf($$(By.xpath(filesGoogleXpath)).get(0)));
            size = $$(By.xpath(filesGoogleXpath)).size();
            filename = $$(By.xpath(filesGoogleXpath)).get(i).getText();
            $$(By.xpath(filesGoogleXpath)).get(i).click();
            for (SelenideElement e : $$("#doclist>div>div>div>div>div>div>div>div>div>div>div")) {
                if (e.getAttribute("id").contains("picker") && e.getText().equals("Select")) {
                    e.click();
                }
                }
//            new WebDriverWait(WebDriverRunner.getWebDriver(), 5).until(ExpectedConditions.numberOfWindowsToBe(1));
//            Selenide.switchTo().window("The Hub");
            switchTo().parentFrame();

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
                System.out.println("File " + filename + " hasn't been uploaded");
                screenshot(filename + ".jpg");
            }

            if (!$$(".file-name").get($$(".file-name").size() - 1).exists() || !filename.contains($$(".file-name").get($$(".file-name").size() - 1).getText().replace("...", "@#@").split("@#@")[0])) {
                result = "fail";
//                    System.out.println("$(\".file-name\").getText() - " + $$(".file-name").get($$(".file-name").size() - 1).getText());
                System.out.println("File " + filename + " hasn't been uploaded");
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
                if (!imageVideoAttach(filename)) {
                    viewAttach = "fail";
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
            }
        }
        System.out.println("Google Drive Files Upload to " + target + " - " + result);
        System.out.println("Google Drive Files Preview - " + viewAttach);
    }
}
