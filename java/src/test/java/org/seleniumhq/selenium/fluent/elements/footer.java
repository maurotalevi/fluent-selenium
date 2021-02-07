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

public class footer extends BaseTest {

    @Test
    public void footer_functionality() {

        setupExpectationsSingle("footer");

        FluentWebElements fe = fwd.footer()
                .footer(By.xpath("@foo = 'bar'"))
                .footer(By.cssSelector("baz"))
                .footers();

        assertThat(fe, notNullValue());
        verificationsSingle("footer");
    }

    @Test
    public void footers_functionality() {

        setupExpectationsMultiple("footer");

        FluentWebElements fe = fwd.footer()
                .footers(By.name("qux"));

        assertThat(fe, notNullValue());

        verificationsMultiple("footer");

    }

    @Test
    public void footer_mismatched() {

        when(wd.findElement(By.linkText("mismatching_tag_name"))).thenReturn(we);
        when(we.getTagName()).thenReturn("boo");

        try {
            fwd.footer(By.linkText("mismatching_tag_name"))
                    .clearField();
            fail("should have barfed");
        } catch (FluentExecutionStopped e) {
            assertThat(e.getMessage(), equalTo("AssertionError during invocation of: ?.footer(By.linkText: mismatching_tag_name)"));
            assertThat(e.getCause().getMessage(), equalTo("tag was incorrect, should have been footer but was boo"));
        }

    }


}
