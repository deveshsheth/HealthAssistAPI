package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bean.AppointmentStatusBean;
import com.bean.ResponseBean;
import com.dao.AppointmentStatusDao;

@RestController
@CrossOrigin
public class AppointmentStatusController {
	
	@Autowired
	AppointmentStatusDao appointmentstatusDao;
	
	@PostMapping("/addappstatus")
	public ResponseBean<AppointmentStatusBean> addCities(@RequestBody AppointmentStatusBean appstatusBean){
		appointmentstatusDao.addAppStatus(appstatusBean);
		ResponseBean<AppointmentStatusBean> response = new ResponseBean<>();
		response.setData(appstatusBean);
		response.setMsg("Appointment Status Inserted successfully...!!");
		response.setStatus(200);
		return response;
		
	}
	
	@PutMapping("/updateappstatus")
	public ResponseBean<AppointmentStatusBean> updateAppointmentStatus(@RequestBody AppointmentStatusBean appstatusBean){
		appointmentstatusDao.updateAppStatus(appstatusBean);
		ResponseBean<AppointmentStatusBean> response = new ResponseBean<>();
		response.setData(appstatusBean);
		response.setMsg("Appointment Status Updated Successfully..!!");
		return response;
	}

}
