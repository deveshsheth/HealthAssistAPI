package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bean.PharmacyBean;
import com.bean.RoleBean;
import com.bean.userPharmacyBean;

@Repository
public class userPharmacyDao {

    @Autowired
    JdbcTemplate stmt;

    public void addUserPharmacy(userPharmacyBean userPharmacyBean) {
        // TODO Auto-generated method stub

        stmt.update("insert into user_pharmacy(userid,pharmacyid) values(?,?)",
                userPharmacyBean.getUserid(), userPharmacyBean.getPharmacyid());

    }


    public void updateUserPharmacy(userPharmacyBean userPharmacyBean) {
        // TODO Auto-generated method stub
        stmt.update("update user_pharmacy set userid=?,pharmacyid=? where userpharmacyid=?", userPharmacyBean.getUserid(), userPharmacyBean.getPharmacyid(), userPharmacyBean.getUserpharmacyid());
    }


	public List<userPharmacyBean> listUserPharmacy(int pharmacyid) {
		// TODO Auto-generated method stub
		 java.util.List<userPharmacyBean> userPharmacyBean = stmt.query("select u.*,p.*,up.* from users as u,pharmacy as p,user_pharmacy as up where up.userid = u.userid and up.pharmacyid = p.pharmacyid and up.userid = ? and up.isdeleted=0",new Object[] {pharmacyid}, BeanPropertyRowMapper.newInstance(userPharmacyBean.class));
		return userPharmacyBean;
	}


	public userPharmacyBean getPharmacyById(int pharmacyId) {
		// TODO Auto-generated method stub
		userPharmacyBean bean = null;
        try {
            bean = stmt.queryForObject("select * from user_pharmacy where userpharmacyid=?", new Object[]{pharmacyId},
                    BeanPropertyRowMapper.newInstance(userPharmacyBean.class));
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return bean;
	}


	public void deleteUserPharmacy(int pharmacyid) {
		// TODO Auto-generated method stub
		stmt.update("update user_pharmacy set isdeleted = 1 where userpharmacyid = ?", pharmacyid);
	}



}
