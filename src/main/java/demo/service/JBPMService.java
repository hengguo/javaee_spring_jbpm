package demo.service;

import java.util.List;

import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.task.Task;

import demo.entity.Leave;


public interface JBPMService {
	public String deployZipNew(String resourceName);		//发布这条流程定义
	
	public String deployNew(String resourceZipName);		//发布这条流程定义
	
	public ProcessInstance startPI(String staffName,String id);		//开始一个流程实例
	
	public String getTaskId(String staffName);		//用户即申请人，获得任务Id，这个Id也就是流程实例的第一个任务的ID
	
	public List<Task> getTasksList(String assignee);	//经理或者老板获得所有任务列表
	
	public void completeTask(String taskId,String leaveLong,String leaveContent);
	
	public void completeTask(String taskId,String result);		//经理完成任务，返回一个result结果
	
	public void completeTask(String taskId);			//按照任务Id完成该任务
	
	public List<Leave> getLeaveDetail(String taskId);	//根据任务Id找到转换成实例ID,从数据库中找到这条记录
	
	//添加一条请假记录进入库表leave
	public void applyLeave(String staffName,String leaveLong,String leaveContent,String leaveInstanceId);		
	
	
	public void updateLeave(Leave leave,String result);		//更新该条记录的状态
	
	public void updateLeave(Leave leave,String leaveLong,String leaveContent);
	
	public List<ProcessDefinition> getAllPd();		//获得所有流程定义
	
	
}
