package demo.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.chain.contexts.ActionContext;
import org.jbpm.api.ProcessInstance;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import demo.entity.Leave;
import demo.service.JBPMService;
/**
 * 
 * @author ht 
 * 	2010 10 20
 *
 */
@Controller
public class RequestAction {
	private final static String resourceName="leave.jpdl.xml";		
	@Resource
	private JBPMService jBPMService;
	
	@RequestMapping("requestAction")
	public ModelAndView execute(HttpServletRequest request, Leave leave){
		
		if(jBPMService.getAllPd().size()==0){	//如果还没有流程定义则发布
			jBPMService.deployNew(resourceName);
		}
		String staffName = (String)request.getSession().getAttribute("staffName");
		String pdId=jBPMService.getAllPd().get(0).getId();	//获得第一个Id
		ProcessInstance processInstance=jBPMService.startPI(staffName, pdId);		//开始流程实例
		jBPMService.applyLeave(staffName, leave.getLeaveLong(), leave.getLeaveContent(), processInstance.getId());	//添加进入数据库
		jBPMService.completeTask(jBPMService.getTaskId(staffName),leave.getLeaveLong(), leave.getLeaveContent());	//完成任务
		return  new ModelAndView("index");
	}
	
	//处理被驳回的请求
	@RequestMapping("reRequestAction")
	public ModelAndView reRequest(HttpServletRequest request, Leave leave, String taskId){
		Leave tmp=jBPMService.getLeaveDetail(taskId).get(0);
		jBPMService.updateLeave(tmp,leave.getLeaveLong(), leave.getLeaveContent());
		jBPMService.completeTask(taskId);
		return  new ModelAndView("index");
	}
}
