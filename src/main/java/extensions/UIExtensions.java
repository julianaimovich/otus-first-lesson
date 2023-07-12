package extensions;

import annotations.Driver;
import factory.FactoryDriver;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.WebDriver;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UIExtensions implements BeforeEachCallback {

    private List<Field> getFieldsByAnnotation(Class<? extends Annotation> annotation, Class<?> testClass) {
        return Arrays.stream(testClass.getFields())
                .filter((Field field) -> field.isAnnotationPresent(annotation) && field.getType().getName().equals(WebDriver.class.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public void beforeEach(ExtensionContext extensionContext) throws Exception {
        WebDriver driver = new FactoryDriver().getDriver();
        List<Field> fields = this.getFieldsByAnnotation(Driver.class, extensionContext.getTestClass().get());

    }

}