import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$$;

public class NodeContainer {
//    String nodeContainerItemsCSS = ".node-container>.node-icon";
    String nodeContainerItemsCSS = ".node";

    @Test
    public void areNodesClickable() {
        new WebDriverWait(WebDriverRunner.getWebDriver(), 5).until(ExpectedConditions.visibilityOf($$(nodeContainerItemsCSS).get(0)));
        for (SelenideElement e :
                $$(nodeContainerItemsCSS)) {
            e.click();
        }
    }
}
