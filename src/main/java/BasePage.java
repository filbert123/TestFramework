import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class BasePage {
    public static AppiumDriver driver;
    public WebDriverWait wait;
    By editBoxLocator = By.id("com.slava.buylist:id/editText1");
    By addButtonLocator = By.id(" com.slava.buylist:id/button2");

    public BasePage(AppiumDriver driver) {
        this.driver = driver;
        //wait = new WebDriverWait(driver,30);
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
    }
    @AndroidFindBy(id="com.slava.buylist:id/editText1")
    public WebElement editBox;

    @AndroidFindBy(id="com.slava.buylist:id/button2")
    public WebElement addButton;

    @AndroidFindBy(id="com.slava.buylist:id/title")
    public WebElement listTitle;

    @Step("Type {0} list name")
    public void typeListName(String name){
        editBox.sendKeys(name);
    }

}
