package pages;

import annotations.Path;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Path("/")
public class MainPage extends AbsBasePage<MainPage> {

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "[data-qa='Wrapper'] div > div > [data-qa='ArticleThumb']")
    private List<WebElement> articleThumbs;

    public MainPage pageArticleThumbsShouldBeVisible() {
        long actualArticleThumbs = articleThumbs.stream().filter((WebElement articleThumb) -> waiters.waitForElementVisible(articleThumb)).count();
        assertThat(actualArticleThumbs)
                .as("")
                .isEqualTo(articleThumbs.size());

        return this;
    }
}
