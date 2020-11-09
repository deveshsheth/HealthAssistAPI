package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bean.ResponseBean;
import com.bean.userPharmacyBean;
import com.dao.userPharmacyDao;

@RestController
public class userPharmacyController {

	@Autowired
	userPharmacyDao userPharmacyDao;
	
	@PostMapping("/adduserPharmacy")
	public ResponseBean<userPharmacyBean> addUserPathology(@RequestBody userPharmacyBean userPharmacyBean){
		userPharmacyDao.addUserPharmacy(userPharmacyBean);
		ResponseBean<userPharmacyBean> response = new ResponseBean<>();
		response.setData(userPharmacyBean);
		response.setMsg("User Pharmacy Added Successfully...!!");
		response.setStatus(200);
		
		return response;
	}
	
}
