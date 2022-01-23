package org.pro;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class BrokenLinks {
public static void main(String[] args) throws Throwable {
	int count =0;
	System.setProperty("webdriver.chrome.driver", "C:\\Users\\Aswin Prabu\\eclipse-workspace\\"
			+ "BrokenLinksAndBrokenImages\\driver\\chromedriver.exe");
	
	WebDriver driver = new ChromeDriver();
	driver.get("https://www.amazon.in/");
	
	List<WebElement> anchorTagElements = driver.findElements(By.tagName("a"));
	
	for (int i = 0; i < anchorTagElements.size(); i++) {
		WebElement anchorTagList = anchorTagElements.get(i);
		String urlLink = anchorTagList.getAttribute("href");
		if (urlLink!=null) {
			if (urlLink.contains("http")) {
				URL url = new URL(urlLink);
				URLConnection openConnection = url.openConnection();
				HttpURLConnection http = (HttpURLConnection) openConnection;
				int responseCode = http.getResponseCode();
				if (responseCode==200) {
					System.out.println("Success Link==>"+responseCode+"\n"+urlLink);
				}else {
					count++;
					System.out.println("Broken Link==>"+responseCode+"\n"+urlLink);
				}
			}
		}
	}
	System.out.println("Number of Broken Links: "+count);
	driver.quit();
}
}
