package demo.controller;


import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import demo.entity.Leave;
import demo.service.JBPMService;
/**
 * 
 * @author ht 
 * 	2010 10 20
 *
 */
@Controller
public class ExamAction {
	@Resource
	private JBPMService jBPMService;
	private String taskId;
	private String result;
	public String execute(){
		Leave leave=jBPMService.getLeaveDetail(taskId).get(0);	//需要修改请假表中状态的数据，需要获得该记录
		jBPMService.updateLeave(leave, result);				//更新
		jBPMService.completeTask(taskId, result);	//完成任务，进入下一个流程
		return "index";
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
}
