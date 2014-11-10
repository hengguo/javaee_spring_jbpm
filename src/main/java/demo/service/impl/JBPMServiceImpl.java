package demo.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.task.Task;
import org.springframework.stereotype.Service;

import demo.dao.LeaveDAO;
import demo.dao.StaffDAO;
import demo.entity.Leave;
import demo.entity.Staff;
import demo.service.JBPMService;
import demo.util.JBPMUtil;

@Service("jBPMService")
public class JBPMServiceImpl implements JBPMService {
	
	@Resource
	private JBPMUtil jBPMUtil;
	@Resource
	private LeaveDAO leaveDAO;
	@Resource
	private StaffDAO staffDAO;
	
	public JBPMUtil getjBPMUtil() {
		return jBPMUtil;
	}

	public void setjBPMUtil(JBPMUtil jBPMUtil) {
		this.jBPMUtil = jBPMUtil;
	}

	public LeaveDAO getLeaveDAO() {
		return leaveDAO;
	}

	public void setLeaveDAO(LeaveDAO leaveDAO) {
		this.leaveDAO = leaveDAO;
	}
	
	public StaffDAO getStaffDAO() {
		return staffDAO;
	}

	public void setStaffDAO(StaffDAO staffDAO) {
		this.staffDAO = staffDAO;
	}
	
	//发布流程
	public String  deployNew(String resourceName) {
		return jBPMUtil.deployNew(resourceName);
	}
	

	//发布流程
	public String  deployZipNew(String resourceZipName) {
		return jBPMUtil.deployZipNew(resourceZipName);
	}

	//开始流程实例
	public ProcessInstance startPI(String staffName,String id) {
		Map map=new HashMap();
		map.put("staff", staffName);
		return jBPMUtil.startPIById(id, map);
		
	}

	//完成任务
	public void completeTask(String taskId) {
		
		jBPMUtil.completeTask(taskId);
	}
	
	//完成任务
	public void completeTask(String taskId, String result) {
		jBPMUtil.completeTask(taskId, result);	
	}

	

	//添加请假信息进入库表
	public void applyLeave(String staffName, String leaveLong,
			String leaveContent,String leaveInstanceId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("staffName", staffName);
		Staff staff =staffDAO.findStaff(map).get(0);
		Leave leave=new Leave();
		leave.setLeaveContent(leaveContent);
		leave.setLeaveLong(leaveLong);
		leave.setStaff(staff);
		leave.setLeaveStaffId(staff.getStaffId());
		leave.setLeaveState("审核中");
		leave.setLeaveInstanceId(leaveInstanceId);
		leaveDAO.saveLeave(leave);
	}
	//更新请假信息的流程状态
	public void updateLeave(Leave leave,String result) {		
		
		if(leave.getLeaveState().equals("审核中")&&result.equals("批准")){
			leave.setLeaveState("经理审核通过");
		}else if(leave.getLeaveState().equals("经理审核通过")&&result.equals("批准")){
			leave.setLeaveState("老板审核通过");
		}
		leaveDAO.updateLeave(leave);
	}
	
	//被驳回的请假信息重新填写
	public void updateLeave(Leave leave,String leaveLong,String leaveContent) {		
		leave.setLeaveLong(leaveLong);
		leave.setLeaveContent(leaveContent);
		leaveDAO.updateLeave(leave);
	}

	public String getTaskId(String staffName) {
		return jBPMUtil.findPersonalTasks(staffName).get(0).getId();
	}

	public List<Task> getTasksList(String assignee) {
		
		return jBPMUtil.findPersonalTasks(assignee);
	}

	public List<Leave> getLeaveDetail(String taskId) {
		String leaveInstanceId=jBPMUtil.getTaskService().getTask(taskId).getExecutionId();
		System.out.println("leaveInstanceId="+leaveInstanceId);
		return leaveDAO.findLeaves(leaveInstanceId);
	}

	public List<ProcessDefinition> getAllPd() {
		return jBPMUtil.getAllPdList();
	}

	public void completeTask(String taskId, String leaveLong,
			String leaveContent) {
		Map map=new HashMap();
		map.put("leaveLong", leaveLong);
		map.put("leaveContent", leaveContent);
		jBPMUtil.completeTask(taskId, map);
	}	
}
