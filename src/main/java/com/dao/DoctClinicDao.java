package com.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bean.DoctClinicBean;
@Repository
public class DoctClinicDao {

	@Autowired
	JdbcTemplate stmt;
	
	public void addDoctClinic(DoctClinicBean doctclinicBean) {
		// TODO Auto-generated method stub
		
		stmt.update("insert into doct_clinic (docid,clinicid) values (?,?)",doctclinicBean.getDocid(),doctclinicBean.getClinicid());
	}
}
