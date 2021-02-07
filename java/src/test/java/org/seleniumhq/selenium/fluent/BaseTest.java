package org.seleniumhq.selenium.fluent;

import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.google.common.collect.Lists.newArrayList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class BaseTest {

    protected WebDriver wd;
    protected WebElement we;
    protected WebElement we2;
    protected WebElement we3;
    protected WebElement we4;
    protected WebElement we5;
    protected FluentWebDriver fwd;

    @Before
    public void setup() {
        wd = mock(WebDriver.class);
        we = mock(WebElement.class);
        we2 = mock(WebElement.class);
        we3 = mock(WebElement.class);
        we4 = mock(WebElement.class);
        we5 = mock(WebElement.class);
        fwd = new FluentWebDriver(wd);
    }

    protected void setupExpectationsSingle(String name) {
        setupExpectationsSingle(name, name);
    }

    protected void setupExpectationsSingle(String byName, String tagName) {
        when(wd.findElement(By.tagName(byName))).thenReturn(we);
        when(we.getTagName()).thenReturn(tagName);
        when(we.findElement(By.xpath(".//"+byName+"[@foo = 'bar']"))).thenReturn(we2);
        when(we2.getTagName()).thenReturn(tagName);
        when(we2.findElement(By.cssSelector("baz"))).thenReturn(we3);
        when(we3.getTagName()).thenReturn(tagName);
        when(we3.findElements(By.tagName(byName))).thenReturn(newArrayList(we4, we5));
        when(we4.getTagName()).thenReturn(tagName);
        when(we5.getTagName()).thenReturn(tagName);
    }

    protected void setupExpectationsSingleGeneric() {
        when(wd.findElement(By.cssSelector("*"))).thenReturn(we);
        setupExpectationsSingle("*");
    }

    protected void verificationsSingle(String name) {
        verify(wd).findElement(By.tagName(name));
        verify(we).getTagName();
        verify(we).findElement(By.xpath(".//"+name+"[@foo = 'bar']"));
        verify(we2).getTagName();
        verify(we2).findElement(By.cssSelector("baz"));
        verify(we3).getTagName();
        verify(we3).findElements(By.tagName(name));
        verify(we4).getTagName();
        verify(we5).getTagName();
        verifyNoMoreInteractions(wd, we, we2, we3, we4, we5);
    }

    protected void verificationsSingleGeneric() {
        String name = "*";
        verify(wd).findElement(By.tagName(name));
        verify(we).findElement(By.xpath(".//"+name+"[@foo = 'bar']"));
        verify(we2).findElement(By.cssSelector("baz"));
        verify(we3).findElements(By.tagName(name));
        verifyNoMoreInteractions(wd, we, we2, we3, we4, we5);
    }

    protected void setupExpectationsMultiple(String name) {
        setupExpectationsMultiple(name, name);
    }

    protected void setupExpectationsMultiple(String byName, String tagName) {
        when(wd.findElement(By.tagName(byName))).thenReturn(we);
        when(we.getTagName()).thenReturn(tagName);
        when(we.findElements(By.name("qux"))).thenReturn(newArrayList(we2, we3));
        when(we2.getTagName()).thenReturn(tagName);
        when(we3.getTagName()).thenReturn(tagName);
    }

    protected void setupExpectationsMultipleGeneric() {
        setupExpectationsMultiple("*");
    }

    protected void verificationsMultiple(String name) {
        verify(wd).findElement(By.tagName(name));
        verify(we).getTagName();
        verify(we).findElements(By.name("qux"));
        verify(we2).getTagName();
        verify(we3).getTagName();
        verifyNoMoreInteractions(wd, we, we2, we3);
    }

    protected void verificationsMultipleGeneric() {
        String name = "*";
        verify(wd).findElement(By.tagName(name));
        verify(we).findElements(By.name("qux"));
        verifyNoMoreInteractions(wd, we, we2, we3);
    }

}
