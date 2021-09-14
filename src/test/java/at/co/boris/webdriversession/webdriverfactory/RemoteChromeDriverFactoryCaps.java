package at.co.boris.webdriversession.webdriverfactory;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;

public class RemoteChromeDriverFactoryCaps extends RemoteWebDriverFactory {


    public WebDriver createDriver() {

        caps.setBrowserName("chrome");
        caps.setVersion(getBrowserVersion());
        caps.setPlatform(Platform.LINUX);
        caps.setCapability("sessionTimeout", "10m");
        caps.setCapability("enableVNC", true);
        caps.setCapability("name", getBranchName());

        HashMap chromePrefs = new HashMap();
        chromePrefs.put("plugins.always_open_pdf_externally", true);
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefs);

        options.merge(caps);

        System.out.println(caps);
        System.out.println(options);

        URL gridServer = null;
        try {
            gridServer = URI.create(getRemoteTestingServer() + "/wd/hub").toURL();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return webDriver = new RemoteWebDriver(gridServer, options);
    }
}
