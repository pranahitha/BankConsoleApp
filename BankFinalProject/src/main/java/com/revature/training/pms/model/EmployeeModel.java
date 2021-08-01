package com.revature.training.pms.model;

import java.io.Serializable;

public class EmployeeModel extends BankModel implements Serializable{
	
	
	private int empId;
	private String employeeName;
	private int deptId;
	private String deptName;
	public EmployeeModel()
	{
		
	}
	
	
	public EmployeeModel(int empId, String employeeName, int deptId) {
		super();
		this.empId = empId;
		this.employeeName = employeeName;
		this.deptId = deptId;
	}


	public EmployeeModel(int empId, String employeeName, int deptId, String deptName) {
		super();
		this.empId = empId;
		this.employeeName = employeeName;
		this.deptId = deptId;
		this.deptName = deptName;
	}
	
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public int getDeptId() {
		return deptId;
	}
	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	@Override
	public String toString() {
		return "\n EmployeeModel [empId=" + empId + ", employeeName=" + employeeName + ", deptId=" + deptId + ", deptName="
				+ deptName + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + deptId;
		result = prime * result + ((deptName == null) ? 0 : deptName.hashCode());
		result = prime * result + empId;
		result = prime * result + ((employeeName == null) ? 0 : employeeName.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmployeeModel other = (EmployeeModel) obj;
		if (deptId != other.deptId)
			return false;
		if (deptName == null) {
			if (other.deptName != null)
				return false;
		} else if (!deptName.equals(other.deptName))
			return false;
		if (empId != other.empId)
			return false;
		if (employeeName == null) {
			if (other.employeeName != null)
				return false;
		} else if (!employeeName.equals(other.employeeName))
			return false;
		return true;
	}
	
}
