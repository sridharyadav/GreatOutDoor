package com.example.demo.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.RevenueTable;
public interface DaoInterface extends JpaRepository<RevenueTable,Integer>{
	@Query("select r from RevenueTable r where r.date1>=?1 and r.date1<=?2 and r.user_id=?3 and r.product_category=?4 order by date1")
	List<RevenueTable> getSalesReport(String date1, String date2,String userid, String category);

	@Query("select r from RevenueTable r where r.date1>=?1 and r.date1<=?2 and r.product_category=?3  order by r.date1")
	List<RevenueTable> getRevenueReport(String date1, String date2, String category);

}
