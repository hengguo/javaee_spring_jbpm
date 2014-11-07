package demo;

import org.jbpm.api.Configuration;
import org.jbpm.api.ExecutionService;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.RepositoryService;
import junit.framework.TestCase;

public class HelloTest extends TestCase {
	//定义一个jbpm4的外观接口，所有的功能服务都是从ProcessEngine里获取的
	ProcessEngine processEngine = null;
	String deployId = null;
	//主要是用来发布流程
	RepositoryService repositoryService = null;
	//主要用来启动流程，执行流程
	ExecutionService executionService = null;

	protected void setUp() {
		processEngine = new Configuration().buildProcessEngine();
		repositoryService = processEngine.getRepositoryService();
		executionService = processEngine.getExecutionService();
		//将定义的流程配置文件部署到数据库中
		deployId = repositoryService.createDeployment()
				.addResourceFromClasspath("helloworld.jpdl.xml")
				.deploy();
	}

	protected void tearDown() {
		repositoryService.deleteDeploymentCascade(deployId);

	}

	public void testEndHelloWorld() {
		//启动流程实例
		ProcessInstance processInstance = executionService
				.startProcessInstanceByKey("helloWorld");
		//启动流程后我们的流程会自动进入到state1活动，并处在等待状态
		assertTrue(processInstance.isActive("state1"));
		String pid = processInstance.getId();
		//让state1活动继续往下执行，并进入结束活动，流程结束
		processInstance = executionService.signalExecutionById(pid);
		assertTrue(processInstance.isEnded());
	}

}
