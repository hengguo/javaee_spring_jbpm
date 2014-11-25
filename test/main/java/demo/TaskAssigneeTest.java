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

	@Override
	protected String getResourcePath() {
		return "jpdl/TaskAssignee.jpdl.xml";
	}
	
	@Test
	public void test(){
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("order", new Order("alex"));
		
		//启动流程实例
		ProcessInstance processInstance = executionService.startProcessInstanceByKey("TaskAssignee", variables);
		
		List<Task> taskList = taskService.findPersonalTasks("alex");
		Task task = taskList.get(0);
		Map<String, Object> names = taskService.getVariables(task.getId(), taskService.getVariableNames(task.getId()));
		for (Map.Entry<String, Object> entry : names.entrySet()) {
			System.out.println(entry.getKey()+" - "+entry.getValue());
		}
		
		Assert.assertTrue(taskList.size() == 1);
	}
	

}
