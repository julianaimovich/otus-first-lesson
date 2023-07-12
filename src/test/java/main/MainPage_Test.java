package main;

import annotations.Driver;
import exceptions.PathEmptyException;
import extensions.UIExtensions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import pages.MainPage;

@ExtendWith(UIExtensions.class)
public class MainPage_Test {

    @Driver
    public WebDriver driver;

    @Test
    public void testArticleThumbs() throws PathEmptyException {
        MainPage mainPage = new MainPage(driver)
                .open()
                .pageHeaderShouldBeVisible()
                .pageArticleThumbsShouldBeVisible();

        String title = mainPage.getArticleThumbsTitle(1);
        mainPage
                .clickArticleThumbsByTitle(title)
                .pageHeaderShouldBeSameAs(title);
    }
}