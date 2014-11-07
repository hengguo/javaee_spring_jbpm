package demo.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Staff entity. @author MyEclipse Persistence Tools
 */

public class Staff implements java.io.Serializable {

	// Fields

	private Integer staffId;
	private String staffName;
	private String staffPsw;
	private String staffPosition;
	private Set leaves = new HashSet(0);

	// Constructors

	/** default constructor */
	public Staff() {
	}

	/** full constructor */
	public Staff(String staffName, String staffPsw, String staffPosition,
			Set leaves) {
		this.staffName = staffName;
		this.staffPsw = staffPsw;
		this.staffPosition = staffPosition;
		this.leaves = leaves;
	}

	// Property accessors

	public Integer getStaffId() {
		return this.staffId;
	}

	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}

	public String getStaffName() {
		return this.staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getStaffPsw() {
		return this.staffPsw;
	}

	public void setStaffPsw(String staffPsw) {
		this.staffPsw = staffPsw;
	}

	public String getStaffPosition() {
		return this.staffPosition;
	}

	public void setStaffPosition(String staffPosition) {
		this.staffPosition = staffPosition;
	}

	public Set getLeaves() {
		return this.leaves;
	}

	public void setLeaves(Set leaves) {
		this.leaves = leaves;
	}

}