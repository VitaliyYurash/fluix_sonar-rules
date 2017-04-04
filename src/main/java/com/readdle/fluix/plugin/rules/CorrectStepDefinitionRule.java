package com.readdle.fluix.plugin.rules;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import org.sonar.plugins.java.api.tree.LiteralTree;
import org.sonar.plugins.java.api.tree.MethodTree;
import org.sonar.squidbridge.annotations.SqaleConstantRemediation;
import org.sonar.squidbridge.annotations.SqaleSubCharacteristic;

import com.readdle.fluix.plugin.FluixQualityProfile;

@Rule(
        key = "com.readdle.fluix.plugin.rules.CorrectStepDefinitionRule",
        name = "Cucumber step should have correct step definition in Javadoc",
        description = "Each Cucumber step in 'Fluix Automation' project should have a Javadoc with correct step definition",
        priority = Priority.MAJOR,
        tags = { "custom", "bad-practice" }
)
@SqaleSubCharacteristic(RulesDefinition.SubCharacteristics.UNDERSTANDABILITY)
@BelongsToProfile(title = FluixQualityProfile.PROFILE_NAME, priority = Priority.MAJOR)
@SqaleConstantRemediation("3min")
public class CorrectStepDefinitionRule extends BaseTreeVisitor implements JavaFileScanner {

    private JavaFileScannerContext context;

    @Override
    public void scanFile(JavaFileScannerContext context) {
        this.context = context;
        scan(context.getTree());
    }

    @Override
    public void visitMethod(MethodTree tree) {
        List<AnnotationTree> methodAnnotations = tree.modifiers().annotations();
        String javadoc = PublicApiChecker.getApiJavadoc(tree);

        for (AnnotationTree annotation : methodAnnotations) {
            if (!"And".equals(annotation.annotationType().toString())
                    || StringUtils.isEmpty(javadoc)
//                    TODO: create new issue
                    || !LiteralTree.class.isAssignableFrom(annotation.arguments().get(0).getClass())) {
                continue;
            }

            LiteralTree literalTree = (LiteralTree) annotation.arguments().get(0);
            String annotationValue = getStringWithoutQuotes(literalTree.token().text());
            annotationValue = getStringWithoutBackslash(annotationValue);

            Pattern pattern = Pattern.compile(".*\\* @step\\. (.*)\n");
            Matcher matcher = pattern.matcher(javadoc);

            while (matcher.find()) {
                if (!matcher.group(1).equals(annotationValue)) {
                    context.reportIssue(this, tree, "Update step definition in Javadoc.");
                }
            }
        }

        super.visitMethod(tree);
    }

    private String getStringWithoutBackslash(String str) {
        StringBuilder stringBuilder = new StringBuilder();

        boolean skipBackslash = true;
        for (char c : str.toCharArray()) {
            if (c != '\\') {
                stringBuilder.append(c);
                skipBackslash = true;
            } else {
                if (!skipBackslash) {
                    stringBuilder.append(c);
                }
                skipBackslash = false;
            }
        }
        return stringBuilder.toString();
    }

    private String getStringWithoutQuotes(String str) {
        return str.replaceAll("^\"|\"$", "");
    }

}
