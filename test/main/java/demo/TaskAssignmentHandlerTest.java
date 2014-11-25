package demo;

import java.util.List;
import java.util.Map;

import org.jbpm.api.ProcessInstance;
import org.jbpm.api.task.Task;
import org.junit.Assert;
import org.junit.Test;

public class TaskAssignmentHandlerTest extends BaseTestCase {

	@Override
	protected String getResourcePath() {
		return "jpdl/TaskAssignmentHandler.jpdl.xml";
	}
	
	@Test
	public void test(){
		//启动流程实例
		ProcessInstance processInstance = executionService.startProcessInstanceByKey("TaskAssignmentHandler");
		
		List<Task> taskList = taskService.findPersonalTasks("andrew");
//		Assert.assertTrue(taskList.size() == 1);
		for (Task task : taskList) {
			Assert.assertEquals("review", task.getName());
			taskService.completeTask(task.getId(), "T2");
//			Map<String, Object> names = taskService.getVariables(task.getId(), taskService.getVariableNames(task.getId()));
//			for (Map.Entry<String, Object> entry : names.entrySet()) {
//				System.out.println(entry.getKey()+" - "+entry.getValue());
//			}
		}
		Assert.assertTrue(processInstance.isEnded());
	}
	

}
