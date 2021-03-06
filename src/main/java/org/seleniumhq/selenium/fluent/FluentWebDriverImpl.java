package org.seleniumhq.selenium.fluent;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public final class FluentWebDriverImpl extends FluentCore implements FluentWebDriver {

    public FluentWebDriverImpl(WebDriver delegate) {
        super(delegate, "?");
    }

    @Override
    protected OngoingSingleElement getOngoingSingleElement(WebElement result, String context) {
        return new OngoingSingleElement(super.delegate, result, context);
    }

    @Override
    protected OngoingMultipleElements getOngoingMultipleElements(List<WebElement> results, String context) {
        return new OngoingMultipleElements(super.delegate, results, context);
    }

    protected final WebElement findIt(By by) {
        WebElement result = delegate.findElement(by);
        return result;
    }

    @Override
    protected List<WebElement> findThem(By by) {
        List<WebElement> results = delegate.findElements(by);
        return results;
    }
}
