package at.co.boris.webdriversession.webdriverfactory;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;

public class RemoteWebDriverFactory extends WebDriverFactory {

    WebDriver webDriver;
    String videoRecording = System.getProperty("videoRecording", "false");

    protected void setBasicCaps() {
        caps.setVersion(getBrowserVersion());
        caps.setPlatform(Platform.LINUX);
        caps.setCapability("sessionTimeout", "10m");
        caps.setCapability("enableVNC", true);
        caps.setCapability("name", getBranchName());

        //caps.setCapability("screenResolution", getScreenSizeAsString(screenDimension));
        caps.setCapability("timeZone", getModifiedTimeZone());

        if (videoRecording.equals("true")) {
            //log.info("Video recording is enabled");
            caps.setCapability("enableVideo", true);
            caps.setCapability("videoName", getVideoPrefix() + "${LocalDateTime.now()}.mp4");
            caps.setCapability("videoCodec", "mpeg4");
        }


    }

    private String getVideoPrefix() {
        return "VIDEO_RECORDING_";
    }
    private String getModifiedTimeZone(){
        return System.getProperty("timezone", "Europe/Vienna");
    }
    protected String getRemoteTestingServer(){
        return System.getProperty("selenium.grid", "http://localhost:4444");
    }
    String getBranchName(){
        return System.getProperty("testbranch", "debug_run");
    }

//    private String getScreenSizeAsString(screenDimension: ScreenDimension, colordepth: Int = 24) {
//        return "${screenDimension.dimension.width}x${screenDimension.dimension.height}x$colordepth";
//    }

}
