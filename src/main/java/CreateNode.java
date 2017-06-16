import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.codearte.jfairy.Fairy;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static com.codeborne.selenide.Selenide.*;

public class CreateNode {
    String setingsBtnsCSS = ".settings-icon";
    String createNodeBtnsCSS = ".md-menu-content>button";
    String tabsBtnsCSS = ".btn-name";
    String addFilesBtnsCSS = ".choose-btn";
    String inputsCSS = ".md-input-table>.md-input-infix>input";
    String changeIconCSS = ".choose-icon>span";
    String nodeIconsXpath = "//select-icon/div[1]/div[2]/div/*";
    String iconColorsCSS = ".circle";
    String chooseIconBtnsCSS = ".btn-title";
    String saveBtnCss = ".bt-save-send";
    String progressbarCSS = ".md-progress-bar-buffer";
    String allRolesCheckboxCSS = ".md-checkbox-indeterminate";
    String nodesTitleCSS = ".header>.title";
    String linkNodesTitleCSS = ".main-title";
    String textNodesTextCSS = ".text-html>p";
    String rolesCheckboxesCSS = ".role-check-box";
    String searchRolesCSS = ".input-header.ng-untouched.ng-pristine.ng-valid";
    String saveRolesBtnCSS = ".save-btn";
    String nodeName;
    Fairy fairy = Fairy.create();
    String changePhotoBtnCSS = ".input-file";
    String chooseFileCSS = ".choose-btn.input-file>input";
    String saveFileCSS = ".send-btn.modal-btn";


    @Test
    public void createFileNode() {
        String result = "ok";
        List<File> fileList = new ArrayList<>();
        fileList.addAll(Arrays.asList(new File("./FilesToUpload").listFiles()));



        for (int i = 0; i < fileList.size(); i++){
            nodeName = fairy.textProducer().latinWord(2).replaceAll(" ", "_") + "." + fileList.get(i).getName().replace(".", "@@").split("@@")[1];
            new WebDriverWait(WebDriverRunner.getWebDriver(), 3).until(ExpectedConditions.visibilityOf($$(setingsBtnsCSS).get(1)));
            new WebDriverWait(WebDriverRunner.getWebDriver(), 3).until(ExpectedConditions.elementToBeClickable($$(setingsBtnsCSS).get(1)));
            $$(setingsBtnsCSS).get(1).click();
            try {
                new WebDriverWait(WebDriverRunner.getWebDriver(), 2).until(ExpectedConditions.elementToBeClickable($$(createNodeBtnsCSS).get(0)));
            } catch (Exception ignored) {
            }
            if ($$(createNodeBtnsCSS).get(0).isDisplayed()) {
                try {
                    new WebDriverWait(WebDriverRunner.getWebDriver(), 3).until(ExpectedConditions.visibilityOf($$(setingsBtnsCSS).get(1)));
                    new WebDriverWait(WebDriverRunner.getWebDriver(), 3).until(ExpectedConditions.elementToBeClickable($$(setingsBtnsCSS).get(1)));
                } catch (Exception ignored) {
                }
                $$(createNodeBtnsCSS).get(0).click();
            } else {
                refresh();
                System.out.println("refresh");
                new WebDriverWait(WebDriverRunner.getWebDriver(), 3).until(ExpectedConditions.visibilityOf($$(setingsBtnsCSS).get(1)));
                new WebDriverWait(WebDriverRunner.getWebDriver(), 3).until(ExpectedConditions.elementToBeClickable($$(setingsBtnsCSS).get(1)));
                $$(setingsBtnsCSS).get(1).click();
                $$(createNodeBtnsCSS).get(0).click();
            }
            try {
                new WebDriverWait(WebDriverRunner.getWebDriver(), 5).until(ExpectedConditions.visibilityOf($$(addFilesBtnsCSS).get(0)));
            } catch (Exception e) {
                screenshot("fileUpload");
            }
//            screenshot("node_creation_may_fail");
            $$(addFilesBtnsCSS).get(0).$(By.xpath("./input")).uploadFile(fileList.get(i));
            $(inputsCSS).setValue(nodeName);
            $(saveBtnCss).click();
            try {
                new WebDriverWait(WebDriverRunner.getWebDriver(), 120).until(ExpectedConditions.invisibilityOf($(progressbarCSS)));
            } catch (Exception ignored) {
            }
            if($(saveBtnCss).isDisplayed()) $(saveBtnCss).click();
            try {
                new WebDriverWait(WebDriverRunner.getWebDriver(), 2).until(ExpectedConditions.invisibilityOf($(saveBtnCss)));
            } catch (Exception ignored) {
            }
            new WebDriverWait(WebDriverRunner.getWebDriver(), 120).until(ExpectedConditions.invisibilityOf($(progressbarCSS)));


            if (!$(".md-simple-snackbar-message").isDisplayed()) {
                $(allRolesCheckboxCSS).click();
                $(saveRolesBtnCSS).click();
                new WebDriverWait(WebDriverRunner.getWebDriver(),120).until(ExpectedConditions.invisibilityOf($(saveRolesBtnCSS)));
                try {
                    new WebDriverWait(WebDriverRunner.getWebDriver(),2).until(ExpectedConditions.visibilityOf($("iframe")));
                } catch (Exception ignored) {
                }
                if ($("iframe").isDisplayed()){
                    new WebDriverWait(WebDriverRunner.getWebDriver(), 5).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));

                    try {
                        switchTo().frame(0);
//                System.out.println("url - " + WebDriverRunner.currentFrameUrl() + " file - " + filename + " frame 0");
                    } catch (Exception ignored) {

                    }
                    try {
                        new WebDriverWait(WebDriverRunner.getWebDriver(), 10).until(ExpectedConditions.invisibilityOf($(".md-primary")));
                    } catch (Exception ignored) {
                    }
                    if (!WebDriverRunner.source().contains("error") || !WebDriverRunner.source().contains("problem")) {
//                if (!$(".md-primary>svg>path").isDisplayed()) {
                        result = "fail";
                    } else {
                        screenshot("WebDriverRunner.source() " + fileList.get(i));
                        System.out.println(fileList.get(i) + " made screenshot");
                    }
                    switchTo().parentFrame();
                    switchTo().parentFrame();
                } else if ($("#Oval").isDisplayed()) {
                    try {
                        new WebDriverWait(WebDriverRunner.getWebDriver(),120).until(ExpectedConditions.invisibilityOf($("#Oval")));
                    } catch (Exception e) {
                        System.out.println("infLoader + " + fileList.get(i).getName());
                    }
                    if ($(By.id("#Oval")).isDisplayed()) {
                        System.out.println("(File Node Creation) File " + fileList.get(i).getName() + " can't be displayed");
                    }
                }

            } else {
                System.out.println("(File Node Creation) File " + fileList.get(i).getName() + " hasn't been uploaded");
                if ($(".clear-image").isDisplayed()) {
                    $(".clear-image").click();

                }
                result = "fail";
            }
        }
        System.out.println("File Node Creation - " + result);
    }

    @Test
    public void createContactNode() {
        $$(setingsBtnsCSS).get(1).click();
        $$(createNodeBtnsCSS).get(0).click();
        new WebDriverWait(WebDriverRunner.getWebDriver(), 5).until(ExpectedConditions.visibilityOf($$(addFilesBtnsCSS).get(0)));
        $$(tabsBtnsCSS).get(1).click();
        changeInputFields();
//        if (!$(".md-simple-snackbar-message").isDisplayed()) {
//            $(allRolesCheckboxCSS).click();
//            $(saveRolesBtnCSS).click();
//        } else {
//            System.out.println("(Contact node creation - fail");
//        }
    }

    @Test
    public void createTextNode() {
        $$(setingsBtnsCSS).get(1).click();
        $$(createNodeBtnsCSS).get(0).click();
        new WebDriverWait(WebDriverRunner.getWebDriver(), 5).until(ExpectedConditions.visibilityOf($$(addFilesBtnsCSS).get(0)));
        $$(tabsBtnsCSS).get(2).click();
        nodeName = fairy.textProducer().latinWord(2);
        String nodeText = fairy.textProducer().latinSentence(5);
        $(inputsCSS).setValue(nodeName);
        switchTo().frame("text-node-editor_ifr");
        new WebDriverWait(WebDriverRunner.getWebDriver(), 3).until(ExpectedConditions.visibilityOf($("#tinymce")));
        $("#tinymce").setValue(nodeText);
        switchTo().parentFrame();
        changeNodeIcon(changeIconCSS, 48, 61);

        $(saveBtnCss).click();
        $(allRolesCheckboxCSS).click();
        $(saveRolesBtnCSS).click();
        if ($(nodesTitleCSS).getText().equals(nodeName) && $(textNodesTextCSS).getText().equals(nodeText)) {
            System.out.println("Text Node Creation - ok");
        } else System.out.println("Text Node Creation - fail");
    }

    @Test
    public void createLinkNode() {
        $$(setingsBtnsCSS).get(1).click();
        $$(createNodeBtnsCSS).get(0).click();
        new WebDriverWait(WebDriverRunner.getWebDriver(), 5).until(ExpectedConditions.visibilityOf($$(addFilesBtnsCSS).get(0)));
        $$(tabsBtnsCSS).get(3).click();
        nodeName = fairy.textProducer().latinWord(2);
        String link = fairy.person().getCompany().getUrl();
        $$(inputsCSS).get(0).setValue(nodeName);
        $$(inputsCSS).get(1).setValue(link);
        //TODO public-private slider
        changeNodeIcon(changeIconCSS, 38, 47);

        $(saveBtnCss).click();
        $(allRolesCheckboxCSS).click();
        $(saveRolesBtnCSS).click();
//        if ($(linkNodesTitleCSS).getText().equals(nodeName) && link.contains($(".text-block>div>input").getAttribute("ng-reflect-model"))) {
//        if ($(linkNodesTitleCSS).getText().equals(nodeName) && link.contains($(".back-btn>p").getText())) {
        if ($(linkNodesTitleCSS).getText().equals(nodeName)) {
            System.out.println("Link Node Creation - ok");
        } else {
            System.out.println($(".text-block>div>input").getAttribute("ng-reflect-model") + " - $(\".text-block>div>input\").getText(); " + link + " - link");
            System.out.println("Link Node Creation - fail");
        }
    }

    @Test
    public void createGenericNode() {
        $$(setingsBtnsCSS).get(1).click();
        $$(createNodeBtnsCSS).get(0).click();
        new WebDriverWait(WebDriverRunner.getWebDriver(), 5).until(ExpectedConditions.visibilityOf($$(addFilesBtnsCSS).get(0)));
        $$(tabsBtnsCSS).get(4).click();
        nodeName = fairy.textProducer().latinWord(2);
        $$(inputsCSS).get(0).setValue(nodeName);
        //TODO sorting
        changeNodeIcon(changeIconCSS, 28, 37);
        $(saveBtnCss).click();
        $(allRolesCheckboxCSS).click();
        $(saveRolesBtnCSS).click();
        //TODO check creation
        $$(".node-name").get(0).hover();
//        SelenideElement element = $$(".node").get($$(".node").size() - 1);
//        ((JavascriptExecutor)WebDriverRunner.getWebDriver()).executeScript("arguments[0].scrollIntoView();", element);
    }

    public void changeNodeIcon (String changeIconCSS, int min, int max) {
        $(changeIconCSS).click();
        Random random = new Random();
        $$(By.xpath(nodeIconsXpath)).get(random.nextInt(max - min) + min).click();
        $$(iconColorsCSS).get(random.nextInt($$(iconColorsCSS).size())).click();
        $$(chooseIconBtnsCSS).get(1).click();
    }

    private void changeInputFields() {

        String prefix = "Dr.";
        String firstName = fairy.person().getFirstName();
        String lastName = fairy.person().getLastName();
        String suffix = "Jr.";
        String title = "Title";
        String companyName = fairy.person().getCompany().getName();
        String email = fairy.person().getEmail();
        String secEmail = fairy.person().getCompanyEmail();
        int ph0 = fairy.baseProducer().randomBetween(0, 9);
        int ph1 = fairy.baseProducer().randomBetween(0, 9);
        int ph2 = fairy.baseProducer().randomBetween(0, 9);
        int ph3 = fairy.baseProducer().randomBetween(0, 9);
        int ph4 = fairy.baseProducer().randomBetween(0, 9);
        int ph5 = fairy.baseProducer().randomBetween(0, 9);
        int ph6 = fairy.baseProducer().randomBetween(0, 9);
        int ph7 = fairy.baseProducer().randomBetween(0, 9);
        int ph8 = fairy.baseProducer().randomBetween(0, 9);
        int ph9 = fairy.baseProducer().randomBetween(0, 9);
        String phone = String.valueOf(ph0) + String.valueOf(ph1) + String.valueOf(ph2) + String.valueOf(ph3) + String.valueOf(ph4) + String.valueOf(ph5) + String.valueOf(ph6) + String.valueOf(ph7) + String.valueOf(ph8) + String.valueOf(ph9);
        String zip = fairy.person().getAddress().getPostalCode();
        String address = fairy.person().getAddress().getAddressLine1();
        String city = fairy.person().getAddress().getCity();
        String website = fairy.company().getUrl();
        String notes = fairy.textProducer().sentence(5);

        for (int i = 0; i < $$(inputsCSS).size(); i++) {
            new WebDriverWait(WebDriverRunner.getWebDriver(), 5).until(ExpectedConditions.elementToBeClickable($$(inputsCSS).get(0)));
            new WebDriverWait(WebDriverRunner.getWebDriver(), 5).until(ExpectedConditions.elementToBeClickable($$(inputsCSS).get(0)));
            switch (i) {
                case 0: $$(inputsCSS).get(i).clear();
                    $$(inputsCSS).get(i).setValue(prefix);
                    break;
                case 1: $$(inputsCSS).get(i).clear();
                    $$(inputsCSS).get(i).setValue(firstName);
                    break;
                case 2: $$(inputsCSS).get(i).clear();
                    $$(inputsCSS).get(i).setValue(lastName);
                    break;
                case 3: $$(inputsCSS).get(i).clear();
                    $$(inputsCSS).get(i).setValue(suffix);
                    break;
                case 4: $$(inputsCSS).get(i).clear();
                    $$(inputsCSS).get(i).setValue(title);
                    break;
                case 5: $$(inputsCSS).get(i).clear();
                    $$(inputsCSS).get(i).setValue(companyName);
                    break;
                case 6:
                    $$(inputsCSS).get(i).clear();
                    $$(inputsCSS).get(i).setValue(email);
                    break;
                case 7: $$(inputsCSS).get(i).clear();
                    $$(inputsCSS).get(i).setValue(secEmail);
                    break;
                case 8: $$(inputsCSS).get(i).clear();
                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph0));
                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph1));
                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph2));
                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph3));
                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph4));
                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph5));
                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph6));
                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph7));
                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph8));
                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph9));
                    break;
                case 9: $$(inputsCSS).get(i).clear();
                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph0));
                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph1));
                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph2));
                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph3));
                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph4));
                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph5));
                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph6));
                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph7));
                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph8));
                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph9));
                    break;
                case 10: $$(inputsCSS).get(i).clear();
                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph0));
                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph1));
                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph2));
                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph3));
                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph4));
                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph5));
                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph6));
                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph7));
                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph8));
                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph9));
                    break;
                case 11: $$(inputsCSS).get(i).clear();
                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph0));
                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph1));
                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph2));
                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph3));
                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph4));
                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph5));
                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph6));
                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph7));
                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph8));
                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph9));
//                    $$(inputsCSS).get(i).sendKeys(Keys.BACK_SPACE);
                    break;
                case 12: $$(inputsCSS).get(i).clear();
                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph0));
                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph1));
                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph2));
                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph3));
                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph4));
                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph5));
                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph6));
                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph7));
                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph8));
                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph9));
                    break;
                case 13: $$(inputsCSS).get(i).clear();
                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph0));
                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph1));
                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph2));
                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph3));
                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph4));
                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph5));
                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph6));
                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph7));
                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph8));
                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph9));
                    break;
                case 14: $$(inputsCSS).get(i).clear();
                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph0));
                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph1));
                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph2));
                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph3));
                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph4));
                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph5));
                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph6));
                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph7));
                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph8));
                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph9));
                    break;
                case 15: $$(inputsCSS).get(i).clear();
                    $$(inputsCSS).get(i).setValue(zip);
                    break;
                case 16: $$(inputsCSS).get(i).clear();
                    $$(inputsCSS).get(i).setValue(address);
                    break;
                case 17: $$(inputsCSS).get(i).clear();
                    $$(inputsCSS).get(i).setValue(city);
                    break;
                case 18: $$(inputsCSS).get(i).clear();
                    $$(inputsCSS).get(i).setValue(city);
                    break;
                case 19: $$(inputsCSS).get(i).clear();
                    $$(inputsCSS).get(i).setValue(website);
                    break;
                case 20: $$(inputsCSS).get(i).clear();
                    $$(inputsCSS).get(i).setValue(notes);
                    break;
                case 21: $$(inputsCSS).get(i).clear();
                    $$(inputsCSS).get(i).setValue(notes);
                    break;
            }
        }

        //file upload
        File file = new ProfileEdit().uploadImage(changePhotoBtnCSS, chooseFileCSS, saveFileCSS);
        try {
            new WebDriverWait(WebDriverRunner.getWebDriver(), 5).until(ExpectedConditions.not(ExpectedConditions.attributeContains($(".profile-icon>img"), "src", "user_profile.png")));
        } catch (Exception ignored) {
        }

        $(saveBtnCss).click();

        $(allRolesCheckboxCSS).click();
        $(saveRolesBtnCSS).click();
        new WebDriverWait(WebDriverRunner.getWebDriver(), 5).until(ExpectedConditions.visibilityOf($(".profile-icon>img")));

        if (!$(".profile-icon>img").getAttribute("src").contains("user_profile.png")) {
            if ($$(".value").get(0).getText().contains(prefix) && $$(".value").get(1).getText().contains(firstName)
                    && $$(".value").get(2).getText().contains(lastName) && $$(".value").get(3).getText().contains(suffix)
                    && $$(".value").get(4).getText().contains(title) && $$(".value").get(5).getText().contains(companyName)
                    && email.contains($$(".value").get(6).getText())
                    && secEmail.contains($$(".value").get(7).getText())
                    && $$(".value").get(8).getAttribute("ng-reflect-model").replace("(", "").replace(")", "").replace("-", "").replace(" ", "").contains(phone)
                    && $$(".value").get(9).getAttribute("ng-reflect-model").replace("(", "").replace(")", "").replace("-", "").replace(" ", "").contains(phone)
                    && $$(".value").get(10).getAttribute("ng-reflect-model").replace("(", "").replace(")", "").replace("-", "").replace(" ", "").contains(phone)
                    && $$(".value").get(11).getAttribute("ng-reflect-model").replace("(", "").replace(")", "").replace("-", "").replace(" ", "").contains(phone)
                    && $$(".value").get(12).getAttribute("ng-reflect-model").replace("(", "").replace(")", "").replace("-", "").replace(" ", "").contains(phone)
                    && $$(".value").get(13).getAttribute("ng-reflect-model").replace("(", "").replace(")", "").replace("-", "").replace(" ", "").contains(phone)
                    && $$(".value").get(14).getText().contains(zip)
                    && $$(".value").get(15).getText().contains(address) && $$(".value").get(16).getText().contains(city)
                    && $$(".value").get(17).getText().contains(city) && $$(".value").get(18).getText().contains(website)
                    && notes.contains($$(".value").get(19).getText()) ) {
                System.out.println("Contact Node Creation - ok");
            } else {
                for (int i = 0; i < $$(".value").size(); i++) {
                    System.out.println($$(".value").get(i).getText() + " - " + i);
                }
                System.out.println("Contact Node Creation1 - fail");
            }
        } else {
            for (int i = 0; i < $$(".value").size(); i++) {
                System.out.println($$(".value").get(i) + " - " + i);
            }
            System.out.println("Contact Node Creation - fail");
//            System.out.println("exp: " + (prefix + " " + firstName + " " + lastName + " " + suffix));
//            System.out.println("fac: " + $(profileNameCSS).getText());
        }
    }

}
