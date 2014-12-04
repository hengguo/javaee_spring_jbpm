package demo;

import java.util.HashMap;
import java.util.Map;

import org.jbpm.api.Execution;
import org.jbpm.api.ProcessInstance;
import org.junit.Assert;
import org.junit.Test;

public class EventListenerTest extends BaseTestCase {

	@Override
	protected String getResourcePath() {
		return "jpdl/EventListener.jpdl.xml";
	}
	
	@Test
	public void test(){
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("content", "good");
		//发起流程实例
		ProcessInstance processInstance = executionService.startProcessInstanceByKey("EventListener");
		Execution exe = processInstance.findActiveExecutionIn("wait");
		
		executionService.signalExecutionById(exe.getId());
		//为什么还是wait状态？？
		Assert.assertEquals(true, processInstance.isActive("wait"));
	}
	

}
