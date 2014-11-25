package demo.task.assignmenthandler;

import org.jbpm.api.model.OpenExecution;
import org.jbpm.api.task.Assignable;
import org.jbpm.api.task.AssignmentHandler;

public class AssignTask implements AssignmentHandler {

	private String assignee;
	public void assign(Assignable assignable, OpenExecution execution) throws Exception {
		assignable.setAssignee(assignee);
		//assignable.addCandidateUser(assignee);
	}

}
