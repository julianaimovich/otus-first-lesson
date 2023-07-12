package extensions;

import annotations.Driver;
import factory.FactoryDriver;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.WebDriver;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UIExtensions implements BeforeEachCallback, AfterEachCallback {

    private WebDriver driver;

    private List<Field> getFieldsByAnnotation(Class<? extends Annotation> annotation, Class<?> testClass) {
        return Arrays.stream(testClass.getFields())
                .filter((Field field) -> field.isAnnotationPresent(annotation) && field.getType().getName().equals(WebDriver.class.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public void beforeEach(ExtensionContext extensionContext) throws Exception {
        driver = new FactoryDriver().getDriver();

        List<Field> fields = this.getFieldsByAnnotation(Driver.class, extensionContext.getTestClass().get());

        for (Field field: fields) {
            AccessController.doPrivileged((PrivilegedAction<Void>)
                 () -> {
                    field.setAccessible(true);
                    try {
                        field.set(extensionContext.getTestInstance().get(), driver);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                    return null;
                }
            );
        }
    }

    @Override
    public void afterEach(ExtensionContext extensionContext) throws Exception {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }
}