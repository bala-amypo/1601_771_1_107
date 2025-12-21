package com.example.demo.util;

import com.example.demo.model.ClaimRule;
import java.util.List;

public class RuleEngineUtil {

    public static double computeScore(String description, List<ClaimRule> rules) {
        if (description == null || rules == null || rules.isEmpty()) {
            return 0.0;
        }
        double score = 0.0;
        String d = description.toLowerCase();
        for (ClaimRule rule : rules) {
            String expr = rule.getConditionExpression();
            if (expr == null) continue;
            String e = expr.toLowerCase();
            if ("always".equals(e)) {
                score += safeWeight(rule.getWeight());
            } else if (e.startsWith("description_contains:")) {
                String keyword = e.substring("description_contains:".length());
                if (!keyword.isEmpty() && d.contains(keyword)) {
                    score += safeWeight(rule.getWeight());
                }
            }
        }
        return score;
    }

    private static double safeWeight(Double w) {
        return w == null ? 0.0 : w;
    }
}
