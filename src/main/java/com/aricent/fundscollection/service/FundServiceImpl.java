package com.aricent.fundscollection.service;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aricent.fundscollection.dao.FundDAO;
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
@Service
@Transactional
public class FundServiceImpl implements FundService {

	@Autowired
	private FundDAO fundDAO;

	@Override
	public Boolean startFundCycle(FundCycle fundCycle) {
		return fundDAO.startFundCycle(fundCycle);
	}

	@Override
	public Map<Records, Object> getFundDetails() {
		return fundDAO.getFundDetails();
	}

	@Override
	public Boolean addFunds(FundsRecord fundsRecord) {
		
		return fundDAO.addFunds(fundsRecord);
	}

	@Override
	public Boolean stopFundCycle(FundCycle fundCycle) {
		return fundDAO.stopFundCycle(fundCycle);
	}

	@Override
	public Boolean addExpenditure(Expenditure expenditure) {
		return fundDAO.addExpenditure(expenditure);
	}

	@Override
	public List<FundsRecord> getReport(Report report) {
		return fundDAO.getReport(report);
	}

}
