package com.example.demo.controller;

import com.example.demo.model.ClaimRule;
import com.example.demo.service.ClaimRuleService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.util.List;

@RestController
@RequestMapping("/rules")
@SecurityRequirement(name="bearerAuth")

public class ClaimRuleController {

    private final ClaimRuleService ruleService;

    public ClaimRuleController(ClaimRuleService ruleService) {
        this.ruleService = ruleService;
    }


    @PostMapping
    public ResponseEntity<ClaimRule> addRule(@RequestBody ClaimRule rule) {
        ClaimRule saved = ruleService.addRule(rule);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public ResponseEntity<List<ClaimRule>> getAllRules() {
        return ResponseEntity.ok(ruleService.getAllRules());
    }
}
