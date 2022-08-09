package com.test.testdrools.controller;

import com.test.testdrools.entities.Customer;
import com.test.testdrools.service.RulesProcessingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RulesController {


    private final RulesProcessingService rulesProcessingService;


    @Autowired
    public RulesController(RulesProcessingService rulesProcessingService) {
        this.rulesProcessingService = rulesProcessingService;
    }


    @PostMapping(path = "/applyRules")
    public Customer applyRulesForInput(@RequestBody Customer request) {
        return rulesProcessingService.applyRules(request);
    }


}
