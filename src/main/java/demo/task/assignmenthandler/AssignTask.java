package demo.task.assignmenthandler;

import org.jbpm.api.model.OpenExecution;
import org.jbpm.api.task.Assignable;
import org.jbpm.api.task.AssignmentHandler;

import demo.entity.Order;

public class AssignTask implements AssignmentHandler {

	public void assign(Assignable assignable, OpenExecution execution) throws Exception {
		
		Order order = (Order) execution.getVariable("order");
		assignable.setAssignee(order.getOwner());
		assignable.addCandidateUser("a");
	}

}
