package com.aricent.fundscollection.model;

import java.util.Date;

public class Report {

	private String reportBy;
	private Long employeeId;
	private Date fromDate;
	private Date toDate;

	public String getReportBy() {
		return reportBy;
	}

	public void setReportBy(String reportBy) {
		this.reportBy = reportBy;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	@Override
	public String toString() {
		return "Report [reportBy=" + reportBy + ", employeeId=" + employeeId + ", fromDate=" + fromDate + ", toDate="
				+ toDate + "]";
	}

}
