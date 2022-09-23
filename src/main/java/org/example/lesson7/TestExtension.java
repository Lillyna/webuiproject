package org.example.lesson7;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

public class TestExtension implements TestWatcher {
    public void testFailed(ExtensionContext context, Throwable cause){
        System.out.println("Test failed!");
    }

}
