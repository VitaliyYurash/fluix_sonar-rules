package com.readdle.fluix.plugin.rules;

import org.junit.Test;
import org.sonar.java.checks.verifier.JavaCheckVerifier;

public class CorrectStepDefinitionRuleTest {

    @Test
    public void test() {
        JavaCheckVerifier.verify("src/test/java/com/readdle/fluix/plugin/files/TestClass3.java", new CorrectStepDefinitionRule());
    }
}
