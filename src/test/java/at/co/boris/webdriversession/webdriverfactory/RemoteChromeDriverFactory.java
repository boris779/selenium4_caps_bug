package at.co.boris.webdriversession.webdriverfactory;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v85.log.Log;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import java.util.HashMap;

public class RemoteChromeDriverFactory extends RemoteWebDriverFactory {


    public WebDriver createDriver() {

        this.setBasicCaps();

        HashMap chromePrefs = new HashMap();
        chromePrefs.put("plugins.always_open_pdf_externally", true);
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefs);

        options.setCapability(CapabilityType.BROWSER_NAME, "chrome");
        options.setCapability(CapabilityType.BROWSER_VERSION, getBrowserVersion());
        //options.setCapability(CapabilityType.PLATFORM_NAME, Platform.LINUX);



        URL gridServer = null;
        try {
            gridServer = URI.create(getRemoteTestingServer() + "/wd/hub").toURL();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        webDriver = new RemoteWebDriver(gridServer, options);
        webDriver = new Augmenter().augment(webDriver);

        DevTools devTools = ((HasDevTools) webDriver).getDevTools();
        devTools.createSession();

        devTools.send(Log.enable());
        return webDriver;
    }
}
