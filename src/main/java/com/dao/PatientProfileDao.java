package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bean.PatientProfileBean;

@Repository
public class PatientProfileDao {
	
	@Autowired
	JdbcTemplate stmt;

	public void addPatient(PatientProfileBean patientBean) {
		// TODO Auto-generated method stub
		stmt.update("insert into patientprofile(patientname,gender,phoneno,email,age,profilepic,cityid,stateid,pincode) values(?,?,?,?,?,?,?,?,?)",
				patientBean.getPatientname(),patientBean.getGender(),patientBean.getPhoneno(),patientBean.getEmail(),
				patientBean.getAge(),patientBean.getProfilepic(),patientBean.getCityid(),patientBean.getStateid(),patientBean.getPincode());
		
	}

	public List<PatientProfileBean> listPatient() {
		// TODO Auto-generated method stub
		
		java.util.List<PatientProfileBean> patientBean = stmt.query("select *,cities.cityname from patientprofile as p join cities using(cityid) where p.cityid = cityid",BeanPropertyRowMapper.newInstance(PatientProfileBean.class));
		return patientBean;
	}

	public void deletePatient(int patientId) {
		// TODO Auto-generated method stub
		stmt.update("delete from patientprofile where patientid = ?",patientId);
	}

	public void updatePatient(PatientProfileBean patientBean) {
		// TODO Auto-generated method stub
		stmt.update("update patientprofile set patientname=?,gender=?,phoneno=?,email=?,age=?,profilepic=?,cityid=?,stateid=?,pincode=? where pathologyid=?",				
				patientBean.getPatientname(),patientBean.getGender(),patientBean.getPhoneno(),patientBean.getEmail(),
				patientBean.getAge(),patientBean.getProfilepic(),patientBean.getCityid(),patientBean.getStateid(),patientBean.getPincode(),patientBean.getPatientid());
	}

}
