package demo.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import demo.dao.StaffDAO;
import demo.service.LoginService;

@Service("loginService")
public class LoginServiceImpl implements LoginService {
	
	@Resource
	private StaffDAO staffDAO;
	
	public boolean isLogin(String staffName, String staffPsw) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("staffName", staffName);
		map.put("staffPsw", staffPsw);
		return staffDAO.findStaff(map).size()==1?true:false;
	}
	
	public StaffDAO getStaffDAO() {
		return staffDAO;
	}
	public void setStaffDAO(StaffDAO staffDAO) {
		this.staffDAO = staffDAO;
	}

}
