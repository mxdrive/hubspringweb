import com.codeborne.selenide.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import static com.codeborne.selenide.Selenide.*;

public class LeftSidebar extends TestSuite{

    @Test
    public void chats() {
        new WebDriverWait(WebDriverRunner.getWebDriver(), 5).until(ExpectedConditions.elementToBeClickable($$(".chats-list-container>div>div>div>div>.message").get(0)));
//        isLastMessageDisplayed(".chats-list-container>div>div>div>div>.message", ".message-parsed", ".chats-list-container>div>div>div>div>.message", "Chats", ".outgoin.time-ago");
//        new ScrollHandler().scrollHandler(".chats-list-container>div", WebDriverRunner.getWebDriver());
        //TODO temp
        isLastMessageDisplayed(".chats-list-container>div>div>div>div>.message", ".message-parsed", ".chats-list-container>div>div>div>div>.message", "Chats");
        chatsFilter();
//        open(baseURL);
        $$(".tab-icon").get(1).click();
        $$(".tab-icon").get(0).click();
        //TODO temp
        chatsAreClickable(".chats-list-container>div>div>div>div>.message", "chats");
//        search(".chats-list-container>div", ".chat-item>div>.title", "Chat");
        new WebDriverWait(WebDriverRunner.getWebDriver(), 5).until(ExpectedConditions.visibilityOf($(".create-chat-icon")));
        createChatIcon(".create-chat-icon", "manage/conversation", "chat", true);
    }

    @Test
    public void streams() {
        $$(".tab-icon").get(1).click();
        $$(".tab-icon>i").get(1).shouldHave(Condition.attribute("class", "material-icons active"));
//        $$(".tab-icon").get(1).shouldBe(Condition.selected);
//        new ScrollHandler().scrollHandler(".chats-list-container>div", WebDriverRunner.getWebDriver());
        //TODO temp
        chatsAreClickable(".chats-list-container>div>div>div>.title","streams");
//        isLastMessageDisplayed(".chats-list-container>div>div>div>.title", ".message-parsed", ".chats-list-container>div>div>div>.message", "Streams", ".msg-date");
        //TODO temp
        isLastMessageDisplayed(".chats-list-container>div>div>div>.title", ".message-parsed", ".chats-list-container>div>div>div>.message", "Streams");
        streamsFilter();
        open(baseURL);
        $$(".tab-icon").get(1).click();
//        search(".chats-list-container>div", ".btn-block>div>div>.title", "Stream");
        createChatIcon(".create-chat-icon", "manage/stream", "stream", true);
    }

    @Test
    public void alerts() {
        $$(".tab-icon").get(2).click();
//        new ScrollHandler().scrollHandler(".chats-list-container>div", WebDriverRunner.getWebDriver());
//        isLastMessageDisplayed(".message-text>p",".chats-list-container>div>div", ".chats-list-container>div>div>div>div>.message", "Alerts", ".message-bubble.tail>footer");
        //TODO temp
        isLastMessageDisplayed(".message-text>p",".chats-list-container>div>div", ".chats-list-container>div>div>div>div>.message", "Alerts");
        open(baseURL);
        $$(".tab-icon").get(2).click();
        $$(".tab-icon>i").get(2).shouldHave(Condition.attribute("class", "material-icons active"));
        $(".selected-filter-value").shouldBe(Condition.hidden);
        //TODO temp
        alertsAreClickable();
//        search(".chats-list-container>div>div", ".btn-block>div>div>.title", "Alert");
        createChatIcon(".create-chat-icon", "manage/notification", "alert", true);
    }

    @Test
    public void chatsFilter() {
        $(".text-block").shouldBe(Condition.visible);
        $(".selected-filter-value").click();
        ElementsCollection filter = $$(".filter-value");
        for (int i = 1; i < filter.size(); i++) {
            String filterText = filter.get(i).getText();
            filter.get(i).sendKeys(Keys.ENTER);
            filter.get(i).shouldBe(Condition.hidden);
            String result = "ok";
            if (filterText.contains("direct")) {
                ElementsCollection results = $$(".chats-list-container>div>div>div>div.title");
                for (SelenideElement result1 : results) {
                    if (result1.find(By.xpath("./i")).exists()) {
                        System.out.println(result1.find(By.xpath("./i")).getAttribute("class"));
                        result = "fail";
                    }
                }
            } else if (filterText.contains("group")) {
                ElementsCollection results = $$(".chats-list-container>div>div>div>div");
                for (SelenideElement result1 : results) {
                    if (result1.find(By.xpath("./i")).exists()) {
                        if (!result1.find(By.xpath("./i")).getAttribute("class").contains("group")) {
                            System.out.println(result1.find(By.xpath("./i")).getAttribute("class"));
                            result = "fail";
                        }
                    }
                }
            } else if (filterText.contains("role")) {
                ElementsCollection results = $$(".chats-list-container>div>div>div>div");
                for (SelenideElement result1 : results) {
                    if (result1.find(By.xpath("./i")).exists()) {
                        if (!result1.find(By.xpath("./i")).getAttribute("class").contains("role")) {
                            System.out.println(result1.find(By.xpath("./i")).getAttribute("class"));
                            result = "fail";
                        }
                    }
                }
            }
            System.out.println("Chats Filter '" + filterText + "' results display " + result);
            $(".selected-filter-value").click();
            filter = $$(".filter-value");
            filter.get(i).shouldBe(Condition.visible);
        }
    }

    @Test
    public void streamsFilter() {
        $$(".tab-icon").get(1).click();
        $(".text-block").shouldBe(Condition.visible);
        $(".selected-filter-value").click();
        ElementsCollection filter = $$(".filter-value");
        for (int i = 1; i < filter.size(); i++) {
            String filterText = filter.get(i).getText();
            filter.get(i).sendKeys(Keys.ENTER);
            filter.get(i).shouldBe(Condition.hidden);
            String result = "ok";
            if (filterText.contains("all")) {
                ElementsCollection results = $$(".chats-list-container>div>div");
                for (SelenideElement result1 : results) {
//                    if (!results.get(j).find(By.xpath("./i")).getAttribute("class").contains("icon")) {
                    if (!result1.find(By.xpath("./i")).exists()) {
                        result = "fail";
                    }
                }
            } else if (filterText.contains("all")) {
                ElementsCollection results = $$(".chats-list-container>div>div");
                for (SelenideElement result1 : results) {
//                    if (!results.get(j).find(By.xpath("./i")).getAttribute("class").contains("icon")) {
                    if (result1.find(By.xpath("./i")).exists()) {
                        result = "fail";
                    }
                }
            }
            System.out.println("Streams Filter '" + filterText + "' results display " + result);
            new WebDriverWait(WebDriverRunner.getWebDriver(), 2).until(ExpectedConditions.elementToBeClickable($(".selected-filter-value")));
            $(".selected-filter-value").click();
            try {
                new WebDriverWait(WebDriverRunner.getWebDriver(), 2).until(ExpectedConditions.visibilityOf(filter.get(i)));
            } catch (Exception ignored) {
            }
            if (!filter.get(i).isDisplayed()) $(".selected-filter-value").click();
            filter = $$(".filter-value");
        }
    }

    @Test
    public void chatsAreClickable(String messageSidebarList, String tab) {
//        new WebDriverWait(WebDriverRunner.getWebDriver(), 4).until(ExpectedConditions.elementToBeClickable($(".selected-filter-value")));
        $(".selected-filter-value").click();
        $$(".filter-value").get(0).click();
        String title = $(".main-title").getText();
        String result = "ok";
        for (int i = 0; i < $$(messageSidebarList).size(); i++ ) {
            if (!$$(messageSidebarList).get(i).isDisplayed()) {
                Actions dragger = new Actions(WebDriverRunner.getWebDriver());
                dragger.moveToElement($$(messageSidebarList).get(i - 1));
                JavascriptExecutor Scrool = (JavascriptExecutor) WebDriverRunner.getWebDriver();
                Scrool.executeScript("arguments[0].scrollIntoView(true);", $$(messageSidebarList).get(i));
            }
            $$(messageSidebarList).get(i).click();
            try {
                new WebDriverWait(WebDriverRunner.getWebDriver(), 8).until(ExpectedConditions.not(ExpectedConditions.textToBe(By.cssSelector(".main-title"), title)));
//                System.out.println("mainTitle" + $(".main-title"));
//                System.out.println("title - " + title);
            } catch (Exception ignored) {
//                System.out.println("title error");
            }
            String titleClicked = $(".main-title").getText();
            if (title.equals(titleClicked) && !$$(messageSidebarList).get(i).parent().parent().parent().getAttribute("class").contains("selected")) {
                result = "fail";
                System.out.println("Old title - " + title);
                System.out.println("New title - " + titleClicked);
            }
            title = titleClicked;
        }
        System.out.println("All " + tab + " are clickable - " + result);
    }

    @Test
    public void alertsAreClickable() {
        try {
            new WebDriverWait(WebDriverRunner.getWebDriver(), 5).until(ExpectedConditions.visibilityOf($$(".chats-list-container>div>div>div>div>.message").get(0)));
        } catch (Exception ignored) {
        }
        if ($$(".chats-list-container>div>div>div>div>.message").size() != 0) {
            $$(".chats-list-container>div>div>div>div>.message").get(0).click();
//            String firstAlert = $(".message-text>p").getText();
            String firstAlert = null;
            if ($$(".chats-list-container>div>div>div>div>.message").size() != 1) {
                String result = "ok";
                for (int i = 0; i < $$(".chats-list-container>div>div>div>div>.message").size(); i++) {
                    if ($$(".chats-list-container>div>div>div>div>.message").get(i).isDisplayed()) {
                        $$(".chats-list-container>div>div>div>div>.message").get(i).click();
//                        try {
//                            new WebDriverWait(WebDriverRunner.getWebDriver(), 5).until(ExpectedConditions.not(ExpectedConditions.textToBe(By.cssSelector(".message-bubble.tail>footer"), date)));
//                        } catch (Exception ignored) {
//                        }
                        try {
                            new WebDriverWait(WebDriverRunner.getWebDriver(), 5).until(ExpectedConditions.not(ExpectedConditions.textToBe(By.cssSelector(".message-parsed"), firstAlert)));
                        } catch (Exception ignored) {
                        }
                        String alert = $(".message-parsed").getText();
                        if (alert.equals(firstAlert) && !$$(".chats-list-container>div>div").get(i).isSelected()) {
                            System.out.println($$(".chats-list-container>div>div").get(i).isSelected());
                            System.out.println(alert + " - alert");
                            System.out.println(firstAlert + " - first alert");
                            result = "fail";
                        }
                        firstAlert = alert;
                    }
                }
                System.out.println("All alerts are clickable - " + result);
            } else System.out.println("Alerts list contains one item");
        } else System.out.println("Alerts list is empty");
    }

    void search(String containerCSS, String listItemTitleCSS, String tabName, String searchValue) {
        if ($(".selected-filter-value").isDisplayed()) {
            $(".selected-filter-value").click();
            $$(".filter-value").get(2).shouldBe(Condition.visible);
//            $(".side-menu-icon").click();
            if ($$(".filter-value").get(0).isDisplayed()) {
                $$(".filter-value").get(0).click();
            } else {
                $(".selected-filter-value").click();
                $$(".filter-value").get(2).shouldBe(Condition.visible);
                $$(".filter-value").get(0).click();
            }
        }
        int count = $$(containerCSS).size();
        $(".input-header").sendKeys(searchValue);
        String result;
        try {
            new WebDriverWait(WebDriverRunner.getWebDriver(), 5).until(ExpectedConditions.visibilityOf($$(containerCSS).get(0)));
        } catch (Exception ignored) {
        }
        if ($$(containerCSS).size() != 0) {
            $$(containerCSS).shouldBe(CollectionCondition.sizeNotEqual(count));
            result = "ok";
            for (int i = 0; i < $$(containerCSS).size(); i++) {
                if (!$$(listItemTitleCSS).get(i).getText().toLowerCase().contains(searchValue.toLowerCase())) {
                    result = "fail";
                    System.out.println("listItemTitle - " + $$(listItemTitleCSS).get(i).getText());
                    System.out.println("searched for - " + searchValue);
                }
            }
            System.out.println(tabName + " Search - " + result);
        } else System.out.println(tabName + " Search result is empty");
        refresh();
    }

    @Test
    public void createChatIcon(String addButtonCSS, String url, String tab, Boolean isOutput) {
        new WebDriverWait(WebDriverRunner.getWebDriver(), 5).until(ExpectedConditions.elementToBeClickable($(addButtonCSS)));
        $(addButtonCSS).click();
        try {
            new WebDriverWait(WebDriverRunner.getWebDriver(), 10).until(ExpectedConditions.urlContains(url));
        } catch (Exception ignored) {
        }
        if (WebDriverRunner.url().contains(url)) {
            if (isOutput) {
                System.out.println("Create " + tab + " icon - ok");
            }
        } else {
            if (isOutput) {
                System.out.println("Create " + tab + " icon - fail");
            }
        }
    }

    @Test
    public void isLastMessageDisplayed(String listElementsCSS, String messagesCSS, String messageSidebarCSS, String tab) {
        String result = "ok";
        for (int i = 0; i < $$(listElementsCSS).size(); i++ ) {
            if ($$(listElementsCSS).get(i).isDisplayed()) {
                $$(listElementsCSS).get(i).click();
                try {
                    new WebDriverWait(WebDriverRunner.getWebDriver(), 1).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(messageSidebarCSS)));
                } catch (Exception ignored) {
                }
                if (!$$(messageSidebarCSS).get(i).getText().equals("")) {
                    try {
//                        new WebDriverWait(WebDriverRunner.getWebDriver(), 3).until(ExpectedConditions.visibilityOf($$(messagesCSS).get($$(messagesCSS).size() - 1)));
                        new WebDriverWait(WebDriverRunner.getWebDriver(), 2).until(ExpectedConditions.elementToBeSelected($$(listElementsCSS).get(i)));
                        new WebDriverWait(WebDriverRunner.getWebDriver(), 3).until(ExpectedConditions.textToBePresentInElement($$(messagesCSS).get($$(messagesCSS).size() - 1), $$(messageSidebarCSS).get(i).getText().substring($$(messageSidebarCSS).get(i).getText().indexOf(":") + 2).replace("...", "")));
//                        System.out.println("wait " + $$(messagesCSS).get($$(messagesCSS).size() - 1).getText());
//                        System.out.println("wait " + $$(messageSidebarCSS).get(i).getText().indexOf(":") + 2);
                    } catch (Exception ignored) {
                    }
                    if (!$$(messagesCSS).get($$(messagesCSS).size() - 1).getText().replaceAll("(\\r|\\n|\\r\\n)+", "").replaceAll(" ", "").contains($$(messageSidebarCSS).get(i).getText().substring($$(messageSidebarCSS).get(i).getText().indexOf(":") + 2).replace("...", "").replaceAll(" ", "")) && !$$(messageSidebarCSS).get(i).getText().substring($$(messageSidebarCSS).get(i).getText().indexOf(":") + 2).replace("...", "").replaceAll(" ", "").contains($$(messagesCSS).get($$(messagesCSS).size() - 1).getText().replaceAll("(\\r|\\n|\\r\\n)+", "").replaceAll(" ", ""))) {
                        if ($$(".file-name").size() == 0) {
                            System.out.println($$(messagesCSS).get($$(messagesCSS).size() - 1).getText().replaceAll("(\\r|\\n|\\r\\n)+", "") + " - last message in chat");
                            System.out.println($$(messageSidebarCSS).get(i).getText().substring($$(messageSidebarCSS).get(i).getText().indexOf(":") + 2).replace("...", "") + " - last message in sidebar");
                            result = "fail";
                        }
                        if ($$(".file-name").size() != 0) {
                            if (!$$(messageSidebarCSS).get(i).getText().substring($$(messageSidebarCSS).get(i).getText().indexOf(":") + 1).replace("...", "").contains($$(".file-name").get($$(".file-name").size() - 1).getText().replace("...", "@#@").split("@#@")[0])) {
                                result = "fail";
                                System.out.println($$(messagesCSS).get($$(messagesCSS).size() - 1).getText().replaceAll("(\\r|\\n|\\r\\n)+", "") + " - last message in chat");
                                System.out.println($$(messageSidebarCSS).get(i).getText().substring($$(messageSidebarCSS).get(i).getText().indexOf(":") + 1).replace("...", "") + " - last message in sidebar");
                                System.out.println($$(".file-name").get($$(".file-name").size() - 1).getText() + " - filename");
                            }
                        }
                    }
                } else {
                    if (!$$(messageSidebarCSS).get(i).parent().parent().parent().getAttribute("class").contains("selected")) {
//                            System.out.println("selected");
                        result = "fail";
                    }
                }
            }
        }
        System.out.println(tab + " last message display - " + result);
    }

//    public static boolean isClickable(WebElement webe) {
//        try {
//            WebDriverWait wait = new WebDriverWait(WebDriverRunner.getWebDriver(), 2);
//            wait.until(ExpectedConditions.elementToBeClickable(webe));
//            return true;
//        } catch (Exception e) {
//            System.out.println("unclickable");
//            return false;
//        }
//    }
}
