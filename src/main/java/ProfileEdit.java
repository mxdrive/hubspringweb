import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.codearte.jfairy.Fairy;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import static com.codeborne.selenide.Selenide.*;

public class ProfileEdit extends TestSuite {
    private String tabsCSS = ".button-tab";
    private String inputsCSS = ".md-input-element";
    private String profileBtnCSS = ".profile-icon";
    public static String sidemenuCSS = ".side-menu-icon";
    private String hubsRadioCSS = ".md-radio-container";
    public static String hubIconsCSS = ".hub-icon";
    private String saveBtnCSS = ".bt-save-send";
    private String alertCSS = ".md-simple-snackbar-message";
    private String profileNameCSS = ".profile-name";
    public static String changePhotoBtnCSS = ".input-file";
    public static String chooseFileCSS = ".choose-btn.input-file>input";
    public static String saveFileCSS = ".send-btn.modal-btn";
    private String profileIconsCSS = ".profile-icon";
    private String profileEditIconCSS = ".profile-icon>img";
    private Fairy fairy = Fairy.create();

    @Test
    public void generalSettingsTab() {
        $(profileBtnCSS).click();
        new WebDriverWait(WebDriverRunner.getWebDriver(), 5).until(ExpectedConditions.visibilityOf($$(tabsCSS).get(0)));
        $$(tabsCSS).get(0).click();
        $$(inputsCSS).get(0).shouldBe(Condition.visible);
        String prefix = "Dr.";
        String firstName = fairy.person().getFirstName();
        String lastName;
        String suffix = "Jr.";
        String title = "Title";
        String companyName = fairy.person().getCompany().getName();
        String secEmail = fairy.textProducer().randomString(5) + "@" + fairy.textProducer().randomString(5) + "." + fairy.textProducer().randomString(3);
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

        if (Objects.equals(email, "achernenko@s-pro.io")) {
            lastName = "Chernenko";
        } else lastName = "Testhub";

        for (int i = 0; i < $$(inputsCSS).size(); i++) {
            new WebDriverWait(WebDriverRunner.getWebDriver(), 5).until(ExpectedConditions.elementToBeClickable($$(inputsCSS).get(0)));
            try {
                new WebDriverWait(WebDriverRunner.getWebDriver(), 5).until(ExpectedConditions.attributeContains($$(inputsCSS).get(0), "ng-reflect-model", "Dr."));
            } catch (Exception ignored) {
            }
            switch (i) {
                case 0: $$(inputsCSS).get(i).clear();
                    $$(inputsCSS).get(i).setValue(prefix);
                    break;
                case 1: $$(inputsCSS).get(i).clear();
                    $$(inputsCSS).get(i).setValue(firstName);
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
                case 6: break;
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
//                case 9: $$(inputsCSS).get(i).clear();
//                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph0));
//                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph1));
//                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph2));
//                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph3));
//                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph4));
//                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph5));
//                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph6));
//                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph7));
//                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph8));
//                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph9));
//                    break;
//                case 10: $$(inputsCSS).get(i).clear();
//                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph0));
//                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph1));
//                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph2));
//                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph3));
//                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph4));
//                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph5));
//                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph6));
//                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph7));
//                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph8));
//                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph9));
//                    break;
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
//                case 12: $$(inputsCSS).get(i).clear();
//                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph0));
//                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph1));
//                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph2));
//                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph3));
//                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph4));
//                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph5));
//                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph6));
//                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph7));
//                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph8));
//                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph9));
//                    break;
//                case 13: $$(inputsCSS).get(i).clear();
//                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph0));
//                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph1));
//                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph2));
//                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph3));
//                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph4));
//                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph5));
//                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph6));
//                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph7));
//                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph8));
//                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph9));
//                    break;
//                case 14: $$(inputsCSS).get(i).clear();
//                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph0));
//                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph1));
//                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph2));
//                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph3));
//                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph4));
//                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph5));
//                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph6));
//                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph7));
//                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph8));
//                    $$(inputsCSS).get(i).sendKeys(String.valueOf(ph9));
//                    break;
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
        File file = uploadImage(changePhotoBtnCSS, chooseFileCSS, saveFileCSS);
        try {
            new WebDriverWait(WebDriverRunner.getWebDriver(), 5).until(ExpectedConditions.not(ExpectedConditions.attributeContains($(profileEditIconCSS), "src", "user_profile.png")));
        } catch (Exception ignored) {
        }
        screenshot("settingsPreSave");

        $(saveBtnCSS).click();
        try {
            new WebDriverWait(WebDriverRunner.getWebDriver(), 5).until(ExpectedConditions.visibilityOf($(alertCSS)));
        } catch (Exception ignored) {
        }

        refresh();
        new WebDriverWait(WebDriverRunner.getWebDriver(), 4).until(ExpectedConditions.visibilityOf($(profileNameCSS)));
        $(profileNameCSS).shouldNot(Condition.empty);
        $$(inputsCSS).get(2).shouldNot(Condition.empty);
        if ($(profileNameCSS).getText().contains(prefix + " " + firstName + " " + lastName + ", " + suffix)) {
            if ($$(inputsCSS).get(0).getAttribute("ng-reflect-model").contains(prefix) && $$(inputsCSS).get(1).getAttribute("ng-reflect-model").contains(firstName)
                    && $$(inputsCSS).get(3).getAttribute("ng-reflect-model").contains(suffix)
                    && $$(inputsCSS).get(4).getAttribute("ng-reflect-model").contains(title) && $$(inputsCSS).get(5).getAttribute("ng-reflect-model").contains(companyName)
                    && secEmail.contains($$(inputsCSS).get(7).getAttribute("ng-reflect-model")) && $$(inputsCSS).get(8).getAttribute("ng-reflect-model").replace("(", "").replace(")", "").replace("-", "").replace(" ", "").contains(phone)
                    && $$(inputsCSS).get(11).getAttribute("ng-reflect-model").contains(phone) && $$(inputsCSS).get(15).getAttribute("ng-reflect-model").contains(zip)
                    && $$(inputsCSS).get(16).getAttribute("ng-reflect-model").contains(address) && $$(inputsCSS).get(17).getAttribute("ng-reflect-model").contains(city)
                    && $$(inputsCSS).get(18).getAttribute("ng-reflect-model").contains(city) && website.contains($$(inputsCSS).get(19).getAttribute("ng-reflect-model"))
                    && notes.contains($$(inputsCSS).get(20).getAttribute("ng-reflect-model")) && notes.contains($$(inputsCSS).get(21).getAttribute("ng-reflect-model"))) {
                if (!$$(profileIconsCSS).get(0).getAttribute("src").contains("user_profile.png")) {
                    System.out.println("Change General Settings - ok");
                } else {
                    System.out.println("Change General Settings - fail");
                    {
                        System.out.println($$(profileIconsCSS).get(0).getAttribute("src"));
                        System.out.println(file.getName());
                    }
                }
            } else System.out.println("Change General Settings - fail");
        } else {
            System.out.println("exp: " + (prefix + " " + firstName + " " + lastName + " " + suffix));
            System.out.println("fac: " + $(profileNameCSS).getText());
            System.out.println("Change General Settings - fail");
            screenshot("general_settings");
        }
    }

    @Test
    public void defaultHubTab() {
        $(profileBtnCSS).click();
        $$(tabsCSS).get(1).click();
        try {
            new WebDriverWait(WebDriverRunner.getWebDriver(), 3).until(ExpectedConditions.visibilityOf($(".md-radio-inner-circle")));
        } catch (Exception ignored) {
        }

        Random random = new Random();
        List<SelenideElement> rbuttons = $$(hubsRadioCSS);
        int i = random.nextInt(rbuttons.size());
        while (rbuttons.get(i).parent().parent().getAttribute("class").contains("checked")) {
            i = random.nextInt(rbuttons.size());
        }
        rbuttons.get(i).click();
        String hubname = rbuttons.get(i).parent().$(By.xpath(".//div[2]/span")).getText();
        $(saveBtnCSS).click();
        new WebDriverWait(WebDriverRunner.getWebDriver(), 5).until(ExpectedConditions.visibilityOf($(alertCSS)));

        new Logout().logout(false);
        new Login().login(password, false);

        $(sidemenuCSS).click();
        $$(hubIconsCSS).get(i).shouldBe(Condition.visible);
        for (SelenideElement e : $$(".left-liner")) {
            if (!e.getAttribute("style").equals("height: 0px;")) {
                if (e.parent().$(By.xpath(".//div[1]/span")).getText().equals(hubname) ) { // || !$$(hubIconsCSS).get(i).parent().parent().$(By.xpath(".//div[2]")).getAttribute("style").equals("height: 0px;")
                    System.out.println("Change Default Hub - ok");
                    $(profileBtnCSS).click();
                    $$(tabsCSS).get(1).click();
                    try {
                        new WebDriverWait(WebDriverRunner.getWebDriver(), 3).until(ExpectedConditions.visibilityOf($(".md-radio-inner-circle")));
                    } catch (Exception ignored) {
                    }
                    for (SelenideElement j : $$(".chat-name")) {
                        if (j.getText().equals("Testhub")) j.click();
                    }
                    $(saveBtnCSS).click();
                    new WebDriverWait(WebDriverRunner.getWebDriver(), 5).until(ExpectedConditions.visibilityOf($(alertCSS)));
                } else System.out.println("Change Default Hub - fail");
            }
        }
    }

    @Test
    public void changePassword() {
        $(profileBtnCSS).click();
        $$(tabsCSS).get(2).click();
        try {
            new WebDriverWait(WebDriverRunner.getWebDriver(), 3).until(ExpectedConditions.invisibilityOf($(".md-radio-inner-circle")));
        } catch (Exception ignored) {
        }

        $$(inputsCSS).get(0).shouldBe(Condition.visible);

        String oldPassword = null;
        try {
             oldPassword = Files.readAllLines(Paths.get(fileName)).get(1);
        } catch (IOException ignored) {
        }
        String newPassword = fairy.person().getPassword() + "1!";

        $$(inputsCSS).get(0).sendKeys(oldPassword);
        $$(inputsCSS).get(1).sendKeys(newPassword);
        $$(inputsCSS).get(2).sendKeys(newPassword);
        $(saveBtnCSS).click();

        new WebDriverWait(WebDriverRunner.getWebDriver(), 5).until(ExpectedConditions.visibilityOf($(alertCSS)));

        new Logout().logout(false);
        new Login().login(newPassword, false);

        try {
            new WebDriverWait(WebDriverRunner.getWebDriver(), 5).until(ExpectedConditions.visibilityOf($(profileBtnCSS)));
        } catch (Exception ignored) {
        }

        if ($(profileBtnCSS).isDisplayed()) {
            $(profileBtnCSS).click();
            $$(tabsCSS).get(2).click();
            try {
                new WebDriverWait(WebDriverRunner.getWebDriver(), 3).until(ExpectedConditions.invisibilityOf($(".md-radio-inner-circle")));
            } catch (Exception ignored) {
            }

            $$(inputsCSS).get(0).shouldBe(Condition.visible);
            $$(inputsCSS).get(1).shouldBe(Condition.visible);
            $$(inputsCSS).get(2).shouldBe(Condition.visible);

            $$(inputsCSS).get(0).sendKeys(newPassword);
            $$(inputsCSS).get(1).sendKeys(password);
            $$(inputsCSS).get(2).sendKeys(password);
            $(saveBtnCSS).click();

            new WebDriverWait(WebDriverRunner.getWebDriver(), 5).until(ExpectedConditions.visibilityOf($(alertCSS)));

            new Logout().logout(false);
            new Login().login(password, false);

            try {
                new WebDriverWait(WebDriverRunner.getWebDriver(), 5).until(ExpectedConditions.visibilityOf($(profileBtnCSS)));
            } catch (Exception ignored) {
            }

            if ($(profileBtnCSS).isDisplayed()) {
                System.out.println("Change Password - ok");
            } else {
                saveToFile(newPassword, 1, "./credentials");
                saveToFile(email, 0, "./credentials");
                System.out.println("New password is " + newPassword);
                System.out.println("Change Password - fail");
            }
        }
    }

    public void saveToFile(String value,  int line, String filepath) {
        List<String> fileContent;
        try {
            fileContent = new ArrayList<>(Files.readAllLines(Paths.get(filepath), StandardCharsets.UTF_8));
            fileContent.set(line, value);
            Files.write(Paths.get(filepath), fileContent, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("Error writing to file! User email is " + value);
        }
    }

    public File uploadImage(String changePhotoBtnCSS, String chooseFileCSS, String saveFileCSS) {
        List<File> listFiles = new ArrayList<>();
        for (final File fileEntry : new File("./FilesToUpload").listFiles()) {
            if (fileEntry.getName().toLowerCase().contains(".png") || fileEntry.getName().toLowerCase().contains(".jpg")) {
                listFiles.add(fileEntry);
            }
        }
        Random random = new Random();
        File file = listFiles.get(random.nextInt(listFiles.size()));
        $(changePhotoBtnCSS).click();
        $(chooseFileCSS).uploadFile(file);
        try {
            new WebDriverWait(WebDriverRunner.getWebDriver(), 10).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".result-image")));
        } catch (Exception ignored) {
        }
        $(saveFileCSS).click();
        return file;
    }

    void changeInputFields() {

        String prefix = "Dr.";
        String firstName = fairy.person().getFirstName();
        String lastName = fairy.person().getLastName();
        String suffix = "Jr.";
        String title = "Title";
        String companyName = fairy.person().getCompany().getName();
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
                case 6: break;
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
        File file = uploadImage(changePhotoBtnCSS, chooseFileCSS, saveFileCSS);
        try {
            new WebDriverWait(WebDriverRunner.getWebDriver(), 5).until(ExpectedConditions.not(ExpectedConditions.attributeContains($(profileEditIconCSS), "src", "user_profile.png")));
        } catch (Exception ignored) {
        }

        $(saveBtnCSS).click();


//        try {
//            new WebDriverWait(WebDriverRunner.getWebDriver(), 5).until(ExpectedConditions.visibilityOf($(alertCSS)));
//        } catch (Exception ignored) {
//        }
//
//        $(profileNameCSS).shouldNot(Condition.empty);
//        $$(inputsCSS).get(2).shouldNot(Condition.empty);
//        if ($(profileNameCSS).getText().contains(prefix + " " + firstName + " " + lastName + " " + suffix)) {
//            if ($$(inputsCSS).get(0).getAttribute("ng-reflect-model").contains(prefix) && $$(inputsCSS).get(1).getAttribute("ng-reflect-model").contains(firstName)
//                    && $$(inputsCSS).get(3).getAttribute("ng-reflect-model").contains(suffix)
//                    && $$(inputsCSS).get(4).getAttribute("ng-reflect-model").contains(title) && $$(inputsCSS).get(5).getAttribute("ng-reflect-model").contains(companyName)
//                    && secEmail.contains($$(inputsCSS).get(7).getAttribute("ng-reflect-model")) && $$(inputsCSS).get(8).getAttribute("ng-reflect-model").replace("(", "").replace(")", "").replace("-", "").replace(" ", "").contains(phone)
//                    && $$(inputsCSS).get(11).getAttribute("ng-reflect-model").contains(phone) && $$(inputsCSS).get(15).getAttribute("ng-reflect-model").contains(zip)
//                    && $$(inputsCSS).get(16).getAttribute("ng-reflect-model").contains(address) && $$(inputsCSS).get(17).getAttribute("ng-reflect-model").contains(city)
//                    && $$(inputsCSS).get(18).getAttribute("ng-reflect-model").contains(city) && website.contains($$(inputsCSS).get(19).getAttribute("ng-reflect-model"))
//                    && notes.contains($$(inputsCSS).get(20).getAttribute("ng-reflect-model")) && notes.contains($$(inputsCSS).get(21).getAttribute("ng-reflect-model"))) {
//                if (!$$(profileIconsCSS).get(0).getAttribute("src").contains("user_profile.png")) {
//                    System.out.println("Change General Settings - ok");
//                } else {
//                    System.out.println("Change General Settings - fail");
//                    {
//                        System.out.println($$(profileIconsCSS).get(0).getAttribute("src"));
//                        System.out.println(file.getName());
//                    }
//                }
//            } else System.out.println("Change General Settings - fail");
//        } else {
//            System.out.println("exp: " + (prefix + " " + firstName + " " + lastName + " " + suffix));
//            System.out.println("fac: " + $(profileNameCSS).getText());
//        }
    }
}
