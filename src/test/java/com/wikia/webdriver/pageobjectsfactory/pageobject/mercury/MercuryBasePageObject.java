package com.wikia.webdriver.pageobjectsfactory.pageobject.mercury;

import com.wikia.webdriver.common.contentpatterns.MercuryContent;
import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mobile.MobileBasePageObject;
import io.appium.java_client.*;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.*;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.TouchScreen;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.HashMap;

public class MercuryBasePageObject extends MobileBasePageObject{

	public MercuryBasePageObject(WebDriver driver) {
		super(driver);
	}

	public SpecialMercuryPageObject openSpecialMercury(String wikiURL) {
		getUrl(wikiURL + MercuryContent.MERCURY_SPECIAL_PAGE);
		PageObjectLogging.log("openSpecialMercury", MercuryContent.MERCURY_SPECIAL_PAGE+ " opened", true);
		return new SpecialMercuryPageObject(driver);
	}

	public MercuryArticlePageObject openMercuryArticleByName(String wikiURL, String articleName) {
		getUrl(wikiURL + URLsContent.WIKI_DIR + articleName);
		PageObjectLogging.log("openMercuryArticleByName", "Article" + articleName + " was opened", true);
		return new MercuryArticlePageObject(driver);
	}

	public void openMercuryWiki(String wikiName){
		String mercuryWiki = urlBuilder.getUrlForWiki(wikiName);
		getUrl(mercuryWiki);
	}

	public void tapOnElement(WebElement element) {
		Actions flick = new Actions(driver);
		flick.click(element).perform();
	}

	public void doubleTapZoom(WebElement element) {
		Actions doubleTapZoom = new Actions(driver);
		doubleTapZoom.doubleClick(element).perform();
	}

	public void swipeLeft(WebElement element) {
		Actions swipeAction = new Actions(driver);
		swipeAction.dragAndDropBy(element, -100, 0).build().perform();
	}

	public void swipeRight(WebElement element) {
		Actions swipeAction = new Actions(driver);
		swipeAction.dragAndDropBy(element, +100, 0).build().perform();
	}

}
