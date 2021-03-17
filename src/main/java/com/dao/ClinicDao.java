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
        stmt.update("insert into clinic (clinicname,timing,address,phoneno,rating,about,lat,log,cityid,pincode) values(?,?,?,?,?,?,?,?,?,?)",
                clinicBean.getClinicname(), clinicBean.getTiming(), clinicBean.getAddress(), clinicBean.getPhoneno(),
                clinicBean.getRating(), clinicBean.getAbout(), clinicBean.getLat(), clinicBean.getLog(), clinicBean.getCityid(),
                clinicBean.getPincode());
    }

    public List<ClinicBean> listClinic() {
        // TODO Auto-generated method stub
        java.util.List<ClinicBean> clinicBean = stmt.query("select *,cities.cityname from clinic as c join cities using(cityid) where c.cityid = cityid and isdeleted =0", BeanPropertyRowMapper.newInstance(ClinicBean.class));
        return clinicBean;
    }

    public void deleteClinic(int clinicid) {
        // TODO Auto-generated method stub.
        stmt.update("update clinic set isdeleted = 1 where clinicid = ?", clinicid);

    }

    public void updateClinic(ClinicBean clinicBean) {
        // TODO Auto-generated method stub
        stmt.update("update clinic set clinicname=?,timing=?,address=?,phoneno=?,rating=?,about=?,lat=?,log=?,cityid=?,pincode=? where clinicid=?",
                clinicBean.getClinicname(), clinicBean.getTiming(), clinicBean.getAddress(), clinicBean.getPhoneno(),
                clinicBean.getRating(), clinicBean.getAbout(), clinicBean.getLat(), clinicBean.getLog(), clinicBean.getCityid(),
                clinicBean.getPincode(), clinicBean.getClinicid());
    }


    public List<ClinicBean> searchClinic() {
        // TODO Auto-generated method stub

        return null;
    }

    public ClinicBean getClinicById(int clinicid) {
        // TODO Auto-generated method stub
        ClinicBean bean = null;
        try {
            bean = stmt.queryForObject("select * from clinic where clinicid=?", new Object[]{clinicid},
                    BeanPropertyRowMapper.newInstance(ClinicBean.class));
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return bean;
    }

}
