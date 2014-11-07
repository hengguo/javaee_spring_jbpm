package demo.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import demo.entity.Staff;

@Repository("staffDAO")
public interface StaffDAO {
	List<Staff> findStaff(Map<String, Object> map);
	
}
