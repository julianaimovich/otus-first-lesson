package pages;

import annotations.Path;
import exceptions.PathEmptyException;
import org.openqa.selenium.WebDriver;
import pageobject.AbsPageObject;

public abstract class AbsBasePage<T> extends AbsPageObject {

    private String BASE_URL = System.getProperty("base.url");

    public AbsBasePage(WebDriver driver) {
        super(driver);
    }

    private String getPath() throws PathEmptyException {
        Class<? extends AbsBasePage> clazz = this.getClass();

        if (clazz.isAnnotationPresent(Path.class)) {
            Path path = clazz.getAnnotation(Path.class);
            return path.value();
        }

        throw new PathEmptyException();
    }

    public T open() throws PathEmptyException {
        String path = getPath();
        String url = BASE_URL + "/" + path;
        if (BASE_URL.endsWith("/")) {
            url = BASE_URL + "/" + path;
        }

        driver.get(url);
        return (T) this;
    }
}