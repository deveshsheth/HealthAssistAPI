package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bean.ResponseBean;
import com.bean.UserPatientBean;
import com.dao.UserPatientDao;

@RestController
@CrossOrigin
public class UserPatientController {
	
	@Autowired
	UserPatientDao userpatientDao;
	
	@PostMapping("/addUserPatient")
	public ResponseBean<UserPatientBean> addPatient(@RequestBody UserPatientBean userpatientBean){
		userpatientDao.addUserPatient(userpatientBean);
		ResponseBean<UserPatientBean> response = new ResponseBean<>();
		response.setData(userpatientBean);
		response.setMsg("User Patient Added successfully...!!");
		response.setStatus(200);
		return response;
		
	}
	
	@PutMapping("/updateUserPatient")
	public ResponseBean<UserPatientBean> updateUserPatient(@RequestBody UserPatientBean userpatientBean){
		userpatientDao.updateUserPatient(userpatientBean);
		ResponseBean<UserPatientBean> response = new ResponseBean<>();
		response.setData(userpatientBean);
		response.setMsg("User Patient Update successfully...!!");
		return response;
	}

	
}
