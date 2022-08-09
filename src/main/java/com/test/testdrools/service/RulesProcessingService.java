package com.test.testdrools.service;

import com.test.testdrools.configuration.DroolsBeanFactory;
import com.test.testdrools.entities.Customer;
import lombok.extern.slf4j.Slf4j;
import org.kie.api.command.Command;
import org.kie.api.runtime.ClassObjectFilter;
import org.kie.api.runtime.ExecutionResults;
import org.kie.api.runtime.StatelessKieSession;
import org.kie.internal.command.CommandFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Slf4j
@Service
public class RulesProcessingService {

    private final DroolsBeanFactory droolsBeanFactory;
    private final RulesHelperService rulesHelperService;

    @Autowired
    public RulesProcessingService(DroolsBeanFactory droolsBeanFactory, RulesHelperService rulesHelperService) {
        this.droolsBeanFactory = droolsBeanFactory;
        this.rulesHelperService = rulesHelperService;
    }


    public Customer applyRules(Customer request) {
        StatelessKieSession kSession = droolsBeanFactory.getKieSession();
        log.info("Executing rules ...");
        List<Command> cmds = new ArrayList<>();
        cmds.add(CommandFactory.newInsert(rulesHelperService));
        cmds.add(CommandFactory.newInsertElements(Arrays.asList(request)));
        cmds.add(CommandFactory.newFireAllRules());
        cmds.add(CommandFactory.newGetObjects(new ClassObjectFilter(Customer.class), "output"));
        ExecutionResults results = kSession.execute(CommandFactory.newBatchExecution(cmds));
        List<Customer> data_out = (List<Customer>) results.getValue("output");
        log.info("rules output = " + data_out);
        return data_out.get(0);
//        kSession.execute(request);
//        return request;
    }

}
