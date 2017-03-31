package com.readdle.fluix.plugin.files;

import cucumber.api.java.en.And;

public class TestClass1 {

    TestClass1(TestClass1 clazz) {
    }

    /**
     * Compliant method
     *
     * @step. ^step with Javadoc$
     */
    @And("^step with Javadoc$")
    public boolean stepWithJavadoc() {
        return true;
    }

    @And("^step without Javadoc$") // Noncompliant
    public int stepWithoutJavadoc() {
        return 0;
    }

    /**
     *
     *
     */
    @And("^step with wrong Javadoc$") // Noncompliant
    public boolean stepWithWrongJavadoc() {
        return false;
    }

    public void notStep() {
        int a = 1;
    }

    @Deprecated
    public Object methodWithOtherAnnotation() {
        return new Object();
    }

}
