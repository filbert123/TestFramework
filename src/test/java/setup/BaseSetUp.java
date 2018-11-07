package setup;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;

public class BaseSetUp {
    public static AppiumDriver driver;
    protected WebDriverWait wait;
    private String testCaseName;
    private long testStartTime;
    private long testFinishTime;
    private long testDurationTime;
    private String testFailedMsg;

    public WebDriver getDriver() {
        return driver;
    }

    protected long getTestStartTime() {
        return testStartTime;
    }

    protected void setTestStartTime(long testStartTime) {
        this.testStartTime = testStartTime;
    }

    protected long getTestFinishTime() {
        return testFinishTime;
    }

    protected void setTestFinishTime(long testFinishTime) {
        this.testFinishTime = testFinishTime;
    }

    protected long getTestDurationTime() {
        return testDurationTime;
    }

    protected void setTestDurationTime(long testDurationTime) {
        this.testDurationTime = testDurationTime;
    }

    protected String getTestCaseName() {
        return testCaseName;
    }

    protected void setTestCaseName(String testCaseName) {
        this.testCaseName = testCaseName;
    }

    public String getTestFailedMsg() {
        return testFailedMsg;
    }

    public void setTestFailedMsg(String testFailedMsg) {
        this.testFailedMsg = testFailedMsg;
    }
    //public static AndroidDriver driver;
    //protected WebDriverWait wait;
    private AppiumDriverLocalService service = AppiumDriverLocalService.buildDefaultService();
    static String deviceName = "Nexus5";

    @BeforeSuite
    public void setUp() throws IOException {
        service.start();
        Runtime.getRuntime().exec("/Users/afilippov/Library/Android/sdk/tools/emulator -avd Nexus5 -netdelay none -netspeed full");
        DeviceSetUp.prepareDevice();
        wait = new WebDriverWait(driver,10);
    }
    @AfterSuite
    public void tearDown() throws IOException {
        driver.quit();
        service.stop();
        Runtime.getRuntime().exec("adb -s Nexus5 emu kill");

    }

}
