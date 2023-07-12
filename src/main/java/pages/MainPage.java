package pages;

import static org.assertj.core.api.Assertions.assertThat;

import annotations.Path;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import java.util.stream.Collectors;

@Path("/")
public class MainPage extends AbsBasePage<MainPage> {

    public MainPage(WebDriver driver) {
        super(driver);
    }

    private String articleTileSelector = "[data-qa='Wrapper'] div > div > [data-qa='ArticleThumb']";
    private String articleTitleSelector = articleTileSelector + " a";

    public MainPage pageArticleThumbsShouldBeVisible() {
        List<WebElement> tiles = $$(articleTileSelector);

        long actualArticleThumbs = tiles.stream().filter((WebElement articleThumb) -> waiters.waitForElementVisible(articleThumb)).count();

        assertThat(actualArticleThumbs)
                .as("")
                .isEqualTo(tiles.size());
        return this;
    }

    public String getArticleThumbsTitle(int index) {
        return $$(articleTitleSelector).get(--index).getText();
    }

    public ArticlePage clickArticleThumbsByTitle(String title) {
        List<WebElement> tiles = $$(articleTitleSelector)
                .stream()
                .filter((WebElement element) -> element.getText().equals(title))
                .collect(Collectors.toList());
        assertThat(tiles)
                .as("Size of tiles list should be 1")
                .hasSize(1);
        tiles.get(0).click();

        return new ArticlePage(driver);
    }
}
