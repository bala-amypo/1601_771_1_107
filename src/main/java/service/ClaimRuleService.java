package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.ClaimRule;

public interface ClaimRuleService {
    ClaimRule addRule(ClaimRule rule);
    List<ClaimRule> getAllRules();
}
