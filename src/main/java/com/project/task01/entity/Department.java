package com.project.task01.entity;

import java.util.Date;

 /*
   It's Department Entity Class
   It is responsible for representing and encapsulating the state and
   behavior of a business entity within the application domain.
 */

public class Department {
	private Long deptId;
	private String departmentName;
	private Date createdDate;

	public Department() {
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departName) {
		this.departmentName = departName;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
}
