package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bean.PharmacyBean;
@Repository
public class PharmacyDao {

	@Autowired
	JdbcTemplate stmt;
	
	public void addPathology(PharmacyBean pharmacyBean) {
		// TODO Auto-generated method stub
		
		stmt.update("insert into pharmacy(pharmacyname,timing,address,phoneno,rating,about,lat,log,cityid,pincode) values(?,?,?,?,?,?,?,?,?,?)", 
				pharmacyBean.getPharmacyname(),pharmacyBean.getTiming(),pharmacyBean.getAddress(),pharmacyBean.getPhoneno(),pharmacyBean.getRating(),
				pharmacyBean.getAbout(),pharmacyBean.getLat(),pharmacyBean.getLog(),pharmacyBean.getCityid(),pharmacyBean.getPincode());
		
	}

	public List<PharmacyBean> listPharmacy() {
		// TODO Auto-generated method stub
		java.util.List<PharmacyBean> pharmacyBean = stmt.query("select * from pharmacy join cities using(cityid) where pharmacyid = ?",BeanPropertyRowMapper.newInstance(PharmacyBean.class));
		return pharmacyBean;
	}

	public void deletePharmacy(int pharmacyId) {
		// TODO Auto-generated method stub
		stmt.update("delete from pharmacy where pharmacyid = ?",pharmacyId);
	}

	public void updatePharmacy(PharmacyBean pharmacyBean) {
		// TODO Auto-generated method stub
		stmt.update("update pharmacy set pharmacyname=?,timing=?,address=?,phoneno=?,rating=?,about=?,lat=?,log=?,cityid=?,pincode=? where pharmacyid=?",
		pharmacyBean.getPharmacyname(),pharmacyBean.getTiming(),pharmacyBean.getAddress(),pharmacyBean.getPhoneno(),pharmacyBean.getRating(),
		pharmacyBean.getAbout(),pharmacyBean.getLat(),pharmacyBean.getLog(),pharmacyBean.getCityid(),pharmacyBean.getPincode(),pharmacyBean.getPharmacyid());
		
	}

}
