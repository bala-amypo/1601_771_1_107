package com.example.demo.controller;

import com.example.demo.model.ClaimRule;
import com.example.demo.service.ClaimRuleService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rules")
public class ClaimRuleController {

    private final ClaimRuleService ruleService;

    public ClaimRuleController(ClaimRuleService ruleService) {
        this.ruleService = ruleService;
    }

    // ===============================
    // ADD NEW CLAIM RULE
    // ===============================
    @PostMapping
    public ResponseEntity<ClaimRule> addRule(@RequestBody ClaimRule rule) {
        ClaimRule saved = ruleService.addRule(rule);
        return ResponseEntity.ok(saved);
    }

    // ===============================
    // GET ALL CLAIM RULES
    // ===============================
    @GetMapping
    public ResponseEntity<List<ClaimRule>> getAllRules() {
        return ResponseEntity.ok(ruleService.getAllRules());
    }
}
