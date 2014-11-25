package demo;

import org.jbpm.api.Configuration;
import org.jbpm.api.ExecutionService;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.RepositoryService;
import org.junit.After;
import org.junit.Before;

import junit.framework.TestCase;

public abstract class BaseTestCase {
		//定义一个jbpm4的外观接口，所有的功能服务都是从ProcessEngine里获取的
		public static ProcessEngine processEngine = null;
		public static String deployId = null;
		//主要是用来发布流程
		public static RepositoryService repositoryService = null;
		//主要用来启动流程，执行流程
		public static ExecutionService executionService = null;

		@Before
		public void setUp() {
			processEngine = new Configuration().buildProcessEngine();
			repositoryService = processEngine.getRepositoryService();
			executionService = processEngine.getExecutionService();
			//将定义的流程配置文件部署到数据库中
			deployId = repositoryService.createDeployment()
					.addResourceFromClasspath(getResourcePath())
					.deploy();
		}
		
		@After
		public void tearDown() {
			//repositoryService.deleteDeploymentCascade(deployId);
		}
		
		protected abstract String getResourcePath();
}
