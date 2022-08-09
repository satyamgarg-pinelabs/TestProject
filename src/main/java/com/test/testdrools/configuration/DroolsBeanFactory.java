package com.test.testdrools.configuration;

import com.test.testdrools.service.RulesHelperService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.StatelessKieSession;
import org.kie.internal.utils.KieHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Getter
public class DroolsBeanFactory {

    private final RulesHelperService rulesHelperService;
    private StatelessKieSession kieSession;

    @Autowired
    public DroolsBeanFactory(RulesHelperService rulesHelperService) {
        this.rulesHelperService = rulesHelperService;
        loadDrlRules();
    }

    private void loadDrlRules() {
        try {
            KieHelper kieHelper = new KieHelper();
            rulesHelperService.getRulesListForService().forEach(rule -> kieHelper.addContent(rule, ResourceType.DRL));
            kieSession = kieHelper.build().newStatelessKieSession();
        } catch (Exception e) {
            log.error("Error while reading the resource:: ", e);
        }

    }


}
