import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.NoSuchElementException;

import static java.lang.System.exit;

public class Spider {
    public static void main(String[] args) throws NullPointerException{
        //设置路径
        System.setProperty("webdriver.chrome.driver",
                Spider.class.getClassLoader().getResource("chromedriver.exe").getPath());
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("https://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=1&tn=monline_7_dg&wd=%E7%8E%8B%E9%9C%81%E8%88%AA&oq=%25E7%2599%25BE%25E5%25BA%25A6&rsv_pq=8a80c3640001bc6a&rsv_t=c040N7bR3Lk3fYtfk1lz3W0tzUS%2B0GSvMHka%2BmVEFdsPa%2BjVC4GOxdLS5QYZ9EO8dsCt&rqlang=cn&rsv_enter=1&rsv_dl=tb&rsv_sug3=11&rsv_sug1=2&rsv_sug7=100&bs=%E7%99%BE%E5%BA%A6");

        spiderAll(webDriver);
    }

    private static void spiderAll(WebDriver webDriver) {
        //提取元素
        List<WebElement> elements = webDriver.findElements(By.className("t"));
        for (WebElement element : elements) {
            String str = element.findElement(By.tagName("a")).getText();
            System.out.println(str);
        }
        WebElement nextPageBtn = null;//跳页设置
        try {
            nextPageBtn = webDriver.findElement(By.xpath("//a[contains(text(),'下一页>')]"));
        } catch (NoSuchElementException e) {
            exit(1);
        }
        /*if(nextPageBtn != null){*/
            System.out.println("----------------------------");
            nextPageBtn.click();
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spiderAll(webDriver);
        /*}*/
    }

}
