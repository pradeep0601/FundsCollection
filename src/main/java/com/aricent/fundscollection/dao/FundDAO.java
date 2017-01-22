package com.aricent.fundscollection.dao;

import java.util.List;
import java.util.Map;

import com.aricent.fundscollection.model.Expenditure;
import com.aricent.fundscollection.model.FundCycle;
import com.aricent.fundscollection.model.FundsRecord;
import com.aricent.fundscollection.model.Report;
import com.aricent.fundscollection.util.FundUtil.Records;

/**
 * 
 * @author Pradeep
 *
 */
public interface FundDAO {

	public Boolean startFundCycle(FundCycle fundsCycle);

	public Boolean stopFundCycle(FundCycle fundsCycle);

	public Map<Records, Object> getFundDetails();

	public Integer addFunds(FundsRecord fundsRecord);

	public Integer addExpenditure(Expenditure expenditure);

	public List<FundsRecord> getReport(Report report);

	public boolean updateRecord(FundsRecord updatedFR);

	public boolean updateExpenditure(Expenditure updatedExpnd);

	public boolean remove(String type, Integer id);

}
