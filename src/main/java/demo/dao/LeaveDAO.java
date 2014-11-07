package demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import demo.entity.Leave;

@Repository("leaveDAO")
public interface LeaveDAO{
	public void saveLeave(Leave leave);			//保存请假单
	public void updateLeave(Leave leave);		//第一次修改请假单中状态信息
	public List<Leave> findLeaves(String queryString);		
}
