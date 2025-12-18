package com.example.demo.util;

import org.springframework.stereotype.Component;

import com.example.demo.model.DamageClaim;

@Component
public class RuleEngineUtil {

    public double evaluate(DamageClaim claim) {
        int length = claim.getClaimDescription().length();
        return length > 50 ? 0.8 : 0.5;
    }
}
