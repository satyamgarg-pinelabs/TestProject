package com.test.testdrools.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.drools.decisiontable.InputType;
import org.drools.decisiontable.SpreadsheetCompiler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RulesHelperService {

    private final ResourcePatternResolver resourcePatternResolver;

    @Autowired
    public RulesHelperService() {
        this.resourcePatternResolver = new PathMatchingResourcePatternResolver();
    }

    public List<String> getRulesListForService() throws IOException {
        Resource[] resources = resourcePatternResolver.getResources("/src/main/resources/rules/Discount.xls");
        List<String> rulesList = new ArrayList<>();
        for (Resource r : resources) {
            log.info("Compiling resource file --> " + r.getFilename());
            InputStream is = getClass().getResourceAsStream("/rules/" + r.getFilename());
            SpreadsheetCompiler sc = new SpreadsheetCompiler();
            String rules = sc.compile(is, InputType.XLS);
            log.info("Compiled drools file == " + rules);
            rulesList.add(rules);
        }
        return rulesList;
    }

    public Integer getRemoteData(){
        return 3;
    }

}
