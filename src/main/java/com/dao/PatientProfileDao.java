package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bean.DoctClinicBean;
import com.bean.PathologyBean;
import com.bean.PatientProfileBean;


@Repository
public class PatientProfileDao {

    @Autowired
    JdbcTemplate stmt;



    public List<PatientProfileBean> listPatient() {
        // TODO Auto-generated method stub

        java.util.List<PatientProfileBean> patientBean = stmt.query("select *,cities.cityname from patientprofile as p join cities using(cityid) where p.cityid = cityid", BeanPropertyRowMapper.newInstance(PatientProfileBean.class));
        return patientBean;
    }

    public void deletePatient(int patientId) {
        // TODO Auto-generated method stub
        stmt.update("delete from patientprofile where patientid = ?", patientId);
    }

    public void updatePatient(PatientProfileBean patientBean) {
        // TODO Auto-generated method stub
        stmt.update("update patientprofile set patientname=?,gender=?,phoneno=?,email=?,age=?,profilepic=?,cityid=?,stateid=?,pincode=? where pathologyid=?",
                patientBean.getPatientname(), patientBean.getGender(), patientBean.getPhoneno(), patientBean.getEmail(),
                patientBean.getAge(), patientBean.getProfilepic(), patientBean.getCityid(), patientBean.getStateid(), patientBean.getPincode(), patientBean.getPatientid());
    }

	public PatientProfileBean getPatientById(int userId) {
		// TODO Auto-generated method stub
		PatientProfileBean bean = null;
	        try {
	            bean = stmt.queryForObject("select * from patientprofile where userid=?", new Object[]{userId},
	                    BeanPropertyRowMapper.newInstance(PatientProfileBean.class));
	        } catch (Exception e) {
	            // TODO: handle exception
	            e.printStackTrace();
	        }
	        return bean;
	}

	public void addPatientProfile(PatientProfileBean patientBean) {
		// TODO Auto-generated method stub
		stmt.update("insert into patientprofile(patientname,gender,phoneno,email,age,profilepic,cityid,pincode,userid) values(?,?,?,?,?,?,?,?,?)",
                patientBean.getPatientname(),patientBean.getGender(), patientBean.getPhoneno(), 
               patientBean.getEmail(),patientBean.getAge(), patientBean.getProfilepic(), 
               patientBean.getCityid(),
                patientBean.getPincode(),
               patientBean.getUserId());
		
	}

	public PatientProfileBean getPatientProfileById(int userId) {
		// TODO Auto-generated method stub
		PatientProfileBean bean = null;
        try {
        	System.out.println("this is patient profile userid "+userId);
            bean = stmt.queryForObject("select *,cities.cityname from patientprofile as p join cities using(cityid) where p.cityid = cityid and userid=?", new Object[]{userId},
                    BeanPropertyRowMapper.newInstance(PatientProfileBean.class));
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
     
        }
        return bean;
	}

	public List<PatientProfileBean> listUserPatient(int userid) {
		// TODO Auto-generated method stub
		 java.util.List<PatientProfileBean> userPatientBean = stmt.query("select pp.*,up.userid from patientprofile as pp,users as up where pp.userid = up.userid and pp.isdeleted =0  and up.userid= ?", new Object[]{userid}, BeanPropertyRowMapper.newInstance(PatientProfileBean.class));
	     return userPatientBean;
	}

}
