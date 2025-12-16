package com.example.demo.modal;

public class ClaimRule {
    private Long id;
    private String ruleName;
    private String ConditionExpression;
    private Double Weight;

public ClaimRule(){
    
}
public ClaimRule(String ruleName, String conditionExpression, Double weight) {
    this.ruleName = ruleName;
    ConditionExpression = conditionExpression;
    Weight = weight;
}
public void setId(Long id) {
    this.id = id;
}
public void setRuleName(String ruleName) {
    this.ruleName = ruleName;
}
public void setConditionExpression(String conditionExpression) {
    ConditionExpression = conditionExpression;
}
public void setWeight(Double weight) {
    Weight = weight;
}
public Long getId() {
    return id;
}
public String getRuleName() {
    return ruleName;
}
public String getConditionExpression() {
    return ConditionExpression;
}
public Double getWeight() {
    return Weight;
}
}
