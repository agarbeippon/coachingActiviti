package fr.ippon.activiti.rapport.helper;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LogHelper {

	Logger LOGGER = LoggerFactory.getLogger(LogHelper.class);
	
	public void logBusinessKey(DelegateExecution exec){
		LOGGER.debug("Business key {}",((ExecutionEntity)exec).getBusinessKey());
	}
}
