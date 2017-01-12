package com.aricent.fundscollection.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "expenditure_record")
public class Expenditure implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer expndId;
	private String eventName;
	private Float amountExpended;
	private String description;
	private Date eventDate;
	private FundCycle fundCycle;

	public Expenditure() {
		System.out.println("===initializing Expenditure===");
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "expend_id")
	public Integer getExpndId() {
		return expndId;
	}

	public void setExpndId(Integer expndId) {
		this.expndId = expndId;
	}

	@Column(name = "event_name")
	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	@Column(name = "event_date")
	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	@Column(name = "amount_expended")
	public Float getAmountExpended() {
		return amountExpended;
	}

	public void setAmountExpended(Float amountExpended) {
		this.amountExpended = amountExpended;
	}

	@Column(name = "description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
	public String toString() {
		return "Expenditure [expndId=" + expndId + ", eventName=" + eventName + ", amountExpended=" + amountExpended
				+ ", description=" + description + ", eventDate=" + eventDate + "]";
	}

	
}
