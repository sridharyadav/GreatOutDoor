package com.example.demo.service;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.DaoInterface;
import com.example.demo.entity.RevenueReport;
import com.example.demo.entity.RevenueTable;


@Service
@Transactional
public class ServiceClass implements ServiceInterface {
	@Autowired
	DaoInterface daoobj;
 
	SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
    //Converting date format from yyyy-mm-dd to dd-mmm-yyyy
	String dateformat(String s) {
		String months = new DateFormatSymbols().getMonths()[Integer.parseInt(s.substring(5, 7)) - 1];
		String cdate = s.subSequence(8, 10) + "-" + months.substring(0, 3) + "-" + s.substring(0, 4);
		return cdate;
	}

	@Override
	public List<RevenueTable> getSalesReport(String date1, String date2, String userid, String category) {
		return daoobj.getSalesReport(dateformat(date1), dateformat(date2), userid, category);
	}

	@Override
	public ArrayList<RevenueReport> getRevenueReport(String date1, String date2, String category) {
        
		List<RevenueTable> l3 = daoobj.getRevenueReport(dateformat(date1), dateformat(date2), category);
		//fetching objects of type revenuetable and performing operations and adding into list of type revenuereport
		int i, j, revenue = 0, change = 0, prev = 0;
		String period, colorcode;
		ArrayList<RevenueReport> revenuelist = new ArrayList<RevenueReport>();
		for (i = 0; i < l3.size(); i++) {
			String objdate = l3.get(i).getDate1();// getting date from object
			for (j = i + 1; j < l3.size(); j++) {
				String objdate1 = l3.get(j).getDate1();
				revenue = l3.get(i).getProduct_price();
				if (objdate1.subSequence(5, 7).equals(objdate.subSequence(5, 7)))
					revenue = revenue + l3.get(j).getProduct_price();
				else
					break;
			}
			// combining month,year as period
			period = objdate.subSequence(5, 7) + " " + objdate.subSequence(0, 4);
			if (prev == 0)
				change = revenue;
			else
				change = revenue - prev;
			// Calculating percentage growth
			int percentagegrowth = (change * 100) / revenue;
			// Indication of growth using color
			if (percentagegrowth > 10)
				colorcode = "green";
			else if (percentagegrowth > 2 && percentagegrowth < 10)
				colorcode = "blue";
			else
				colorcode = "red";
			RevenueReport r = new RevenueReport(period, revenue, change, percentagegrowth, colorcode);
			revenuelist.add(r);
			i = j - 1;
			prev = revenue;
		}
		return revenuelist;
	}
}
