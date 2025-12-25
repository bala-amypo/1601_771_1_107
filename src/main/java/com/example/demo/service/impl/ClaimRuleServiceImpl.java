package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.ClaimRule;
import com.example.demo.repository.ClaimRuleRepository;
import com.example.demo.service.ClaimRuleService;

@Service
public class ClaimRuleServiceImpl implements ClaimRuleService {

    private final ClaimRuleRepository ruleRepository;

    public ClaimRuleServiceImpl(ClaimRuleRepository ruleRepository) {
        this.ruleRepository = ruleRepository;
    }

    @Override
    public ClaimRule addRule(ClaimRule rule) {

        if (rule.getWeight() == null || rule.getWeight() < 0) {
            throw new BadRequestException("weight must be >= 0");
        }

        return ruleRepository.save(rule);
    }

    @Override
    public List<ClaimRule> getAllRules() {
        return ruleRepository.findAll();
    }
}
