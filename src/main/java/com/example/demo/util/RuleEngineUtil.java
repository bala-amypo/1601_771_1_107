package com.example.demo.util;

import java.util.List;

import com.example.demo.model.ClaimRule;
import com.example.demo.model.DamageClaim;

public class RuleEngineUtil {

    private RuleEngineUtil() {}

    public static double computeScore(String description, List<ClaimRule> rules) {
        DamageClaim claim = new DamageClaim();
        claim.setClaimDescription(description);
        return evaluate(claim, rules);
    }

    // existing logic (unchanged)
    public static double evaluate(DamageClaim claim, List<ClaimRule> rules) {

        if (rules == null || rules.isEmpty()) {
            return 0.0;
        }

        double totalWeight = 0.0;
        double matchedWeight = 0.0;

        String description = claim.getClaimDescription();
        if (description == null) {
            description = "";
        }

        description = description.toLowerCase();

        for (ClaimRule rule : rules) {

            double weight = rule.getWeight() == null ? 0.0 : rule.getWeight();
            totalWeight += weight;

            String condition = rule.getConditionExpression();
            if (condition == null) continue;

            if ("always".equalsIgnoreCase(condition)) {
                matchedWeight += weight;
                continue;
            }

            if (condition.toLowerCase().startsWith("description_contains:")) {
                String keyword = condition.substring(
                        "description_contains:".length()
                ).toLowerCase();

                if (!keyword.isEmpty() && description.contains(keyword)) {
                    matchedWeight += weight;
                }
            }
        }

        if (totalWeight == 0.0) return 0.0;

        return Math.max(0.0, Math.min(1.0, matchedWeight / totalWeight));
    }
}
