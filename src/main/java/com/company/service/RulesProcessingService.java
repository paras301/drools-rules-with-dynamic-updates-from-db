package com.company.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.kie.api.command.Command;
import org.kie.api.runtime.ClassObjectFilter;
import org.kie.api.runtime.ExecutionResults;
import org.kie.api.runtime.StatelessKieSession;
import org.kie.internal.command.CommandFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.repository.MongoRepository;
import com.company.vo.Discount;
import com.company.vo.Item;

@Slf4j
@Service
public class RulesProcessingService {
	
	@Autowired
	RulesCompiler rulesCompiler;
	
	@Autowired
	MongoRepository mongoRepository;

    public Discount applyRules(List<Item> req) {
    	    	
        StatelessKieSession kSession = rulesCompiler.getKSession();

        log.info("Executing rules ...");

        List<Command> cmds = new ArrayList<>();
        cmds.add(CommandFactory.newInsert(mongoRepository));
        cmds.add(CommandFactory.newInsertElements(req));
        cmds.add(CommandFactory.newFireAllRules());
        
        cmds.add(CommandFactory.newGetObjects(new ClassObjectFilter(Discount.class), "output"));

        ExecutionResults results = kSession.execute(CommandFactory.newBatchExecution(cmds));

        List<Discount> data_out = (List<Discount>) (Collection<?>) results.getValue("output");

        log.info("rules output = " + data_out);

        return data_out.get(0);
    }
}
