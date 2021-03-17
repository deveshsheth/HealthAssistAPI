package com.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bean.AppointmentBean;
import com.bean.ResponseBean;
import com.dao.AppointmentDao;

@RestController
@CrossOrigin
public class AppointmentController {

    @Autowired
    AppointmentDao appointmentDao;

    @PostMapping("/addappointment")
    public ResponseBean<AppointmentBean> addclinic(@RequestBody AppointmentBean appointmentBean) {
        //appointmentBean.setAppointmentdate(new Date());
        appointmentBean.setAppcreatedate(new Date());
        System.out.println(appointmentBean.getDoctorid());
        System.out.println(appointmentBean.getAppcreatedate());
        System.out.println(appointmentBean.getAppointmentdate());
        appointmentDao.addAppointment(appointmentBean);
        ResponseBean<AppointmentBean> response = new ResponseBean<>();
        response.setData(appointmentBean);
        response.setMsg("Appointment Added successfully...!!");
        response.setStatus(200);
        return response;

    }

    @GetMapping("/listappointment")
    public ResponseBean<java.util.List<AppointmentBean>> listClinic() {
        ResponseBean<java.util.List<AppointmentBean>> response = new ResponseBean<>();

        java.util.List<AppointmentBean> appointmentBean = appointmentDao.listAppointment();
        response.setData(appointmentBean);
        response.setMsg("Appointment List Display..!!!!");
        response.setStatus(201);
        return response;
    }

    @DeleteMapping("/addappointment/{AppointmentId}")
    public ResponseBean<AppointmentBean> deleteClinic(@PathVariable("AppointmentId") int AppointmentId) {

        ResponseBean<AppointmentBean> response = new ResponseBean<>();
        appointmentDao.deleteAppointment(AppointmentId);
        response.setMsg("Appointment Deleted Successfully..!!");
        response.setStatus(200);
        return response;
    }

    @PutMapping("/updateappointment")
    public ResponseBean<AppointmentBean> updateAppointment(@RequestBody AppointmentBean appointmentBean) {
        appointmentDao.updateAppointment(appointmentBean);
        ResponseBean<AppointmentBean> response = new ResponseBean<>();
        response.setData(appointmentBean);
        response.setMsg("Appointment Updated Successfully..!!");
        return response;
    }


    @PutMapping("/accept_reject_appointment")
    public ResponseBean<AppointmentBean> Accept_Reject_Appointment(@RequestBody AppointmentBean appointmentBean) {
        appointmentDao.accept_reject_Appointment(appointmentBean);
        ResponseBean<AppointmentBean> response = new ResponseBean<>();
        response.setData(appointmentBean);
        response.setMsg("Appointment Status Updated Successfully..!!");
        return response;
    }

    @PutMapping("/reschedule_appointment")
    public ResponseBean<AppointmentBean> Reschedule_Appointment(@RequestBody AppointmentBean appointmentBean) {
        appointmentDao.reschedule_Appointment(appointmentBean);
        ResponseBean<AppointmentBean> response = new ResponseBean<>();
        response.setData(appointmentBean);
        response.setMsg("Appointment Status Updated Successfully..!!");
        return response;
    }


}
