package fr.ippon.activiti.rapport.service;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.springframework.stereotype.Service;

@Service
public class RapportInitialisationSousProcess implements JavaDelegate {


	public void execute(DelegateExecution execution) throws Exception {

		ExecutionEntity thisEntity = (ExecutionEntity)execution;
		
		thisEntity.setBusinessKey(execution.getProcessBusinessKey()+"_"+execution.getVariable("personnelEvalue"));
 
	}
}