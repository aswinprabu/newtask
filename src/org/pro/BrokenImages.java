package org.pro;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class BrokenImages {
public static void main(String[] args) throws Throwable {
	System.setProperty("webdriver.chrome.driver", "C:\\Users\\Aswin Prabu\\eclipse-workspace\\"
			+ "BrokenLinksAndBrokenImages\\driver\\chromedriver.exe");
	int count=0;
	WebDriver driver = new ChromeDriver();
	driver.get("https://www.amazon.in/");
	
	List<WebElement> brokenImgElement = driver.findElements(By.tagName("img"));
	for (int i = 0; i < brokenImgElement.size(); i++) {
		WebElement brokenImg = brokenImgElement.get(i);
		String urlLink = brokenImg.getAttribute("src");
		if(urlLink!=null) {
			if(urlLink.contains("http")) {
				URL url = new URL(urlLink);
				URLConnection openConnection = url.openConnection();
				HttpURLConnection http = (HttpURLConnection) openConnection;
				int responseCode = http.getResponseCode();
				if(responseCode==200) {
				System.out.println("Success Image==> "+responseCode+"\n"+urlLink);
				}else {
					System.out.println("Broken Image==>"+responseCode+"\n"+urlLink);
					count++;
				}
			}
		}
	}
	System.out.println("Number of Broken Images: "+count);
	driver.quit();
}
}
