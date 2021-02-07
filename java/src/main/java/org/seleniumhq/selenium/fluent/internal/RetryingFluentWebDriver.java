package org.seleniumhq.selenium.fluent.internal;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.seleniumhq.selenium.fluent.FluentWebDriver;
import org.seleniumhq.selenium.fluent.Monitor;
import org.seleniumhq.selenium.fluent.Period;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class RetryingFluentWebDriver extends FluentWebDriver {

    private final Period period;

    public RetryingFluentWebDriver(WebDriver webDriver, Period period, Context context, Monitor monitor) {
        super(webDriver, monitor, context, false);
        this.period = period;
    }

    @Override
    protected WebElement findElement(By by, Context ctx, SearchContext searchContext) {
        return retryingFindIt(by, searchContext);
    }

    @Override
    protected List<WebElement> findElements(By by, Context ctx) {
        return retryingFindThem(by);
    }

    @Override
    protected Period getPeriod() {
        return period;
    }

    @Override
    protected void changeTimeout() {
        delegate.manage().timeouts().implicitlyWait(period.howLong(), period.timeUnit());

    }

    @Override
    protected void resetTimeout() {
        delegate.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    }

}
