package demo.controller;


import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
	
	//批准，驳回申请
	@RequestMapping("examAction")
	public String examAction(String taskId, String result){
		Leave leave=jBPMService.getLeaveDetail(taskId).get(0);	//需要修改请假表中状态的数据，需要获得该记录
		jBPMService.updateLeave(leave, result);				//更新
		jBPMService.completeTask(taskId, result);	//完成任务，进入下一个流程
		return "index";
	}
}
