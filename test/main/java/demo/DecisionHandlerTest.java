package demo;

import java.util.HashMap;
import java.util.Map;

import org.jbpm.api.Execution;
import org.jbpm.api.ProcessInstance;
import org.junit.Assert;
import org.junit.Test;

public class DecisionHandlerTest extends BaseTestCase {

	@Override
	protected String getResourcePath() {
		return "jpdl/DecisionHandler.jpdl.xml";
	}
	
	@Test
	public void test(){
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("content", "you're great");
		//发起流程实例
		ProcessInstance processInstance = executionService
				.startProcessInstanceByKey("DecisionHandler", variables);
		Execution exe = processInstance.findActiveExecutionIn("submit document");
		//流程已自动进入decision
		Assert.assertEquals(true, processInstance.isActive("submit document"));
	}
	

}
