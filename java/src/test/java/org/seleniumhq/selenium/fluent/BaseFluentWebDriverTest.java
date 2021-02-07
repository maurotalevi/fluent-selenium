package org.seleniumhq.selenium.fluent;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.seleniumhq.selenium.fluent.internal.Context;
import org.seleniumhq.selenium.fluent.internal.Execution;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.startsWith;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;

public class BaseFluentWebDriverTest {

    private WebDriver wd = mock(WebDriver.class);
    private Internal.BaseFluentWebDriver fc;

    @Before
    public void setup() {
        fc = new Internal.BaseFluentWebDriver(wd, Context.singular(null, "dummy"), new Monitor.NULL(), false) {

            @Override
            protected FluentWebElements makeFluentWebElements(List<FluentWebElement> results, Context context, Monitor monitor1) {
                return null;
            }

            @Override
            protected WebElement findElement(By by, Context ctx, SearchContext searchContext) {
                return null;
            }

            @Override
            protected List<WebElement> findElements(By by, Context ctx) {
                return null;
            }

            public Internal.BaseFluentWebDriver within(Period p) {
                return null;
            }

            public FluentWebDriver without(Period p) {
                return null;
            }

            @Override
            protected WebElement actualFindElement(By by, Context ctx, SearchContext searchContext) {
                return null;
            }

            @Override
            protected List<WebElement> actualFindElements(By by, Context ctx) {
                return null;
            }

        };

    }

    @Test
    public void assertionError_should_be_wrapped_in_context_exception() {

        try {
            Context dummy_context = Context.singular(null, "dummy");
            fc.executeAndWrapReThrowIfNeeded(new Execution() {
                public Void execute() {
                    throw new AssertionError("Oops");
                }
            }, null, dummy_context, true);
            fail("should have barfed");
        } catch (FluentExecutionStopped e) {
            assertThat(e.getMessage(), equalTo("AssertionError during invocation of: ?.dummy()"));
            assertThat(e.getCause().getMessage(), equalTo("Oops"));
        }

    }

    @Test
    public void runtimeException_should_be_wrapped_in_context_exception() {

        try {
            fc.executeAndWrapReThrowIfNeeded(new Execution() {
                public Void execute() {
                    throw new RuntimeException("Oops");
                }
            }, null, Context.singular(null, "dummy"), true);
            fail("should have barfed");
        } catch (FluentExecutionStopped e) {
            assertThat(e.getMessage(), equalTo("RuntimeException during invocation of: ?.dummy()"));
            assertThat(e.getCause().getMessage(), equalTo("Oops"));
        }

    }

    @Test
    public void staleElementException_should_be_wrapped_in_context_exception() {

        try {
            fc.executeAndWrapReThrowIfNeeded(new Execution() {
                public Void execute() {
                    throw new StaleElementReferenceException("Oops");
                }
            }, null, Context.singular(null, "dummy"), true);
            fail("should have barfed");
        } catch (FluentExecutionStopped.BecauseOfStaleElement e) {
            assertThat(e.getMessage(), equalTo("StaleElementReferenceException during invocation of: ?.dummy()"));
            assertThat(e.getCause().getMessage(), startsWith("Oops\nFor documentation"));
        }

    }

    @Test
    public void unsupportedOperationException_should_not_be_wrapped() {

        try {
            fc.executeAndWrapReThrowIfNeeded(new Execution() {
                public Void execute() {
                    throw new UnsupportedOperationException("Oops");
                }
            }, null, Context.singular(null, "dummy"), true);
            fail("should have barfed");
        } catch (UnsupportedOperationException e) {
            assertThat(e.getMessage(), equalTo("Oops"));
        }
    }
}
