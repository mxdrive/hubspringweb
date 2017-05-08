import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class TestSuite {

    public static String baseURL = "http://dev.elhubio.com";
    public String fileName = "./credentials.txt";
    public static String email = "achernenko@s-pro.io";
//    public static String email = "testhub@mailinator.com";
    public static String password = "Password1!";
    public static String password2 = "Ploki100!";
//    public static String email = "dacevedo@jhsmiami.org";
//    public static String password = "123123";

    @BeforeSuite
    public void preconditions() {
        System.setProperty("selenide.browser", "chrome");
//        System.setProperty("webdriver.gecko.driver", "./geckodriver");
//        Configuration.browser = "marionette";
        getWebDriver().manage().window().maximize();
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

    @Test(dependsOnMethods = "login", priority = 3)
    public void leftSidebar() throws InterruptedException {
        new LeftSidebar().chats();
        CreateChat chat = new CreateChat();
        chat.createChat("Role");
        LeftSidebar search = new LeftSidebar();
        search.search(".chats-list-container>div", ".chat-item>div>.title", "Chat", chat.getSearchValue());
        new LeftSidebar().createChatIcon(".create-chat-icon", "manage/conversation", "chat", false);
        chat.createChat("Group");
        search.search(".chats-list-container>div", ".chat-item>div>.title", "Chat", chat.getSearchValue());
        new LeftSidebar().streams();
        CreateStream stream = new CreateStream();
        stream.createStream();
        search.search(".chats-list-container>div", ".btn-block>div>div>.title", "Stream", stream.getSearchValue());
        new LeftSidebar().alerts();
        CreateAlert alert = new CreateAlert();
        alert.createAlert("Direct");
        search.search(".chats-list-container>div>div", ".btn-block>div>div>.title", "Alert", alert.getSearchValue());
        $(".create-chat-icon").click();
        alert.createAlert("Group");
        search.search(".chats-list-container>div>div", ".btn-block>div>div>.title", "Alert", alert.getSearchValue());
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