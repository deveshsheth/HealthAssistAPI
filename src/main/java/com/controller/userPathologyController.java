package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bean.ResponseBean;
import com.bean.userPathologyBean;
import com.dao.userPathologyDao;

@RestController
@CrossOrigin
public class userPathologyController {

	@Autowired
	userPathologyDao userPathologyDao;
	
	@PostMapping("/adduserPathology")
	public ResponseBean<userPathologyBean> addUserPathology(@RequestBody userPathologyBean userPathologyBean){
		userPathologyDao.addUserPathology(userPathologyBean);
		ResponseBean<userPathologyBean> response = new ResponseBean<>();
		response.setData(userPathologyBean);
		response.setMsg("User Pathology Added Successfully...!!");
		response.setStatus(200);
		return response;
	}
	
	@PutMapping("/updateuserPathology")
	public ResponseBean<userPathologyBean> updateUserPathology(@RequestBody userPathologyBean userPathologyBean){
		userPathologyDao.updateUserPathology(userPathologyBean);
		ResponseBean<userPathologyBean> response = new ResponseBean<>();
		response.setData(userPathologyBean);
		response.setMsg("User Pathology Updated Successfully..!!");
		return response;
	}
}
