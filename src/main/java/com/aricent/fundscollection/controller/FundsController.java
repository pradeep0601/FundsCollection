package com.aricent.fundscollection.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aricent.fundscollection.model.Expenditure;
import com.aricent.fundscollection.model.FundCycle;
import com.aricent.fundscollection.model.FundsRecord;
import com.aricent.fundscollection.model.RemoveParams;
import com.aricent.fundscollection.model.Report;
import com.aricent.fundscollection.service.FundService;
import com.aricent.fundscollection.util.FundUtil.Records;

@Controller
@RequestMapping("/funds")
public class FundsController {

	@Autowired
	private FundService fundService;

	@RequestMapping(value = "/startFundCycle", method = RequestMethod.POST)
	@ResponseBody
	public boolean startFunding(@RequestBody FundCycle fundCycle) {

		System.out.println(fundCycle);

		boolean result = fundService.startFundCycle(fundCycle);

		return result;
	}

	@RequestMapping(value = "/stopFundCycle", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<HttpStatus> stopFunding(@RequestBody FundCycle fundCycle) {

		System.out.println(fundCycle);
		try {
			fundService.stopFundCycle(fundCycle);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception exception) {
			exception.printStackTrace();
           return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/fundDetails", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<Records, Object>> getFundDetails() {

		Map<Records, Object> fundDetails = fundService.getFundDetails();

		if (fundDetails == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(fundDetails, HttpStatus.OK);

	}

	@RequestMapping(value = "/addFunds", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Integer> addFunds(@RequestBody FundsRecord fundsRecord) {

		Integer recordId = null;
		
		try {
			recordId = fundService.addFunds(fundsRecord);

			return new ResponseEntity<Integer>(recordId, HttpStatus.OK);
		} catch (Exception exception) {
			exception.printStackTrace();
			return new ResponseEntity<Integer>(recordId, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/expenditure", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Integer> addExpenditure(@RequestBody Expenditure expenditure) {

		System.out.println(expenditure);
		Integer expndId = null;
		try {
			expndId = fundService.addExpenditure(expenditure);
			return new ResponseEntity<Integer>(expndId, HttpStatus.OK);
		} catch (Exception exception) {
			exception.printStackTrace();
           return new ResponseEntity<Integer>(expndId, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/reports", method = RequestMethod.POST)
	public ResponseEntity<List<FundsRecord>> getReport(@RequestBody Report report){
		
		System.out.println(report);
		
		List<FundsRecord> reports = fundService.getReport(report);
		
		if(reports == null || reports.size() == 0){
			return new ResponseEntity<List<FundsRecord>>(reports, HttpStatus.NO_CONTENT);
		}
		else{
			return new ResponseEntity<List<FundsRecord>>(reports, HttpStatus.OK);
		}
	}
	@RequestMapping(value = "/updateFR", method = RequestMethod.POST)
	public ResponseEntity<Boolean> updateFRecord(@RequestBody FundsRecord updatedFR){
		
		Boolean result = false;
		try{
			result = fundService.updateRecord(updatedFR);
			
			return new ResponseEntity<Boolean>(result, HttpStatus.OK);
		}
		catch(Exception ex){
			ex.printStackTrace();
			return new ResponseEntity<Boolean>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/updateExpnd", method = RequestMethod.POST)
	public ResponseEntity<Boolean> updateExpnd(@RequestBody Expenditure updatedExpnd){
		
		Boolean result = false;
		try{
			result = fundService.updateExpenditure(updatedExpnd);
			
			return new ResponseEntity<Boolean>(result, HttpStatus.OK);
		}
		catch(Exception ex){
			ex.printStackTrace();
			return new ResponseEntity<Boolean>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public ResponseEntity<Boolean> remove(@RequestBody RemoveParams removeParams){
		
		Boolean result = false;
		try{
			result = fundService.remove(removeParams.getType(), removeParams.getId());
			return new ResponseEntity<Boolean>(result, HttpStatus.OK);
		}
		catch(Exception ex){
			ex.printStackTrace();
			return new ResponseEntity<Boolean>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
