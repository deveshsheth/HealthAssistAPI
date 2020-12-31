package com.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RestController;

import com.bean.AppointmentStatusBean;

@RestController
public class AppointmentStatusDao {

	@Autowired
	JdbcTemplate stmt;

	public void addAppStatus(AppointmentStatusBean appstatusBean) {
		// TODO Auto-generated method stub
		
		stmt.update("insert into appointmentstatus(statusname) values(?)",appstatusBean.getStatusname());
		
	}

	public void updateAppStatus(AppointmentStatusBean appstatusBean) {
		// TODO Auto-generated method stub
		
		stmt.update("update appointmentstatus set statusname=? where statusid=?", appstatusBean.getStatusname(),appstatusBean.getStatusid());
		
	}
	
	
	
}
