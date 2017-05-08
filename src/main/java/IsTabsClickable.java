import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class IsTabsClickable {

    @Test
    public void allMethods() {
        tabs();
    }

    private void tabs() {
        String newTabName;
        new WebDriverWait(WebDriverRunner.getWebDriver(), 10).until(ExpectedConditions.visibilityOf($(".header-title")));
        String currentTabName = $(".header-title").getText();
        for (int i = $$(".md-tab-label").size() - 1; i >= 0 ; i--) {
            $$(".md-tab-label").get(i).click();
            $(".header-title").shouldNotBe(Condition.text(currentTabName));
            $(".header-title").shouldNotBe(Condition.empty);
            newTabName = $(".header-title").getText();
            if (!newTabName.equals(currentTabName)) {
                currentTabName = newTabName;
                if ($$(".md-tab-label").get(i).getAttribute("class").contains("active")) {
                    System.out.println($(".header-title").getText() + " tab click - ok");
                } else System.out.println($(".header-title") + " tab click - fail");
            }
        }
    }
}
