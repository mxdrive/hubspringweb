import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverProvider;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.codeborne.selenide.WebDriverRunner.setWebDriver;

public class TestSuite {


//    static String baseURL = "http://web.elhubio.com";
    static String baseURL = "http://dev.elhubio.com";
    String fileName = "./credentials.txt";
    static String email = "achernenko@s-pro.io";
//    public static String email = "testhub23@mailinator.com";
    static String password = "Password1!";
    public static String password2 = "Ploki100!";
//    public static String email = "dacevedo@jhsmiami.org";
//    public static String password = "123123";

    @BeforeSuite
    public void preconditions() {
//        System.setProperty("selenide.browser", "chrome");

        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        setWebDriver(new CustomWebDriverProvider().createDriver(capabilities));
        getWebDriver().manage().window().maximize();

//        System.setProperty("webdriver.gecko.driver", "./geckodriver");
//        Configuration.browser = "marionette";


        open(baseURL);
    }

    @Test (priority = 1)
    public void login() {
        new Login().login(password, true);
    }

    @Test (priority = 2)
    public void tabs() {
        new IsTabsClickable().allMethods();
    }

//    @Test
//    public void chats() throws InterruptedException {
//        for (int i = 0; i <= 50; i++) {
//            leftSidebar();
//        }
//    }

    @Test(dependsOnMethods = "login", priority = 3)
    public void leftSidebar() throws InterruptedException {
//        new LeftSidebar().chats();
        //TODO remove and uncomment upper line
        Thread.sleep(2000);
        $(".create-chat-icon").click();

        CreateChat chat = new CreateChat();
        chat.createChat("Role");
        LeftSidebar search = new LeftSidebar();
        search.search(".chats-list-container>div", ".chat-item>div>.title", "Chat", chat.getSearchValue());
        new LeftSidebar().createChatIcon(".create-chat-icon", "manage/conversation", "chat", false);
        chat.createChat("Group");
        search.search(".chats-list-container>div", ".chat-item>div>.title", "Chat", chat.getSearchValue());
        new LeftSidebar().createChatIcon(".create-chat-icon", "manage/conversation", "chat", false);
        chat.createChat("Direct");
        search.search(".chats-list-container>div", ".chat-item>div>.title", "Chat", chat.getSearchValue());
        new LeftSidebar().streams();
        CreateStream stream = new CreateStream();
        stream.createStream();
        search.search(".chats-list-container>div", ".btn-block>div>div>.title", "Stream", stream.getSearchValue());
        new LeftSidebar().alerts();

        CreateAlert alert = new CreateAlert();

        alert.createAlert("Direct", false);
        search.search(".chats-list-container>div>div", ".btn-block>div>div>.title", "Alert", alert.getSearchValue());
        alert.createAlert("Group", false);
        search.search(".chats-list-container>div>div", ".btn-block>div>div>.title", "Alert", alert.getSearchValue());
        alert.createAlert("Direct", true);
        alert.createAlert("Group", true);
    }

    @Test (priority = 4)
    public void profileEdit() {
        new ProfileEdit().generalSettingsTab();
        new ProfileEdit().defaultHubTab();
        //uncomment if want to check change password option
//        new ProfileEdit().changePassword();
    }

    @Test (priority = 5)
    public void changeHub() {
        new ChangeHub().changeHub();
    }

    @Test(priority = 6)
    public void createNode() {
        CreateNode createNode = new CreateNode();
        createNode.createFileNode();
        createNode.createContactNode();
        createNode.createTextNode();
        createNode.createLinkNode();
        createNode.createGenericNode();
    }
}
