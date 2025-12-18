package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.ClaimRule;
import com.example.demo.repository.ClaimRuleRepository;
import com.example.demo.service.ClaimRuleService;

import jakarta.validation.ValidationException;

@Service
public class ClaimRuleServiceImpl implements ClaimRuleService {

    @Autowired
    private ClaimRuleRepository claimRuleRepository;

    @Override
    public ClaimRule addRule(ClaimRule rule) {
        if (rule.getWeight() < 0) {
            throw new ValidationException("Rule weight must be non-negative");
        }
        return claimRuleRepository.save(rule);
    }

    @Override
    public List<ClaimRule> getAllRules() {
        return claimRuleRepository.findAll();
    }
}
