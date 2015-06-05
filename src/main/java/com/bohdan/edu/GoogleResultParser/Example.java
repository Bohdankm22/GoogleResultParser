package com.bohdan.edu.GoogleResultParser;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Example {
	public static void main(String[] args) throws Exception {
		// The Firefox driver supports javascript
		WebDriver driver = new FirefoxDriver();

		// Go to the Google Suggest home page
		driver.get("http://www.google.com/webhp?complete=1&hl=en");

		// Enter the query string "Cheese"
		WebElement query = driver.findElement(By.name("q"));
		query.sendKeys("Cheese");
		WebElement button = driver.findElement(By.name("btnG"));
		button.click();
		// Sleep until the div we want is visible or 5 seconds is over
		wait(driver, By.className("r"));
		// And now list the suggestions
		List<WebElement> allResults = driver.findElements(By
				.xpath("//h3[@class='r']/a"));
		List<String> list = new ArrayList<String>();
		for (WebElement result : allResults){
			list.add(result.getAttribute("href"));
		}
		for (String link : list) {
			driver.get(link);
			wait(driver, By.tagName("title"));
			String title = driver.getTitle();
			System.out.println(title);
		}

		driver.quit();
	}

	private static void wait(WebDriver driver, By by) {
		long end = System.currentTimeMillis() + 5000;
		while (System.currentTimeMillis() < end) {
			try {
				WebElement resultDiv = driver.findElement(by);;
				if (resultDiv.isDisplayed()) {
					break;
				}
			} catch (NullPointerException e) {
			} catch (NoSuchElementException e) {
			}
		}
	}
}