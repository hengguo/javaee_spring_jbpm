package demo;

import org.jbpm.api.ProcessInstance;
import org.junit.Assert;
import org.junit.Test;

public class HelloWorldTest extends BaseTestCase {

	@Test
	public void testEndHelloWorld() {
		//启动流程实例
		ProcessInstance processInstance = executionService
				.startProcessInstanceByKey("helloWorld");
		//启动流程后我们的流程会自动进入到state1活动，并处在等待状态
		//assertTrue(processInstance.isActive("state1"));
		String pid = processInstance.getId();
		//让state1活动继续往下执行，并进入结束活动，流程结束
		processInstance = executionService.signalExecutionById(pid);
		Assert.assertEquals(true, processInstance.isEnded());
	}

	@Override
	protected String getResourcePath() {
		return "jpdl/helloworld.jpdl.xml";
	}

}
