package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class ClaimRule {
    @Id
    @GeneratedValue
    private Long id;

    private String ruleName;
    private String conditionExpression;
    private Double weight;

    public ClaimRule(){}

    public ClaimRule(String ruleName, String conditionExpression, Double weight) {
        this.ruleName = ruleName;
        this.conditionExpression = conditionExpression;
        this.weight = weight;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public void setConditionExpression(String conditionExpression) {
        this.conditionExpression = conditionExpression;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Long getId() {
        return id;
    }

    public String getRuleName() {
        return ruleName;
    }

    public String getConditionExpression() {
        return conditionExpression;
    }

    public Double getWeight() {
        return weight;
    }

    
}
