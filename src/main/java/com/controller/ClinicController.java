package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bean.ClinicBean;
import com.bean.ResponseBean;
//import com.bean.SearchClinicBean;
import com.dao.ClinicDao;

@RestController
@CrossOrigin
public class ClinicController {

	@Autowired
	ClinicDao clinicDao;
	
	@PostMapping("/addClinic")
	public ResponseBean<ClinicBean> addclinic(@RequestBody ClinicBean clinicBean){
		clinicDao.addClinic(clinicBean);
		ResponseBean<ClinicBean> response = new ResponseBean<>();
		response.setData(clinicBean);
		response.setMsg("Clinic Added successfully...!!");
		response.setStatus(200);
		return response;
		
	}
	
	@GetMapping("/listClinic")
	public ResponseBean<java.util.List<ClinicBean>> listClinic()
	{
		ResponseBean<java.util.List<ClinicBean>> response = new ResponseBean<>();

		java.util.List<ClinicBean> clinicBean = clinicDao.listClinic();
		response.setData(clinicBean);
		response.setMsg("Clinic List Display..!!!!");
		response.setStatus(201);
		return response;
	}
	
	@DeleteMapping("/addClinic/{ClinicId}")
	public ResponseBean<ClinicBean> deleteClinic(@PathVariable("ClinicId")int ClinicId){
		
		ResponseBean<ClinicBean> response = new ResponseBean<>();
		clinicDao.deleteClinic(ClinicId);
		response.setMsg("Clinic Deleted Successfully..!!");
		response.setStatus(200);
		return response;
	}
	
	@PutMapping("/updateClinic")
	public ResponseBean<ClinicBean> updateClinic(@RequestBody ClinicBean clinicBean){
		clinicDao.updateClinic(clinicBean);
		ResponseBean<ClinicBean> response = new ResponseBean<>();
		response.setData(clinicBean);
		response.setMsg("Clinic Updated Successfully..!!");
		return response;
	}
	
//	@GetMapping("/searchClinic")
//	public ResponseBean<java.util.List<ClinicBean>> searchClinic(@RequestBody String searchclinic)
//	{
//		ResponseBean<java.util.List<ClinicBean>> response = new ResponseBean<>();
//		
//		java.util.List<ClinicBean> clinicBean = clinicDao.searchClinic(searchclinic);
//		response.setData(clinicBean);
//		response.setMsg("Search Clinic List Display..!!!!");
//		response.setStatus(201);
//		return response;
//	}
	
	
	
}
