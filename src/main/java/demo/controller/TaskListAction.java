package demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import demo.service.JBPMService;
/**
 * 
 * @author ht 
 * 	2010 10 20
 *
 */
@Controller
public class TaskListAction{
	
	@Resource
	private JBPMService jBPMService;
	 
	@RequestMapping("taskListAction")
	public ModelAndView getTasks(HttpServletRequest request, String assignee){
		ModelAndView mav = new ModelAndView();
		 List taskList = new ArrayList();
		if(assignee.equals("staff")){	//用户身份
			taskList=jBPMService.getTasksList((String)request.getSession().getAttribute("staffName"));
			mav.addObject("taskList", taskList);
			if(taskList.size()==0){		//如果没有任务,跳转到请求页面
				mav.setViewName("request");
			}else{					//如果有被驳回的任务，跳转到原请求列表页面
				mav.setViewName("requestList");
			}
		}else{
			taskList =jBPMService.getTasksList(assignee);
			mav.addObject("taskList", taskList);
			mav.setViewName("taskList");
		}
		return mav;
	}
}
