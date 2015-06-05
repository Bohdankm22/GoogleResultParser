package com.bohdan.edu.GoogleResultParser;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class GoogleResultParser {
	public static void main(String[] args) throws Exception {
		// The Firefox window
		WebDriver driver = new FirefoxDriver();
		// Getting users phrase for query
		String request = args[0];
		// Go to the Google Suggest home page
		driver.get("http://www.google.com/webhp?complete=1&hl=en");
		// waiting for page downloading until the element we want is visible or
		// 5 seconds is over
		WebElement query = driver.findElement(By.name("q"));
		query.sendKeys(request);
		WebElement button = driver.findElement(By.name("btnG"));
		button.click();
		// waiting for page downloading until the element we want is visible or
		// 5 seconds is over
		wait(driver, By.className("r"));
		// And now list the suggestions
		List<WebElement> allResults = driver.findElements(By
				.xpath("//h3[@class='r']/a"));
		List<String> list = new ArrayList<String>();
		for (WebElement result : allResults) {
			list.add(result.getAttribute("href"));
		}
		for (String link : list) {
			driver.get(link);
			// waiting for page downloading until the title is visible or 5
			// seconds is over
			wait(driver, By.tagName("title"));
			String title = driver.getTitle();
			System.out.println(title);
		}

		driver.close();
	}

	private static void wait(WebDriver driver, By by) {
		long end = System.currentTimeMillis() + 5000;
		while (System.currentTimeMillis() < end) {
			try {
				WebElement resultDiv = driver.findElement(by);
				;
				if (resultDiv.isDisplayed()) {
					break;
				}
			} catch (NullPointerException e) {
			} catch (NoSuchElementException e) {
			}
		}
	}
}