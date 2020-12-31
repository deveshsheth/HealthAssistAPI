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
		
		stmt.update("insert into doct_clinic(docid,clinicid,monday,tuesday,wednesday,thrusday,friday,saturday,sunday,threshold) values (?,?,?,?,?,?,?,?,?,?)",
				doctclinicBean.getDocid(),doctclinicBean.getClinicid(),doctclinicBean.getMonday(),doctclinicBean.getTuesday(),doctclinicBean.getWednesday(),
				doctclinicBean.getThrusday(),doctclinicBean.getFriday(),doctclinicBean.getSaturday(),doctclinicBean.getSunday(),doctclinicBean.getThreshold());
	}

	public void updateDoctClinic(DoctClinicBean doctclinicBean) {
		// TODO Auto-generated method stub
		
		stmt.update("update doct_clinic set docid=?,clinicid=?,monday=?,tuesday=?,wednesday=?,thrusday=?,friday=?,saturday=?,sunday=?,threshold=? where doctclinicid=?", 
				doctclinicBean.getDocid(),doctclinicBean.getClinicid(),doctclinicBean.getMonday(),doctclinicBean.getTuesday(),doctclinicBean.getWednesday(),
				doctclinicBean.getThrusday(),doctclinicBean.getFriday(),doctclinicBean.getSaturday(),doctclinicBean.getSunday(),doctclinicBean.getThreshold(),
				doctclinicBean.getDoctclinicid());
	}
}
