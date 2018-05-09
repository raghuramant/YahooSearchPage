package org.tasks.YahooSearchNavigation;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class MainScriptYahooSearchNavigation extends Utilities {
	static int searchResultDisplayEndIndex;
	static int searchResultDisplayStartIndex;
	static String ref;

	public static void main(String[] args) throws InterruptedException {
		browserOpen();
		launchURL("https://in.yahoo.com/");
		driver.findElement(By.id("uh-search-box")).sendKeys("Java", Keys.ENTER);
		navigateToSearchPage(2);
		Thread.sleep(3000);
		browserClose();
	}

	public static void navigateToSearchPage(int pageNumber) throws InterruptedException {
		searchResultDisplayEndIndex = pageNumber * 10;
		searchResultDisplayStartIndex = searchResultDisplayEndIndex - 9;
		ref = searchResultDisplayStartIndex + "-" + searchResultDisplayEndIndex;
		System.out.println(ref);
		String elementReference = "//a[@title='Results $']";
		String locatorPageNumber = elementReference.replace("$", ref);

		Thread.sleep(2000);
		// List<WebElement> listElements =
		// driver.findElements(By.xpath(locatorPageNumber));
		List<WebElement> listElements;
		listElements = driver.findElements(By.xpath(locatorPageNumber));

		if (pageNumber > 5) {

			do {

				Thread.sleep(3000);
				driver.findElement(By.xpath("//a[text()='Next']")).click();
				System.out.println(driver.findElement(By.xpath("//strong")).getText());
				listElements = driver.findElements(By.xpath(locatorPageNumber));

			} while (listElements.isEmpty());
		}
		driver.findElement(By.xpath(locatorPageNumber)).click();

	}

}
