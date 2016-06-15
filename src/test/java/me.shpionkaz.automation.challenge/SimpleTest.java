package me.shpionkaz.automation.challenge;

import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class SimpleTest {

    @Test
    public void testAddition() throws Exception {
        assertThat(true, equalTo(true));
    }
}
