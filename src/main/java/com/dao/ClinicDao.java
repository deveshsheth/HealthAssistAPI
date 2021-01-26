package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bean.ClinicBean;

@Repository
public class ClinicDao {

	@Autowired
	JdbcTemplate stmt;
	
	public void addClinic(ClinicBean clinicBean) {
		// TODO Auto-generated method stub
		stmt.update("insert into clinic (clinicname,timing,address,phoneno,rating,about,lat,log,cityid,stateid,pincode) values(?,?,?,?,?,?,?,?,?,?,?)",
				clinicBean.getClinicname(),clinicBean.getTiming(),clinicBean.getAddress(),clinicBean.getPhoneno(),
				clinicBean.getRating(),clinicBean.getAbout(),clinicBean.getLat(),clinicBean.getLog(),clinicBean.getCityid(),
				clinicBean.getStateid(),clinicBean.getPincode());
	}

	public List<ClinicBean> listClinic() {
		// TODO Auto-generated method stub
		java.util.List<ClinicBean> clinicBean = stmt.query("select * from clinic",BeanPropertyRowMapper.newInstance(ClinicBean.class));
		return clinicBean;
	}

	public void deleteClinic(int clinicId) {
		// TODO Auto-generated method stub.
		stmt.update("delete from clinic where clinicid = ?",clinicId);
		
	}

	public void updateClinic(ClinicBean clinicBean) {
		// TODO Auto-generated method stub
		stmt.update("update clinic set clinicname=?,timing=?,address=?,phoneno=?,rating=?,about=?,lat=?,log=?,cityid=?,stateid=?,pincode=? where clinicid=?",
				clinicBean.getClinicname(),clinicBean.getTiming(),clinicBean.getAddress(),clinicBean.getPhoneno(),
				clinicBean.getRating(),clinicBean.getAbout(),clinicBean.getLat(),clinicBean.getLog(),clinicBean.getCityid(),
				clinicBean.getStateid(),clinicBean.getPincode(),clinicBean.getClinicid());
	}


	public List<ClinicBean> searchClinic() {
		// TODO Auto-generated method stub
		
		return null;
	}

}
