package com.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bean.userPharmacyBean;
@Repository
public class userPharmacyDao {

	@Autowired
	JdbcTemplate stmt;
	
	public void addUserPharmacy(userPharmacyBean userPharmacyBean) {
		// TODO Auto-generated method stub
		
		stmt.update("insert into user_pharmacy(userid,pharmacyid) values(?,?)",
				userPharmacyBean.getUserid(),userPharmacyBean.getPharmacyid());
		
	}


	public void updateUserPharmacy(userPharmacyBean userPharmacyBean) {
		// TODO Auto-generated method stub
		stmt.update("update user_pharmacy set userid=?,pharmacyid=? where userpharmacyid=?", userPharmacyBean.getUserid(),userPharmacyBean.getPharmacyid(),userPharmacyBean.getUserpharmacyid());
	}

}
