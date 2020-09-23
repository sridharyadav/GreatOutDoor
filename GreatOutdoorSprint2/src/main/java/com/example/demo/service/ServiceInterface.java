package com.example.demo.service;

import java.util.List;
import java.util.ArrayList;

import com.example.demo.entity.RevenueReport;
import com.example.demo.entity.RevenueTable;

public interface ServiceInterface {
	
	List<RevenueTable> getSalesReport(String date1,String date2,String userid,String category);

	ArrayList<RevenueReport> getRevenueReport(String date1,String date2,String category);

}
