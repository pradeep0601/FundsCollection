package com.aricent.fundscollection.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.aricent.fundscollection.model.Employee;
import com.aricent.fundscollection.model.Expenditure;
import com.aricent.fundscollection.model.FundCycle;
import com.aricent.fundscollection.model.FundsRecord;
import com.aricent.fundscollection.model.Report;
import com.aricent.fundscollection.util.FundUtil;
import com.aricent.fundscollection.util.FundUtil.Records;

@Repository
public class FundDAOImpl implements FundDAO {

	@PersistenceContext
	private EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public Boolean startFundCycle(FundCycle fundCycle) {
		
		//set total balance
		FundCycle currentCycle = getCurrentCycle();
		Float totalBalance = currentCycle == null || currentCycle.getTotalBalance() == null ? 0.0f : currentCycle.getTotalBalance();
		fundCycle.setTotalBalance(totalBalance);
		
		entityManager.persist(fundCycle);
		return true;
	}

	@Override
	public Map<Records, Object> getFundDetails() {

		Map<Records, Object> fundDetails = new HashMap<>();

		List<Employee> employees = getAllEmployee();
		
		fundDetails.put(Records.BIRTHDAY_LIST, getBirthdayList(employees));
		
		FundCycle fundCycle = getCurrentCycle();

		if (fundCycle == null) {
			return fundDetails;
		}

		Map<Records, List<FundsRecord>> currPrevRecords = getCurrPrevFundRecords(fundCycle.getFundId());

		List<FundsRecord> currFundRecords = currPrevRecords.get(Records.CURRENT);
		List<FundsRecord> prevFundRecords = currPrevRecords.get(Records.PREVIOUS);

		List<FundsRecord> paidFundRecords = new ArrayList<>();

		for (FundsRecord paidFundRecord : currFundRecords) {
			if (paidFundRecord.getActualPaidAmount() != null) {
				paidFundRecords.add(paidFundRecord);
			}
		}
		List<FundsRecord> fundsRecords = new ArrayList<>();

		for (Employee employee : employees) {

			FundsRecord fundsRecord = getFilledFundRecord(currFundRecords, prevFundRecords, fundCycle, employee);
			fundsRecords.add(fundsRecord);

		}

		fundsRecords.removeAll(paidFundRecords);

		fundDetails.put(Records.FUNDRECORDS, fundsRecords);
		fundDetails.put(Records.PAIDFUNDRECORDS, paidFundRecords);
		fundDetails.put(Records.FUNDCYCLE, fundCycle);
		fundDetails.put(Records.EMPLOYEES, employees);
		fundDetails.put(Records.EXPENDITURES, getExpenditures(fundCycle));
		
		return fundDetails;
	}

	private List<Employee> getAllEmployee() {
		Query query = entityManager.createQuery("from Employee e");
		List<Employee> employees = query.getResultList();

		return employees;
	}

	private FundCycle getCurrentCycle() {
		//Query query = entityManager.createQuery("SELECT fc FROM FundCycle fc where fc.isInProgress is true ORDER BY fc.fundId DESC");
		Query query = entityManager.createQuery("SELECT fc FROM FundCycle fc  ORDER BY fc.fundId DESC");
		List<FundCycle> fundCycles = query.getResultList();
		FundCycle fundCycle = fundCycles.size() != 0 ? fundCycles.get(0) : null;
		return fundCycle;
	}
	
	/*private FundCycle getPreviousCycle() {
		Query query = entityManager.createQuery("SELECT fc FROM FundCycle fc where fc.isInProgress is false ORDER BY fc.fundId DESC");
		List<FundCycle> fundCycles = query.getResultList();
		FundCycle fundCycle = fundCycles.size() != 0 ? fundCycles.get(0) : null;
		return fundCycle;
	}*/

	private Map<Records, List<FundsRecord>> getCurrPrevFundRecords(Integer fundId) {

		Map<Records, List<FundsRecord>> currPrevRecords = new HashMap<>();

		Query currFRQuery = entityManager
				.createQuery("SELECT FR FROM FundsRecord FR WHERE FR.fundCycle.fundId = :curr_fund_id");
		currFRQuery.setParameter("curr_fund_id", fundId);

		List<FundsRecord> currFundsRecords = currFRQuery.getResultList();

		if (currFundsRecords != null) {
			currPrevRecords.put(Records.CURRENT, currFundsRecords);
		}

		Query prevFCQuery = entityManager
				.createQuery("SELECT FC.fundId FROM FundCycle FC WHERE FC.fundId < :fund_id ORDER BY FC.fundId DESC");
		prevFCQuery.setParameter("fund_id", fundId);

		List<Integer> fundIds = prevFCQuery.getResultList();

		if (fundIds != null && fundIds.size() != 0) {

			Integer prevFundId = fundIds.get(0);

			Query prevFRQuery = entityManager
					.createQuery("SELECT FR FROM FundsRecord FR WHERE FR.fundCycle.fundId = :prev_fund_id");

			prevFRQuery.setParameter("prev_fund_id", prevFundId);
			List<FundsRecord> prevFundsRecords = prevFRQuery.getResultList();

			if (prevFundsRecords != null) {
				currPrevRecords.put(Records.PREVIOUS, prevFundsRecords);
			}
		}

		return currPrevRecords;
	}

	private FundsRecord getFilledFundRecord(List<FundsRecord> currFundRecords, List<FundsRecord> prevFundRecords,
			FundCycle fundCycle, Employee employee) {

		FundsRecord fundsRecord = new FundsRecord();

		fundsRecord.setEmployee(employee);
		fundsRecord.setFundCycle(fundCycle);
		fundsRecord.setAmountNeedToPay(fundCycle.getSharePerPerson());

		/*
		 * if(currFundRecords == null ||
		 * !currFundRecords.contains(fundsRecord)){ return fundsRecord; }
		 */
		if (prevFundRecords == null) {
			return fundsRecord;
		}
		for (FundsRecord prevFundRecord : prevFundRecords) {

			if (prevFundRecord != null && prevFundRecord.getEmployee().equals(employee)) {
				Float amountRemained = prevFundRecord.getAmountRemained();
				amountRemained = amountRemained != null ? amountRemained : 0.0f;

				// fundsRecord.setPrevBalance(amountRemained);
				fundsRecord.setAmountRemained(amountRemained);
				fundsRecord.setAmountNeedToPay(fundsRecord.getAmountNeedToPay() + amountRemained);
			}
		}
		return fundsRecord;
	}

	@Override
	public Integer addFunds(FundsRecord fundsRecord) {

		FundCycle fundCycle = entityManager.find(FundCycle.class, fundsRecord.getFundCycle().getFundId());
		
		fundCycle.setTotalBalance(fundCycle.getTotalBalance() + fundsRecord.getActualPaidAmount());
		
		Float totalCollection = fundCycle.getTotalCollection() == null ? fundsRecord.getActualPaidAmount() : fundCycle.getTotalCollection() + fundsRecord.getActualPaidAmount();
		fundCycle.setTotalCollection(totalCollection);
				
		entityManager.persist(fundsRecord);
		Integer recordId = fundsRecord.getRecordId();

		return recordId;
	}

	@Override
	public Boolean stopFundCycle(FundCycle fundCycle) {

		List<Employee> employees = getAllEmployee();
		Map<Records, List<FundsRecord>> currPrevFundRecords = getCurrPrevFundRecords(fundCycle.getFundId());

		List<FundsRecord> currFundRecords = currPrevFundRecords.get(Records.CURRENT);
		List<FundsRecord> prevFundRecords = currPrevFundRecords.get(Records.PREVIOUS);

		// to set the fund record for the employee who didn't pay in current
		// cycle
		if (currFundRecords.size() != employees.size()) {

			for (Employee employee : employees) {

				FundsRecord fundsRecord = new FundsRecord();
				fundsRecord.setEmployee(employee);
				fundsRecord.setFundCycle(fundCycle);
				fundsRecord.setAmountRemained(fundCycle.getSharePerPerson());
				fundsRecord.setAmountNeedToPay(fundCycle.getSharePerPerson());
				
				if (currFundRecords.size() ==0 || !currFundRecords.contains(fundsRecord)) {
					if (prevFundRecords != null) {
						for (FundsRecord prevFundRecord : prevFundRecords) {
							if(employee.equals(prevFundRecord.getEmployee())){
							
								fundsRecord.setAmountNeedToPay(
										fundsRecord.getAmountNeedToPay() + prevFundRecord.getAmountRemained());
								fundsRecord.setAmountRemained(fundsRecord.getAmountNeedToPay());
								break;
							}
						}
					}

					entityManager.persist(fundsRecord);
				}
			}
			fundCycle.setIsInProgress(false);
			//fundCycle.setTotalCollection(getTotalCollection(fundCycle));
			//fundCycle.setTotalExpenditure(getTotalExpediture(fundCycle));
			entityManager.merge(fundCycle);
		}
		return true;
	}

	private Float getTotalCollection(FundCycle fundCycle) {

		Query getTotalCollectionQuery = entityManager
				.createQuery("SELECT SUM(fr.actualPaidAmount) FROM FundsRecord fr where fr.fundCycle.fundId = :fundId");
		getTotalCollectionQuery.setParameter("fundId", fundCycle.getFundId());

		Float totalCollection = (Float) getTotalCollectionQuery.getSingleResult();

		return totalCollection;
	}

	private Float getTotalExpediture(FundCycle fundCycle) {

		Query getTotalCollectionQuery = entityManager
				.createQuery("SELECT SUM(exp.amountExpended) FROM Expenditure exp where exp.fundCycle.fundId = :fundId");

		getTotalCollectionQuery.setParameter("fundId", fundCycle.getFundId());

		Double result =  (Double) getTotalCollectionQuery.getSingleResult();

		Float totalCollection = new Float(result);
		
		return totalCollection;
	}

	@Override
	public Integer addExpenditure(Expenditure expenditure) {
		
		entityManager.persist(expenditure);
		
		//update FundCycle with totalExpenditure
		FundCycle fundCycle = entityManager.find(FundCycle.class, expenditure.getFundCycle().getFundId());
		
		//String updateCycleWithExpnd = "UPDATE FundCycle fc SET fc.totalExpenditure = :totalExpenditure WHERE fc.fundId = :fundId";
		//Query updateQuery = entityManager.createQuery(updateCycleWithExpnd);
		Float totalExpenditure = fundCycle.getTotalExpenditure() != null ? fundCycle.getTotalExpenditure() + expenditure.getAmountExpended() : expenditure.getAmountExpended(); 
		//updateQuery.setParameter("totalExpenditure", totalExpenditure);
		//updateQuery.setParameter("fundId", expenditure.getFundCycle().getFundId());
		
		//Float totalBalance = fundCycle.getTotalBalance() != null ? fundCycle.getTotalBalance() - expenditure.getAmountExpended()
		
		fundCycle.setTotalBalance(fundCycle.getTotalBalance() - expenditure.getAmountExpended());	
		fundCycle.setTotalExpenditure(totalExpenditure);
		
		Integer expndId = expenditure.getExpndId();
		return expndId;
	}
	
	private List<Expenditure> getExpenditures(FundCycle fundCycle){
		String getExpenditureOfACycle = "FROM Expenditure expnd WHERE expnd.fundCycle.fundId = :fundId";
		Query query = entityManager.createQuery(getExpenditureOfACycle);
		
		query.setParameter("fundId", fundCycle.getFundId());
		
		return query.getResultList();
	}

	@Override
	public List<FundsRecord> getReport(Report report) {
		
		String reportBy = report.getReportBy();
		
		Query reportQuery = null;
		
		switch(reportBy){
		case "employeeId":
			reportQuery = entityManager.createQuery("FROM FundsRecord fr where fr.employee.userName = :empId AND fr.fundCycle.startDate >= :fromDate AND fr.fundCycle.startDate <= :toDate");
			reportQuery.setParameter("empId", report.getEmployeeId());
			break;
		case "fundCycle":
			reportQuery = entityManager.createQuery("FROM FundsRecord fr where fr.fundCycle.startDate >= :fromDate AND fr.fundCycle.startDate <= :toDate");
			break;
		}
		
		reportQuery.setParameter("fromDate", report.getFromDate());
		reportQuery.setParameter("toDate", report.getToDate());
		
       @SuppressWarnings("unchecked")
	   List<FundsRecord> reports = reportQuery.getResultList();
		
       return reports;
	}
	
	private List<Employee> getBirthdayList(List<Employee> employees){
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		
		List<Employee> result = new ArrayList<>();
		for(Employee employee : employees){
			Date dob = employee.getDob();
			Calendar dobCal = Calendar.getInstance();
			dobCal.setTime(dob);
			if(cal.get(Calendar.MONTH) == dobCal.get(Calendar.MONTH)){
				result.add(employee);
			}
		}
		return result;
	}

	@Override
	public boolean updateRecord(FundsRecord updatedFR) {
		FundsRecord frFetchedFromDB = entityManager.find(FundsRecord.class, updatedFR.getRecordId());
		
		FundCycle fundCycle = entityManager.find(FundCycle.class, updatedFR.getFundCycle().getFundId());
			
		fundCycle.setTotalBalance(fundCycle.getTotalBalance() + (updatedFR.getActualPaidAmount() - frFetchedFromDB.getActualPaidAmount()));
		fundCycle.setTotalCollection(fundCycle.getTotalCollection() + (updatedFR.getActualPaidAmount() - frFetchedFromDB.getActualPaidAmount()));
			
		//only actual paid amount can be updated
		
		frFetchedFromDB.setActualPaidAmount(updatedFR.getActualPaidAmount());
		frFetchedFromDB.setAmountRemained(updatedFR.getAmountRemained());
		frFetchedFromDB.setPayDate(updatedFR.getPayDate());
		
       
		return true;
	}

	@Override
	public boolean updateExpenditure(Expenditure updatedExpnd) {
		
		Expenditure expndFetchedFromDB = entityManager.find(Expenditure.class, updatedExpnd.getExpndId());
		
		expndFetchedFromDB.setEventName(updatedExpnd.getEventName());
		expndFetchedFromDB.setAmountExpended(updatedExpnd.getAmountExpended());
		expndFetchedFromDB.setEventDate(updatedExpnd.getEventDate());
		expndFetchedFromDB.setDescription(updatedExpnd.getDescription());
		
		FundCycle fundCycle = expndFetchedFromDB.getFundCycle();
		Float expndDiff = updatedExpnd.getAmountExpended() - expndFetchedFromDB.getAmountExpended();
		fundCycle.setTotalExpenditure(fundCycle.getTotalExpenditure() + expndDiff);
		fundCycle.setTotalBalance(fundCycle.getTotalBalance() - expndDiff);
		return true;
	}
	

	@Override
	public boolean remove(String type, Integer id) {
		int rowsDeleted = 0;
		FundCycle fundCycle = null;
		switch(type){
		case FundUtil.FUNDS_RECORD:
			FundsRecord fundsRecord = entityManager.find(FundsRecord.class, id);
			
			fundCycle = fundsRecord.getFundCycle();
					
			Query fundRecordDlt = entityManager.createQuery("DELETE FROM FundsRecord fr WHERE fr.recordId = :recordId");
			fundRecordDlt.setParameter("recordId", id);
			rowsDeleted = fundRecordDlt.executeUpdate();
			
			fundCycle.setTotalBalance(fundCycle.getTotalBalance() - fundsRecord.getActualPaidAmount());
			fundCycle.setTotalCollection(fundCycle.getTotalCollection() - fundsRecord.getActualPaidAmount());
		
			break;
		case FundUtil.EXPENDITURE:
			Expenditure expenditure = entityManager.find(Expenditure.class, id);
			
			fundCycle = expenditure.getFundCycle();
			
			Query expndDlt = entityManager.createQuery("DELETE FROM Expenditure expnd WHERE expnd.expndId = :expndId");
			expndDlt.setParameter("expndId", id);
			rowsDeleted = expndDlt.executeUpdate();
			
			fundCycle.setTotalBalance(fundCycle.getTotalBalance() + expenditure.getAmountExpended());
			fundCycle.setTotalExpenditure(fundCycle.getTotalExpenditure() - expenditure.getAmountExpended());
			
			break;
		}
		return rowsDeleted > 0 ? true : false;
	}


}
