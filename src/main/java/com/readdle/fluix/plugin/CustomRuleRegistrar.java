package com.readdle.fluix.plugin;

import java.util.Arrays;

import org.sonar.plugins.java.api.CheckRegistrar;
import org.sonar.plugins.java.api.JavaCheck;

import com.readdle.fluix.plugin.rules.MissingStepDefinitionRule;
import com.readdle.fluix.plugin.rules.UndocumentedStepRule;

public class CustomRuleRegistrar implements CheckRegistrar {

    @Override
    public void register(RegistrarContext registrarContext) {
        registrarContext.registerClassesForRepository(
                CustomRulesDefinition.REPOSITORY_KEY,
                Arrays.asList(checkClasses()),
                Arrays.asList(testCheckClasses()));
    }

    private static Class<? extends JavaCheck>[] checkClasses() {
        return new Class[] {
                UndocumentedStepRule.class,
                MissingStepDefinitionRule.class
        };
    }

    private static Class<? extends JavaCheck>[] testCheckClasses() {
        return new Class[] {};
    }

}
