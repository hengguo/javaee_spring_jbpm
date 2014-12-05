package demo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.jbpm.api.Execution;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.task.Task;
import org.junit.Assert;
import org.junit.Test;

import demo.entity.Order;

public class TaskAssigneeTest extends BaseTestCase {

	String dept;
	
	@Override
	protected String getResourcePath() {
		return "jpdl/TaskAssignee.jpdl.xml";
	}
	
	@Test
	public void test(){
		createDept();
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("order", new Order("BB"));
		
		//启动流程实例
		ProcessInstance processInstance = executionService.startProcessInstanceByKey("TaskAssignee", variables);
		
		List<Task> taskList = taskService.findPersonalTasks("BB");
		Task task = taskList.get(0);
		Map<String, Object> names = taskService.getVariables(task.getId(), taskService.getVariableNames(task.getId()));
		for (Map.Entry<String, Object> entry : names.entrySet()) {
			System.out.println(entry.getKey()+" - "+entry.getValue());
		}
		Assert.assertTrue(taskList.size() == 1);
		// submit the task
	    taskService.completeTask(task.getId());		
		 // verify that process moved to the next state
	    processInstance = executionService.findProcessInstanceById(processInstance.getId());
	    Assert.assertTrue("'wait' state ia active", processInstance.isActive("wait"));
	    Execution exe = processInstance.findActiveExecutionIn("wait");
	    processInstance = executionService.signalExecutionById(exe.getId());
	    
	    exe = processInstance.findActiveExecutionIn("submit");
	    List<Task> groupTasks = taskService.findGroupTasks("joesmoe");
		Assert.assertTrue(groupTasks.size() == 1);
		taskService.takeTask(groupTasks.get(0).getId(), "joesmoe");
	    taskService.completeTask(groupTasks.get(0).getId());		

		 // verify that process moved to the last state
	    processInstance = executionService.findProcessInstanceById(processInstance.getId());
		Assert.assertTrue("'last' state ia active", processInstance.isActive("last"));
	    exe = processInstance.findActiveExecutionIn("last");
	    processInstance = executionService.signalExecutionById(exe.getId());
	    
	    Assert.assertTrue(processInstance.isEnded());
	}

	private void createDept() {
		dept = identityService.createGroup("sales-dept");

	    identityService.createUser("johndoe", "John", "Doe");
	    identityService.createMembership("johndoe", dept, "developer");

	    identityService.createUser("joesmoe", "Joe", "Smoe");
	    identityService.createMembership("joesmoe", dept, "developer");
	}
	

}
