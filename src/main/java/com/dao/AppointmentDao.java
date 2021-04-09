package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RestController;

import com.bean.AppointmentBean;


@RestController
public class AppointmentDao {

    @Autowired
    JdbcTemplate stmt;

    public void addAppointment(AppointmentBean appointmentBean) {
        // TODO Auto-generated method stub
    	appointmentBean.setStatusid(4);
        stmt.update("insert into appointment(patientid,doctorid,statusid,appcreatedate,comment,clinicid,reference,complain,appointmentdate,appointmenttime) values (?,?,?,?,?,?,?,?,?,?)", 
        		appointmentBean.getPatientid(),
        		appointmentBean.getDoctorid(),appointmentBean.getStatusid() ,appointmentBean.getAppcreatedate(), appointmentBean.getComment(),
                appointmentBean.getClinicid(), appointmentBean.getReference(), appointmentBean.getComplain(), appointmentBean.getAppointmentdate(), appointmentBean.getAppointmenttime());
    }

    public List<AppointmentBean> listAppointment(int userid) {
        // TODO Auto-generated method stub
        java.util.List<AppointmentBean> appointmentBean = stmt.query("select p.*,a.*,s.*,u.*,dp.*,cli.* from patientprofile as p,clinic as cli,doctorprofile as dp,users as u,appointment as a,appointmentstatus as s where a.patientid = p.patientid and a.clinicid = cli.clinicid and a.statusid = s.statusid and u.userid = a.doctorid and a.doctorid = dp.userid  and u.userid = ?"
        		+ "", new Object[] {userid} ,BeanPropertyRowMapper.newInstance(AppointmentBean.class));
        return appointmentBean;
    }

    public void deleteAppointment(int appointmentId) {
        // TODO Auto-generated method stub
        stmt.update("delete from appointment where appointmentid = ?", appointmentId);
    }

    public void updateAppointment(AppointmentBean appointmentBean) {
        // TODO Auto-generated method stub
        stmt.update("update appointment set ,patientid=?,doctorid=?,statusid=?,appcreatedate=?,comment=?,clinicid=?,reference=?,appointmentdate=?,appointmenttime=?,complain=? where appointmentid=?",
                appointmentBean.getDoctorid(), appointmentBean.getStatusid(), appointmentBean.getAppcreatedate(), appointmentBean.getComment(),
                appointmentBean.getClinicid(), appointmentBean.getReference(), appointmentBean.getComplain(), appointmentBean.getAppointmentdate(), appointmentBean.getAppointmenttime()
        );

    }

    public void accept_reject_Appointment(AppointmentBean appointmentBean) {
        // TODO Auto-generated method stub
        stmt.update("update appointment set statusid=? where appointmentid=?", appointmentBean.getStatusid(), appointmentBean.getAppointmentid());
    }

    public void reschedule_Appointment(AppointmentBean appointmentBean) {
        // TODO Auto-generated method stub
        stmt.update("update appointment set statusid=?,appointmentdate=?,appointmenttime=? where appointmentid=?", appointmentBean.getStatusid(), appointmentBean.getAppointmentdate(), appointmentBean.getAppointmenttime(), appointmentBean.getAppointmentid());
    }

	public List<AppointmentBean> listAppointmentForDoctor(int userid) {
		// TODO Auto-generated method stub
		java.util.List<AppointmentBean> appointmentBean = stmt.query("select p.*,a.*,s.*,dp.*,cli.* from patientprofile as p,clinic as cli,users as u,doctorprofile as dp,appointment as a,appointmentstatus as s where a.patientid = p.patientid and u.userid = dp.userid and a.clinicid = cli.clinicid and a.statusid = s.statusid and dp.userid = ?"
        		, new Object[] {userid} ,BeanPropertyRowMapper.newInstance(AppointmentBean.class));
        return appointmentBean;
	}

	public AppointmentBean getAppointmentById(int appointmentid) {
		// TODO Auto-generated method stub
		AppointmentBean bean = null;
        try {
            bean = stmt.queryForObject("select ap.*,pp.*,cli.* from appointment as ap,clinic as cli,patientprofile as pp where  ap.patientid=pp.patientid and ap.clinicid = cli.clinicid and  ap.appointmentid=?", new Object[]{appointmentid},
                    BeanPropertyRowMapper.newInstance(AppointmentBean.class));
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
		return bean;
	}

	public List<AppointmentBean> viewPatientAppointment(int userid) {
		// TODO Auto-generated method stub
		java.util.List<AppointmentBean> appointmentBean = stmt.query("select p.*,a.*,s.*,u.*,cli.* from patientprofile as p,clinic as cli,users as u,appointment as a,appointmentstatus as s where a.patientid = p.patientid and a.clinicid = cli.clinicid and a.statusid = s.statusid and  u.userid = ?"
        		, new Object[] {userid} ,BeanPropertyRowMapper.newInstance(AppointmentBean.class));
		return appointmentBean;
	}

}
