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
        stmt.update("insert into appointment(patientid,doctorid,statusid,appcreatedate,comment,clinicid,reference,complain,appointmentdate,appointmenttime) values (?,?,?,?,?,?,?,?,?,?)", appointmentBean.getPatientid(),
                appointmentBean.getDoctorid(), appointmentBean.getStatusid(), appointmentBean.getAppcreatedate(), appointmentBean.getComment(),
                appointmentBean.getClinicid(), appointmentBean.getReference(), appointmentBean.getComplain(), appointmentBean.getAppointmentdate(), appointmentBean.getAppointmenttime());
    }

    public List<AppointmentBean> listAppointment() {
        // TODO Auto-generated method stub
        java.util.List<AppointmentBean> appointmentBean = stmt.query("select * from appointment", BeanPropertyRowMapper.newInstance(AppointmentBean.class));
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

}
