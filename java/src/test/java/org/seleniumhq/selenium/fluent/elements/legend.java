package org.seleniumhq.selenium.fluent.elements;

import org.junit.Test;
import org.openqa.selenium.By;
import org.seleniumhq.selenium.fluent.BaseTest;
import org.seleniumhq.selenium.fluent.FluentExecutionStopped;
import org.seleniumhq.selenium.fluent.Internal;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

public class legend extends BaseTest {

    @Test
    public void legend_functionality() {

        setupExpectationsSingle("legend");

        Internal.BaseFluentWebElements fe = fwd.legend()
                .legend(By.xpath("@foo = 'bar'"))
                .legend(By.cssSelector("baz"))
                .legends();

        assertThat(fe, notNullValue());
        verificationsSingle("legend");
    }
    
    @Test
    public void legend_case_functionality() {

        setupExpectationsSingle("legend", "LEGEND");

        Internal.BaseFluentWebElements fe = fwd.legend()
                .legend(By.xpath("@foo = 'bar'"))
                .legend(By.cssSelector("baz"))
                .legends();

        assertThat(fe, notNullValue());
        verificationsSingle("legend");
    }

    @Test
    public void legends_functionality() {

        setupExpectationsMultiple("legend");

        Internal.BaseFluentWebElements fe = fwd.legend()
                .legends(By.name("qux"));

        assertThat(fe, notNullValue());

        verificationsMultiple("legend");

    }
    
    @Test
    public void legends_case_functionality() {

        setupExpectationsMultiple("legend", "LEGEND");

        Internal.BaseFluentWebElements fe = fwd.legend()
                .legends(By.name("qux"));

        assertThat(fe, notNullValue());

        verificationsMultiple("legend");

    }

    @Test
    public void legend_mismatched() {

        when(wd.findElement(By.linkText("mismatching_tag_name"))).thenReturn(we);
        when(we.getTagName()).thenReturn("boo");

        try {
            fwd.legend(By.linkText("mismatching_tag_name"))
                    .clearField();
            fail("should have barfed");
        } catch (FluentExecutionStopped e) {
            assertThat(e.getMessage(), equalTo("AssertionError during invocation of: ?.legend(By.linkText: mismatching_tag_name)"));
            assertThat(e.getCause().getMessage(), equalTo("tag was incorrect, should have been legend but was boo"));
        }

    }


}
