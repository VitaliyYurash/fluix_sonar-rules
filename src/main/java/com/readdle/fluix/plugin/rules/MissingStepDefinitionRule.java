package com.readdle.fluix.plugin.rules;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.check.BelongsToProfile;
import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.java.ast.visitors.PublicApiChecker;
import org.sonar.plugins.java.api.JavaFileScanner;
import org.sonar.plugins.java.api.JavaFileScannerContext;
import org.sonar.plugins.java.api.tree.AnnotationTree;
import org.sonar.plugins.java.api.tree.BaseTreeVisitor;
import org.sonar.plugins.java.api.tree.MethodTree;
import org.sonar.squidbridge.annotations.SqaleConstantRemediation;
import org.sonar.squidbridge.annotations.SqaleSubCharacteristic;

import com.readdle.fluix.plugin.FluixQualityProfile;

@Rule(
        key = "com.readdle.fluix.plugin.rules.MissingStepDefinitionRule",
        name = "Cucumber step should have a Javadoc with step definition",
        description = "Each Cucumber step in 'Fluix Automation' project should have a Javadoc with step definition",
        priority = Priority.MAJOR,
        tags = { "custom", "bad-practice" }
)
@SqaleSubCharacteristic(RulesDefinition.SubCharacteristics.UNDERSTANDABILITY)
@BelongsToProfile(title = FluixQualityProfile.PROFILE_NAME, priority = Priority.MAJOR)
@SqaleConstantRemediation("3min")
public class MissingStepDefinitionRule extends BaseTreeVisitor implements JavaFileScanner {

    private JavaFileScannerContext context;

    @Override
    public void scanFile(JavaFileScannerContext context) {
        this.context = context;
        scan(context.getTree());
    }

    @Override
    public void visitMethod(MethodTree tree) {
        List<AnnotationTree> methodAnnotations = tree.modifiers().annotations();

        for (AnnotationTree annotation : methodAnnotations) {
            if ("And".equals(annotation.annotationType().toString())) {
                String methodJavadoc = PublicApiChecker.getApiJavadoc(tree);

                if (StringUtils.isNotEmpty(methodJavadoc)
                        && StringUtils.isNotEmpty(methodJavadoc.replace("/**", "").replace("*/", "").replaceAll("\\*", "").trim())
                        && !methodJavadoc.contains("@step.")) {
                    context.reportIssue(this, tree, "Add step definition to Javadoc.");
                }
            }
        }

        super.visitMethod(tree);
    }
}
