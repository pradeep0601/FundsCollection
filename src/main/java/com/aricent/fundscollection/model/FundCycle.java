package com.aricent.fundscollection.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Component
@Entity
@Table(name = "fund_cycle")
public class FundCycle implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer fundId;
	private Date startDate;
	private Date endDate;
	private Boolean isInProgress = true;
	private Float sharePerPerson;
	private Float totalCollection;
	private Float totalExpenditure;
	private Float totalBalance;
	
	private Set<FundsRecord> fundsRecords = new HashSet<>();

	public FundCycle() {
		System.out.println("===initialzing FundCycle===========");
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "fund_id")
	public Integer getFundId() {
		return fundId;
	}

	public void setFundId(Integer fundId) {
		this.fundId = fundId;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "start_date")
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "end_date")
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	@Column(name = "is_in_progress")
	public Boolean getIsInProgress() {
		return isInProgress;
	}
	public void setIsInProgress(Boolean isInProgress) {
		this.isInProgress = isInProgress;
	}
	@Column(name = "share_per_person")
	public Float getSharePerPerson() {
		return sharePerPerson;
	}

	public void setSharePerPerson(Float sharePerPerson) {
		this.sharePerPerson = sharePerPerson;
	}

	@Override
	public String toString() {
		return "FundsCycle [fundId=" + fundId + ", startDate=" + startDate + ", endDate=" + endDate + ", isInProgress="
				+ isInProgress + ", sharePerPerson=" + sharePerPerson + "]";
	}
	@JsonIgnore
	@OneToMany(mappedBy="fundCycle")
	public Set<FundsRecord> getFundsRecords() {
		return fundsRecords;
	}

	public void setFundsRecords(Set<FundsRecord> fundsRecords) {
		this.fundsRecords = fundsRecords;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fundId == null) ? 0 : fundId.hashCode());
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
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
		FundCycle other = (FundCycle) obj;
		if (fundId == null) {
			if (other.fundId != null)
				return false;
		} else if (!fundId.equals(other.fundId))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		return true;
	}
	@Column(name = "total_collection")
	public Float getTotalCollection() {
		return totalCollection;
	}
	public void setTotalCollection(Float totalCollection) {
		this.totalCollection = totalCollection;
	}
	
	@Column(name = "total_expenditure")
	public Float getTotalExpenditure() {
		return totalExpenditure;
	}
	public void setTotalExpenditure(Float totalExpenditure) {
		this.totalExpenditure = totalExpenditure;
	}
	
	@Column(name = "total_balance")
	public Float getTotalBalance() {
		return totalBalance;
	}
	public void setTotalBalance(Float totalBalance) {
		this.totalBalance = totalBalance;
	}
	
	
	
}
