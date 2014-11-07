package demo.entity;

/**
 * Leave entity. @author MyEclipse Persistence Tools
 */

public class Leave implements java.io.Serializable {

	// Fields
	private String leaveLong;
	private String leaveContent;
	private String leaveState;
	private String leaveInstanceId;
	private Integer LeaveStaffId;
	private Staff staff;

	// Constructors

	/** default constructor */
	public Leave() {
	}

	/** full constructor */
	public Leave(Staff staff, String leaveLong, String leaveContent,
			String leaveState, String leaveInstanceId) {
		this.staff = staff;
		this.leaveLong = leaveLong;
		this.leaveContent = leaveContent;
		this.leaveState = leaveState;
		this.leaveInstanceId = leaveInstanceId;
	}

	// Property accessors
	public Integer getLeaveId() {
		return this.leaveId;
	}

	public void setLeaveId(Integer leaveId) {
		this.leaveId = leaveId;
	}

	public Staff getStaff() {
		return this.staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public String getLeaveLong() {
		return this.leaveLong;
	}

	public void setLeaveLong(String leaveLong) {
		this.leaveLong = leaveLong;
	}

	public String getLeaveContent() {
		return this.leaveContent;
	}

	public void setLeaveContent(String leaveContent) {
		this.leaveContent = leaveContent;
	}

	public String getLeaveState() {
		return this.leaveState;
	}

	public void setLeaveState(String leaveState) {
		this.leaveState = leaveState;
	}

	public String getLeaveInstanceId() {
		return this.leaveInstanceId;
	}

	public void setLeaveInstanceId(String leaveInstanceId) {
		this.leaveInstanceId = leaveInstanceId;
	}
	private Integer leaveId;
	public Integer getLeaveStaffId() {
		return LeaveStaffId;
	}

	public void setLeaveStaffId(Integer leaveStaffId) {
		LeaveStaffId = leaveStaffId;
	}


}