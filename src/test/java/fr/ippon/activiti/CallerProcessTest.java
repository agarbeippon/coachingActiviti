package fr.ippon.activiti;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@Transactional
public class CallerProcessTest {

	@Autowired
	RuntimeService runtimeService;

	@Autowired
	TaskService taskService;

	@Test
	public void testCallerProcess() {
		
		runtimeService.startProcessInstanceByKey("callerProcess");

	}

}
