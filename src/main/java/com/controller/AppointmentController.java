package com.controller;

import java.util.Date;
import java.util.List;

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
import com.services.MailerService;

@RestController
@CrossOrigin
public class AppointmentController {

    @Autowired
    AppointmentDao appointmentDao;
    
    @Autowired
	MailerService mailerService;

    @PostMapping("/addappointment")
    public ResponseBean<AppointmentBean> addAppointment(@RequestBody AppointmentBean appointmentBean) {
        //appointmentBean.setAppointmentdate(new Date());
        appointmentBean.setAppcreatedate(new Date());
        System.out.println(appointmentBean.getDoctorid());
        appointmentDao.addAppointment(appointmentBean);
        ResponseBean<AppointmentBean> response = new ResponseBean<>();
        response.setData(appointmentBean);
        response.setMsg("Appointment Added successfully...!!");
        response.setStatus(200);
        return response;

    }
    
    @GetMapping("/getappointmentid/{appointmentid}")
    public ResponseBean<AppointmentBean> getUser(@PathVariable("appointmentid") int appointmentid, AppointmentBean bean) {
        ResponseBean<AppointmentBean> responseBean = new ResponseBean<>();
        bean = appointmentDao.getAppointmentById(appointmentid);
        responseBean.setData(bean);
        responseBean.setMsg("Single Appointment Return");
        responseBean.setStatus(200);

        return responseBean;
    }
    
    @GetMapping("/getPatientDetails/{appointmentid}")
    public ResponseBean<AppointmentBean> getPatientDetails(@PathVariable("appointmentid") int appointmentid, AppointmentBean bean) {
        ResponseBean<AppointmentBean> responseBean = new ResponseBean<>();
        bean = appointmentDao.getPatientDetailsById(appointmentid);
        responseBean.setData(bean);
        responseBean.setMsg("Single Patient Details Return");
        responseBean.setStatus(200);

        return responseBean;
    }

    @GetMapping("/listappointment/{userid}")
    public ResponseBean<java.util.List<AppointmentBean>> listAppointment(@PathVariable("userid") int userid) {
        ResponseBean<java.util.List<AppointmentBean>> response = new ResponseBean<>();
        System.out.println(userid);
        java.util.List<AppointmentBean> appointmentBean = appointmentDao.listAppointment(userid);
        response.setData(appointmentBean);
        response.setMsg("Appointment List Display..!!!!");
        response.setStatus(201);
        return response;
    }
    
    @GetMapping("/viewPatientAppointment/{userid}")
    public ResponseBean<java.util.List<AppointmentBean>> viewPatientAppointment(@PathVariable("userid") int userid) {
        ResponseBean<java.util.List<AppointmentBean>> response = new ResponseBean<>();
        
        java.util.List<AppointmentBean> appointmentBean = appointmentDao.viewPatientAppointment(userid);
        response.setData(appointmentBean);
        response.setMsg("Appointment List Display..!!!!");
        response.setStatus(201);
        return response;
    }
    
    @GetMapping("/listAppointmentForDoctor/{userid}")
    public ResponseBean<java.util.List<AppointmentBean>> listAppointmentForDoctor(@PathVariable("userid") int userid) {
        ResponseBean<java.util.List<AppointmentBean>> response = new ResponseBean<>();
        System.out.println("Appointment ID "+userid);
        java.util.List<AppointmentBean> appointmentBean = appointmentDao.listAppointmentForDoctor(userid);
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
    
    @PutMapping("/updateRescheduleAppointment")
    public ResponseBean<AppointmentBean> updateRescheduleAppointment(@RequestBody AppointmentBean appointmentBean) {
        appointmentDao.updateRescheduleAppointment(appointmentBean);
        ResponseBean<AppointmentBean> response = new ResponseBean<>();
        response.setData(appointmentBean);
        response.setMsg("Appointment Reschedule Successfully..!!");
        return response;
    }
    
    
    @GetMapping("/rescheduleReason/{email}/{appointmentid}")
	public ResponseBean<AppointmentBean> sendOtpForResetPassword(@PathVariable("email") String email,@PathVariable("appointmentid") int appointmentid) {
		System.out.println("Reschedule Reason call...");
		
		AppointmentBean bean = appointmentDao.getRescheduleReasonByEmail(email,appointmentid);
		
		ResponseBean<AppointmentBean> responseBean = new ResponseBean<>();
		
		responseBean.setData(bean);

		if (bean == null) {

			responseBean.setMsg("Invalid Email Address");
			responseBean.setStatus(201);

		} else {

			
			mailerService.sendRescheduleReason(bean);

			responseBean.setMsg("Email sent for Reschedule");
			responseBean.setStatus(200);

		}

		return responseBean;
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
    
    @PutMapping("/done_appointment")
    public ResponseBean<AppointmentBean> done_Appointment(@RequestBody AppointmentBean appointmentBean) {
        appointmentDao.done_Appointment(appointmentBean);
        ResponseBean<AppointmentBean> response = new ResponseBean<>();
        response.setData(appointmentBean);
        response.setMsg("Details Submited Successfully..!!");
        return response;
    }
    
    @GetMapping("/pastAppointmentList/{patientid}")
	public ResponseBean<List<AppointmentBean>> pastAppointmentList(@PathVariable("patientid") int patientid){
		List<AppointmentBean> Bean = appointmentDao.pastAppointmentList(patientid);
		
		ResponseBean<List<AppointmentBean>> responseBean = new ResponseBean<>();
	
		responseBean.setData(Bean);	
		responseBean.setMsg("User Diet List!!");
		responseBean.setStatus(200);
		
		return responseBean;
	}
    
    @GetMapping("/todayAppointment/{userid}")
    public ResponseBean<java.util.List<AppointmentBean>> todayAppointment(@PathVariable("userid") int userid) {
        ResponseBean<java.util.List<AppointmentBean>> response = new ResponseBean<>();
        System.out.println(userid);
        java.util.List<AppointmentBean> appointmentBean = appointmentDao.todayAppointment(userid);
        response.setData(appointmentBean);
        response.setMsg(" Today Appointment Display..!!!!");
        response.setStatus(201);
        return response;
    }
    
    @GetMapping("/waitForAcceptAppointment/{userid}")
    public ResponseBean<java.util.List<AppointmentBean>> waitForAcceptAppointment(@PathVariable("userid") int userid) {
        ResponseBean<java.util.List<AppointmentBean>> response = new ResponseBean<>();
        System.out.println(userid);
        java.util.List<AppointmentBean> appointmentBean = appointmentDao.waitForAcceptAppointment(userid);
        response.setData(appointmentBean);
        response.setMsg(" Wait For Accept Appointment Display..!!!!");
        response.setStatus(201);
        return response;
    }
    
    @GetMapping("/acceptAppointment/{userid}")
    public ResponseBean<java.util.List<AppointmentBean>> acceptAppointment(@PathVariable("userid") int userid) {
        ResponseBean<java.util.List<AppointmentBean>> response = new ResponseBean<>();
        System.out.println(userid);
        java.util.List<AppointmentBean> appointmentBean = appointmentDao.acceptAppointment(userid);
        response.setData(appointmentBean);
        response.setMsg("Accept Appointment Display..!!!!");
        response.setStatus(201);
        return response;
    }
    
    @GetMapping("/rescheduleAppointment/{userid}")
    public ResponseBean<java.util.List<AppointmentBean>> rescheduleAppointment(@PathVariable("userid") int userid) {
        ResponseBean<java.util.List<AppointmentBean>> response = new ResponseBean<>();
        System.out.println(userid);
        java.util.List<AppointmentBean> appointmentBean = appointmentDao.rescheduleAppointment(userid);
        response.setData(appointmentBean);
        response.setMsg("Reschedule Appointment Display..!!!!");
        response.setStatus(201);
        return response;
    }
    
    @GetMapping("/doneAppointment/{userid}")
    public ResponseBean<java.util.List<AppointmentBean>> doneAppointment(@PathVariable("userid") int userid) {
        ResponseBean<java.util.List<AppointmentBean>> response = new ResponseBean<>();
        System.out.println(userid);
        java.util.List<AppointmentBean> appointmentBean = appointmentDao.doneAppointment(userid);
        response.setData(appointmentBean);
        response.setMsg("Done Appointment Display..!!!!");
        response.setStatus(201);
        return response;
    }
    
   


}
