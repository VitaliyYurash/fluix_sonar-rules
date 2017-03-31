package com.readdle.fluix.plugin;

import java.util.ArrayList;
import java.util.Collection;

import org.sonar.api.profiles.ProfileDefinition;
import org.sonar.api.profiles.RulesProfile;
import org.sonar.api.rules.Rule;
import org.sonar.api.rules.RuleAnnotationUtils;
import org.sonar.api.rules.RuleFinder;
import org.sonar.api.rules.RulePriority;
import org.sonar.api.utils.ValidationMessages;

import com.readdle.fluix.plugin.rules.MissingStepDefinitionRule;
import com.readdle.fluix.plugin.rules.UndocumentedStepRule;

public class FluixQualityProfile extends ProfileDefinition {

    public static final String PROFILE_NAME = "Fluix Profile";

    private final RuleFinder ruleFinder;

    public FluixQualityProfile(RuleFinder ruleFinder) {
        this.ruleFinder = ruleFinder;
    }

    @Override
    public RulesProfile createProfile(ValidationMessages validationMessages) {
        Collection<Class> ruleList = new ArrayList<>();
        ruleList.add(UndocumentedStepRule.class);
        ruleList.add(MissingStepDefinitionRule.class);

        RulesProfile profile = RulesProfile.create(PROFILE_NAME, "java");

        for (Class ruleClass : ruleList) {
            String ruleKey = RuleAnnotationUtils.getRuleKey(ruleClass);
            Rule rule = ruleFinder.findByKey(CustomRulesDefinition.REPOSITORY_KEY, ruleKey);
            profile.activateRule(rule, null);
        }
        return profile;
    }

}
