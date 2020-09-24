package com.example.demo;

import static org.junit.Assert.assertEquals;


import java.util.ArrayList;
import java.util.List;




import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.entity.RevenueReport;
import com.example.demo.entity.RevenueTable;
import com.example.demo.service.ServiceClass;





@RunWith(SpringRunner.class)
@SpringBootTest
public class GreatOutdoorSprint2ApplicationTests {
	@Autowired
	private ServiceClass service;
	@MockBean
	private RevenueTable revenue;
	
	@Test
	@DisplayName("Test for sales report null")
	public void getSalesReport() {
		
		ArrayList<RevenueTable> expected = new ArrayList<RevenueTable>();
		revenue=new RevenueTable("SRIDHA","03-AUG-2020",12458,852369,"MOBIL",40000);
		assertEquals(expected,service.getSalesReport("2020-02-03", "2020-04-03", "SRIDHAR", "MOBILE"));
	
	}
	@Test
	@DisplayName("Test for  salesreport values")
	public void getSalesReport1() {
		int expectedListSize=2;
		List<RevenueTable> revenue1=service.getSalesReport("2020-02-03","2020-12-19", "SRIDHAR", "MOBILE");
		assertEquals(expectedListSize,revenue1.size());

	
	}
	@Test
	@DisplayName("Test For revenue report")
	public void getRevenueReport() {
		int expectedListSize=2;
		List<RevenueReport> revenu=service.getRevenueReport("2020-02-03","2020-12-19","MOBILE");
		assertEquals(expectedListSize,revenu.size());
	}

	

}
