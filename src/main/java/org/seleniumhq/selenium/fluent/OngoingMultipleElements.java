package org.seleniumhq.selenium.fluent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

public class OngoingMultipleElements extends OngoingFluentWebDriver implements List<WebElement> {

    private final List<WebElement> currentElements;

    public OngoingMultipleElements(WebDriver delegate, List<WebElement> currentElements, String context) {
        super(delegate, context);
        this.currentElements = currentElements;
    }

    protected WebElement findIt(By by) {
        throw new UnsupportedOperationException("not applicable to this impl");
    }

    @Override
    protected List<WebElement> findThem(By by) {
        throw new UnsupportedOperationException("not applicable to this impl");
    }

    public OngoingFluentWebDriver click() {
        String ctx = context + ".click()";
        try {
            for (WebElement webElement : this) {
                webElement.click();
            }
            return getOngoingMultipleElements(this, ctx);
        } catch (WebDriverException e) {
            throw decorateWebDriverException(ctx, e);
        }
    }

    /**
     *  Use this instead of clear() to clear an WebElement
     */
    public OngoingFluentWebDriver clearField() {
        String ctx = context + ".clearField()";
        try {
            for (WebElement webElement : this) {
                webElement.clear();
            }
            return getOngoingMultipleElements(this, ctx);
        } catch (WebDriverException e) {
            throw decorateWebDriverException(ctx, e);
        }
    }

    public OngoingFluentWebDriver submit() {
        String ctx = context + ".submit()";
        try {
            for (WebElement webElement : this) {
                webElement.submit();
            }
            return getOngoingMultipleElements(this, ctx);
        } catch (WebDriverException e) {
            throw decorateWebDriverException(ctx, e);
        }
    }

    // These are as they would be in the WebElement API

    public OngoingFluentWebDriver sendKeys(CharSequence... keysToSend) {
        String ctx = context + ".sendKeys(" + charSeqArrayAsHumanString(keysToSend) + ")";
        try {
            for (WebElement webElement : this) {
                webElement.sendKeys(keysToSend);
            }
            return getOngoingMultipleElements(this, ctx);
        } catch (WebDriverException e) {
            throw decorateWebDriverException(ctx, e);
        }
    }

    public boolean isSelected() {
        String ctx = context + ".isSelected()";
        try {
            boolean areSelected = true;
            for (WebElement webElement : this) {
                areSelected = areSelected & webElement.isSelected();
            }
            return areSelected;
        } catch (WebDriverException e) {
            throw decorateWebDriverException(ctx, e);
        }
    }

    public boolean isEnabled() {
        String ctx = context + ".isEnabled()";
        try {
            boolean areEnabled = true;
            for (WebElement webElement : this) {
                areEnabled = areEnabled & webElement.isEnabled();
            }
            return areEnabled;
        } catch (WebDriverException e) {
            throw decorateWebDriverException(ctx, e);
        }
    }

    public boolean isDisplayed() {
        String ctx = context + ".isDisplayed()";
        try {
            boolean areDisplayed = true;
            for (WebElement webElement : this) {
                areDisplayed = areDisplayed & webElement.isDisplayed();
            }
            return areDisplayed;
        } catch (WebDriverException e) {
            throw decorateWebDriverException(ctx, e);
        }
    }

    public String getText() {
        String ctx = context + ".getText()";
        try {
            String text = "";
            for (WebElement webElement : this) {
                text = text + webElement.getText();
            }
            return text;
        } catch (WebDriverException e) {
            throw decorateWebDriverException(ctx, e);
        }
    }

    @Override
    public Point getLocation() {
        throw new UnsupportedOperationException("getLocation() has no meaning for multiple elements");
    }

    @Override
    public String getCssValue(String cssName) {
        throw new UnsupportedOperationException("getCssValue() has no meaning for multiple elements");
    }

    @Override
    public String getAttribute(String attrName) {
        throw new UnsupportedOperationException("getAttribute() has no meaning for multiple elements");
    }

    @Override
    public String getTagName() {
        throw new UnsupportedOperationException("getTagName() has no meaning for multiple elements");
    }

    @Override
    public Dimension getSize() {
        throw new UnsupportedOperationException("getSize() has no meaning for multiple elements");
    }

    public OngoingMultipleElements filter(FluentMatcher matcher) {
        ArrayList<WebElement> results = new ArrayList<WebElement>();
        for (WebElement webElement : this) {
            if (matcher.matches(webElement)) {
                results.add(webElement);
            }
        }
        return getOngoingMultipleElements(results, "");
    }

    public OngoingSingleElement first(FluentMatcher matcher) {
        WebElement result = null;
        for (WebElement webElement : this) {
            if (matcher.matches(webElement)) {
                result = webElement;
                break;
            }
        }
        if (result == null) {
            throw new NothingMatches();
        }
        return getOngoingSingleElement(result, context);
    }


    // From java.util.List

    public void clear() {
        currentElements.clear();
    }

    public int size() {
        return currentElements.size();
    }

    public boolean isEmpty() {
        return currentElements.isEmpty();
    }

    public boolean contains(Object o) {
        return currentElements.contains(o);
    }

    public Iterator<WebElement> iterator() {
        return currentElements.iterator();
    }

    public Object[] toArray() {
        return currentElements.toArray();
    }

    public <WebElement> WebElement[] toArray(WebElement[] ts) {
        return currentElements.toArray(ts);
    }

    public boolean add(WebElement webElement) {
        return currentElements.add(webElement);
    }

    public boolean remove(Object o) {
        return currentElements.remove(o);
    }

    public boolean containsAll(Collection<?> objects) {
        return currentElements.containsAll(objects);
    }

    public boolean addAll(Collection<? extends WebElement> webElements) {
        return currentElements.addAll(webElements);
    }

    public boolean addAll(int i, Collection<? extends WebElement> webElements) {
        return currentElements.addAll(webElements);
    }

    public boolean removeAll(Collection<?> objects) {
        return currentElements.removeAll(objects);
    }

    public boolean retainAll(Collection<?> objects) {
        return currentElements.retainAll(objects);
    }

    public WebElement get(int i) {
        return currentElements.get(i);
    }

    public WebElement set(int i, WebElement webElement) {
        return currentElements.set(i, webElement);
    }

    public void add(int i, WebElement webElement) {
        currentElements.add(i,webElement);
    }

    public WebElement remove(int i) {
        return currentElements.remove(i);
    }

    public int indexOf(Object o) {
        return currentElements.indexOf(o);
    }

    public int lastIndexOf(Object o) {
        return currentElements.lastIndexOf(o);
    }

    public ListIterator<WebElement> listIterator() {
        return currentElements.listIterator();
    }

    public ListIterator<WebElement> listIterator(int i) {
        return currentElements.listIterator(i);
    }

    public List<WebElement> subList(int i, int i1) {
        return currentElements.subList(i, i1);
    }

}
