package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.ClaimRule;

public interface ClaimRuleRepository extends JpaRepository<ClaimRule, Long> {

    List<ClaimRule> findAll();
}
