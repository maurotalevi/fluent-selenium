/*
Copyright 2011-2013 Software Freedom Conservancy

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/
package org.seleniumhq.selenium.fluent;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.seleniumhq.selenium.fluent.internal.Context;
import org.seleniumhq.selenium.fluent.internal.Execution;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class FluentSelect extends FluentWebElement {

    private Select currentSelect;

    protected FluentSelect(WebDriver delegate, WebElement currentElement, Context context, Monitor monitor, boolean booleanInsteadOfNoSuchElement) {
        super(delegate, new Internal.WebElementHolder(null, currentElement, null), context, monitor, booleanInsteadOfNoSuchElement);
    }

    protected FluentSelect(WebDriver delegate, Select currentSelect, WebElement currentElement, Context context, Monitor monitor, boolean booleanInsteadOfNoSuchElement) {
        super(delegate, new Internal.WebElementHolder(null, currentElement, null), context, monitor, booleanInsteadOfNoSuchElement);
        this.currentSelect = currentSelect;
    }

    @Override
    public FluentSelect ifInvisibleWaitUpTo(Period period) {
        Context ifInvisibleWaitUpTo = Context.singular(context, "ifInvisibleWaitUpTo", period);
        executeAndWrapReThrowIfNeeded(new IfInvisibleWait(period), ifInvisibleWaitUpTo, true);
        return new FluentSelect(delegate, currentElement.getFound(), ifInvisibleWaitUpTo, monitor, booleanInsteadOfNotFoundException);
    }

    /**
     * @return Whether this select element support selecting multiple options at the same time? This
     *         is done by checking the value of the "multiple" attribute.
     */
    public boolean isMultiple() {
        return executeAndWrapReThrowIfNeeded(new IsMultiple(), Context.singular(context, "isMultiple"), true);
    }

    /**
     * @return All options belonging to this select tag
     */
    public List<WebElement> getOptions() {
        return executeAndWrapReThrowIfNeeded(new GetOptions(), Context.singular(context, "getOptions"), true);
    }

    /**
     * @return All selected options belonging to this select tag
     */
    public List<WebElement> getAllSelectedOptions() {
        return executeAndWrapReThrowIfNeeded(new GetAllSelectedOptions(), Context.singular(context, "getAllSelectedOptions"), true);
    }

    /**
     * @return The first selected option in this select tag (or the currently selected option in a
     *         normal select)
     */
    public WebElement getFirstSelectedOption() {
        return executeAndWrapReThrowIfNeeded(new GetFirstSelectedOption(), Context.singular(context, "getFirstSelectedOption"), true);
    }

    /**
     * <p>
     * Select all options that display text matching the argument. That is, when given "Bar" this
     * would select an option like:
     * </p>
     * &lt;option value="foo"&gt;Bar&lt;/option&gt;
     *
     * @param text The visible text to match against
     */
    public FluentSelect selectByVisibleText(final String text) {
        executeAndWrapReThrowIfNeeded(new SelectByVisibleText(text), Context.singular(context, "selectByVisibleText", null, text), true);
        return new FluentSelect(super.delegate, currentElement.getFound(), this.context, monitor, booleanInsteadOfNotFoundException);
    }

    /**
     * Select the option at the given index. This is done by examing the "index" attribute of an
     * element, and not merely by counting.
     *
     * @param index The option at this index will be selected
     */
    public FluentSelect selectByIndex(final int index) {
        executeAndWrapReThrowIfNeeded(new SelectByIndex(index), Context.singular(context, "selectByIndex", null, index), true);
        return new FluentSelect(super.delegate, currentElement.getFound(), this.context, monitor, booleanInsteadOfNotFoundException);
    }

    /**
     * <p>
     * Select all options that have a value matching the argument. That is, when given "foo" this
     * would select an option like:
     * </p>
     * &lt;option value="foo"&gt;Bar&lt;/option&gt;
     *
     * @param value The value to match against
     */
    public FluentSelect selectByValue(final String value) {
        executeAndWrapReThrowIfNeeded(new SelectByValue(value), Context.singular(context, "selectByValue", null, value), true);
        return new FluentSelect(super.delegate, currentElement.getFound(), this.context, monitor, booleanInsteadOfNotFoundException);
    }

    /**
     * Clear all selected entries. This is only valid when the SELECT supports multiple selections.
     *
     * @throws UnsupportedOperationException If the SELECT does not support multiple selections
     */
    public FluentSelect deselectAll() {
        executeAndWrapReThrowIfNeeded(new DeselectAll(), Context.singular(context, "deselectAll"), true);
        return new FluentSelect(super.delegate, currentElement.getFound(), this.context, monitor, booleanInsteadOfNotFoundException);
    }

    /**
     * <p>
     * Deselect all options that have a value matching the argument. That is, when given "foo" this
     * would deselect an option like:
     * </p>
     * &lt;option value="foo"&gt;Bar&lt;/option&gt;
     *
     * @param value The value to match against
     */
    public FluentSelect deselectByValue(final String value) {
        executeAndWrapReThrowIfNeeded(new DeselectByValue(value), Context.singular(context, "deselectByValue", null, value), true);
        return new FluentSelect(super.delegate, currentElement.getFound(), this.context, monitor, booleanInsteadOfNotFoundException);
    }

    private <T> T executeAndWrapReThrowIfNeeded(Execution<T> execution, Context ctx, boolean expectedToBeThere) {
        return executeAndWrapReThrowIfNeeded(execution, currentElement, ctx, expectedToBeThere);
    }

    /**
     * Deselect the option at the given index. This is done by examining the "index" attribute of an
     * element, and not merely by counting.
     *
     * @param index The option at this index will be deselected
     */
    public FluentSelect deselectByIndex(final int index) {
        executeAndWrapReThrowIfNeeded(new DeselectByIndex(index), Context.singular(context, "deselectByIndex", null, index), true);
        return new FluentSelect(super.delegate, currentElement.getFound(), this.context, monitor, booleanInsteadOfNotFoundException);
    }

    /**
     * <p>
     * Deselect all options that display text matching the argument. That is, when given "Bar" this
     * would deselect an option like:
     * </p>
     * &lt;option value="foo"&gt;Bar&lt;/option&gt;
     *
     * @param text The visible text to match against
     */
    public FluentSelect deselectByVisibleText(final String text) {
        executeAndWrapReThrowIfNeeded(new DeselectByVisibleText(text), Context.singular(context, "deselectByVisibleText", null, text), true);
        return new FluentSelect(super.delegate, currentElement.getFound(), this.context, monitor, booleanInsteadOfNotFoundException);
    }

    protected synchronized Select getSelect() {
        if (currentSelect == null) {
            currentSelect = new Select(currentElement.getFound());
        }
        return currentSelect;
    }

    public FluentSelect within(Period period) {
        return new RetryingFluentSelect(delegate, Context.singular(context, "within", null, period), currentSelect, currentElement.getFound(), period, monitor, booleanInsteadOfNotFoundException);
    }

    private class RetryingFluentSelect extends FluentSelect {

        private final Period period;

        public RetryingFluentSelect(WebDriver webDriver, Context context, Select currentSelect, WebElement currentElement, Period period, Monitor monitor, boolean booleanInsteadOfNoSuchElement) {
            super(webDriver, currentSelect, currentElement, context, monitor, booleanInsteadOfNoSuchElement);
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


    private class DeselectAll extends Execution<Boolean> {
        public Boolean execute() {
            getSelect().deselectAll();
            return true;
        }
    }

    private class SelectByValue extends Execution<Boolean> {
        private final String value;

        public SelectByValue(String value) {
            this.value = value;
        }

        public Boolean execute() {
            Select select = getSelect();
            select.selectByValue(value);
            return true;
        }
    }

    private class SelectByIndex extends Execution<Boolean> {
        private final int index;

        public SelectByIndex(int index) {
            this.index = index;
        }

        public Boolean execute() {
            getSelect().selectByIndex(index);
            return true;
        }
    }

    private class SelectByVisibleText extends Execution<Boolean> {
        private final String text;

        public SelectByVisibleText(String text) {
            this.text = text;
        }

        public Boolean execute() {
            getSelect().selectByVisibleText(text);
            return true;
        }
    }

    private class GetFirstSelectedOption extends Execution<WebElement> {
        public WebElement execute() {
            return getSelect().getFirstSelectedOption();
        }
    }

    private class GetAllSelectedOptions extends Execution<List<WebElement>> {
        public List<WebElement> execute() {
            return getSelect().getAllSelectedOptions();
        }
    }

    private class GetOptions extends Execution<List<WebElement>> {
        public List<WebElement> execute() {
            return getSelect().getOptions();
        }
    }

    private class IsMultiple extends Execution<Boolean> {
        public Boolean execute() {
            return getSelect().isMultiple();
        }
    }

    private class DeselectByValue extends Execution<Boolean> {
        private final String value;

        public DeselectByValue(String value) {
            this.value = value;
        }

        public Boolean execute() {
            getSelect().deselectByValue(value);
            return true;
        }
    }

    private class DeselectByIndex extends Execution<Boolean> {
        private final int index;

        public DeselectByIndex(int index) {
            this.index = index;
        }

        public Boolean execute() {
            getSelect().deselectByIndex(index);
            return true;
        }
    }

    private class DeselectByVisibleText extends Execution<Boolean> {
        private final String text;

        public DeselectByVisibleText(String text) {
            this.text = text;
        }

        public Boolean execute() {
            getSelect().deselectByVisibleText(text);
            return true;
        }
    }
}
