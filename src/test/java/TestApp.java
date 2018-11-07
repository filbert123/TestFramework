import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.LongPressOptions;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import setup.BaseSetUp;

import static io.appium.java_client.touch.offset.ElementOption.element;

public class TestApp extends BaseSetUp {
    private String listName = "MyList";
    private String currency = " £";
    private String defPackage = " pcs.";
    String item1Name = "Matches";
    String item2Name = "Apple";
    String item2Price = "3";
    String item3Name = "Sugar";
    String item3Price = "3";
    String item3Amount = "2";
    String item4Name = "Milk";
    String item4Price = "2";
    String item4Amount = "3";
    String item4Comment = "5% fat";
    String item4Package = "l";
    String item5Name = "Beer";
    String item5Price = "1.5";
    String item5Amount = "5";
    String item5Comment = "Light";
    String item5Package = "bottles";
    String item5Category = "Drinks, juices";
    String item5NewAmount = "10";
    String item5NewPrice = "2.5";
    String item5NewComment = "Dark";

    @Test (priority = 0, description = "Add new list and check it name")
    @Description("Some description")
    public void addNewListCheck() throws InterruptedException {
        BasePage basePage = new BasePage(driver);
        ListPage listPage = new ListPage(driver);
        //basePage.editBox.sendKeys(listName);
       basePage.typeListName(listName);
        basePage.addButton.click();
        Assert.assertEquals(listPage.listTitle.getText(),listName);
    }
    @Test (priority = 1, dependsOnMethods = {"addNewListCheck"})
    public void addNewItemWithNameOnly(){
        ListPage listPage = new ListPage(driver);
        listPage.addProductBox.sendKeys(item1Name);
        listPage.addProductButton.click();
        Assert.assertEquals(listPage.items.get(0).findElement(By.id(listPage.labelName)).getText(),item1Name);
    }
    @Test(priority = 2, dependsOnMethods = {"addNewListCheck"})
    public void addNewItemWithNameAndPrice(){
        ListPage listPage = new ListPage(driver);
        listPage.addProductBox.sendKeys(item2Name);
        listPage.priceBox.sendKeys(item2Price);
        listPage.addProductButton.click();
        if(listPage.items.size()==1){
            Assert.assertEquals(listPage.items.get(0).findElement(By.id(listPage.labelName)).getText(),item2Name);
            Assert.assertEquals(listPage.items.get(0).findElement(By.id(listPage.labelPrice)).getText(),item2Price+currency);
        }
        else {
            Assert.assertEquals(listPage.items.get(1).findElement(By.id(listPage.labelName)).getText(), item2Name);
            Assert.assertEquals(listPage.items.get(1).findElement(By.id(listPage.labelPrice)).getText(), item2Price + currency);
        }
    }
    @Test(priority = 3, dependsOnMethods = {"addNewListCheck"})
    public void addNewItemWithNameAndPriceAndAmount(){
        ListPage listPage = new ListPage(driver);
        listPage.addProductBox.sendKeys(item3Name);
        listPage.priceBox.sendKeys(item3Price);
        listPage.amountBox.sendKeys(item3Amount);
        listPage.addProductButton.click();
        if(listPage.items.size()==1){
            Assert.assertEquals(listPage.items.get(0).findElement(By.id(listPage.labelName)).getText(),item3Name);
            Assert.assertEquals(listPage.items.get(0).findElement(By.id(listPage.labelPrice)).getText(),item3Price+currency);
            Assert.assertEquals(listPage.items.get(0).findElement(By.id(listPage.labelAmount)).getText(),item3Amount+defPackage);
        }
        else {
            Assert.assertEquals(listPage.items.get(2).findElement(By.id(listPage.labelName)).getText(), item3Name);
            Assert.assertEquals(listPage.items.get(2).findElement(By.id(listPage.labelPrice)).getText(), item3Price + currency);
            Assert.assertEquals(listPage.items.get(2).findElement(By.id(listPage.labelAmount)).getText(), item3Amount + defPackage);
        }
    }
    @Test(priority = 4, dependsOnMethods = {"addNewListCheck"})
    public void addNewItemWithNameAndPriceAndAmountAndComment(){
        ListPage listPage = new ListPage(driver);
        listPage.addProductBox.sendKeys(item4Name);
        listPage.priceBox.sendKeys(item4Price);
        listPage.amountBox.sendKeys(item4Amount);
        listPage.packageList.click();
        listPage.selectDialog.findElementByAndroidUIAutomator("text(\""+item4Package+"\")").click();
        listPage.commentBox.sendKeys(item4Comment);
        listPage.addProductButton.click();
        if(listPage.items.size()==1){
            Assert.assertEquals(listPage.items.get(0).findElement(By.id(listPage.labelName)).getText(),item4Name);
            Assert.assertEquals(listPage.items.get(0).findElement(By.id(listPage.labelPrice)).getText(),item4Price+currency);
            Assert.assertEquals(listPage.items.get(0).findElement(By.id(listPage.labelAmount)).getText(),item4Amount+" "+item4Package);
            Assert.assertEquals(listPage.items.get(0).findElement(By.id(listPage.labelComment)).getText(),item4Comment);
        }
        else {
            Assert.assertEquals(listPage.items.get(3).findElement(By.id(listPage.labelName)).getText(), item4Name);
            Assert.assertEquals(listPage.items.get(3).findElement(By.id(listPage.labelPrice)).getText(), item4Price + currency);
            Assert.assertEquals(listPage.items.get(3).findElement(By.id(listPage.labelAmount)).getText(), item4Amount + " " + item4Package);
            Assert.assertEquals(listPage.items.get(3).findElement(By.id(listPage.labelComment)).getText(), item4Comment);
        }
    }

    @Test(priority = 5, dependsOnMethods = {"addNewListCheck"})
    public void addNewItemWithNameAndPriceAndAmountAndCommentAndCategory(){
        ListPage listPage = new ListPage(driver);
        listPage.addProductBox.sendKeys(item5Name);
        listPage.priceBox.sendKeys(item5Price);
        listPage.amountBox.sendKeys(item5Amount);
        listPage.packageList.click();
        listPage.selectDialog.findElementByAndroidUIAutomator("text(\""+item5Package+"\")").click();
        listPage.commentBox.sendKeys(item5Comment);
        listPage.listofCategories.click();
        listPage.selectDialog.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+item5Category+"\"))").click();
        listPage.addProductButton.click();
        driver.hideKeyboard();
        if(listPage.items.size()==1){
            Assert.assertEquals(listPage.items.get(0).findElement(By.id(listPage.labelName)).getText(),item5Name);
            Assert.assertEquals(listPage.items.get(0).findElement(By.id(listPage.labelPrice)).getText(),item5Price+currency);
            Assert.assertEquals(listPage.items.get(0).findElement(By.id(listPage.labelAmount)).getText(),item5Amount+" "+item5Package);
            Assert.assertEquals(listPage.items.get(0).findElement(By.id(listPage.labelComment)).getText(),item5Comment);
            Assert.assertTrue(listPage.items.get(0).findElement(By.xpath("//preceding-sibling::android.widget.ImageView")).isDisplayed());
        }
        else {
            Assert.assertEquals(listPage.items.get(4).findElement(By.id(listPage.labelName)).getText(), item5Name);
            Assert.assertEquals(listPage.items.get(4).findElement(By.id(listPage.labelPrice)).getText(), item5Price + currency);
            Assert.assertEquals(listPage.items.get(4).findElement(By.id(listPage.labelAmount)).getText(), item5Amount + " " + item5Package);
            Assert.assertEquals(listPage.items.get(4).findElement(By.id(listPage.labelComment)).getText(), item5Comment);
            Assert.assertTrue(listPage.items.get(4).findElement(By.xpath("//preceding-sibling::android.widget.ImageView")).isDisplayed());
        }
    }

    @Test(priority = 6, dependsOnMethods = {"addNewListCheck", "addNewItemWithNameOnly", "addNewItemWithNameAndPrice", "addNewItemWithNameAndPriceAndAmount", "addNewItemWithNameAndPriceAndAmountAndComment", "addNewItemWithNameAndPriceAndAmountAndCommentAndCategory"})
    public void checkTotal(){
        ListPage listPage = new ListPage(driver);
        Assert.assertEquals(listPage.totalLabel.getText(),"Total: 22.5 £");
    }

    @Test(priority = 7, dependsOnMethods = {"addNewListCheck", "addNewItemWithNameAndPriceAndAmountAndCommentAndCategory"})
    public void editExistingElement(){
        ListPage listPage = new ListPage(driver);
        TouchAction ta = new TouchAction(driver);
        if(listPage.items.size()==1) {
            ta.longPress(LongPressOptions.longPressOptions().withElement(element(listPage.items.get(0)))).perform();
        }
        else {
            ta.longPress(LongPressOptions.longPressOptions().withElement(element(listPage.items.get(4)))).perform();
        }
        listPage.selectDialog.findElementByAndroidUIAutomator("text(\"Edit\")").click();
        listPage.amountBox.clear();
        listPage.amountBox.sendKeys(item5NewAmount);
        listPage.priceBox.clear();
        listPage.priceBox.sendKeys(item5NewPrice);
        listPage.commentBox.clear();
        listPage.commentBox.sendKeys(item5NewComment);
        listPage.addProductButton.click();
        if(listPage.items.size()==1) {
            Assert.assertEquals(listPage.items.get(0).findElement(By.id(listPage.labelName)).getText(),item5Name);
            Assert.assertEquals(listPage.items.get(0).findElement(By.id(listPage.labelPrice)).getText(),item5NewPrice+currency);
            Assert.assertEquals(listPage.items.get(0).findElement(By.id(listPage.labelAmount)).getText(),item5NewAmount+" "+item5Package);
            Assert.assertEquals(listPage.items.get(0).findElement(By.id(listPage.labelComment)).getText(),item5NewComment);
        }
        else{
            Assert.assertEquals(listPage.items.get(4).findElement(By.id(listPage.labelName)).getText(),item5Name);
            Assert.assertEquals(listPage.items.get(4).findElement(By.id(listPage.labelPrice)).getText(),item5NewPrice+currency);
            Assert.assertEquals(listPage.items.get(4).findElement(By.id(listPage.labelAmount)).getText(),item5NewAmount+" "+item5Package);
            Assert.assertEquals(listPage.items.get(4).findElement(By.id(listPage.labelComment)).getText(),item5NewComment);
        }

        }

    @Test(priority = 8, dependsOnMethods = {"addNewListCheck", "addNewItemWithNameOnly", "addNewItemWithNameAndPrice", "addNewItemWithNameAndPriceAndAmount", "addNewItemWithNameAndPriceAndAmountAndComment", "addNewItemWithNameAndPriceAndAmountAndCommentAndCategory", "editExistingElement"})
    public void checkTotalAfterChangeItem(){
        ListPage listPage = new ListPage(driver);
        Assert.assertEquals(listPage.totalLabel.getText(),"Total: 40 £");
    }

    @Test(priority = 9, dependsOnMethods = {"addNewListCheck", "addNewItemWithNameAndPriceAndAmount"})
    public void deleteItem(){
        ListPage listPage = new ListPage(driver);
        TouchAction ta = new TouchAction(driver);
        int totalItems = listPage.items.size();
        if(listPage.items.size()==1) {
            ta.longPress(LongPressOptions.longPressOptions().withElement(element(listPage.items.get(0)))).perform();
        }
        else {
            ta.longPress(LongPressOptions.longPressOptions().withElement(element(listPage.items.get(2)))).perform();
        }
        listPage.selectDialog.findElementByAndroidUIAutomator("text(\"Remove\")").click();
        driver.findElementById("android:id/button1").click();
        Assert.assertEquals(listPage.items.size(),totalItems-1);
        if(listPage.items.size()==0) {
            Assert.assertEquals(listPage.totalLabel.getText(),"Total: 0 £");
        }
        else {
            Assert.assertEquals(listPage.totalLabel.getText(), "Total: 34 £");
        }
        }
    @Step("This is step")
    public void doStep(){
        BasePage basePage = new BasePage(driver);
        basePage.editBox.sendKeys(listName);
    }


}
