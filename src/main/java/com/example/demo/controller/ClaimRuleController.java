package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.ClaimRule;
import com.example.demo.service.ClaimRuleService;

@RestController
@RequestMapping("/rules")
public class ClaimRuleController {

    private final ClaimRuleService ruleService;

    public ClaimRuleController(ClaimRuleService ruleService) {
        this.ruleService = ruleService;
    }

    @PostMapping
    public ResponseEntity<ClaimRule> addRule(@RequestBody ClaimRule rule) {
        return ResponseEntity.ok(ruleService.addRule(rule));
    }

    @GetMapping
    public ResponseEntity<List<ClaimRule>> getAllRules() {
        return ResponseEntity.ok(ruleService.getAllRules());
    }
}
