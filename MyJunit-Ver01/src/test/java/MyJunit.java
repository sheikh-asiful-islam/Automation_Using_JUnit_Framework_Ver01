import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class MyJunit {
    WebDriver driver;

    @BeforeAll
    public void setup(){
        driver= new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

    }
    @DisplayName("Check if title is showing")
    @Test
    public void a_getTitle(){
        driver.get("https://demoqa.com/");
        String titleActual= driver.getTitle();
        String titleExpected= "DEMOQA";
        Assertions.assertEquals(titleExpected,titleActual);
    }

    @DisplayName("check if image is exist")

    @Test
    public void b_checkImageExists(){
        driver.get("https://demoqa.com/");
        boolean status= driver.findElements(By.tagName("img")).get(1).isDisplayed();
        Assertions.assertTrue(status);


    }

@DisplayName("Check if image is exists")
@Test
    public void c_automateForm(){
        driver.get("https://demoqa.com/text-box");
        driver.findElement(By.id("userName")).sendKeys("Mr Rahim");
        driver.findElement(By.id("userEmail")).sendKeys("rahim@test.com");
        scroll(0,250);
        driver.findElement(By.id("submit")).click();
        String nameActual=driver.findElement(By.id("name")).getText();
        Assertions.assertTrue(nameActual.contains("Rahim"));

    }
@Test
    public void automateForm(){
        driver.get("https://demoqa.com/text-box");
        List<WebElement> formElement= driver.findElements(By.className("form-control"));
        formElement.get(0).sendKeys("Mr. Rahim");
        formElement.get(1).sendKeys("rahim@test.com");
        formElement.get(2).sendKeys("Mirpur 2"); //current address
        formElement.get(3).sendKeys("Mirpur 1"); // permanent class

        scroll(0,500);
        List<WebElement>buttonElem= driver.findElements(By.cssSelector("[type=button]"));
        buttonElem.get(1).click();



    }


@Test
    public void handleAlert() throws InterruptedException {
        driver.get("https://demoqa.com/alerts");
        driver.findElement(By.id("alertButton")).click();
        Thread.sleep(5000);
        driver.switchTo().alert().accept(); // for accepting pop up
//        driver.findElement(By.id("promtButton")).click();
//      driver.switchTo().alert().dismiss(); //if I want to cancel the alert
//       driver.switchTo().alert().sendKeys("AB");  // If I want to write  something to the promt box
    }



@Test
    public void selectDatePicket(){
        driver.get("https://demoqa.com/date-picker");
        WebElement txtCalendar= driver.findElement(By.id("datePickerMonthYearInput"));  // using variable
        txtCalendar.click();
        txtCalendar.sendKeys(Keys.CONTROL+"a",Keys.BACK_SPACE);
        txtCalendar.sendKeys("09/28/2023");
        txtCalendar.sendKeys(Keys.ENTER);

    }

@Test
    public void selectDropdown(){
        driver.get("https://demoqa.com/select-menu");
        Select select=new Select(driver.findElement(By.id("oldSelectMenu")));
        select.selectByVisibleText("Green");
        select.selectByValue("3");
        select.selectByIndex(4);
        scroll(0,500);

        // for selecting multiple
        Select multipleSelect=new Select(driver.findElement(By.name("cars")));
        if(multipleSelect.isMultiple()) {
            multipleSelect.selectByVisibleText("Volvo");
            multipleSelect.selectByVisibleText("Audi");
        }

    }







    public void scroll(int x, int y){
        JavascriptExecutor js= (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo("+x+","+y+")");
//        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }


    
@Test
    public void selectReactDropdown() throws InterruptedException {
        driver.get("https://demoqa.com/select-menu"); // site visit
        scroll(0,250);
        driver.findElements(By.className("css-1hwfws3")).get(2).click();
        Thread.sleep(2000);

        Actions actions= new Actions(driver);
        actions.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();



    }

//@Test
//    public void mouseHover(){
//        driver.get("https://daffodilvarsity.edu.bd/");
//        Actions actions=new Actions(driver);
//        WebElement lblMenu= driver.findElement(By.xpath("//a[contains(text(),'Admissions')]"));
//        actions.moveToElement(lblMenu).perform();
//    }

@Test
    public void uploadImage_DownloadImage() throws InterruptedException {
        driver.get("https://demoqa.com/upload-download");
//      driver.findElement(By.id("uploadFile")).sendKeys("D:/SQA Own Work/Junit/MyJunit-Ver01/src/test/resources/Screenshot (26).png");
        driver.findElement(By.id("uploadFile")).sendKeys(System.getProperty("user.dir")+"./src/test/resources/Screenshot (26).png");
        Thread.sleep(1000);
        driver.findElement(By.id("downloadButton")).click();

    }


    @Test
    public void buttonClick(){
        driver.get("https://demoqa.com/buttons");
        List<WebElement> btnElements= driver.findElements(By.className("btn-primary"));
        Actions actions=new Actions(driver);
        actions.doubleClick(btnElements.get(0)).perform();
        actions.contextClick(btnElements.get(1)).perform();
        actions.click(btnElements.get(2)).perform();
        // for getting
        List<WebElement> txtElement=driver.findElements(By.tagName("p"));
        String txt1= txtElement.get(0).getText();
        String txt2= txtElement.get(1).getText();
        String txt3= txtElement.get(2).getText();

        //assertion
        Assertions.assertTrue(txt1.contains("double click"));
        Assertions.assertTrue(txt2.contains("right click"));
        Assertions.assertTrue(txt3.contains("dynamic click"));






    }















    @Test
    public void handleTab() throws InterruptedException {
        driver.get("https://demoqa.com/browser-windows");
        driver.findElement(By.id("tabButton")).click();
        Thread.sleep(3000);
        ArrayList<String> w= new ArrayList(driver.getWindowHandles());
//switch to open tab
        driver.switchTo().window(w.get(1));
        System.out.println("New tab title: " + driver.getTitle());
        String text = driver.findElement(By.id("sampleHeading")).getText();
        Assertions.assertEquals(text,"This is a sample page");
        driver.close();
        driver.switchTo().window(w.get(0));
    }

    @Test
    public void handleWindow(){
        driver.get("https://demoqa.com/browser-windows");
        driver.findElement(By.id(("windowButton"))).click();
        String mainWindowHandle = driver.getWindowHandle();
        System.out.println(mainWindowHandle);
        Set<String> allWindowHandles = driver.getWindowHandles();

        Iterator<String> iterator = allWindowHandles.iterator();

        while (iterator.hasNext()) {
            String ChildWindow = iterator.next();
            System.out.println(ChildWindow);
            if (!mainWindowHandle.equalsIgnoreCase(ChildWindow)) {
                driver.switchTo().window(ChildWindow);
                String text= driver.findElement(By.id("sampleHeading")).getText();
                Assertions.assertTrue(text.contains("This is a sample page"));
            }

        }
        driver.close();
        driver.switchTo().window(mainWindowHandle);





    }


    @Test
    public void webTables(){
        driver.get("https://demoqa.com/webtables");
        driver.findElement(By.xpath("//span[@id='edit-record-1']//*[@stroke='currentColor']")).click();
        driver.findElement(By.id("submit")).click();

    }



    @Test

    public void scrapData(){
        driver.get("https://demoqa.com/webtables");
        WebElement table = driver.findElement(By.className("rt-tbody"));
        List<WebElement> allRows = table.findElements(By.className("rt-tr"));
        int i=0;
        for (WebElement row : allRows) {
            List<WebElement> cells = row.findElements(By.className("rt-td"));
            for (WebElement cell : cells) {
                i++;
                System.out.println("num["+i+"] "+ cell.getText());

            }
        }
    }
















    @AfterAll
    public void quitBrowser(){
        driver.quit();
    }



}
