package com.readdle.fluix.plugin;

import java.util.Arrays;
import java.util.List;

import org.sonar.api.SonarPlugin;

import com.readdle.fluix.plugin.rules.CorrectStepDefinitionRule;
import com.readdle.fluix.plugin.rules.MissingStepDefinitionRule;
import com.readdle.fluix.plugin.rules.UndocumentedStepRule;

public class FluixSonarPlugin extends SonarPlugin {

    @Override
    public List getExtensions() {
        return Arrays.asList(
                UndocumentedStepRule.class,
                MissingStepDefinitionRule.class,
                CorrectStepDefinitionRule.class,

                FluixQualityProfile.class,
                CustomRulesDefinition.class,
                CustomRuleRegistrar.class);
    }

}
