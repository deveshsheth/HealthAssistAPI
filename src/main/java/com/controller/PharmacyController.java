package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bean.PharmacyBean;
import com.bean.ResponseBean;
import com.dao.PharmacyDao;

@RestController
public class PharmacyController {

	@Autowired
	PharmacyDao pharmacyDao;
	
	@PostMapping("/addPharmacy")
	public ResponseBean<PharmacyBean> addPharmacy(@RequestBody PharmacyBean pharmacyBean){
		pharmacyDao.addPathology(pharmacyBean);
		ResponseBean<PharmacyBean> response = new ResponseBean<>();
		response.setData(pharmacyBean);
		response.setMsg("Pharmacy Added successfully...!!");
		response.setStatus(200);
		return response;
		
	}
	
	@GetMapping("/listPharmacy")
	public ResponseBean<java.util.List<PharmacyBean>> listPharmacy()
	{
		ResponseBean<java.util.List<PharmacyBean>> response = new ResponseBean<>();

		java.util.List<PharmacyBean> pharmacyBean = pharmacyDao.listPharmacy();
		response.setData(pharmacyBean);
		response.setMsg("Pharmacy List Display..!!!!");
		response.setStatus(201);
		return response;
	}
	
	@DeleteMapping("/addPharmacy/{PharmacyId}")
	public ResponseBean<PharmacyBean> deletePharmacy(@PathVariable("PharmacyId")int PharmacyId){
		
		ResponseBean<PharmacyBean> response = new ResponseBean<>();
		pharmacyDao.deletePharmacy(PharmacyId);
		response.setMsg("Pharmacy Deleted Successfully..!!");
		response.setStatus(200);
		return response;
	}
	
	@PutMapping("/updatePharmacy")
	public ResponseBean<PharmacyBean> updatePharmacy(@RequestBody PharmacyBean pharmacyBean){
		pharmacyDao.updatePharmacy(pharmacyBean);
		ResponseBean<PharmacyBean> response = new ResponseBean<>();
		response.setData(pharmacyBean);
		response.setMsg("Pharmacy Updated Successfully..!!");
		return response;
	}
	
	
}
