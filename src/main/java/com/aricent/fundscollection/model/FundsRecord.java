package com.aricent.fundscollection.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "FUNDS_RECORD")
public class FundsRecord implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer recordId;
	private Employee employee;
	private Float amountNeedToPay = 0.0f;
	private Float actualPaidAmount = 0.0f;
	private Float amountRemained = 0.0f;
	private Date payDate;
	private FundCycle fundCycle;

	public FundsRecord() {
		System.out.println("====initializing FundsRecord========");
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "record_id")
	public Integer getRecordId() {
		return recordId;
	}

	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}
    @LazyCollection(LazyCollectionOption.FALSE)
	@OneToOne
	@JoinColumn(name = "EMP_ID", referencedColumnName = "USERNAME")
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Column(name = "amount_need_to_pay")
	public Float getAmountNeedToPay() {
		return amountNeedToPay;
	}

	public void setAmountNeedToPay(Float amountNeedToPay) {
		this.amountNeedToPay = amountNeedToPay;
	}

	@Column(name = "actual_paid_amount")
	public Float getActualPaidAmount() {
		return actualPaidAmount;
	}

	public void setActualPaidAmount(Float actualPaidAmount) {
		this.actualPaidAmount = actualPaidAmount;
	}

	@Column(name = "amount_remained")
	public Float getAmountRemained() {
		return amountRemained;
	}

	public void setAmountRemained(Float amountRemained) {
		this.amountRemained = amountRemained;
	}
	
	@Column(name = "payment_date")
	@Temporal(TemporalType.DATE)
	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}


    @LazyCollection(LazyCollectionOption.FALSE)
	@ManyToOne
	@JoinColumn(name = "fund_id")
	public FundCycle getFundCycle() {
		return fundCycle;
	}

	public void setFundCycle(FundCycle fundCycle) {
		this.fundCycle = fundCycle;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((employee == null) ? 0 : employee.hashCode());
		result = prime * result + ((fundCycle == null) ? 0 : fundCycle.hashCode());
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
		FundsRecord other = (FundsRecord) obj;
		if (employee == null) {
			if (other.employee != null)
				return false;
		} else if (!employee.equals(other.employee))
			return false;
		if (fundCycle == null) {
			if (other.fundCycle != null)
				return false;
		} else if (!fundCycle.equals(other.fundCycle))
			return false;
		return true;
	}	
}
