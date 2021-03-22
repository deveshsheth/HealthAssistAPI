package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import com.bean.PrescriptionMedicineBean;

@Repository
public class PrescriptionMedicineDao {

	@Autowired
	JdbcTemplate stmt;
	
	public void addPrescriptionMedicine(PrescriptionMedicineBean prescriptionmedicineBean) {
		// TODO Auto-generated method stub
		stmt.update("insert into prescriptionmedicine(prescriptionid,medicineid,frequency,duration,instructions)", 
				prescriptionmedicineBean.getPrescriptionid(),prescriptionmedicineBean.getMedicineid(),
				prescriptionmedicineBean.getFrequency(),prescriptionmedicineBean.getDuration(),
				prescriptionmedicineBean.getInstructions());
	}

	public List<PrescriptionMedicineBean> listPrescriptionMedicine() {
		// TODO Auto-generated method stub
		List<PrescriptionMedicineBean> prescriptionmedicineBean = stmt.query("select * from prescriptionmedicine where isdeleted=0",
				BeanPropertyRowMapper.newInstance(PrescriptionMedicineBean.class));
		return prescriptionmedicineBean;
	}

	public PrescriptionMedicineBean getPrescriptionMedicineById(int prescriptionmedicineid) {
		// TODO Auto-generated method stub
		PrescriptionMedicineBean prescriptionmedicineBean = null;
        try {
        	prescriptionmedicineBean = stmt.queryForObject("select * from prescriptionmedicine where prescriptionmedicineid=?", new Object[]{prescriptionmedicineid},
                    BeanPropertyRowMapper.newInstance(PrescriptionMedicineBean.class));
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
		return prescriptionmedicineBean;
	}

	public void updatePrescriptionMedicine(PrescriptionMedicineBean prescriptionmedicineBean) {
		// TODO Auto-generated method stub
		stmt.update("update prescriptionmedicine set prescriptionid=?, medicineid=?, frequency=?, duration=?, instructions=? where prescriptionmedicineid = ?",
				prescriptionmedicineBean.getPrescriptionid(),prescriptionmedicineBean.getMedicineid(),
				prescriptionmedicineBean.getFrequency(),prescriptionmedicineBean.getDuration(),
				prescriptionmedicineBean.getInstructions(),prescriptionmedicineBean.getPrescriptionmedicineid());

	}

	public void deletePrescriptionMedicine(int prescriptionmedicineid) {
		// TODO Auto-generated method stub
		stmt.update("update prescriptionmedicine set isdeleted=1 where prescriptionmedicineid = ?",prescriptionmedicineid);
		
	}

}
