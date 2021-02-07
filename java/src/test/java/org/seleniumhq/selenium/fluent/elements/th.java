package org.seleniumhq.selenium.fluent.elements;

import org.junit.Test;
import org.openqa.selenium.By;
import org.seleniumhq.selenium.fluent.BaseTest;
import org.seleniumhq.selenium.fluent.FluentExecutionStopped;
import org.seleniumhq.selenium.fluent.FluentWebElements;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

public class th extends BaseTest {

    @Test
    public void th_functionality() {

        setupExpectationsSingle("th");

        FluentWebElements fe = fwd.th()
                .th(By.xpath("@foo = 'bar'"))
                .th(By.cssSelector("baz"))
                .ths();

        assertThat(fe, notNullValue());
        verificationsSingle("th");
    }
    
    @Test
    public void th_case_functionality() {

        setupExpectationsSingle("th", "TH");

        FluentWebElements fe = fwd.th()
                .th(By.xpath("@foo = 'bar'"))
                .th(By.cssSelector("baz"))
                .ths();

        assertThat(fe, notNullValue());
        verificationsSingle("th");
    }

    @Test
    public void ths_functionality() {

        setupExpectationsMultiple("th");

        FluentWebElements fe = fwd.th()
                .ths(By.name("qux"));

        assertThat(fe, notNullValue());

        verificationsMultiple("th");

    }
    
    @Test
    public void ths_case_functionality() {

        setupExpectationsMultiple("th", "TH");

        FluentWebElements fe = fwd.th()
                .ths(By.name("qux"));

        assertThat(fe, notNullValue());

        verificationsMultiple("th");

    }

    @Test
    public void th_mismatched() {

        when(wd.findElement(By.linkText("mismatching_tag_name"))).thenReturn(we);
        when(we.getTagName()).thenReturn("boo");

        try {
            fwd.th(By.linkText("mismatching_tag_name"))
                    .clearField();
            fail("should have barfed");
        } catch (FluentExecutionStopped e) {
            assertThat(e.getMessage(), equalTo("AssertionError during invocation of: ?.th(By.linkText: mismatching_tag_name)"));
            assertThat(e.getCause().getMessage(), equalTo("tag was incorrect, should have been th but was boo"));
        }

    }


}
