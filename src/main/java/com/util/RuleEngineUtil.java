package com.example.demo.util;

import com.example.demo.entity.ClaimRule;
import java.util.List;

public final class RuleEngineUtil {
    private RuleEngineUtil() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static double computeScore(String description, List<ClaimRule> rules) {
        double score = 0;
        for (ClaimRule rule : rules) {
            if (description.contains(rule.getConditionExpression())) {
                score += rule.getWeight();
            }
        }
        return score;
    }
}
