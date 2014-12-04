package demo.eventlistener;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.jbpm.api.listener.EventListener;
import org.jbpm.api.listener.EventListenerExecution;


public class LogListener implements EventListener {
	Logger log = Logger.getLogger(LogListener.class);
	
	private String msg;
	public void notify(EventListenerExecution execution) throws Exception {
		//通过execution接口变量，可以获取所有流程变量
		List<String> logs = (List<String>) execution.getVariable("logs");
		if(logs == null){
			logs = new ArrayList<String>();
			execution.setVariable("logs", logs);
		}
		logs.add(msg);
		
		log.debug(msg);
		
		execution.setVariable("logs", logs);
	}
}
