package com.readdle.fluix.plugin.files;

import cucumber.api.java.en.And;

public class TestClass3 {

    /**
     * Compliant method
     *
     * @step. ^user can't see notification that file "([^"]*)" is in (Briefcase|\d+|Cabinet|Documents|Inbox) state(?: with message "([^"]*)")?$
     */
    @And("^user can't see notification that file \"([^\"]*)\" is in (Briefcase|\\d+|Cabinet|Documents|Inbox) state(?: with message \"([^\"]*)\")?$")
    public boolean stepWithCorrectStepDefinition() {
        return true;
    }

    /**
     * Noncompliant method
     *
     * @step. ^Incorrect step definition$
     */
    @And("^step with Javadoc$") // Noncompliant
    public boolean stepWithIncorrectStepDefinition() {
        return false;
    }

    /**
     * Noncompliant method
     *
     * @step. ^user can't see notification that file \"([^\"]*)\" is in (Briefcase|\\d+|Cabinet|Documents|Inbox) state(?: with message \"([^\"]*)\")?$
     */
    @And("^user can't see notification that file \"([^\"]*)\" is in (Briefcase|\\d+|Cabinet|Documents|Inbox) state(?: with message \"([^\"]*)\")?$") // Noncompliant
    public boolean stepWithIncorrectStepDefinition(String value) {
        return false;
    }

    /**
     * Noncompliant method
     *
     * @step. step with Javadoc$
     */
    @And("^step with Javadoc$") // Noncompliant
    public boolean stepWithIncorrectStepDefinition(boolean bool) {
        return bool;
    }

    @And("^step without Javadoc$")
    public int stepWithoutJavadoc() {
        return 0;
    }

    /**
     * Compliant method
     *
     */
    @And("^step without step definition")
    public boolean stepWithoutStepDefinition() {
        return false;
    }

}
