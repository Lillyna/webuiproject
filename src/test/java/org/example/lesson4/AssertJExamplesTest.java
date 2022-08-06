package org.example.lesson4;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.example.lesson4.Functions.isPolindrome;

public class AssertJExamplesTest {
    @Test
    void assertExample(){
        assertThat(isPolindrome("1234568")).isFalse();
        assertThat(6).isLessThan(10).isGreaterThan(0);

    }
}
