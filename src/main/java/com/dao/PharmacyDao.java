package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.bean.PharmacyBean;
import com.bean.UserBean;
import com.bean.userPharmacyBean;

@Repository
public class PharmacyDao {

    @Autowired
    JdbcTemplate stmt;

    public int addPharmacy(PharmacyBean pharmacyBean) {
        // TODO Auto-generated method stub
    	KeyHolder keyHolder = new GeneratedKeyHolder();
    	String insertSql = "insert into pharmacy(pharmacyname,timing,address,phoneno,rating,about,lat,log,cityid,pincode) values(?,?,?,?,?,?,?,?,?,?)";
    	
    	
    	stmt.update(new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {

                PreparedStatement pstmt = con.prepareStatement(insertSql, java.sql.Statement.RETURN_GENERATED_KEYS);
                pstmt.setString(1, pharmacyBean.getPharmacyname());
                pstmt.setString(2, pharmacyBean.getTiming());
                pstmt.setString(3, pharmacyBean.getAddress());
                pstmt.setString(4, pharmacyBean.getPhoneno());
                pstmt.setDouble(5, pharmacyBean.getRating());
                pstmt.setString(6, pharmacyBean.getAbout());
                pstmt.setDouble(7, pharmacyBean.getLat());
                pstmt.setDouble(8, pharmacyBean.getLog());
                pstmt.setInt(9, pharmacyBean.getCityid());
                pstmt.setInt(10, pharmacyBean.getPincode());
                return pstmt;
            }
        }, keyHolder);
    	int pharmacyid = (Integer) keyHolder.getKeys().get("pharmacyid");
    	pharmacyBean.setPharmacyid(pharmacyid);
        return pharmacyBean.getPharmacyid();
    }
    
    
    public void addAssignUserPharmacy(PharmacyBean pharmacyBean) {
		// TODO Auto-generated method stub
         int pharmacyid = addPharmacy(pharmacyBean);

         pharmacyBean.setPharmacyid(pharmacyid);
    	stmt.update("insert into user_pharmacy(userid,pharmacyid) values(?,?)",
    			pharmacyBean.getUserid(), pharmacyBean.getPharmacyid());
	}

    public List<PharmacyBean> listPharmacy() {
        // TODO Auto-generated method stub
        java.util.List<PharmacyBean> pharmacyBean = stmt.query("select *,cities.cityname from pharmacy as p join cities using(cityid) where p.cityid = cityid and p.isdeleted =0", BeanPropertyRowMapper.newInstance(PharmacyBean.class));
        System.out.println(pharmacyBean);
        return pharmacyBean;
    }

    public void deletePharmacy(int pharmacyid) {
        // TODO Auto-generated method stub
        stmt.update("update pharmacy set isdeleted = 1 where pharmacyid = ?", pharmacyid);
    }

    public void updatePharmacy(PharmacyBean pharmacyBean) {
        // TODO Auto-generated method stub
        stmt.update("update pharmacy set pharmacyname=?,timing=?,address=?,phoneno=?,rating=?,about=?,lat=?,log=?,cityid=?,pincode=? where pharmacyid=?",
                pharmacyBean.getPharmacyname(), pharmacyBean.getTiming(), pharmacyBean.getAddress(), pharmacyBean.getPhoneno(), pharmacyBean.getRating(),
                pharmacyBean.getAbout(), pharmacyBean.getLat(), pharmacyBean.getLog(), pharmacyBean.getCityid(), pharmacyBean.getPincode(), pharmacyBean.getPharmacyid());

    }

    public PharmacyBean getPharmacyById(int pharmacyId) {
        // TODO Auto-generated method stub
        PharmacyBean bean = null;
        try {
            bean = stmt.queryForObject("select *,cities.cityname from pharmacy as p join cities using(cityid) where p.cityid = cityid and pharmacyid=?", new Object[]{pharmacyId},
                    BeanPropertyRowMapper.newInstance(PharmacyBean.class));
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return bean;
    }


	public List<UserBean> listAssignUserPharmacy() {
		// TODO Auto-generated method stub
		 java.util.List<UserBean> pharmacyBean = stmt.query("select * from users where roleid=4", BeanPropertyRowMapper.newInstance(UserBean.class));
		return pharmacyBean;
	}

	

}
