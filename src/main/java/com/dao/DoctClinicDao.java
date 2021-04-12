package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bean.ClinicBean;
import com.bean.DoctClinicBean;

@Repository
public class DoctClinicDao {

    @Autowired
    JdbcTemplate stmt;

    public void addDoctClinic(DoctClinicBean doctclinicBean) {
        // TODO Auto-generated method stub

        stmt.update("insert into doct_clinic(docid,clinicid,monday,tuesday,wednesday,thrusday,friday,saturday,sunday,threshold) values (?,?,?,?,?,?,?,?,?,?)",
                doctclinicBean.getDocid(), doctclinicBean.getClinicid(), doctclinicBean.getMonday(), doctclinicBean.getTuesday(), doctclinicBean.getWednesday(),
                doctclinicBean.getThrusday(), doctclinicBean.getFriday(), doctclinicBean.getSaturday(), doctclinicBean.getSunday(), doctclinicBean.getThreshold());
    }

    public void updateDoctClinic(DoctClinicBean doctclinicBean) {
        // TODO Auto-generated method stub

        stmt.update("update doct_clinic set docid=?,clinicid=?,monday=?,tuesday=?,wednesday=?,thrusday=?,friday=?,saturday=?,sunday=?,threshold=? where doctclinicid=?",
                doctclinicBean.getDocid(), doctclinicBean.getClinicid(), doctclinicBean.getMonday(), doctclinicBean.getTuesday(), doctclinicBean.getWednesday(),
                doctclinicBean.getThrusday(), doctclinicBean.getFriday(), doctclinicBean.getSaturday(), doctclinicBean.getSunday(), doctclinicBean.getThreshold(),
                doctclinicBean.getDoctclinicid());
    }

    public List<DoctClinicBean> listDoctCLinic(int userid) {
        // TODO Auto-generated method stub
        java.util.List<DoctClinicBean> doctClinicBean = stmt.query("select dc.*,dp.userid,cli.clinicname,u.firstname from doct_clinic as dc,clinic cli,doctorprofile as dp,users as u where dp.userid = dc.docid and cli.clinicid = dc.clinicid and u.userid = dp.userid and dc.isdeleted =0  and u.userid= ?", new Object[]{userid}, BeanPropertyRowMapper.newInstance(DoctClinicBean.class));
        return doctClinicBean;
    }

    public void deleteDoctClinic(int doctclinicid) {
        // TODO Auto-generated method stub
        stmt.update("update doct_clinic set isdeleted = 1 where doctclinicid = ?", doctclinicid);

    }

    public DoctClinicBean getDoctClinicById(int doctclinicid) {
        // TODO Auto-generated method stub
        DoctClinicBean bean = null;
        try {
            bean = stmt.queryForObject("select * from doct_clinic where doctclinicid=?", new Object[]{doctclinicid},
                    BeanPropertyRowMapper.newInstance(DoctClinicBean.class));
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return bean;
    }

	public List<DoctClinicBean> listDoctClinicTiming(int clinicid) {
		// TODO Auto-generated method stub
		java.util.List<DoctClinicBean> doctClinicBean = stmt.query("select dc.* from doct_clinic as dc where dc.clinicid = ?", new Object[]{clinicid}, BeanPropertyRowMapper.newInstance(DoctClinicBean.class));
        return doctClinicBean;
	}
}
