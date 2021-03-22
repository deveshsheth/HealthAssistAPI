package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bean.PrescriptionBean;
@Repository
public class PrescriptionDao {
	
	@Autowired
	JdbcTemplate stmt;

	public void addPrescription(PrescriptionBean prescriptionBean) {
		// TODO Auto-generated method stub
		stmt.update(
				"insert into prescription(patientprofileid,doctorprofileid,appointmentid,description,prescriptiondate,generaladvice,followupcomment) values (?,?,?,?,?,?,?)",
				prescriptionBean.getPatientProfileid(), prescriptionBean.getDoctorProfileid(),
				prescriptionBean.getAppointmentid(), prescriptionBean.getDescription(),
				prescriptionBean.getPrescriptiondate(), prescriptionBean.getGeneraladvice(),
				prescriptionBean.getFollowupcomment());
	}

	public List<PrescriptionBean> listPrescription() {
		// TODO Auto-generated method stub
		List<PrescriptionBean> prescriptionBean = stmt.query("select * from prescription",
				BeanPropertyRowMapper.newInstance(PrescriptionBean.class));
		return prescriptionBean;
	}

	public PrescriptionBean getPrescriptionById(int prescriptionId) {
		// TODO Auto-generated method stub
		PrescriptionBean prescriptionBean = null;

		try {
			prescriptionBean = stmt.queryForObject("select * from prescription where prescriptionid = ?",
					new Object[prescriptionId], BeanPropertyRowMapper.newInstance(PrescriptionBean.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prescriptionBean;
	
	}

	public void updatePrescription(PrescriptionBean prescriptionBean) {
		// TODO Auto-generated method stub
		stmt.update(
				"update prescription set patientprofileid = ?,doctorprofileid = ?,appointmentid = ?,description = ?,prescriptiondate = ?,generaladvice = ?,followupcomment = ? where prescriptionid = ?",
				prescriptionBean.getPatientProfileid(), prescriptionBean.getDoctorProfileid(),
				prescriptionBean.getAppointmentid(), prescriptionBean.getDescription(),
				prescriptionBean.getPrescriptiondate(), prescriptionBean.getGeneraladvice(),
				prescriptionBean.getFollowupcomment(), prescriptionBean.getPrescriptiondate());
	}

	public void deletePrescription(int prescriptionId) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
