package com.readdle.fluix.plugin.files;

import cucumber.api.java.en.And;

public class TestClass2 {

    /**
     * Compliant method
     *
     * @step. ^step with Javadoc$
     */
    @And("^step with Javadoc$")
    public boolean stepWithStepDefinition() {
        return true;
    }

    @And("^step without Javadoc$")
    public int stepWithoutJavadoc() {
        return 0;
    }

    /**
     * This step without step definition
     *
     */
    @And("^step without step definition") // Noncompliant
    public boolean stepWithWrongJavadoc() {
        return false;
    }

    /**
     *
     * @step,
     */
    @And("^step without step definition") // Noncompliant
    public boolean stepWithWrongStepDefinition() {
        return false;
    }

}
