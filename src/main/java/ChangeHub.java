import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.util.Random;

import static com.codeborne.selenide.Selenide.*;

public class ChangeHub extends ProfileEdit{
    @Test
    public void changeHub() {
        int currentHub;
        int newHub;
        $(sidemenuCSS).click();
        $$(hubIconsCSS).get(0).shouldBe(Condition.visible);
        new WebDriverWait(WebDriverRunner.getWebDriver(), 5).until(ExpectedConditions.visibilityOf($$(hubIconsCSS).get($$(hubIconsCSS).size() - 1)));
        currentHub = isSelected();
        Random random = new Random();
        SelenideElement element;
        for (SelenideElement e : $$(hubIconsCSS)) {
            element = $$(hubIconsCSS).get(random.nextInt($$(hubIconsCSS).size()));
            try {
                new WebDriverWait(WebDriverRunner.getWebDriver(), 2).until(ExpectedConditions.visibilityOf(e));
            } catch (Exception e1) {
                $(sidemenuCSS).click();
            }
            try {
                new WebDriverWait(WebDriverRunner.getWebDriver(), 5).until(ExpectedConditions.elementToBeClickable(element));
            } catch (Exception ignored) {
            }
            if (e.parent().parent().$(By.xpath(".//div[2]")).getAttribute("style").equals("height: 0px;")) {
                element.click();
            }
        }
        new WebDriverWait(WebDriverRunner.getWebDriver(), 5).until(ExpectedConditions.visibilityOf($$(hubIconsCSS).get($$(hubIconsCSS).size() - 1)));
        newHub = isSelected();
        if (currentHub != newHub) {
            System.out.println("Change Hub - ok");
            new WebDriverWait(WebDriverRunner.getWebDriver(), 3).until(ExpectedConditions.elementToBeClickable($$(hubIconsCSS).get(currentHub)));
            $$(hubIconsCSS).get(2).click();
            System.out.println("currentHub " + currentHub);
        } else {
            System.out.println("Change Hub - fail");
            $$(hubIconsCSS).get(2).click();
        }
    }

    private int isSelected() {
        int hub = -1;
        for (int i = 0; i < $$(hubIconsCSS).size(); i++) {
            if (!$$(hubIconsCSS).get(i).parent().parent().$(By.xpath(".//div[2]")).getAttribute("style").equals("height: 0px;")) {
                hub = i;
            }
        }
        return hub;
    }
}
