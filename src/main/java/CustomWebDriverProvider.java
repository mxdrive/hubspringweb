import com.codeborne.selenide.WebDriverProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class CustomWebDriverProvider implements WebDriverProvider {
    @Override
    public WebDriver createDriver(DesiredCapabilities capabilities) {
//            FirefoxProfile profile = new FirefoxProfile(new File("/home/test/MozzillaProf/"));
//            profile.setAssumeUntrustedCertificateIssuer(false);
//            profile.addAdditionalPreference("general.useragent.override", "some UA string");
//
//            capabilities.setCapability(FirefoxDriver.PROFILE, profile);
//            return new FirefoxDriver(capabilities);
//        System.setProperty("selenide.browser", "chrome");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-data-dir=./Default");
        return new ChromeDriver(capabilities);
    }
}