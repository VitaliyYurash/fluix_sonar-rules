package com.readdle.fluix.plugin;

import java.util.List;

import org.sonar.plugins.java.api.JavaCheck;

import com.google.common.collect.ImmutableList;
import com.readdle.fluix.plugin.rules.CorrectStepDefinitionRule;
import com.readdle.fluix.plugin.rules.MissingStepDefinitionRule;
import com.readdle.fluix.plugin.rules.UndocumentedStepRule;

public class FluixRulesList {

    private FluixRulesList() {
    }

    public static List<Class> getChecks() {
        return ImmutableList.<Class>builder().addAll(getJavaChecks()).addAll(getJavaTestChecks()).build();
    }

    private static List<Class<? extends JavaCheck>> getJavaChecks() {
        return ImmutableList.<Class<? extends JavaCheck>>builder()
                .add(UndocumentedStepRule.class, MissingStepDefinitionRule.class, CorrectStepDefinitionRule.class)
                .build();
    }

    private static List<Class<? extends JavaCheck>> getJavaTestChecks() {
        return ImmutableList.<Class<? extends JavaCheck>>builder().build();
    }

}
