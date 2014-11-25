package demo.decision.handler;

import org.jbpm.api.jpdl.DecisionHandler;
import org.jbpm.api.model.OpenExecution;

public class ContentEvaluation implements DecisionHandler {

	public String decide(OpenExecution execution) {
		String content = (String)execution.getVariable("content");
		if(content.equals("you're great")){
			return "good";
		}else if(content.equals("you gotta improve")){
			return "bad";
		}
		return "ugly";
	}

}
