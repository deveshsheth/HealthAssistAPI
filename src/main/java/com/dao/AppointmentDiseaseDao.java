package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bean.AppointmentDiseaseBean;


@Repository
public class AppointmentDiseaseDao {

	@Autowired
	JdbcTemplate stmt;
	
	public void addAppointmentDisease(AppointmentDiseaseBean appointmentDiseaseBean) {
		// TODO Auto-generated method stub
		stmt.update("insert into appointmentdisease(appointmentid,diseaseid,patientprofileid) values(?,?,?)", 
				appointmentDiseaseBean.getAppointmentid(),appointmentDiseaseBean.getDiseaseid(),appointmentDiseaseBean.getPatientprofileid());
	}

	public List<AppointmentDiseaseBean> listAppointmentDisease() {
		// TODO Auto-generated method stub
		List<AppointmentDiseaseBean> appointmentDiseaseBean = stmt.query("select *,disease.diseasename from appointmentdisease as ad join disease using(diseaseid) where ad.diseaseid = diseaseid and ad.isdeleted=0",
				BeanPropertyRowMapper.newInstance(AppointmentDiseaseBean.class));
		return appointmentDiseaseBean;
	}

	public void updateAppointmentDisease(AppointmentDiseaseBean appointmentDiseaseBean) {
		// TODO Auto-generated method stub
		stmt.update("update appointmentdisease set appointmentid = ?, diseaseid = ?, patientprofileid =? where appointmentdiseaseid = ?",
				appointmentDiseaseBean.getAppointmentid(), appointmentDiseaseBean.getDiseaseid(), appointmentDiseaseBean.getPatientprofileid(),appointmentDiseaseBean.getAppointmentdiseaseid());

	}

	public AppointmentDiseaseBean getAppointmentDiseaseById(int appointmentdiseaseid) {
		// TODO Auto-generated method stub
		AppointmentDiseaseBean appointmentDiseaseBean = null;
        try {
        	appointmentDiseaseBean = stmt.queryForObject("select * from appointmentdisease where appointmentdiseaseid=?", new Object[]{appointmentdiseaseid},
                    BeanPropertyRowMapper.newInstance(AppointmentDiseaseBean.class));
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
		return appointmentDiseaseBean;
	}

	public void deleteAppointmentDisease(int appointmentdiseaseid) {
		// TODO Auto-generated method stub
		stmt.update("update appointmentdisease set isdeleted=1 where appointmentdiseaseid = ?",appointmentdiseaseid);
	}

}
