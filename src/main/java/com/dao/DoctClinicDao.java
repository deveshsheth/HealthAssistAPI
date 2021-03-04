package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
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

	public List<DoctClinicBean> listDoctCLinic() {
		// TODO Auto-generated method stub
		 java.util.List<DoctClinicBean> doctClinicBean = stmt.query("select *,clinic.clinicname from doct_clinic as dc join clinic using(clinicid) where dc.clinicid = clinicid and dc.isdeleted = 0",BeanPropertyRowMapper.newInstance(DoctClinicBean.class));
		return doctClinicBean;
	}

	public void deleteDoctClinic(int doctclinicid) {
		// TODO Auto-generated method stub
		stmt.update("update doct_clinic set isdeleted = 1 where doctclinicid = ?",doctclinicid);
		
	}
}
