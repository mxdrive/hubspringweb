import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.codeborne.selenide.Selenide.$$;

class ScrollHandler {

    void scrollHandler(String objectsCSS, WebDriver driver) {
        new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable($$(objectsCSS).get(0)));
        $$(objectsCSS).get(0).click();
        $$(objectsCSS).get(0).click();
        while (!isClickable($$(objectsCSS).get($$(objectsCSS).size() - 1))) {
            $$(objectsCSS).get($$(objectsCSS).size() - 1).scrollTo();
        }

    }

    private Boolean isClickable(SelenideElement element) {
        Boolean isClickable = false;
        try {
            new WebDriverWait(WebDriverRunner.getWebDriver(), 150).until(ExpectedConditions.elementToBeClickable(element));
            Configuration.timeout = 150000;
            element.click();
            isClickable = true;
        } catch (Exception ignored) {
        }
        return isClickable;
    }
}
