package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.entity.RevenueReport;
import com.example.demo.entity.RevenueTable;
import com.example.demo.service.ServiceInterface;
import java.util.ArrayList;

@RestController
@RequestMapping("/Reports")
@CrossOrigin("http://localhost:8090")
public class ControllerClass {
	@Autowired
	ServiceInterface serviceobj;

	// Get SalesReports
	@GetMapping("/SalesReport/{date1}/{date2}/{userid}/{category}")
	private ResponseEntity<List<RevenueTable>> getSalesReport(@PathVariable("date1") String date1, @PathVariable("date2") String date2,@PathVariable("userid") String userid,@PathVariable("category") String category) {
		System.out.println(date1+date2+userid+category);
		List<RevenueTable> saleslist = serviceobj.getSalesReport(date1,date2,userid,category);
		return new ResponseEntity<List<RevenueTable>>(saleslist,HttpStatus.OK);
	}

	// Get RevenueReports
	@GetMapping("/RevenueReport/{date1}/{date2}/{category}")
	private ResponseEntity<ArrayList<RevenueReport>> getRevenueReport(@PathVariable("date1") String date1, @PathVariable("date2") String date2,@PathVariable("category") String category) {
		ArrayList<RevenueReport> revenuelist = serviceobj.getRevenueReport(date1,date2,category);
		return new ResponseEntity<ArrayList<RevenueReport>>(revenuelist,HttpStatus.OK);
	}

}
