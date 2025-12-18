package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.ClaimRule;
import com.example.demo.service.ClaimRuleService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rules")
public class ClaimRuleController {

    @Autowired
    private ClaimRuleService claimRuleService;
    @PostMapping
    public ResponseEntity<ClaimRule> addRule(@Valid @RequestBody ClaimRule rule) {
        ClaimRule savedRule = claimRuleService.addRule(rule);
        return ResponseEntity.ok(savedRule);
    }
    @GetMapping
    public ResponseEntity<List<ClaimRule>> getAllRules() {
        List<ClaimRule> rules = claimRuleService.getAllRules();
        return ResponseEntity.ok(rules);
    }
}
