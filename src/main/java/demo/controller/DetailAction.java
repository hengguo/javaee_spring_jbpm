package demo.controller;

import java.util.List;

import javax.annotation.Resource;

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
public class DetailAction {
	@Resource
	private JBPMService jBPMService;
	private List<Leave> leaveList;
	
	@RequestMapping("detailAction")
	public ModelAndView reDetailAction(String taskId){
		ModelAndView mav = new ModelAndView("taskDetail");
		leaveList=jBPMService.getLeaveDetail(taskId);		//根据任务Id从库表获取相应的记录详细情况
		mav.addObject("leaveList", leaveList);
		mav.addObject("taskId", taskId);
		return mav;
	}
	
	@RequestMapping("reDetailAction")
	public ModelAndView reRequest(String taskId){
		ModelAndView mav = new ModelAndView("reRequest");
		mav.addObject("taskId", taskId);
		leaveList=jBPMService.getLeaveDetail(taskId);
		mav.addObject("leaveList", leaveList);
		return mav;
	}

	public List<Leave> getLeaveList() {
		return leaveList;
	}

	public void setLeaveList(List<Leave> leaveList) {
		this.leaveList = leaveList;
	}
}
