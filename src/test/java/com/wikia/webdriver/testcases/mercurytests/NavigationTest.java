package com.wikia.webdriver.testcases.mercurytests;

import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.url.UrlBuilder;
import com.wikia.webdriver.common.core.url.UrlChecker;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.common.Navigate;
import com.wikia.webdriver.elements.mercury.components.Navigation;
import com.wikia.webdriver.elements.mercury.components.TopBar;
import com.wikia.webdriver.elements.mercury.pages.MainPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;

@Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
@InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
public class NavigationTest extends NewTestTemplate {

  private TopBar topBar;
  private Navigation navigation;

  private void init() {
    this.topBar = new TopBar(driver);
    this.navigation = new Navigation(driver);

    new Navigate(driver).toPage(MercurySubpages.MAIN_PAGE);
  }

  @Test(groups = "mercury_navigation_openAndCloseNavigationAndItsSubMenu")
  public void mercury_navigation_openAndCloseNavigationAndItsSubMenu() {
    init();

    topBar.openNavigation();
    navigation.openSubMenu(1);
    navigation.closeSubMenu();
    topBar.closeNavigation();
  }

  @Test(groups = "mercury_navigation_navigateToPageUsingSearch")
  public void mercury_navigation_navigateToPageUsingSearch() {
    String articleToNavigate = "Infobox";
    init();

    topBar.openSearch();
    navigation.typeInSearch(articleToNavigate);
    navigation.selectSearchSuggestion(0);

    Assertion.assertTrue(driver.getCurrentUrl().contains(articleToNavigate));
  }

  @Test(groups = "mercury_navigation_topbarIsAlwaysVisible")
  public void mercury_navigation_topbarIsAlwaysVisible() {
    init();

    driver.executeScript("window.scrollTo(100, document.body.scrollHeight)");
    Assertion.assertTrue(topBar.isNavigationBarVisible());
    Assertion.assertTrue(topBar.isLogoVisible());
  }
}
