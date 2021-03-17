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
                pharmacyBean.getPharmacyname(), pharmacyBean.getTiming(), pharmacyBean.getAddress(), pharmacyBean.getPhoneno(), pharmacyBean.getRating(),
                pharmacyBean.getAbout(), pharmacyBean.getLat(), pharmacyBean.getLog(), pharmacyBean.getCityid(), pharmacyBean.getPincode());

    }

    public List<PharmacyBean> listPharmacy() {
        // TODO Auto-generated method stub
        java.util.List<PharmacyBean> pharmacyBean = stmt.query("select *,cities.cityname from pharmacy as p join cities using(cityid) where p.cityid = cityid and isdeleted =0", BeanPropertyRowMapper.newInstance(PharmacyBean.class));
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

}
