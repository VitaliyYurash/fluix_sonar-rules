package com.readdle.fluix.plugin;

import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.squidbridge.annotations.AnnotationBasedRulesDefinition;

public class CustomRulesDefinition implements RulesDefinition {

    public static final String REPOSITORY_KEY = "Fluix Custom Rule Repository";

    public CustomRulesDefinition() {
    }

    @Override
    public void define(Context context) {
        RulesDefinition.NewRepository repository = context.createRepository(REPOSITORY_KEY, "java");
        repository.setName(REPOSITORY_KEY);

        AnnotationBasedRulesDefinition.load(repository, "java", FluixRulesList.getChecks());
        repository.done();
    }

}
