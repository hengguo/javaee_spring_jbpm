package demo;

import org.jbpm.api.Execution;
import org.jbpm.api.ProcessInstance;
import org.junit.Assert;
import org.junit.Test;

public class StateChoiceTest extends BaseTestCase {

	@Override
	protected String getResourcePath() {
		return "jpdl/StateChoice.jpdl.xml";
	}
	
	@Test
	public void test(){
		//启动流程实例
		ProcessInstance processInstance = executionService
				.startProcessInstanceByKey("StateChoice");
		Execution exe = processInstance.findActiveExecutionIn("wait for response");
		String exeId = exe.getId();
		String pid = processInstance.getId();
		//让state1活动继续往下执行，并进入结束活动，流程结束
		processInstance = executionService.signalExecutionById(exeId, "accept");
		Assert.assertEquals(true, processInstance.isActive("submit document"));
	}
	

}
