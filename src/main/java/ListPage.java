import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ListPage extends BasePage {
    String labelName = "com.slava.buylist:id/title";
    String labelPrice = "com.slava.buylist:id/textView1";
    String labelAmount = "com.slava.buylist:id/TextView01";
    String labelComment = "com.slava.buylist:id/str1";
    public ListPage(AppiumDriver driver){
        super(driver);
       // wait = new WebDriverWait(driver,30);
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
    }
    @AndroidFindBy(id="com.slava.buylist:id/textView1")
    AndroidElement listTitle;

    @AndroidFindBy(id="com.slava.buylist:id/editText1")
    AndroidElement addProductBox;

    @AndroidFindBy(id="com.slava.buylist:id/button2")
    AndroidElement addProductButton;

    @AndroidFindBy(id="com.slava.buylist:id/editText2")
    AndroidElement priceBox;

    @AndroidFindBy(id="com.slava.buylist:id/editText3")
    AndroidElement amountBox;

    @AndroidFindBy(id="com.slava.buylist:id/rr1")
    List <AndroidElement> items;

    @AndroidFindBy(id="com.slava.buylist:id/editText4")
    AndroidElement commentBox;

    @AndroidFindBy(id="com.slava.buylist:id/spinner1")
    AndroidElement packageList;

    @AndroidFindBy(id="android:id/select_dialog_listview")
    AndroidElement selectDialog;

    @AndroidFindBy(id="com.slava.buylist:id/spinner2")
    AndroidElement listofCategories;

    @AndroidFindBy(id="com.slava.buylist:id/textView2")
    AndroidElement totalLabel;


}
