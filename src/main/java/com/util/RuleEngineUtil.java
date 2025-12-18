package com.example.demo.util;

import com.example.demo.model.ClaimRule;
import java.util.List;

public class RuleEngineUtil {

    public static double computeScore(String description, List<ClaimRule> rules) {
        if (description == null || rules == null || rules.isEmpty())
            return 0.0;

        double score = 0.0;

        for (ClaimRule rule : rules) {
            String expr = rule.getConditionExpression();

            if ("always".equalsIgnoreCase(expr)) {
                score += rule.getWeight();
            } else if (expr.startsWith("description_contains:")) {
                String keyword = expr.split(":")[1];
                if (description.toLowerCase().contains(keyword.toLowerCase())) {
                    score += rule.getWeight();
                }
            }
        }
        return score;
    }
}
